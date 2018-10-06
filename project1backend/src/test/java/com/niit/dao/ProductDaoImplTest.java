package com.niit.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.configuration.DBConfiguration;
import com.niit.models.Product;

import junit.framework.TestCase;

public class ProductDaoImplTest extends TestCase {

	ApplicationContext context=new AnnotationConfigApplicationContext(DBConfiguration.class,ProductDaoImpl.class); 
	
	ProductDao productDao=(ProductDao)context.getBean("productDaoImpl");
	
	
	public void testSaveProduct() {
		//fail("Not yet implemented");
		Product product=new Product();    
		product.setProductname("LP");
		product.setProductdesc("HP");
		product.setPrice(50000);
		product.setQuantity(2);
		product=productDao.saveProduct(product);
		assertTrue(product.getId()>0);
	}

	public void testGetProduct() {
		Product product1=productDao.getProduct(33);
		assertNull(product1);
		Product product2=productDao.getProduct(37);
		assertNull(product2);
	}

	public void testDeleteProduct() {
		
	}

	public void testUpdateProduct() {
		 Product product=productDao.getProduct(130);
		 product.setProductname("PENCIL");
		 productDao.updateProduct(product);
		 assertTrue(product.getProductname()=="PENCIL");
	}

}
