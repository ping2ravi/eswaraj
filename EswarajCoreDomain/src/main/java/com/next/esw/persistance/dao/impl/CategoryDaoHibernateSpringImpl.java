package com.next.esw.persistance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.next.esw.persistance.Category;
import com.next.esw.persistance.dao.CategoryDao;


@Repository
public class CategoryDaoHibernateSpringImpl extends BaseDaoHibernateSpring<Category> implements CategoryDao{


	private static final long serialVersionUID = 1L;

	@Override
	public Category saveCategory(Category category) {
		saveObject(category);
		return category;
	}


	@Override
	public Category getCategoryById(Long id) {
		return (Category)getObjectById(Category.class, id);
	}

	@Override
	public List<Category> getAllCategories() {
		String query = "from Category order by name";
		List<Category> list = executeQueryGetList(query);
		return list;
	}

}
