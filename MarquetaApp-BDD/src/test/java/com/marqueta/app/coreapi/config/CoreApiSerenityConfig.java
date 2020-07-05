package com.marqueta.app.coreapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marqueta.app.coreapi.urlfactory.CoreApiUrlFactory;

@Configuration
public class CoreApiSerenityConfig {
	
	@Bean(name="coreAPiUrl")
	public CoreApiUrlFactory getCoreApiUrlConfig() {
		CoreApiUrlFactory conf = new CoreApiUrlFactory();
		conf.setAppName("marqeta.com");
		conf.setResource("v3");
		return conf;
	}
}
