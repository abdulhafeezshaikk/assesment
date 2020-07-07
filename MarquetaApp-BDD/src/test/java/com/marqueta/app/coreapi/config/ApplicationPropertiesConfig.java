/*
 * package com.marqueta.app.coreapi.config;
 * 
 * import java.io.IOException; import java.io.InputStream; import
 * java.util.Properties;
 * 
 * import
 * org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Configuration public class ApplicationPropertiesConfig { private static
 * final String APP_DEV_PROPERTIES = "application-development.properties";
 * 
 * @Bean public static PropertyPlaceholderConfigurer properties() throws
 * IOException { PropertyPlaceholderConfigurer ppc = new
 * PropertyPlaceholderConfigurer(); InputStream stream =
 * ApplicationPropertiesConfig.class.getClassLoader().getResourceAsStream(
 * APP_DEV_PROPERTIES); Properties props = new Properties(); props.load(stream);
 * ppc.setProperties(props); return ppc; } }
 */