package com.ecommerce.shubkart.repositories;

import com.ecommerce.shubkart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product p);

    @Override
    void delete(Product p);

    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    //Search by subcategory name

    List<Product> findAllByCategory_Subcategory_nameEquals(String name);
}
