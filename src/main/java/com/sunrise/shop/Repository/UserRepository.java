package com.sunrise.shop.Repository;

import java.util.Optional;

import com.sunrise.shop.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunrise.shop.model.User;
@Repository
//UserRepository kế thừa JpaRepository để truy xuất thông tin từ database
public interface UserRepository extends JpaRepository<User, Long> {
	@Override
	Page<User> findAll(Pageable pageable);

	Optional<User> findByMobile(String mobile);
}
