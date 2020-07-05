package com.marqueta.app.coreapi.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marqueta.app.coreapi.constants.CoreApiScenario;

@Configuration
public class ScenarioNameMapConfig {
	
	@Bean(name="CoreApiScenarioNamesMap")
	public Map<CoreApiScenario, String> CoreApiScenarioNamesMap(){
		Map<CoreApiScenario, String> map = new HashMap<CoreApiScenario, String>();
		map.put(CoreApiScenario.INDIVIDUAL_USER, "IndividualUserRequest");
		map.put(CoreApiScenario.PARENT_USER, "ParentUserRequest");
		return map;
	}
}
