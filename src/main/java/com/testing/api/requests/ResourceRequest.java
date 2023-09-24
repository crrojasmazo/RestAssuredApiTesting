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

public class ResourceRequest extends BaseRequest {
    private String endpoint;

    public Response getRescources() {
        endpoint = String.format(Constants.URL, Constants.RESOURCE_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response getResource(String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCE_PATH, resourceId);
        return requestGet(endpoint, createBaseHeaders());
    }

    public Response updateResource(Client client, String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCE_PATH, resourceId);
        return requestPut(endpoint, createBaseHeaders(), client);
    }

    public Response deleteClient(String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestDelete(endpoint, createBaseHeaders());
    }

    public Client getClientEntity(@NotNull Response response) {
        return response.as(Client.class);
    }

    public List<Resource> getResourcesEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Resource.class);
    }

    public Response createResource(Resource resource) {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestPost(endpoint, createBaseHeaders(), resource);
    }

    public Response createDefaultResource() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createResource(jsonFile.getResourceByJson(Constants.DEFAULT_RESOURCE_FILE_PATH));
    }

    public Client getClientEntity(String clientJson) {
        Gson gson = new Gson();
        return gson.fromJson(clientJson, Client.class);
    }

    public boolean validateSchema(Response response, String schemaPath) {
        try {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true; // Return true if the assertion passes
        } catch (AssertionError e) {
            // Assertion failed, return false
            return false;
        }
    }
}
