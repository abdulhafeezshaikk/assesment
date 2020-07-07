package com.marqueta.app.coreapi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.JSONObject;
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
			String content;
			try {
				content = new String(Files.readAllBytes(Paths.get(PATH+scenarioMap.get(scenario)+FILE_EXTENSION)));
				JSONObject data = new JSONObject(content) ;
		        String json = data.toString();
		        return json;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	        
	}
	
	public JSONObject getObject(String request) {
			JSONObject data = new JSONObject(request);
			return data;
	}
}

