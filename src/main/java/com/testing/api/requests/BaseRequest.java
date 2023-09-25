package com.testing.api.requests;

import com.testing.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * BaseRequest class
 * Use to define REST request
 */
public class BaseRequest {
    /**
     * This is a funtion to retrieve information using rest assured
     * @param endpoint api url
     * @param headers a map of headers
     * @return Response
     */
    protected Response requestGet(String endpoint, Map<String, ?> headers) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .when()
                          .get(endpoint);
    }

    /**
     * This is a funtion to create a new element using rest assured
     * @param endpoint api url
     * @param headers a map of headers
     * @param body model object
     * @return Response
     */
    protected Response requestPost(String endpoint, Map<String, ?> headers, Object body) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .body(body)
                          .when()
                          .post(endpoint);
    }


    /**
     * This is a funtion to update information using rest assured
     * @param endpoint api url
     * @param headers a map of headers
     * @param body body of the request
     * @return Response
     */
    protected Response requestPut(String endpoint, Map<String, ?> headers, Object body) {
        return RestAssured.given()
                          .contentType(Constants.VALUE_CONTENT_TYPE)
                          .headers(headers)
                          .body(body)
                          .when()
                          .put(endpoint);
    }


    /**
     * Function to define de base headers of the query
     * @return Map<String, String> Headers
     */
    protected Map<String, String> createBaseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.CONTENT_TYPE, Constants.VALUE_CONTENT_TYPE);
        return headers;
    }


    /**
     *
     * @param response : The response of a request
     * @param schemaPath : Path from schema resources to a json schema file
     * @return boolean : true if schema is legit, otherwise is false
     */
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
