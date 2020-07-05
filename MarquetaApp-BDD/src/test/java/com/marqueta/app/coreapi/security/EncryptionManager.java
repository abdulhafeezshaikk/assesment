package com.marqueta.app.coreapi.security;

import org.springframework.stereotype.Component;

@Component
public class EncryptionManager {
	private static final String PASSPHRASE = "encryption.passphrase";
	
	public static String decryptPassword(String encryptedPassword) {
		String passphrase = System.getProperty(PASSPHRASE);
		
		return AES.decrypt(encryptedPassword, passphrase);
	}
}