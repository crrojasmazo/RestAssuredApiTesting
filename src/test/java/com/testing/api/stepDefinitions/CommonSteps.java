package com.testing.api.stepDefinitions;

import io.cucumber.java.en.Then;

import org.junit.Assert;

/**
 * Class to define the common steps among the different features
 */
public class CommonSteps extends BaseStepDefinition {
    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        logger.info(response);
        Assert.assertEquals(statusCode, response.statusCode());
    }

}
