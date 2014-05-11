package com.eswaraj.base.aspect;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

/**
 * This class keeps track of all DB objects created in a test and then delete after the test
 * @author ravi
 *
 */
@Component
public class TestObjectContextManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Set<Object> allDbSavedObjects = new HashSet<Object>();
	
	@Autowired Neo4jTemplate neo4jTemplate;
	
	private boolean dontDeleteForThisTest;
	
	public void addSavedObject(Object object){
		logger.trace("Adding Object to test Context "+object);
		allDbSavedObjects.add(object);
	}
	public void clearAllObjectsCreatdDuringTest(){
		for(Object oneObject:allDbSavedObjects){
			try{
				logger.trace("Deleting : "+oneObject);
				if(!dontDeleteForThisTest){
					neo4jTemplate.delete(oneObject);	
				}
				
			}catch(Exception ex){
				logger.error("Exception occured while deleting data : "+oneObject, ex);
				Assert.fail("Unable to delete object "+ oneObject);
			}
		}
		allDbSavedObjects.clear();
		dontDeleteForThisTest = false;
	}
	public boolean isDontDeleteForThisTest() {
		return dontDeleteForThisTest;
	}
	public void setDontDeleteForThisTest(boolean dontDeleteForThisTest) {
		this.dontDeleteForThisTest = dontDeleteForThisTest;
	}

}
