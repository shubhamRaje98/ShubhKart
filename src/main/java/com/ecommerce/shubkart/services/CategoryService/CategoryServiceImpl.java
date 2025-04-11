package com.ecommerce.shubkart.services.CategoryService;

import com.ecommerce.shubkart.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void incrementProductCount(Long categoryId) {
        this.categoryRepository.increaseTheCount(categoryId);
    }
}
//18605001066