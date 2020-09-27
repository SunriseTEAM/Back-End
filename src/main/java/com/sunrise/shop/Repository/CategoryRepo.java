package com.sunrise.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunrise.shop.model.Category;

public interface CategoryRepo  extends JpaRepository<Category, Long> {

}
