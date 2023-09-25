package com.testing.api.stepDefinitions;

import com.testing.api.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * class to define the before and after of every test
 */
public class Hooks extends BaseStepDefinition {

    @Before
    public void testStart(Scenario scenario) {
        logger.info("*****************************************************************************************");
        logger.info("	Scenario: " + scenario.getName());
        logger.info("*****************************************************************************************");
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @After
    public void testEnd(){
        logger.info("*****************************************************************************************");
        logger.info("	test done   ");
        logger.info("*****************************************************************************************");
    }

}
