package com.sunrise.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.sunrise.shop.Repository.ProductRepo;
import com.sunrise.shop.controller.RequestPojo.SearchForm;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunrise.shop.model.Category;
import com.sunrise.shop.model.Products;
import com.sunrise.shop.service.ProductService.ProductServices;

@RestController
@RequestMapping("api/product")
public class ProductController {
	@Autowired
	ProductServices ProductServices;
	@Autowired
	ProductRepo productRepo;
	
	@RequestMapping("getAll")
	public List<Products> getAllPRoducts(){
		return ProductServices.getAllProducts();
	}
	@RequestMapping("getAllCategory")
	public List<Category> getAllCategory(){
		return ProductServices.getAllCategory();
	}
	@RequestMapping("getProductsByCategory")
	public List<Products> getProductsByCategory(@RequestBody HashMap<String,String> request){
		String category_id = request.get("cat_id");		
		return ProductServices.getProductsByCategory(category_id);
	}
	
	
	
	@GetMapping( value = "/getimage/{img_name}",produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
	    InputStream in = getClass().getResourceAsStream("/images/"+img_name);
	    return IOUtils.toByteArray(in);
	}
	@PostMapping("/searchAllColumn")
	public ResponseEntity<?> showEditForm(@RequestBody SearchForm searchString) {
		List<Products> products = productRepo.findAll();
//		products = products.stream().filter(
//				item -> item.getId().toString().contains(searchString.getSearchString())
//						|| item.getName().contains(searchString.getSearchString())
//						|| item.getPrice().contains(searchString.getSearchString())
//						|| item.getCategory_id().contains(searchString.getSearchString())
//		).collect(Collectors.toList());
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
