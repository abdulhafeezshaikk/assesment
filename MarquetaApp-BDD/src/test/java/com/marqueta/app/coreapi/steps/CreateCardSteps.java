package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public void buildRequest(CoreApiScenario scenario) {
		request = readerUtil.buildJson(scenario);
	}
	
	public void buildNoAddressRequest() {
		JSONObject requestObject = new JSONObject();
		requestObject.put("name", "No Address Card");
		requestObject.put("token", "noaddress");
		requestObject.put("start_date", "2020-04-27");
		request = requestObject.toString();
	}
	
	public void setCardType(String cardType) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", cardType+"token");
		requestObject.getJSONObject("config").getJSONObject("poi").put(cardType, true);
		request = requestObject.toString();
	}
	
	public void setProvider(String provider) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.getJSONObject("config").getJSONObject("fulfillment").put("fulfillment_provider", provider);
		request = requestObject.toString();
	}
	
	public void setCardName(String cardName) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", cardName);
		request = requestObject.toString();
	}
	
	public void disableCreateCard() {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", "cardcreationdisabled");
		requestObject.getJSONObject("config").getJSONObject("fulfillment").put("allow_card_creation", false);
		request = requestObject.toString();
	}
	
	public void setAuthMessages() {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", "addressVerificationAuthMessageToken");
		requestObject.getJSONObject("config").getJSONObject("transaction_controls").getJSONObject("address_verification").getJSONObject("auth_messages").put("validate", true);
		requestObject.getJSONObject("config").getJSONObject("transaction_controls").getJSONObject("address_verification").getJSONObject("auth_messages").put("decline_on_postal_code_mismatch", true);
		request = requestObject.toString();
	}
	
	public void callCardProducts() {
		try {
			response = service.createCardProduct(request);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void verifyCardTypeResponse(String cardType) {
		assertEquals(readerUtil.getObject(response).get("token").toString(), cardType+"token");
		assertEquals(readerUtil.getObject(response).getJSONObject("config").getJSONObject("poi").get(cardType), true);
	}
	
	public void verifyAuthMessages() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).getJSONObject("config").getJSONObject("transaction_controls").getJSONObject("address_verification").getJSONObject("auth_messages").get("decline_on_postal_code_mismatch"), true);
	}
	
	public void verifyCardCreationIsDisabled() {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).getJSONObject("config").getJSONObject("fulfillment").get("allow_card_creation"), false);
	}
	
	public void verifyNameAndProvider(String cardName, String provider) {
		assertNotNull(response);
		assertEquals(readerUtil.getObject(response).get("token").toString(), cardName);
		assertEquals(readerUtil.getObject(response).getJSONObject("config").getJSONObject("fulfillment").get("fulfillment_provider").toString(), provider);
	}
	
	public void verifyNoAddress() {
		assertNotNull(response);
		JSONObject fulfillment = readerUtil.getObject(response).getJSONObject("config").getJSONObject("fulfillment");
		Iterator<String> keys = fulfillment.keys();
		while(keys.hasNext()) {
		    String key = keys.next();
		    if (key.equalsIgnoreCase("shipping")) {
		          assertTrue(false);    
		    }
		}
	}
}
