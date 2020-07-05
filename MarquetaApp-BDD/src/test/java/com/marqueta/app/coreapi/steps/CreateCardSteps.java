package com.marqueta.app.coreapi.steps;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.builder.CoreApiRequestBuilder;
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
	
	public void buildRequest() {
		request = readerUtil.buildJson();
		System.out.println(request);
	}
	
	public void callCardProducts() {
		/*
		 * CreateCardProductRequest request = new CreateCardProductRequest();
		 * request.setName("My Card Product 01"); request.setStart_date("2017-04-27");
		 */
		try {
			response = service.createCardProduct(request);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void verifyCardProductsResponse() {
		Assert.assertNotNull(response);
	}
}
