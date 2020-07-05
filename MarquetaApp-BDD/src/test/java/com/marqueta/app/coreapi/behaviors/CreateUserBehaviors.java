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
	
	@Given("a $newChildAccount is added to $parentToken account")
	public void addChildToexistingUser(String userToken, String parentToken) {
		createUserSteps.buildChildToExistingParentRequest(userToken, parentToken);
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
}
