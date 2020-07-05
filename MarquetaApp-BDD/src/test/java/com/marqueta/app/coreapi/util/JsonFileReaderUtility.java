package com.marqueta.app.coreapi.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class JsonFileReaderUtility {
	
	public String buildJson() {
		try {
	        JSONParser parser = new JSONParser();
	        JSONObject data = (JSONObject) parser.parse(
	              new FileReader("src/test/resources/data-templates/CreateCardProductRequest.json"));

	        String json = data.toJSONString();
	        return json;
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
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

