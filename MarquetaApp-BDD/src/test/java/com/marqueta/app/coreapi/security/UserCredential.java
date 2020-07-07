package com.marqueta.app.coreapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserCredential {
	
	@Value("#{APP_USERNAME}")
	private String userID;
	
	@Value("#{APP_PASSWORD}")
	private String password;
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
}