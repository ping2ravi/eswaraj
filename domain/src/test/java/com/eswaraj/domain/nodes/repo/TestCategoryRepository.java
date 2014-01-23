package com.eswaraj.domain.nodes.repo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCategoryRepository extends BaseEswarajTest{

	@Autowired CategoryRepository categoryRepository;
	@Autowired DepartmentRepository departmentRepository;
	
	/**
	 * Simple test to create a category
	 */
	@Test
    public void basicSaveCategoryTest() {
        Category category = new Category();
        category.setName(randomAlphaString(32));
        category.setDescription(randomAlphaString(64));
        
        Department department = new Department();
        department.setName(randomAlphaString(32));
        department.setDescription(randomAlphaString(64));
        department = departmentRepository.save(department);
        
        category.setDepartment(department);
        
        
        category = categoryRepository.save(category);
        
        Category cat2 = categoryRepository.getCategoryById(category.getId());

        assertNotNull(cat2);
        
        Department department2 = departmentRepository.getDepartmentById(category.getDepartment().getId());
        assertNotNull(department2);
        
    }
	
	/**
	 * Simple test to create a category where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveCategoryFailTest_NameIsNull() {
        Category category = new Category();
        category.setName(null);
        category.setDescription(randomAlphaString(64));
        
        Department department = new Department();
        department.setName(randomAlphaString(32));
        department.setDescription(randomAlphaString(64));
        department = departmentRepository.save(department);
        
        category.setDepartment(department);
        
        
        category = categoryRepository.save(category);
        
    }
	
	/**
	 * Simple test to create a category where Department is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveCategoryFailTest_DepartmentIsNull() {
        Category category = new Category();
        category.setName(randomAlphaString(32));
        category.setDescription(randomAlphaString(64));
        
        category.setDepartment(null);
        category = categoryRepository.save(category);
        
    }

}
