package com.marqueta.app.coreapi.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.security.BDDSecurityManager;

@Component
public class CoreApiHttpHeadersBuilder {
	
	@Autowired
	private BDDSecurityManager securityManager;
	
	public HttpHeaders createHttpHeaders() {
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.setAccept(acceptableMediaTypes);
		//headers.set("Authorization", securityManager.generateBasicAuthToken());
		return headers;
	}
}
