package com.next.esw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.next.esw.persistance.Category;
import com.next.esw.persistance.dao.CategoryDao;

@ContextConfiguration(locations = { "classpath:eswaraj-core.xml","classpath:eswaraj-core-local-inmemory-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCategoryDaoHibernateSpringImpl {

	@Autowired
	private CategoryDao categoryDao;
	
	@Test
    // overrides the class-level defaultRollback setting
    @Rollback(false)
    public void modifyDatabaseWithinTransaction() {
        System.out.println("Test");
        Category category = new Category();
        category.setName("Test1");
        category.setDescription("This a test Description");
        category = categoryDao.saveCategory(category);
        System.out.println("Id = "+category.getId());
    }

}
