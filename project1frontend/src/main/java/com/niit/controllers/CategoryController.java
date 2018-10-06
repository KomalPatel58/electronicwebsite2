package com.niit.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.niit.dao.CategoryDao;
import com.niit.models.Category;
import com.niit.models.Product;


@Controller
@Transactional(propagation=Propagation.REQUIRED)
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;

	public CategoryController() {
		System.out.println("Category Controller Bean is created");
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/all/getallcategories")
	public String getAllCategories(Model model)
	{
		List<Category> categories=categoryDao.getAllCategories();
		model.addAttribute("categoriesList", categories);
		return "listofcategories";
	}
	
	@RequestMapping(value="/admin/categoryform")
	public String getCategoryForm(Model model)
	{
		Category category=new Category();
		model.addAttribute("category",category);
		
		return "addcategory";
	}
	
	@RequestMapping(value="/admin/addcategory")
	public String addCategory(@ModelAttribute Category category,BindingResult result,HttpServletRequest request)
	{
		if(result.hasErrors()){//if it is true, result has errors

			
			return "addcategory";
		}
		categoryDao.saveCategory(category);
		MultipartFile img=category.getImage();
		System.out.println(request.getServletContext().getRealPath("/"));
		//Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+product.getId()+".png");
		//Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/"+id+".png");
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/c"+category.getId()+".png");
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
		return "redirect:/all/getallcategories";
	}
	
	@RequestMapping(value="/admin/getupdatecategoryform/{id}")
	public String getUpdateProductForm(@PathVariable int id,Model model)
	{
		Category category=categoryDao.getCategory(id);
		model.addAttribute("category", category);
		
		return "updatecategory";
	}
	
	@RequestMapping(value="/admin/updatecategory")
	public String updateProduct(@ModelAttribute Category category,BindingResult result,Model model,HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			model.addAttribute("categories", categoryDao.getAllCategories());
			return "updateproductform";
		}
		categoryDao.updateCategory(category);
		MultipartFile img=category.getImage();
		System.out.println(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/c"+category.getId()+".png");
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/c"+category.getId()+".png");
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
		return "redirect:/all/getallcategories";
	}
	
	@RequestMapping(value="/all/getcategory/{id}")
	public String getProduct(@PathVariable int id,Model model)
	{
		Category category=categoryDao.getCategory(id);
		model.addAttribute("category",category);
		List<Product> products=category.getProducts();
		//List<Product> products=categoryDao.getProductsCategoryWise(category);
		model.addAttribute("products", products);
		return "viewcategory";
	}
	
	@RequestMapping(value="admin/deletecategory/{id}")
	public String deleteProduct(@PathVariable int id,HttpServletRequest request)
	{
		categoryDao.deleteCategory(id);
		Path path=Paths.get(request.getServletContext().getRealPath("/")+"/WEB-INF/resources/images/c"+id+".png");
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
		return "redirect:/all/getallcategories";
	}
	
	
}
