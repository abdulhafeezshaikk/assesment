package com.marqueta.app.coreapi.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BDDSecurityManagerImpl implements BDDSecurityManager {
	
	@Autowired
	private CredentialManager credentialManager;
	
	@Override
	public String generateBasicAuthToken() {
		String plainCredentials = credentialManager.getUserID() + ":" + credentialManager.getPassword();
		String base64Credentials = new String(Base64.decodeBase64(plainCredentials.getBytes()));
		return "Basic " + base64Credentials;
	}
}