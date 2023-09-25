package com.testing.api.stepDefinitions;

import com.testing.api.models.Resource;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

/**
 * Class to define the steps of the resource feature
 */
public class ResourceSteps extends BaseStepDefinition {

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getResources();
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
        response = resourceRequest.getResources();
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
    public void iSendAPUTRequestToUpdateTheLatestResource(String requestBody) {
        logger.info(requestBody);
        response = resourceRequest.updateResource(resourceRequest.getResourceEntityString(requestBody),lastResource.getId());
        logger.info(response);
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
