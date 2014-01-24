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
import com.eswaraj.domain.nodes.division.ParliamentConstituency;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestParliamentConstituencyRepository extends BaseEswarajTest{

	@Autowired ParliamentConstituencyRepository parliamentConstituencyRepository;
	@Autowired StateRepository stateRepository;
	@Autowired CountryRepository countryRepository;
	
	/**
	 * Simple test to create a parliamentConstituency
	 */
	@Test
    public void basicSaveParliamentConstituencyTest() {
        ParliamentConstituency parliamentConstituency = new ParliamentConstituency();
        parliamentConstituency.setName(randomAlphaString(32));
        
        State state = new State();
        state.setName(randomAlphaString(32));
        Country country = new Country();
        country.setName(randomAlphaNumericString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        state = stateRepository.save(state);
        
        parliamentConstituency.setState(state);
        
        
        parliamentConstituency = parliamentConstituencyRepository.save(parliamentConstituency);
        
        ParliamentConstituency parliamentConstituency2 = parliamentConstituencyRepository.getParliamentConstituencyById(parliamentConstituency.getId());

        assertNotNull(parliamentConstituency2);
        assertEquals(parliamentConstituency.getName(), parliamentConstituency2.getName());
        
    }
	
	/**
	 * Simple test to create a parliamentConstituency where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveParliamentConstituencyFailTest_NameIsNull() {
        ParliamentConstituency parliamentConstituency = new ParliamentConstituency();
        parliamentConstituency.setName(null);
        
        State state = new State();
        state.setName(randomAlphaString(32));
        
        Country country = new Country();
        country.setName(randomAlphaNumericString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        state = stateRepository.save(state);
        
        parliamentConstituency.setState(state);
        
        
        parliamentConstituency = parliamentConstituencyRepository.save(parliamentConstituency);
        
    }
	
	/**
	 * Simple test to create a parliamentConstituency where State is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveParliamentConstituencyFailTest_StateIsNull() {
        ParliamentConstituency parliamentConstituency = new ParliamentConstituency();
        parliamentConstituency.setName(randomAlphaString(32));
        
        parliamentConstituency.setState(null);
        parliamentConstituency = parliamentConstituencyRepository.save(parliamentConstituency);
        
    }

}
