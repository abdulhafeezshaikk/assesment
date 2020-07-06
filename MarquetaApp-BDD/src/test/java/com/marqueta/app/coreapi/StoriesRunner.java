package com.marqueta.app.coreapi;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;

import net.serenitybdd.jbehave.SerenityStories;

public class StoriesRunner extends SerenityStories {
	
	@Override
	public Configuration configuration() {
		Configuration configuration = super.configuration();
		configuration.usePendingStepStrategy(new FailingUponPendingStep());
		return configuration;
	}
	
	@Override
	protected String getStoryPath() {
		return "**/*.story";
	}
	
	
	  @Override public InjectableStepsFactory stepsFactory() { return new
	  SpringStepsFactory(configuration(), createContext()); }
	  
	  private ApplicationContext createContext() { return new
	  SpringApplicationContextFactory("applicationContext-serenity.xml").
	  createApplicationContext(); }
	 
}