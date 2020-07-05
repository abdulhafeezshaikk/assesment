package com.marqueta.app.coreapi.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marqueta.app.coreapi.util.JsonFileReaderUtility;

@Component
public class CreateUserVerification {
	
	@Autowired
	public JsonFileReaderUtility readerUtil;
	
	public void verifyToken() {}
}
