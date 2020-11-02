package com.sunrise.shop.service.Category;

import com.sunrise.shop.model.Category;

public interface CategoryService {
    Category findById(long categoryId) throws Exception;
    Category getCategoryDetailById(long categoryId) throws Exception;
}
