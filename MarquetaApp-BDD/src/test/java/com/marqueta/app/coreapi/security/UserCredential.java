package com.marqueta.app.coreapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCredential {
	
	@Value("${app.username}")
	private String userID;
	
	@Value("${app.password}")
	private String password;
	
	public String getUserID() {
		return "9dc0f495-95f3-46a1-b1db-56caa14951ab";
	}
	
	public String getPassword() {
		return "{ENCRYPT_AES}SGsNX+5vQ5MbHyze9ToXK5KP2Q4NU/hXgKqPGHbOr+CXOvTUi/NqAY5iDuGto0Z3";
	}
}