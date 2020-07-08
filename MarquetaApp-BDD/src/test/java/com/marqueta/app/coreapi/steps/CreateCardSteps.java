package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
import com.marqueta.app.coreapi.service.CoreApiService;
import com.marqueta.app.coreapi.util.JsonFileReaderUtility;

@Component
public class CreateCardSteps {
	
	@Autowired
	public CoreApiService service;
	
	@Autowired
	public JsonFileReaderUtility readerUtil;
	
	public String request;
	public String response;
	public HttpClientErrorException exception;
	
	public void buildRequest(CoreApiScenario scenario) {
		request = readerUtil.buildJson(scenario);
	}
	
	public void setNewAddress(String address, String city) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.getJSONObject("fulfillment").getJSONObject("shipping").getJSONObject("recipient_address").put("address1", address);
		requestObject.getJSONObject("fulfillment").getJSONObject("shipping").getJSONObject("recipient_address").put("city", city);
		request = requestObject.toString();
	}
	
	public void addPersonalizedDetails(String name, String image, String signature) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", "personalizedcard");
		requestObject.put("card_product_token", "noaddress");
		requestObject.put("user_token", "blueebird_token");
		requestObject.getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("text").getJSONObject("name_line_1").put("value", name);
		requestObject.getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("images").getJSONObject("card").put("name", image);
		requestObject.getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("images").getJSONObject("signature").put("name", signature);
		request = requestObject.toString();;
	}
	
	public void setUserToken(String userToken) {
		JSONObject requestObject = new JSONObject();
		requestObject.put("user_token", userToken);
		request = requestObject.toString();
	}
	
	public void setAtmCardProductToken(String cardProductToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("card_product_token", cardProductToken);
		requestObject.put("token", "atmenabledcard");
		request = requestObject.toString();
	}
	
	public void setVirtualCardProductToken(String cardProductToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("card_product_token", cardProductToken);
		requestObject.put("token", "virtualparentchildcard");
		request = requestObject.toString();
	}
	
	public void setDigitalCardProductToken(String cardProductToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("card_product_token", cardProductToken);
		requestObject.put("token", "digitalwalletenabledcard");
		request = requestObject.toString();
	}
	
	public void setCantCreateCardProductToken(String cardProductToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("card_product_token", cardProductToken);
		request = requestObject.toString();
	}
	
	public void setAtmEnabledCardProduct() {
		JSONObject requestObject = readerUtil.getObject(request);
		request = requestObject.toString();
	}
	
	public void setupExpediteshipping() {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("expedite", true);
		request = requestObject.toString();
	}
	
	public void setUsOnlyIndividualUser(String userToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("user_token", userToken);
		request = requestObject.toString();
	}
	
	public void callCreateCard() {
		try {
			response = service.createCard(request);
		} catch(HttpClientErrorException e) {
			exception = e;
		}
	}
	
	public void verifyAtmResponse() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).get("token").toString(), "atmenabledcard");
	}
	
	public void verifyDigitalEnabledResponse() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).get("token").toString(), "digitalwalletenabledcard");
	}
	
	public void verifyVirtualCardResponse() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).get("token").toString(), "virtualparentchildcard");
	}
	
	public void verifyExpeditedShipping() {
		assertEquals(readerUtil.getObject(response).get("expedite"), true);
	}
	
	public void verifyPersonalizedCardCreated() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).get("token").toString(), "personalizedcard");
	}
	
	public void verifyAddressDetails(String address, String city) {
		assertEquals(readerUtil.getObject(response).getJSONObject("fulfillment").getJSONObject("shipping").getJSONObject("recipient_address").get("address1").toString(), address);
		assertEquals(readerUtil.getObject(response).getJSONObject("fulfillment").getJSONObject("shipping").getJSONObject("recipient_address").get("city").toString(), city);
	}
	
	public void verifyPersonalizedDetails(String name, String image, String signature) {
		assertEquals(readerUtil.getObject(response).getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("text").getJSONObject("name_line_1").get("value"), name);
		assertEquals(readerUtil.getObject(response).getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("images").getJSONObject("card").get("name"), image);
		assertEquals(readerUtil.getObject(response).getJSONObject("fulfillment").getJSONObject("card_personalization").getJSONObject("images").getJSONObject("signature").get("name"), signature);
	}
	
	public void verifyUnableTOCreateCardErrorMessage(String errorMessage) {
		assertNotNull(exception);
		assertTrue(exception.getResponseBodyAsString().contains(errorMessage));
	}
	
	public void verifyUnableTOCreateCardErrorErrorCode(String errorCode) {
		assertTrue(exception.getResponseBodyAsString().contains(errorCode));
	}
}
