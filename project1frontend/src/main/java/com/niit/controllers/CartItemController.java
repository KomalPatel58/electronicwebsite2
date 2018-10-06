
package com.niit.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartItemDao;
import com.niit.dao.ProductDao;
import com.niit.models.CartItem;
import com.niit.models.Customer;
import com.niit.models.CustomerOrder;
import com.niit.models.Product;
import com.niit.models.ShippingAddress;
import com.niit.models.User;



@Controller
public class CartItemController {
	
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value="/cart/addtocart/{productID}")
	public String addToCart(@PathVariable int productID,@AuthenticationPrincipal Principal principal,HttpSession session,@RequestParam int requestedQuantity)
	{
		if(principal==null)
			return"login";
		String email=principal.getName();
		User user=(User)cartItemDao.getUser(email);
		Product product=productDao.getProduct(productID);
		List<CartItem> cartItems=cartItemDao.getCart(email);
		for(CartItem cartitem:cartItems)
		{
			if(cartitem.getProduct().getId()==productID)
			{
				int quantity=cartitem.getQuantity();	
				cartitem.setQuantity(quantity+requestedQuantity);
				cartitem.setTotalprice(cartitem.getTotalprice()+(requestedQuantity*product.getPrice()));
				cartItemDao.addToCart(cartitem);
				return"redirect:/cart/getcart";
			}
		}
		CartItem cartitem=new CartItem();
		cartitem.setQuantity(requestedQuantity);
		cartitem.setProduct(product);
		cartitem.setUser(user);
		double totalprice=requestedQuantity*product.getPrice();
		cartitem.setTotalprice(totalprice);
		cartItemDao.addToCart(cartitem);
		cartItems=cartItemDao.getCart(email);
		session.setAttribute("cartSize",cartItems.size());
		return"redirect:/cart/getcart";
	}
	
	@RequestMapping(value="/cart/getcart")
	public String getcart(@AuthenticationPrincipal Principal principal,Model model)
	{
		if(principal==null)
			return"login";
		String email=principal.getName();
		User user=(User)cartItemDao.getUser(email);
		List<CartItem> cartitems=cartItemDao.getCart(email);
		model.addAttribute("cartitems",cartitems);
		return"cart";
	}
	
	@RequestMapping(value="/cart/removecartitem/{cartitemId}")
	public String removeCartItem(@PathVariable int cartitemId,@AuthenticationPrincipal Principal principal,HttpSession session)
	{
			cartItemDao.removeCartItem(cartitemId);
			String email=principal.getName();
			List<CartItem> cartItems=cartItemDao.getCart(email);
			session.setAttribute("cartSize",cartItems.size());
			return"redirect:/cart/getcart";
	}
	
	@RequestMapping(value="/cart/clearcart")
	public String clearCart(@AuthenticationPrincipal Principal principal,HttpSession session)
	{
		List<CartItem> cartitems=cartItemDao.getCart(principal.getName());
		for(CartItem cartitem:cartitems)
		{
			cartItemDao.removeCartItem(cartitem.getCartitemid());
		}
		String email=principal.getName();
		List<CartItem> cartItems=cartItemDao.getCart(email);
		session.setAttribute("cartSize",cartItems.size());
		return "redirect:/cart/getcart";
	}
	
	@RequestMapping(value="/cart/shippingaddressform")
	public String getShippingForm(@AuthenticationPrincipal Principal principal,Model model)
	{
		if(principal==null)
			return"login";
		String email=principal.getName();
		User user=cartItemDao.getUser(email);
		Customer customer=user.getCustomer();
		ShippingAddress shippingaddress=customer.getShippingaddress();
		model.addAttribute("shippingaddress",shippingaddress);
		
		return"shippingaddress";
	}
	
	@RequestMapping(value="/cart/createorder")
	public String createCustomerOrder(@ModelAttribute ShippingAddress shippingaddress,
    		Model model,
    		@AuthenticationPrincipal Principal principal,HttpSession session){
		
		String email=principal.getName();
		User user=cartItemDao.getUser(email);
		Customer customer=user.getCustomer();
		customer.setShippingaddress(shippingaddress);
		customer.setUser(user);
		user.setCustomer(customer);
		
		List<CartItem> cartitems=cartItemDao.getCart(email);
		for(CartItem cartitem:cartitems)
		{
			Product product=cartitem.getProduct();
			if((product.getQuantity()-cartitem.getQuantity())<0)
			{
				cartItemDao.removeCartItem(cartitem.getCartitemid());
				model.addAttribute("productNA",product);
				return "productnotavailable";
			}
		}
		
		double grandTotal = 0;
		for(CartItem cartitem:cartitems)
		{
			grandTotal=grandTotal+cartitem.getTotalprice();
		}
		
		CustomerOrder customerorder=new CustomerOrder();
		customerorder.setPurchaseDate(new Date());
		customerorder.setGrandTotal(grandTotal);
		customerorder.setUser(user);
		if(cartitems.size()>0)
		{
			customerorder=cartItemDao.createCustomerOrder(customerorder);
		}
			
		for(CartItem cartitem:cartitems)
		{
			Product product=cartitem.getProduct();
			product.setQuantity(product.getQuantity()-cartitem.getQuantity());
			productDao.updateProduct(product);
			cartItemDao.removeCartItem(cartitem.getCartitemid());
			
		}
		
		model.addAttribute("customerorder",customerorder);
		model.addAttribute("cartitems", cartitems);
		session.setAttribute("cartsize", 0);
		return "orderdetails";
	}
	
	

}
