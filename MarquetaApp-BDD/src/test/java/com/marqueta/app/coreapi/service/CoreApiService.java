package com.marqueta.app.coreapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.marqueta.app.coreapi.builder.CoreApiHttpHeadersBuilder;
import com.marqueta.app.coreapi.model.CreateCardRequest;
import com.marqueta.app.coreapi.model.CreateCardResponse;
import com.marqueta.app.coreapi.model.CreateProgramFundingRequest;
import com.marqueta.app.coreapi.model.CreateProgramFundingResponse;
import com.marqueta.app.coreapi.model.CreateUserResponse;
import com.marqueta.app.coreapi.model.FundUserAccountRequest;
import com.marqueta.app.coreapi.model.FundUserAccountResponse;
import com.marqueta.app.coreapi.model.TransactionRequest;
import com.marqueta.app.coreapi.model.TransactionResponse;

@Component
public class CoreApiService {
	
	@Autowired
	private CoreApiHttpHeadersBuilder headerBuilder;
	
	@Value("#{coreAPiUrl}")
	private String coreApiUrl;
	
	public String createCardProduct(String request) {
		RestTemplate restTemplate = new RestTemplate();
		String createCardProductUrl = coreApiUrl+"/cardproducts";
		HttpEntity<String> entity = new HttpEntity<String>(request, headerBuilder.createHttpHeaders());
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("9dc0f495-95f3-46a1-b1db-56caa14951ab","bcf02eeb-f2eb-4d8d-814d-a4d0b67df002"));
		return restTemplate.postForObject(createCardProductUrl, entity, String.class);
	}
	
	public CreateProgramFundingResponse createProgramFunding(CreateProgramFundingRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		String createProgramFundingUrl = coreApiUrl+"/fundingsources/program";
		return restTemplate
				.exchange(createProgramFundingUrl, HttpMethod.POST, 
				new HttpEntity<CreateProgramFundingRequest>(request, headerBuilder.createHttpHeaders()), 
				CreateProgramFundingResponse.class).getBody();
	}
	
	public CreateUserResponse createUser(String request) {
		RestTemplate restTemplate = new RestTemplate();
		String createUserUrl = coreApiUrl+"/users";
		HttpEntity<String> entity = new HttpEntity<String>(request, headerBuilder.createHttpHeaders());
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("9dc0f495-95f3-46a1-b1db-56caa14951ab","bcf02eeb-f2eb-4d8d-814d-a4d0b67df002"));
		return restTemplate.postForObject(createUserUrl, entity, CreateUserResponse.class);
	}
	
	public CreateCardResponse createCard(CreateCardRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		String createCardUrl = coreApiUrl+"/cards";
		return restTemplate
				.exchange(createCardUrl, HttpMethod.POST, 
				new HttpEntity<CreateCardRequest>(request, headerBuilder.createHttpHeaders()), 
				CreateCardResponse.class).getBody();
	}
	
	public FundUserAccountResponse createCard(FundUserAccountRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		String fundUserAccountUrl = coreApiUrl+"/gpaorders";
		return restTemplate
				.exchange(fundUserAccountUrl, HttpMethod.POST, 
				new HttpEntity<FundUserAccountRequest>(request, headerBuilder.createHttpHeaders()), 
				FundUserAccountResponse.class).getBody();
	}
	
	public TransactionResponse createCard(TransactionRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		String transactionUrl = coreApiUrl+"/simulate/authorization";
		return restTemplate
				.exchange(transactionUrl, HttpMethod.POST, 
				new HttpEntity<TransactionRequest>(request, headerBuilder.createHttpHeaders()), 
				TransactionResponse.class).getBody();
	}
}