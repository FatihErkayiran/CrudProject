package com.cognixia.jump.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.ProductDao;
import com.cognixia.jump.model.Product;

@WebServlet("/")
public class ProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;
	
	public void init() {
		
		productDao = new ProductDao();
	}


	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/list":
			listProducts(request, response);
			break;
		case "/delete":
			deleteProduct(request, response);
			break;
		case "/edit":
			goToEditProductForm(request, response);
			break;
		case "/update":
			updateProduct(request, response);
			break;
		case "/new":
			goToNewProductForm(request, response);
			break;
		case "/add":
			addNewProduct(request, response);
			break;

		default:
			
			response.sendRedirect("/");
			break;
		}
			
	}
	
	private void listProducts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Product> allProducts = productDao.getAllProducts();
		//System.out.println("allProducts = " + allProducts);
		
		request.setAttribute("allProducts", allProducts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		
		dispatcher.forward(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get the id for the product to delete
		int id = Integer.parseInt(request.getParameter("id"));
		
		// use our dao to do our delete
		productDao.deleteProduct(id);
		
		// redirect back to our product list page (should show table without product we just
		// deleted)
		response.sendRedirect("list");
	}
	
	private void goToEditProductForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// get the id of the product we need edit/update
		int id = Integer.parseInt(request.getParameter("id"));
		
		// get the full row info for the product
		Product product = productDao.getProductById(id);
		
		// send product to edit to new page
		request.setAttribute("product", product);
		
		// forward info and redirect to that page
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// grab info to do update for product submitted by form
		int id = Integer.parseInt(request.getParameter("id").trim());
		String item = request.getParameter("item").trim();
		int qty = Integer.parseInt(request.getParameter("qty").trim());
		String description = request.getParameter("description").trim();
		
		// create the product object
		Product product = new Product(id, item, qty, description);
		
		// pass object to update from the dao
		productDao.updateProduct(product);
		
		// redirect to our list products page once we finish updating info on product
		response.sendRedirect("list");
	}
	
	private void goToNewProductForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void addNewProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// grab values to create product from our form
		String item = request.getParameter("item");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String description = request.getParameter("description");
		
		// create object for product
		Product product = new Product(0, item, qty, description);
		
		// call dao to add product to our database
		productDao.addProduct(product);
		
		// once added, redirect to the product list page
		response.sendRedirect("list");
		
	}


}







