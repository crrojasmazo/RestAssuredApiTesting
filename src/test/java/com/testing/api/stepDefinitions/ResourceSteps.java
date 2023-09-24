package com.testing.api.stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class ResourceSteps extends BaseStepDefinition {

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getRescources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());
        List<Resource> resources = resourceRequest.getResourcesEntity(response);
        if(resources.isEmpty()) {
            response = resourceRequest.createDefaultResource();
            logger.info(response.statusCode());
            Assert.assertEquals(201, response.statusCode());
        }
    }

    @When("I send a GET request to view all the resources")
    public void iSendAGETRequestToViewAllTheResources() {
        response = resourceRequest.getRescources();
    }

    @And("validates the response with the resource list JSON schema")
    public void validatesTheResponseWithTheResourceListJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response, "schemas/resourceListSchema.json"));
    }

    @And("I retrieve the details of the latest resource")
    public void iRetrieveTheDetailsOfTheLatestResource() {
        List<Resource> resources = resourceRequest.getResourcesEntity(response);
        lastResource = resources.get(resources.size()-1);
        logger.info(lastResource);
    }

    @When("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheLatestResource(String requestBody) throws JsonProcessingException {
        logger.info(requestBody);

        String jsonString = "{\n" +
                "    \"name\": \"Thug Pants\",\n" +
                "    \"trademark\": \"Colorado jeans\",\n" +
                "    \"stock\": 1000,\n" +
                "    \"price\": 99.99,\n" +
                "    \"description\": \"Blue short jeans\",\n" +
                "    \"tags\": \"thug\",\n" +
                "    \"is_active\": true\n" +
                "}";

        Resource requestResourceJSONBody = objectMapper.readValue(jsonString, Resource.class);
        response = resourceRequest.updateResource(requestResourceJSONBody,lastResource.getId());

    }

    @And("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable resourceDataTable) {
        // get the resource
        logger.info(resourceDataTable);
        resourceDataMap = resourceDataTable.asMaps().get(0);
        resource = Resource.builder().name(resourceDataMap.get("name"))
                .trademark(resourceDataMap.get("trademark"))
                .stock(Integer.parseInt(resourceDataMap.get("stock")))
                .price(Double.valueOf(resourceDataMap.get("price")))
                .description(resourceDataMap.get("description"))
                .tags(resourceDataMap.get("tags"))
                .is_active(Boolean.valueOf(resourceDataMap.get("is_active")))
                .build();

        Resource responseResource = resourceRequest.getResourceEntity(response);

        //assert they are the same
        Assert.assertEquals(responseResource.getName(), resource.getName());
        Assert.assertEquals(responseResource.getTrademark(), resource.getTrademark());
        Assert.assertEquals(responseResource.getStock(), resource.getStock());
        Assert.assertEquals(responseResource.getPrice(), resource.getPrice());
        Assert.assertEquals(responseResource.getDescription(), resource.getDescription());
        Assert.assertEquals(responseResource.getTags(), resource.getTags());
        Assert.assertEquals(responseResource.getIs_active(), resource.getIs_active());
        logger.info("All legit");
    }

    @And("validates the response with the resource JSON schema")
    public void validatesTheResponseWithTheResourceJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response, "schemas/resourceSchema.json"));
    }
}
