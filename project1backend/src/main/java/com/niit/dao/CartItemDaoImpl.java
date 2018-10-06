package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.models.CartItem;
import com.niit.models.CustomerOrder;
import com.niit.models.User;

@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}

	public void removeCartItem(int cartItemId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		CartItem cartitem=(CartItem)session.get(CartItem.class, cartItemId);
		session.delete(cartitem);

	}

	public void addToCart(CartItem cartItem) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
	}

	public List<CartItem> getCart(String email) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from CartItem where user.email=?");
		query.setString(0, email);
		List<CartItem> cartitems=query.list();
		return cartitems;
	}
	
	public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
		Session session=sessionFactory.getCurrentSession();
		session.save(customerOrder);
		//customerOrder.user -> user obj
		//user -> customer -> updated shipping address
		return customerOrder;
	}


}
