package com.sunrise.shop.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.sunrise.shop.Repository.ProductRepo;
import com.sunrise.shop.controller.RequestPojo.SearchForm;
import com.sunrise.shop.service.FilesStorage.FilesStorageServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunrise.shop.model.Category;
import com.sunrise.shop.model.Products;
import com.sunrise.shop.service.ProductService.ProductServiceslmpl;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductServiceslmpl productServiceslmpl;
    @Autowired
    ProductRepo productRepo;

    @RequestMapping("getAll")
    public List<Products> getAllPRoducts() {
        return productServiceslmpl.getAllProducts();
    }

    @RequestMapping("getAllCategory")
    public List<Category> getAllCategory() {
        return productServiceslmpl.getAllCategory();
    }

    @RequestMapping("getProductsByCategory")
    public List<Products> getProductsByCategory(@RequestBody HashMap<String, String> request) {
        String category_id = request.get("cat_id");
        return productServiceslmpl.getProductsByCategory(category_id);
    }

    @GetMapping(value = "/getimage/{img_name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
        File file = new File("src/main/resources/images/" + img_name);
//        System.out.println(file.toPath().toAbsolutePath().toString());
        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte[] data = bos.toByteArray();
        return data;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> addProducts(@RequestBody Products product) {
        try {
            product.setImages(FilesStorageServiceImpl.counter);
            Products returnedCategory = productServiceslmpl.saveProduct(product);
            return new ResponseEntity<>(Arrays.asList(returnedCategory, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new String("lỗi" + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getFindById(@PathVariable long id) {
        try {

            Optional<Products> optProduct = productServiceslmpl.getProductById(id);

            if (optProduct.isPresent()) {
                return new ResponseEntity<>(Arrays.asList(optProduct.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Products _product, @PathVariable long id) {
        try {

            Products product = productServiceslmpl.getProductById(id).get();

            //set new values for products
            product.setName(_product.getName());
            product.setPrice(_product.getPrice());
            product.setImages(_product.getImages());
            product.setDescription(_product.getDescription());
            product.setQuatityavi(_product.getQuatityavi());
            product.setAdded_on(_product.getAdded_on());
            product.setCategory_id(_product.getCategory_id());
            product.setRatings(_product.getRatings());
            product.setFavourite(_product.getFavourite());
            product.setSeller(_product.getSeller());

            // save the change to database
            productServiceslmpl.updateProduct(product);

            return new ResponseEntity<>(Arrays.asList(product, ""), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new String("lỗi"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable long id) {
        try {
            // checking the existed of a Customer with id
            productServiceslmpl.deleteProductById(id);
            return new ResponseEntity(new String(" Xóa thành công"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
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
