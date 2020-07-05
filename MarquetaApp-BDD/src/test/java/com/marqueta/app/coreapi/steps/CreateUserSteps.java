package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.constants.CoreApiScenario;
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
	
	public void buildValidUserRequest(CoreApiScenario scenario) {
		request = readerUtil.buildJson(scenario);
	}
	
	@SuppressWarnings("unchecked")
	public void buildChildRequest(String userToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", userToken);
		requestObject.put("email", userToken+"@gmail.com");
		requestObject.put("parent_token", parentToken);
		request = requestObject.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public void buildChildToExistingParentRequest(String userToken, String parentToken) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("token", userToken);
		requestObject.put("email", userToken+"@gmail.com");
		requestObject.put("parent_token", parentToken);
		request = requestObject.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public void setNoChildrenFlag() {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("uses_parent_account", false);
		request = requestObject.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public void setShareBalancesFlag(Boolean flag) {
		JSONObject requestObject = readerUtil.getObject(request);
		requestObject.put("uses_parent_account", flag);
		request = requestObject.toJSONString();
	}
	
	public void callCreateUser() {
		try {
			response = service.createUser(request);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void verifyCreateUserResponse() {
		assertNotNull(response);
		assertEquals(response.getToken(), readerUtil.getObject(request).get("token").toString());
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
}
