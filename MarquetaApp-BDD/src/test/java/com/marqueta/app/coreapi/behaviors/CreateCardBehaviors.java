package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
import com.marqueta.app.coreapi.steps.CreateCardSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class CreateCardBehaviors extends ScenarioSteps{

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	public CreateCardSteps createCardSteps;
	
	@Given("I am a us only individual user")
	@Alias("I am a individual user")
	public void setIndividualUserToken() {
		createCardSteps.setUserToken("blueebird_token");
	}
	
	@Given("I am a parent-child user account")
	public void setParentChildUserToken() {
		createCardSteps.setUserToken("parentuserr_token");
	}
	
	@Given("card product doesnt allow create card")
	public void setCantCreateCardToken() {
		createCardSteps.setCantCreateCardProductToken("cardcreationdisabled");
	}
	
	@Given("I am a individual customer")
	public void setIndividualCustomer() {
		createCardSteps.buildRequest(CoreApiScenario.DEFAULT_CARD);
	}
	
	@Given("I want to update my <Address><City>")
	public void updateDetails(@Named("Address")String address,@Named("City")String city) {
		createCardSteps.setNewAddress(address, city);
	}
	
	@Given("I want to add my <Name><Image> and <Signature>")
	public void personalizeCard(@Named("Name")String name, @Named("Image")String image, @Named("Signature")String signature) {
		createCardSteps.addPersonalizedDetails(name, image, signature);
	}
	
	@Given("I want a perfect plastic physical atm enabled card")
	//@Then("I want a perfect plastic physical atm enabled card")
	public void setupAtmEnabledCard() {
		createCardSteps.setAtmCardProductToken("atmtoken");
	}
	
	@Given("I want virtual card with address validation on postal code")
	public void setupVirtualCard() {
		createCardSteps.setVirtualCardProductToken("virtualcardtoken");
	}
	
	@Given("I want a digital wallet enabled card")
	public void setupDigitalEnabledCard() {
		createCardSteps.setDigitalCardProductToken("digitalwallettokenenablecard");
	}
	
	@Given("I want a to expedite shipping")
	public void setupExpidite() {
		createCardSteps.setupExpediteshipping();
	}
	
	@When("create card operation is invoked")
	public void invokeCreateUserOperation() {
		createCardSteps.callCreateCard();
	}
	
	@Then("my atm enabled custom card is created")
	public void getAtmSuccesfulResponse() {
		createCardSteps.verifyAtmResponse();
	}
	
	@Then("my digital wallet enabled card is created")
	public void getDigitalSuccesfulResponse() {
		createCardSteps.verifyDigitalEnabledResponse();
	}
	
	@Then("virtual card is created")
	public void getVirtualSuccesfulResponse() {
		createCardSteps.verifyVirtualCardResponse();
	}
	
	@Then("shipping is expedited")
	public void getExpeditedResponse() {
		createCardSteps.verifyExpeditedShipping();
	}
	
	@Then("my personalized card is created")
	public void getPersonalizedResponse() {
		createCardSteps.verifyPersonalizedCardCreated();
	}
	
	@Then("address is update to <Address><City>")
	public void getAddressFromResponse(@Named("Address")String address,@Named("City")String city) {
		createCardSteps.verifyAddressDetails(address, city);
	}
	
	@Then("<Name><Image> and <Signature> are added to the card")
	public void getAddressFromResponse(@Named("Name")String name,@Named("Image")String image, @Named("Signature")String signature) {
		createCardSteps.verifyPersonalizedDetails(name, image, signature);
	}
	
	@Then("error message $errorMessage is thrown")
	public void getChildrenCardholderErrorMessage(String errorMessage) {
		createCardSteps.verifyUnableTOCreateCardErrorMessage(errorMessage);
	}
	
	@Then("error code is $errorCode")
	public void receiveErrorCode(String errorCode) {
		createCardSteps.verifyUnableTOCreateCardErrorErrorCode(errorCode);
	}
}
