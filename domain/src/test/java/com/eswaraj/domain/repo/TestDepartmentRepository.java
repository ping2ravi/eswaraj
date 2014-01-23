package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.repo.DepartmentRepository;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDepartmentRepository extends BaseEswarajTest{

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
        
        Department department2 = departmentRepository.getDepartmentById(department.getId());
        assertNotNull(department2);
        assertEquals(department.getName(), department2.getName());
        assertEquals(department.getDescription(), department2.getDescription());
        
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
