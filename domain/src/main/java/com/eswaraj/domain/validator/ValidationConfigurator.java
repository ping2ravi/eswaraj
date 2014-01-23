package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.lifecycle.AfterDeleteEvent;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.data.neo4j.lifecycle.BeforeDeleteEvent;
import org.springframework.data.neo4j.lifecycle.BeforeSaveEvent;

@Configuration
@EnableNeo4jRepositories
public class ValidationConfigurator extends Neo4jConfiguration {
	@Autowired
	private ValidationManager validationManager;
    @Bean
    ApplicationListener<BeforeSaveEvent> beforeSaveEventApplicationListener() {
        return new ApplicationListener<BeforeSaveEvent>() {
            @Override
            public void onApplicationEvent(BeforeSaveEvent beforeSaveEvent) {
            	validationManager.validateBeforeSave(beforeSaveEvent);
            }
        };
    }

    @Bean
    ApplicationListener<AfterSaveEvent> afterSaveEventApplicationListener() {
        return new ApplicationListener<AfterSaveEvent>() {
            @Override
            public void onApplicationEvent(AfterSaveEvent event) {
            	//System.out.println("AfterSaveEvent "+event.getEntity());
            }
        };
    }

    @Bean
    ApplicationListener<BeforeDeleteEvent> beforeDeleteEventApplicationListener() {
        return new ApplicationListener<BeforeDeleteEvent>() {
            @Override
            public void onApplicationEvent(BeforeDeleteEvent event) {
            	//System.out.println("BeforeDeleteEvent "+event.getEntity());
            }
        };
    }
    
    @Bean
    ApplicationListener<AfterDeleteEvent> afterDeleteEventApplicationListener() {
        return new ApplicationListener<AfterDeleteEvent>() {
            @Override
            public void onApplicationEvent(AfterDeleteEvent event) {
            	//System.out.println("AfterDeleteEvent "+event.getEntity());
            }
        };
    }
}
