package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
import com.marqueta.app.coreapi.steps.CreateUserSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class CreateUserBehaviors extends ScenarioSteps {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	public CreateUserSteps createUserSteps;

	@Given("I am an individual user")
	public void setupIndividualUserRequest() {
		createUserSteps.buildValidUserRequest(CoreApiScenario.INDIVIDUAL_USER);
	}
	
	@Given("user tries to create an account with existing <Field>")
	public void setupIndividualUserWithExistingEmailRequest(@Named("Field")String field) {
		createUserSteps.buildRequestWithExistingField(field);
	}
	
	@Given("I am a new parent user")
	public void setupParentUserRequest() {
		createUserSteps.buildValidUserRequest(CoreApiScenario.PARENT_USER);
	}
	
	@Given("I have no child users")
	public void setNoChildren() {
		createUserSteps.setNoChildrenFlag();
	}
	
	@Given("$parentToken is an existing user/account")
	public void existingUser(String parentToken) {
		createUserSteps.buildValidUserRequest(CoreApiScenario.PARENT_USER);
	}
	
	@Given("$newChildAccount is an existing child user")
	public void existingChildUser(String childToken) {
		createUserSteps.buildValidUserRequest(CoreApiScenario.PARENT_USER);
	}
	
	@Given("a $userToken is added to $parentToken account")
	public void addChildToExistingUser(String userToken, String parentToken) {
		createUserSteps.buildChildToExistingParentRequest(userToken, parentToken);
	}
	
	@Given("add a $grandChildToken to a non business $childToken account")
	public void addGrandChildToExistingNonBusinessUser(String grandChildToken, String childToken) {
		createUserSteps.buildGrandChildToExistingNonBusinessRequest(grandChildToken, childToken);
	}
	
	@When("create user core api operation is invoked")
	public void invokeCreateUserOperation() {
		createUserSteps.callCreateUser();
	}
	
	@Then("I get a successful response")
	public void getSuccesfulresponse() {
		createUserSteps.verifyCreateUserResponse();
	}
	
	@Then("parent account is created")
	public void getSuccesfulParentresponse() {
		createUserSteps.verifyParentResponse();
	}
	
	@Then("I have <User> child account")
	public void createChildAccount(@Named("User") String userToken) {
		createUserSteps.buildChildRequest(userToken);
	}
	
	@Then("user <Shares> balances with parent")
	public void setChildAccountBalanceShare(@Named("Shares") Boolean flag) {
		createUserSteps.setShareBalancesFlag(flag);
	}
	
	@Then("<User> is created with balance <Shares> preference")
	public void verifyChildAccount(@Named("User") String userToken, @Named("Shares") Boolean flag) {
		createUserSteps.verifyChildResponse(userToken, flag);
	}
	
	@Then("the $newChildAccount account is created for $parentuserr_token account")
	public void verifyChildOfExistingParentAccount(String userToken, String parentToken) {
		createUserSteps.verifyChildToExistingUserResponse(userToken, parentToken);
	}
	
	@Then("error message $errorMessage is received")
	public void getChildrenCardholderErrorMessage(String errorMessage) {
		createUserSteps.verifyChildrenCardHolderErrorMessage(errorMessage);
	}
	
	@Then("error code $errorCode is received")
	public void receiveErrorCode(String errorCode) {
		createUserSteps.verifyChildrenCardHolderErrorCode(errorCode);
	}
	
	@Then("user receives <ErrorCode> and <ErrorMessage>")
	public void receiveErrorCodeAndMessage(@Named("ErrorCode") String code, @Named("ErrorMessage") String message) {
		createUserSteps.verifyErrorCodeAndMessage(message, code);
	}
}
