package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class ClientSteps extends BaseStepDefinition {
    @Given("there are registered clients in the system")
    public void thereAreRegisteredClientsInTheSystem() {
        response = clientRequest.getClients();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());
        List<Client> clients = clientRequest.getClientsEntity(response);
        if(clients.isEmpty()) {
            response = clientRequest.createDefaultClient();
            logger.info(response.statusCode());
            Assert.assertEquals(201, response.statusCode());
        }
    }

    @When("I send a GET request to view all the clients")
    public void iSendAGETRequestToViewAllTheClients() {
        response = clientRequest.getClients();
    }



    @And("validates the response with the client list JSON schema")
    public void validatesTheResponseWithTheClientListJSONSchema() {
        Assert.assertTrue(clientRequest.validateSchema(response, "schemas/clientListSchema.json"));
    }

}
