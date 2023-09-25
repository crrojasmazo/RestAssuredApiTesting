package com.testing.api.requests;

import com.google.gson.Gson;

import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * class to define resource requests functionalities
 */
public class ResourceRequest extends BaseRequest {
    private String endpoint;

    /**
     * Function to retrieve resources from endpoint
     * @return : Response with resources in it
     */
    public Response getResources() {
        endpoint = String.format(Constants.URL, Constants.RESOURCE_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    /**
     * Class to update a resource
     * @param resource : Resource object
     * @param resourceId : Id of the object to update
     * @return : Response 200 if update was successful
     */
    public Response updateResource(Resource resource, String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCE_PATH, resourceId);
        return requestPut(endpoint, createBaseHeaders(), resource);
    }

    /**
     * Function to convert a response in a Resource object
     * @param response : Response of a getResource
     * @return : New Resource
     */
    public Resource getResourceEntity(@NotNull Response response) {
        return response.as(Resource.class);
    }

    /**
     * Function to get a list of resources
     * @param response : Response of a getResources
     * @return : A lis of new Resources
     */
    public List<Resource> getResourcesEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Resource.class);
    }

    /**
     * Function to create a new Resource
     * @param resource : Resource object
     * @return : Response with status 200 if creation was successful
     */
    public Response createResource(Resource resource) {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestPost(endpoint, createBaseHeaders(), resource);
    }

    /**
     * Function to create a default resource from a jsonfile if there is not any resource in the system
     * @return Response
     */
    public Response createDefaultResource() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createResource(jsonFile.getResourceByJson(Constants.DEFAULT_RESOURCE_FILE_PATH));
    }

    /**
     * Function to convert a json in a string to a Resource
     * @param resourceJson : String with a json structure
     * @return : New Resource
     */
    public Resource getResourceEntityString(String resourceJson) {
        Gson gson = new Gson();
        return gson.fromJson(resourceJson, Resource.class);
    }



}
