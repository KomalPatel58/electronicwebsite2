package com.niit.project1backend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.configuration.DBConfiguration;
import com.niit.dao.ProductDao;
import com.niit.dao.ProductDaoImpl;
import com.niit.models.Product;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context=new AnnotationConfigApplicationContext(DBConfiguration.class,ProductDaoImpl.class);
        ProductDao productdao=(ProductDao)context.getBean("productDaoImpl");
        Product product=new Product();
        product.setProductname("PEN");
        product.setProductdesc("RED");
        product.setPrice(100);
        product.setQuantity(10);
        productdao.saveProduct(product);
        
        product.setProductname("PENcil");
        product.setProductdesc("REDcolor");
        product.setPrice(1000);
        product.setQuantity(100);
        productdao.saveProduct(product);
        
        product=productdao.getProduct(3);
        System.out.println(product.getProductname());
        System.out.println(product.getProductdesc());
        System.out.println(product.getPrice());
        System.out.println(product.getQuantity());
        
        productdao.deleteProduct(1);
        
    }
}

