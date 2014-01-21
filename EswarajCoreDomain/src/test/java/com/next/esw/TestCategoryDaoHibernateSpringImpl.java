package com.next.esw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
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

	@Autowired Neo4jTemplate template;
	
	@Autowired CategoryDao categoryDao;

	
	@Test
    // overrides the class-level defaultRollback setting
    @Rollback(false)
    public void modifyDatabaseWithinTransaction() {
        System.out.println("Test");
        Category category = new Category();
        category.setName("Test1");
        category.setDescription("This a test Description");
        
        Category parentCategory = categoryDao.getCategoryById(1L);
        System.out.println("parentCategory = "+parentCategory);
        category.setParentCategory(parentCategory);
        
        category = categoryDao.save(category);
        
        
        System.out.println("Cat = "+category.getId());
        //GraphRepository<Category> categoryRepository = template.repositoryFor(Category.class);
        Category cat2 = categoryDao.getCategoryById(category.getId());
        if(cat2 == null){
        	System.out.println("Cat2 is null");
        }else{
        	System.out.println("Cat = "+cat2.getId()+","+cat2.getName());	
        }
        
    }

}
