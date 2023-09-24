package com.testing.api.stepDefinitions;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.requests.ClientRequest;
import com.testing.api.requests.ResourceRequest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class BaseStepDefinition {
    protected static final Logger logger = LogManager.getLogger(Hooks.class);
    protected static ClientRequest clientRequest = new ClientRequest();
    protected static final ResourceRequest resourceRequest = new ResourceRequest();
    protected static Response response;
    protected Client client;
    protected Resource resource;
    protected static Map<String, String> clientDataMap;
    protected static Map<String, String> resourceDataMap;

    protected Resource lastResource;

    ObjectMapper objectMapper = new ObjectMapper();
}
