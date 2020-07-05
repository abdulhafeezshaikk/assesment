package com.marqueta.app.coreapi.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.constants.CoreApiScenario;

@Component
public class JsonFileReaderUtility {
	
	public static final String PATH = "src/test/resources/data-templates/";
	public static final String FILE_EXTENSION = ".json";
	
	@Autowired
	@Qualifier("CoreApiScenarioNamesMap")
	private Map<CoreApiScenario, String> scenarioMap;
	
	public String buildJson(CoreApiScenario scenario) {
		try {
	        JSONParser parser = new JSONParser();
	        JSONObject data = (JSONObject) parser.parse(
	              new FileReader(PATH+scenarioMap.get(scenario)+FILE_EXTENSION));

	        String json = data.toJSONString();
	        return json;
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public JSONObject getObject(String request) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request);
			return data;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}

