package com.next.esw.persistance.dao;

import java.util.List;

import com.next.esw.persistance.Category;

public interface CategoryDao {

	public abstract Category saveCategory(Category category);

	public abstract Category getCategoryById(Long id);

	public abstract List<Category> getAllCategories();

}