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
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	public ProductDaoImpl(){
		System.out.println("ProductDaoImpl bean is created");
	}
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println("PRoduct ID before persisting"+product.getId());
		session.save(product);
		return product;
	}

	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		return product;
	}

	public void deleteProduct(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		Product product=(Product)session.get(Product.class, id);
		if(product!=null)
		session.delete(product);
	}

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.update(product);
		
	}
	
	public List<Product> getAllProducts() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Product");
		List<Product> products=query.list();
		return products;
	}
	
	public List<Category> getAllCategories()
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Category");
		List<Category> categories=(List<Category>)query.list();
		return categories;
	}
	
	
}
