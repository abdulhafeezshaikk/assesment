package com.marqueta.app.coreapi.behaviors;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
import com.marqueta.app.coreapi.steps.CreateCardProductSteps;

import net.thucydides.core.steps.ScenarioSteps;

@Component
public class CreateCardProductBehaviors extends ScenarioSteps {
	
	@Autowired
	public CreateCardProductSteps createCardSteps;

	private static final long serialVersionUID = 1L;
	
	@Given("I want to create <CardType> card")
	public void createTypeOfCard(@Named("CardType")String cardType) {
		createCardSteps.buildRequest(CoreApiScenario.DEFAULT_CARD_PRODUCT);
		createCardSteps.setCardType(cardType);
	}
	
	@Given("user creates <CardName>")
	public void createCardNameCard(@Named("CardName")String cardName) {
		createCardSteps.buildRequest(CoreApiScenario.DEFAULT_CARD_PRODUCT);
		createCardSteps.setCardName(cardName);
	}
	
	@Given("wants fulfilment provider as <Provider>")
	public void createProvider(@Named("Provider")String provider) {
		createCardSteps.setProvider(provider);
	}
	
	@Given("user creates a card product")
	public void addressVerificationCard() {
		createCardSteps.buildRequest(CoreApiScenario.DEFAULT_CARD_PRODUCT);
	}
	
	@Given("user creates a card product with no address")
	public void noAddressCardRequest() {
		createCardSteps.buildNoAddressRequest();
	}
	
	@Given("enables auth messages for address verification on postal code")
	public void enableAuthMessages() {
		createCardSteps.setAuthMessages();
	}
	
	@Given("the product doesnt allow card creation")
	public void disableCardCreation() {
		createCardSteps.disableCreateCard();
	}
	
	@When("create card product is invoked")
	public void when() {
		createCardSteps.callCardProducts();
	}
	
	@Then("I get a successful <CardType> response")
	public void verifySuccessfulCardTypeResponse(@Named("CardType")String cardType) {
		createCardSteps.verifyCardTypeResponse(cardType);
	}
	
	@Then("a <CardName> product is created with <Provider>")
	public void verifySuccessfulCardTypeResponse(@Named("CardName")String cardName, @Named("Provider")String provider) {
		createCardSteps.verifyNameAndProvider(cardName, provider);
	}
	
	@Then("address verification messages are enabled for the card")
	public void verifyAuthMessagesEnabled() {
		createCardSteps.verifyAuthMessages();
	}
	
	@Then("card creation is disabled for the product")
	public void verifyDisabledFlag() {
		createCardSteps.verifyCardCreationIsDisabled();
	}
	
	@Then("card is created with no address")
	public void noAddressCard() {
		createCardSteps.verifyNoAddress();
	}
}
