package com.testing.api.requests;

import com.google.gson.Gson;
import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class to define the main functionalities of the requests for the endpoint
 * of client
 */
public class ClientRequest extends BaseRequest {
    /** String to define the endpoint path */
    private String endpoint;

    /**
     * Retrieve the clients from the endpoint
     * @return : Response with the clients
     */
    public Response getClients() {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    /**
     * Create a new client through the POST client endpoint
     * @param client : Instance of client class
     * @return : Response with the results
     */
    public Response createClient(Client client) {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestPost(endpoint, createBaseHeaders(), client);
    }


    /**
     * Function to get an Instance of Client class from a response
     * @param response : Response with the client in it
     * @return : New Client
     */
    public Client getClientEntity(@NotNull Response response){
            return response.as(Client.class);
    }

    /**
     * Function to get list of clients
     * @param response : Response with clients in it
     * @return : A list of Clients
     */
    public List<Client> getClientsEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Client.class);
    }

    /**
     * Function to create a default client if there is not any client in the system
     * @return : Response of the request
     */
    public Response createDefaultClient() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createClient(jsonFile.getClientByJson(Constants.DEFAULT_CLIENT_FILE_PATH));
    }

}
