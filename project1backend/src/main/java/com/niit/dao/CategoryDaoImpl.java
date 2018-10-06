package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.models.Category;
import com.niit.models.Product;
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	public CategoryDaoImpl() {
		//super();
		System.out.println("Category");
		// TODO Auto-generated constructor stub
	}

	public List<Category> getAllCategories()
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Category");
		List<Category> categories=query.list();
		return categories;
	}

	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(category);
		return category;
	}

	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Session session=sessionFactory.getCurrentSession();
				session.update(category);
	}

	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Session session=sessionFactory.getCurrentSession();
				Category category=(Category)session.get(Category.class,id);
				session.delete(category);
	}

	public Category getCategory(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Category category=(Category)session.get(Category.class,id);
		return category;
	}

	/*public List<Product> getProductsCategoryWise(Category category) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from category where ");
		List<Product> products=query.list();
		return products;
	}
	*/
	
}
