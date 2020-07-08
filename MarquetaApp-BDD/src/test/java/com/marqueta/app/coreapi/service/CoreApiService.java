package com.marqueta.app.coreapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.marqueta.app.coreapi.builder.CoreApiHttpHeadersBuilder;
import com.marqueta.app.coreapi.model.CreateUserResponse;
import com.marqueta.app.coreapi.model.TransactionRequest;

@Component
public class CoreApiService {
	
	@Autowired
	private CoreApiHttpHeadersBuilder headerBuilder;
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Value("#{coreAPiUrl}")
	private String coreApiUrl;
	
	public String createCardProduct(String request) {
		String createCardProductUrl = coreApiUrl+"/cardproducts";
		HttpEntity<String> entity = new HttpEntity<String>(request, headerBuilder.createHttpHeaders());
		return restTemplate.postForObject(createCardProductUrl, entity, String.class);
	}
	
	public CreateUserResponse createUser(String request) {
		String createUserUrl = coreApiUrl+"/users";
		HttpEntity<String> entity = new HttpEntity<String>(request, headerBuilder.createHttpHeaders());
		return restTemplate.postForObject(createUserUrl, entity, CreateUserResponse.class);
	}
	
	public String createCard(String request) {
		String createCardUrl = coreApiUrl+"/cards";
		HttpEntity<String> entity = new HttpEntity<String>(request, headerBuilder.createHttpHeaders());
		return restTemplate.postForObject(createCardUrl,entity,String.class);
	}
	
	public String transact(TransactionRequest request) {
		String transactionUrl = coreApiUrl+"/simulate/authorization";
		HttpEntity<TransactionRequest> entity = new HttpEntity<>(request, headerBuilder.createHttpHeaders());
		return restTemplate.postForObject(transactionUrl,entity,String.class);
	}
}