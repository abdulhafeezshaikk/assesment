package com.marqueta.app.coreapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import com.marqueta.app.coreapi.security.CredentialManager;

@Configuration
public class RestTemplateConfig {
	
	@Autowired
	public CredentialManager credentialManager;
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(credentialManager.getUserID(),credentialManager.getPassword()));
		return restTemplate;
	}
}
