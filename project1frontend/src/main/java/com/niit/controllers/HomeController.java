package com.niit.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartItemDao;
import com.niit.dao.CustomerDao;
import com.niit.dao.ProductDao;
import com.niit.models.CartItem;


@Controller
public class HomeController {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private CustomerDao customerDao;
	public HomeController()
	{
		System.out.println("HomeController Beans is instantiated");
	}
	@RequestMapping(value= {"/","/home"})
	public String homePage(HttpSession session,Model model,@AuthenticationPrincipal Principal principal)
	{
		model.addAttribute("productlist",productDao.getAllProducts());
		//model.addAttribute("productlist", customerDao.getRecords());
		session.setAttribute("categories",productDao.getAllCategories());
		if(principal!=null)
		{
			String email=principal.getName();
			List<CartItem> cartItems=cartItemDao.getCart(email);
			session.setAttribute("cartSize",cartItems.size());
		}
		
		return "home";
		
	}
	
	@RequestMapping(value="/login")
	public String login()
	{
		return"login";
	}
	
	@RequestMapping(value="/loginerror")
	public String loginFailed(Model model)
	{
		model.addAttribute("error","Invalid Credantials...");
		return"login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model)
	{
		model.addAttribute("msg","Loggedout Successfully...");
		return "login";
	}
	@RequestMapping(value="/all/searchByCategory")
	public String searchByCategory(@RequestParam String searchCondition,Model model)
	{
		if(searchCondition.equalsIgnoreCase("All"))
		{
			model.addAttribute("searchCondition","");
		}
		else
		{
				model.addAttribute("searchCondition",searchCondition);
		}
		model.addAttribute("productsList",productDao.getAllProducts());

		return "listofproducts";
	}
	
	@RequestMapping(value="/aboutus")
	public String aboutusPage()
	{
		return "aboutus";
	}
	
	
}
