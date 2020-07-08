package com.marqueta.app.coreapi.steps;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.marqueta.app.coreapi.model.TransactionRequest;
import com.marqueta.app.coreapi.service.CoreApiService;
import com.marqueta.app.coreapi.util.JsonFileReaderUtility;

@Component
public class TransactionsSteps {

	@Autowired
	public CoreApiService service;
	
	@Autowired
	public JsonFileReaderUtility readerUtil;

	public TransactionRequest request;
	public String response;
	public HttpClientErrorException exception;

	public void buildValidTransactionRequest(String cardToken) {
		request = new TransactionRequest();
		request.setAmount("10");
		request.setCard_token(cardToken);
		request.setMid("123456890");
	}

	public void buildInactiveCardTransactionRequest() {
		buildValidTransactionRequest("atmenabledcard");
	}

	public void buildInSufficientFundsCardTransactionRequest() {
		buildValidTransactionRequest("virtualparentchildcard");
	}

	public void callTransactions() {
		try {
			response = service.transact(request);
		} catch (HttpClientErrorException e) {
			exception = e;
			System.out.println(e.getResponseBodyAsString());
		}
	}

	public void verifyErrorMessageAndCode(String message, String code) {
		assertEquals(readerUtil.getObject(response).getJSONObject("transaction").getJSONObject("response").get("code").toString(), code);
		assertEquals(readerUtil.getObject(response).getJSONObject("transaction").getJSONObject("response").get("memo").toString(), message);
	}

	public void verifyState(String state) {
		assertEquals(readerUtil.getObject(response).getJSONObject("transaction").get("state").toString(), state);
	}
}
