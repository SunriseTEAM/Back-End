package com.sunrise.shop.controller;

import com.sunrise.shop.Repository.CategoryRepo;
import com.sunrise.shop.model.Category;
import com.sunrise.shop.service.Category.CategoryServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryServicelmpl categoryServicelmpl;

    @Autowired
    CategoryRepo categoryRepo;

    @RequestMapping("getAll")
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            Category returnedCategory = categoryServicelmpl.saveCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getFindById(@PathVariable long id) {
        try {

            Optional<Category> optCategory = categoryServicelmpl.getCategoryById(id);

            if(optCategory.isPresent()) {
                return new ResponseEntity<>(Arrays.asList(optCategory.get()),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category _category,
                                            @PathVariable long id) {
        try {

            Category category = categoryServicelmpl.getCategoryById(id).get();

            //set new values for customer
            category.setName(_category.getName());

            // save the change to database
            categoryServicelmpl.updateCategory(category);

            return new ResponseEntity(new String ("thành công"), HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity(new String ("lỗi"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable long id) {
        try {
            // checking the existed of a Customer with id
            categoryServicelmpl.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

