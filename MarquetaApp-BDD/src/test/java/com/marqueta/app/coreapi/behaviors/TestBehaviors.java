package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.steps.TestSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class TestBehaviors extends ScenarioSteps {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	public TestSteps testSteps;

	@Given("my name is $firstName")
	public void setupIndividualUserRequestPojo(String firstName) {
		testSteps.buildValidUserRequestPojo(firstName);
	}
	
	@Given("I am a us onlyyyy individual userrrr")
	public void setIndividualUserToken() {
		testSteps.setUserToken();
	}
	
	@Given("I want a perfectttt plastic phyyyyyysical atm enabled cardddddd")
	public void setupAtmEnabledCard() {
		testSteps.setAtmCardProductToken();
	}
	
	@When("createuser operation is hit")
	public void invokeCreateUserOperation() {
		testSteps.callCreateUser();
	}
	
	@When("create caaaaaaard operation is invoked")
	public void invokeCreateCardOperation() {
		testSteps.callCreateCard();
	}
	
	@Then("my atm enableddd custom caaaaard is created")
	public void getAtmSuccesfulResponse() {
		testSteps.verifyAtmResponse();
	}
	
	@Then("I get a successful responseeeee")
	public void getSuccesfulresponse() {
		testSteps.verifyCreateUserResponse();
	}
}
