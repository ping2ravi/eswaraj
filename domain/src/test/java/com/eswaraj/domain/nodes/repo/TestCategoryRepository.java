package com.eswaraj.domain.nodes.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.mapping.MappingPolicy;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.nodes.Department;

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCategoryRepository {

	@Autowired CategoryRepository categoryRepository;
	@Autowired DepartmentRepository departmentRepository;
	@Autowired Neo4jTemplate neo4jTemplate;
	
	@Test
    public void basicSaveCategoryTest() {
		MappingPolicy mappingPolicy = neo4jTemplate.getMappingPolicy(Department.class);
		System.out.println("mappingPolicy="+mappingPolicy);
		MappingPolicy categoryMappingPolicy = neo4jTemplate.getMappingPolicy(Category.class);
		System.out.println("categoryMappingPolicy="+categoryMappingPolicy);
        Category category = new Category();
        category.setName("Test1");
        category.setDescription("This a test Description");
        
        Department department = new Department();
        department.setName("Dept-1");
        department.setDescription("Dept-1 Description");
        department = departmentRepository.save(department);
        System.out.println("department = "+department);
        
        category.setDepartment(department);
        
        
        category = categoryRepository.save(category);
        
        System.out.println("Cat = "+category.getId()+","+category.getName()+","+category.getDepartment());
        
        System.out.println("Cat = "+category.getId());
        Category cat2 = categoryRepository.getCategoryById(category.getId());
        if(cat2 == null){
        	System.out.println("Cat2 is null");
        }else{
        	System.out.println("Cat = "+cat2.getId()+","+cat2.getName()+","+cat2.getDepartment());	
        }
        
        
        Department department2 = departmentRepository.getDepartmentById(category.getDepartment().getId());
        System.out.println("department2="+department2);
        
        
        Category cat3 = neo4jTemplate.findOne(category.getId(), Category.class);
        System.out.println("cat3 = "+cat3.getId()+","+cat3.getName()+","+cat3.getDepartment());
    }

}
