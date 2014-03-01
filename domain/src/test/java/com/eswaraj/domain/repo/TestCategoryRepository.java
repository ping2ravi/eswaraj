package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.validator.exception.ValidationException;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCategoryRepository extends BaseEswarajTest{

	@Autowired CategoryRepository categoryRepository;
	
	/**
	 * Simple test to create a category
	 */
	@Test
    public void basicSaveCategoryTest() {
        Category category = new Category();
        category.setName(randomAlphaString(32));
        category.setDescription(randomAlphaString(64));
        
        category = categoryRepository.save(category);
        
        Category cat2 = categoryRepository.getById(category.getId());

        assertNotNull(cat2);
        assertEquals(category.getName(), cat2.getName());
        assertEquals(category.getDescription(), cat2.getDescription());
    }
	
	/**
	 * Simple test to create a category where Name is null
	 */
	@Test(expected=ValidationException.class)
    public void basicSaveCategoryFailTest_NameIsNull() {
        Category category = new Category();
        category.setName(null);
        category.setDescription(randomAlphaString(64));
        
        category = categoryRepository.save(category);
        
    }
	
	@Test
	public void testParentCategory() {
		Category category = new Category();
        category.setName(randomAlphaNumericString(32));
        category.setDescription(randomAlphaString(64));
        category.setDescription(randomAlphaString(64));
        
        Category parent = new Category();
        parent.setName(randomAlphaNumericString(32));
        parent.setDescription(randomAlphaString(64));
        parent = categoryRepository.save(parent);
        
        category.setParentCategory(parent);
        category = categoryRepository.save(category);
 		
        Category expected = categoryRepository.getById(category.getId());
        Category parentExpected = categoryRepository.getById(expected.getParentCategory().getId());
        
        
        assertNotNull(expected);
        assertNotNull(expected.getParentCategory());
        assertEquals(parentExpected.getName(), parent.getName());
        assertEquals(parentExpected.getDescription(), parent.getDescription());
	}
}
