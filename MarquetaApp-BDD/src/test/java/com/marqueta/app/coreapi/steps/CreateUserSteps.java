package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
import com.marqueta.app.coreapi.model.CreateUserRequest;
import com.marqueta.app.coreapi.model.CreateUserResponse;
import com.marqueta.app.coreapi.service.CoreApiService;
import com.marqueta.app.coreapi.util.JsonFileReaderUtility;

@Component
public class CreateUserSteps {
	
	@Autowired
	public CoreApiService service;
	
	@Autowired
	public JsonFileReaderUtility readerUtil;
	
	public String request;
	public CreateUserResponse response;
	public String parentToken;
	public HttpClientErrorException exception;
	
	public CreateUserRequest createUserRequest;
	
	public void defaults() {
		exception = null;
		request = null;
		response = null;
	}
	
	public void buildValidUserRequest(CoreApiScenario scenario) {
		request = readerUtil.buildJson(scenario);
	}
	
	public void buildValidUserRequestPojo(String firstName) {
		createUserRequest = new CreateUserRequest();
		createUserRequest.setFirst_name(firstName);
		createUserRequest.setLast_name("LastName");
	}
	
	public void buildRequestWithExistingField(String field) {
		JSONObject requestObject = readerUtil.getObject(request);
		if(field.equalsIgnoreCase("email")) {
			requestObject.put(field, "blueebird@gmail.com");
		}else {
			requestObject.put("email", "newemail@gmail.com");
		}
		if(field.equalsIgnoreCase("token")) {
			requestObject.put(field, "blueebird_token");
		}else {
			requestObject.put("token", "token1");
		}
		
		request = requestObject.toString();
	}
	
	public void buildChildRequest(String userToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", userToken);
		requestObject.put("email", userToken+"@gmail.com");
		requestObject.put("parent_token", parentToken);
		request = requestObject.toString();
	}
	
	public void buildChildToExistingParentRequest(String userToken, String parentToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", userToken);
		requestObject.put("email", userToken+"@gmail.com");
		requestObject.put("parent_token", parentToken);
		request = requestObject.toString();
	}
	
	public void buildGrandChildToExistingNonBusinessRequest(String grandChildToken, String childToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", grandChildToken);
		requestObject.put("email", grandChildToken+"@gmail.com");
		requestObject.put("parent_token", childToken);
		request = requestObject.toString();
	}
	
	public void setNoChildrenFlag() {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("uses_parent_account", false);
		request = requestObject.toString();
	}
	
	public void setShareBalancesFlag(Boolean flag) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("uses_parent_account", flag);
		request = requestObject.toString();
	}
	
	public void callCreateUser() {
		try {
			System.out.println(createUserRequest.getAddress1());
			response = service.createUser(createUserRequest);
		} catch(HttpClientErrorException e) {
			exception = e;
		}
	}
	
	public void verifyCreateUserResponse() {
		assertNotNull(response);
		//assertEquals(response.getToken(), readerUtil.getObject(request).get("token").toString());
		assertNotNull(response.getToken());
		assertEquals(response.getUses_parent_account(), false);
	}
	
	public void verifyParentResponse() {
		assertNotNull(response);
		parentToken = response.getToken();
	}
	
	public void verifyChildResponse(String userToken, Boolean flag) {
		assertNotNull(response);
		assertEquals(response.getToken(), readerUtil.getObject(request).get("token").toString());
		assertEquals(response.getUses_parent_account(), flag);
		assertEquals(response.getParent_token(), readerUtil.getObject(request).get("parent_token").toString());
	}
	
	public void verifyChildToExistingUserResponse(String userToken, String parentToken) {
		assertNotNull(response);
		assertEquals(response.getToken(), readerUtil.getObject(request).get("token").toString());
		assertEquals(response.getParent_token(), parentToken);
	}
	
	public void verifyChildrenCardHolderErrorMessage(String errorMessage) {
		assertNotNull(exception);
		assertTrue(exception.getResponseBodyAsString().contains(errorMessage));
	}
	
	public void verifyChildrenCardHolderErrorCode(String errorCode) {
		assertTrue(exception.getResponseBodyAsString().contains(errorCode));
	}
	
	public void verifyErrorCodeAndMessage(String errorMessage, String errorCode) {
		assertNotNull(exception);
		System.out.println(exception.getResponseBodyAsString());
		assertTrue(exception.getResponseBodyAsString().contains(errorMessage));
		assertTrue(exception.getResponseBodyAsString().contains(errorCode));
	}
}
