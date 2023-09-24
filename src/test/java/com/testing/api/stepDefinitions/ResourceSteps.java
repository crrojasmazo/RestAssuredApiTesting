package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.models.Resource;
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

}
