package com.ecommerce.shubkart.repositories;

import com.ecommerce.shubkart.models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Category save(Category category);

    Optional<Category> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Category c set c.countOfProducts = c.countOfProducts + 1 where c.id = :categoryId")
    void increaseTheCount(@Param("categoryId") Long categoryId);
}
