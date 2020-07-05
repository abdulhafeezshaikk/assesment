package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.steps.CreateCardSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class CreateCardBehaviors extends ScenarioSteps {
	
	@Autowired
	public CreateCardSteps createCardSteps;

	private static final long serialVersionUID = 1L;
	
	@Given("abc")
	public void given() {
		createCardSteps.buildRequest();
	}
	
	@When("cdde")
	public void when() {
		createCardSteps.callCardProducts();
	}
	
	@Then("I get a response")
	public void theb() {
		createCardSteps.verifyCardProductsResponse();
	}

}
