package com.niit.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.niit.dao.ProductDao;
import com.niit.models.Product;
@Controller
public class ProductController {
	
	@Autowired
	private ProductDao productDao;

	public ProductController() {
		System.out.println("ProductController Bean is Created");
	}
	
	@RequestMapping(value="/all/getallproducts")
	public String getAllProducts(Model model)
	{
		List<Product> products=productDao.getAllProducts();
		model.addAttribute("productsList", products);
		return "listofproducts";
	}
	
	@RequestMapping(value="/admin/getproductform")
	public String getProductForm(Model model)
	{
		Product p=new Product();
		model.addAttribute("product",p);
		model.addAttribute("categories",productDao.getAllCategories());
		return "productform";
	}
	
	@RequestMapping(value="/admin/addproduct")
	public String addProduct(@Valid @ModelAttribute Product product,BindingResult result,Model model,HttpServletRequest request)
	{
		if(result.hasErrors()){//if it is true, result has errors

			model.addAttribute("categories",productDao.getAllCategories());
			return "productform";
		}
		productDao.saveProduct(product);
		MultipartFile img=product.getImage();
		System.out.println(request.getServletContext().getRealPath("/"));
		//Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+product.getId()+".png");
		//Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+id+".png");
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+product.getId()+".png");
		if(!img.isEmpty()&&img!=null)
		{
			//System.out.println("hhh");
			try
			{
				img.transferTo(new File(path.toString()));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	//	System.out.println(image);
		return "redirect:/all/getallproducts";
	}
	
	@RequestMapping(value="admin/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id,HttpServletRequest request)
	{
		productDao.deleteProduct(id);
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+id+".png");
		if(Files.exists(path))
		{
			try
			{
				Files.delete(path);
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/all/getallproducts";
	}
	
	@RequestMapping(value="/admin/getupdateproductform/{id}")
	public String getUpdateProductForm(@PathVariable int id,Model model)
	{
		Product product=productDao.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("categories", productDao.getAllCategories());
		return "updateproductform";
	}
	
	@RequestMapping(value="/admin/updateproduct")
	public String updateProduct(@ModelAttribute Product product,BindingResult result,Model model,HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			model.addAttribute("categories", productDao.getAllCategories());
			return "updateproductform";
		}
		productDao.updateProduct(product);
		MultipartFile img=product.getImage();
		System.out.println(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+product.getId()+".png");
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+product.getId()+".png");
		if(!img.isEmpty()&&img!=null)
		{
			try
			{
				img.transferTo(new File(path.toString()));
			}
			catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/all/getallproducts";
	}
	
	@RequestMapping(value="/all/getproduct/{id}")
	public String getProduct(@PathVariable int id,Model model)
	{
		Product product=productDao.getProduct(id);
		model.addAttribute("productobj",product);
		return "viewproduct";
	}
	
	
}
