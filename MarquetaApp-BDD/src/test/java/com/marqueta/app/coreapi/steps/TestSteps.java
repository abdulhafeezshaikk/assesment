package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.marqueta.app.coreapi.model.CreateUserRequest;
import com.marqueta.app.coreapi.model.CreateUserResponse;
import com.marqueta.app.coreapi.service.CoreApiService;
import com.marqueta.app.coreapi.util.JsonFileReaderUtility;

@Component
public class TestSteps {
	
	@Autowired
	public CoreApiService service;
	
	@Autowired
	public JsonFileReaderUtility readerUtil;
	
	public String request;
	public CreateUserResponse response;
	public String responsee;
	public String parentToken;
	public HttpClientErrorException exception;
	
	public CreateUserRequest createUserRequest;
	
	public void defaults() {
		exception = null;
		request = null;
		response = null;
	}
	
	public void buildValidUserRequestPojo(String firstName) {
		createUserRequest = new CreateUserRequest();
		createUserRequest.setFirst_name(firstName);
		createUserRequest.setLast_name("LastName");
	}
	
	public void setAtmCardProductToken() {
		JSONObject requestObject = readerUtil.getObject(request);
		//requestObject.put("token", "atmenabledcard");
		request = requestObject.toString();
	}
	
	public void setUserToken() {
		JSONObject requestObject = new JSONObject();
		requestObject.put("user_token", "blueebird_token");
		requestObject.put("card_product_token", "noaddress");
		request = requestObject.toString();
	}
	
	public void callCreateCard() {
		try {
			responsee = service.createCard(request);
		} catch(HttpClientErrorException e) {
			exception = e;
		}
	}
	
	public void callCreateUser() {
		try {
			System.out.println(createUserRequest.getAddress1());
			response = service.createUser(createUserRequest);
		} catch(HttpClientErrorException e) {
			exception = e;
		}
	}
	
	public void verifyAtmResponse() {
		assertNotNull(responsee);
		//assertEquals(readerUtil.getObject(response).get("token").toString(), "atmenabledcard");
	}
	
	public void verifyCreateUserResponse() {
		assertNotNull(response);
		//assertEquals(response.getToken(), readerUtil.getObject(request).get("token").toString());
		assertNotNull(response.getToken());
		assertEquals(response.getUses_parent_account(), false);
	}
}
