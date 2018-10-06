package com.niit.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartitemid;
	
	@ManyToOne
	private Product product;
	@ManyToOne
	private User user;
	private int quantity;
	private double totalprice;
	public int getCartitemid() {
		return cartitemid;
	}
	public void setCartitemid(int cartitemid) {
		this.cartitemid = cartitemid;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	

}
