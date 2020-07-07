package com.marqueta.app.coreapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreApiSecurityConfig {

	private String APP_USERNAME = "9dc0f495-95f3-46a1-b1db-56caa14951ab";
	private String APP_PASSWORD = "{ENCRYPT_AES}SGsNX+5vQ5MbHyze9ToXK5KP2Q4NU/hXgKqPGHbOr+CXOvTUi/NqAY5iDuGto0Z3";

	@Bean("APP_USERNAME")
	public String getUserID() {
		return APP_USERNAME;
	}

	@Bean("APP_PASSWORD")
	public String getPassword() {
		return APP_PASSWORD;
	}
}
