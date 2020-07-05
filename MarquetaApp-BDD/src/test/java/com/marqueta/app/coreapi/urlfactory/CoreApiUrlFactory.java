package com.marqueta.app.coreapi.urlfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CoreApiUrlFactory extends AbstractFactoryBean<String>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreApiUrlFactory.class);
	
	private static final String SERVER = "%s-api.%s";
	private static final String ADDRESS = "%s://%s/%s";
	
	private String environment;
	private String protocol = "https";
	private String appName;
	private String resource;
	
	@Override
	public Class<?> getObjectType() {
		return String.class;
	}
	
	@Override
	protected String createInstance() {
		environment = "sandbox";
		String server = String.format(SERVER, environment, appName);
		String endpoint = String.format(ADDRESS, protocol, server, resource);
		LOGGER.info(endpoint);
		return endpoint;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}
}
