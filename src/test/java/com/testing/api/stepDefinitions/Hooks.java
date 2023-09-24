package com.testing.api.stepDefinitions;

import com.testing.api.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

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
        logger.info("	test end   ");
        logger.info("*****************************************************************************************");
    }
}
