package com.sunrise.shop.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunrise.shop.model.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {
	@Override
	Page<Products> findAll(Pageable pageable);

	@Query("Select pro FROM Products pro WHERE pro.category_id=:cat_id")
	List<Products> getByCategoryId(@Param("cat_id")String cat_id);
}
