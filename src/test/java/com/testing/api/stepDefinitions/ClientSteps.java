package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

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
        logger.info(response.jsonPath().prettify());
        Assert.assertTrue(clientRequest.validateSchema(response, "schemas/clientListSchema.json"));
    }

    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientDataTable) {
        logger.info(clientDataTable);
        clientDataMap = clientDataTable.asMaps().get(0);

        client = Client.builder().name(clientDataMap.get("Name"))
                                 .lastName(clientDataMap.get("LastName"))
                                 .country(clientDataMap.get("Country"))
                                 .city(clientDataMap.get("City"))
                                 .email(clientDataMap.get("Email"))
                                 .phone(clientDataMap.get("Phone"))
                                 .build();
    }

    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        logger.info("Client Created");
        response = clientRequest.createClient(client);
    }

    @And("the response should have a status code {int}")
    public void theResponseShouldHaveAStatusCode(int statusCode) {
        logger.info("Verify status code equals to :"+statusCode);
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @And("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        // response to client
        Client responseClient = clientRequest.getClientEntity(response);

        // Compare attributes between client response and the client sent
        Assert.assertEquals(responseClient.getName(), client.getName());
        Assert.assertEquals(responseClient.getCity(), client.getCity());
        Assert.assertEquals(responseClient.getCountry(), client.getCountry());
        Assert.assertEquals(responseClient.getEmail(), client.getEmail());
        Assert.assertEquals(responseClient.getPhone(), client.getPhone());

    }

    @And("validates the response with the client JSON schema")
    public void validatesTheResponseWithTheClientJSONSchema() {
        logger.info("Verifying schema");
        Assert.assertTrue(clientRequest.validateSchema(response, "schemas/clientSchema.json"));
    }
}
