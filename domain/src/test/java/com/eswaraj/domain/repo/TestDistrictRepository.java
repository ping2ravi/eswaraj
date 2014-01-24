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
import com.eswaraj.domain.nodes.division.District;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDistrictRepository extends BaseEswarajTest{

	@Autowired DistrictRepository districtRepository;
	@Autowired StateRepository stateRepository;
	@Autowired CountryRepository countryRepository;
	
	/**
	 * Simple test to create a district
	 */
	@Test
    public void basicSaveDistrictTest() {
        District district = new District();
        district.setName(randomAlphaString(32));
        
        State state = new State();
        state.setName(randomAlphaString(32));
        Country country = new Country();
        country.setName(randomAlphaNumericString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        state = stateRepository.save(state);
        
        district.setState(state);
        
        
        district = districtRepository.save(district);
        
        District district2 = districtRepository.getDistrictById(district.getId());

        assertNotNull(district2);
        assertEquals(district.getName(), district2.getName());
        
    }
	
	/**
	 * Simple test to create a district where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveDistrictFailTest_NameIsNull() {
        District district = new District();
        district.setName(null);
        
        State state = new State();
        state.setName(randomAlphaString(32));
        
        Country country = new Country();
        country.setName(randomAlphaNumericString(32));
        country = countryRepository.save(country);
        
        state.setCountry(country);
        state = stateRepository.save(state);
        
        district.setState(state);
        
        
        district = districtRepository.save(district);
        
    }
	
	/**
	 * Simple test to create a district where State is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveDistrictFailTest_StateIsNull() {
        District district = new District();
        district.setName(randomAlphaString(32));
        
        district.setState(null);
        district = districtRepository.save(district);
        
    }

}
