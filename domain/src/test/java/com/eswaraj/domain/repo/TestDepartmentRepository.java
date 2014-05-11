package com.eswaraj.domain.repo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore
public class TestDepartmentRepository extends BaseNeo4jEswarajTest{

	@Autowired DepartmentRepository departmentRepository;
	
	/**
	 * Simple test to create a department
	 */
	@Test
    public void basicSaveDepartmentTest() {
        Department department = new Department();
        department.setName(randomAlphaString(32));
        department.setDescription(randomAlphaString(64));
        
        department = departmentRepository.save(department);
        
        
    }
	
	/**
	 * Simple test to create a department where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveDepartmentFailTest_NameIsNull() {
        Department department = new Department();
        department.setName(null);
        department.setDescription(randomAlphaString(64));
        
        department = departmentRepository.save(department);
    }
	
}
