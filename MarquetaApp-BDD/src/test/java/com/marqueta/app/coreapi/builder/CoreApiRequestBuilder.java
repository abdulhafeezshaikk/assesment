package com.marqueta.app.coreapi.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CoreApiRequestBuilder {
	
	public String buildRequestFromJson() {
		try {
			Resource resource = new ClassPathResource("data-templates/CreateCardProductRequest.json");
			return new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}
}
