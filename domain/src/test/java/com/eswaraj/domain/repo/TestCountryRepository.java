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
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCountryRepository extends BaseEswarajTest{

	@Autowired CountryRepository countryRepository;
	@Autowired DepartmentRepository departmentRepository;
	
	/**
	 * Simple test to create a country
	 */
	@Test
    public void basicSaveCountryTest() {
        Country country = new Country();
        country.setName(randomAlphaString(32));
        
        country = countryRepository.save(country);
        
        Country country2 = countryRepository.getCountryById(country.getId());

        assertNotNull(country2);
        assertEquals(country.getName(), country2.getName());
        
    }
	
	/**
	 * Simple test to create a country where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveCountryFailTest_NameIsNull() {
        Country country = new Country();
        country.setName(null);
        country = countryRepository.save(country);
        
    }
	
	

}
