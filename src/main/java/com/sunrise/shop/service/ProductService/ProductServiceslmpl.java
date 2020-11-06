package com.sunrise.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunrise.shop.Repository.CategoryRepo;
import com.sunrise.shop.Repository.ProductRepo;
import com.sunrise.shop.model.Category;
import com.sunrise.shop.model.Products;

@Service
public class ProductServiceslmpl {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	CategoryRepo cateRepo;
	
	public List<Products>getAllProducts(){
		return productRepo.findAll();
	}
	public List<Products>getProductsByCategory(String product_id){
		return productRepo.getByCategoryId(product_id);
	}
	
	public List<Category>getAllCategory(){
		return cateRepo.findAll();
	}

	public Products saveProduct(Products product){
		return productRepo.save(product);
	}

	public Optional<Products> getProductById(long id) {
		return productRepo.findById(id);
	}

	public Products updateProduct(Products product) {
		return productRepo.save(product);
	}

	public void deleteProductById(long id) {
		productRepo.deleteById(id);
	}
}
