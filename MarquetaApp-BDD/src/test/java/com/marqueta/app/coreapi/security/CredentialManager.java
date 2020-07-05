package com.marqueta.app.coreapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredentialManager {
	
	@Autowired
	private UserCredential credential;
	
	public String getPassword() {
		return EncryptionManager.decryptPassword(credential.getPassword());
	}
	
	public String getUserID() {
		return credential.getUserID();
	}
}