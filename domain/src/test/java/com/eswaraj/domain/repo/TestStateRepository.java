package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.division.Country;
import com.eswaraj.domain.nodes.division.State;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestStateRepository extends BaseEswarajTest{

	@Autowired StateRepository stateRepository;
	@Autowired CountryRepository countryRepository;
	
	/**
	 * Simple test to create a state
	 */
	@Test
    public void basicSaveStateTest() {
        State state = new State();
        state.setName(randomAlphaString(32));
        
        Country country = new Country();
        country.setName(randomAlphaString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        
        
        state = stateRepository.save(state);
        
        State state2 = stateRepository.getStateById(state.getId());

        assertNotNull(state2);
        assertEquals(state.getName(), state2.getName());
        
    }
	
	/**
	 * Simple test to create a state where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveStateFailTest_NameIsNull() {
        State state = new State();
        state.setName(null);
        
        Country country = new Country();
        country.setName(randomAlphaString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        
        
        state = stateRepository.save(state);
        
    }
	
	/**
	 * Simple test to create a state where Country is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveStateFailTest_CountryIsNull() {
        State state = new State();
        state.setName(randomAlphaString(32));
        
        state.setCountry(null);
        state = stateRepository.save(state);
        
    }

}
