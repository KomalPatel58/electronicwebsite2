package com.niit.dao;

import java.util.List;

import com.niit.models.Category;
import com.niit.models.Product;

public interface CategoryDao {

	List<Category> getAllCategories();
	Category saveCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(int id);
	Category getCategory(int id);
	//List<Product> getProductsCategoryWise(Category category);
}
