package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.steps.TransactionsSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class TransactionsBehaviors extends ScenarioSteps {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	public TransactionsSteps transactionsSteps;
	
	@Given("I have a inactive card with funds still in pending credits")
	public void setupIndividualUserRequest() {
		transactionsSteps.buildInactiveCardTransactionRequest();
	}
	
	@Given("I have a card with funds still in pending credits")
	public void setupInsufficientFundsRequest() {
		transactionsSteps.buildInSufficientFundsCardTransactionRequest();
	}
	
	@When("transaction operation is called")
	public void callService() {
		transactionsSteps.callTransactions();
	}
	
	@Then("I get response status as $state")
	public void verifyStatus(String state) {
		transactionsSteps.verifyState(state);
	}
	
	@Then("response as $message, code as $code")
	public void verifyNotProcessedResponse(String message, String code) {
		transactionsSteps.verifyErrorMessageAndCode(message, code);
	}
}
