package com.ecommerce.shubkart.services.productService;

import com.ecommerce.shubkart.exceptions.ProductNotFoundException;
import com.ecommerce.shubkart.models.Category;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.repositories.CategoryRepository;
import com.ecommerce.shubkart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService{
    ProductRepository productRepo;
    CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepo, CategoryRepository categoryRepository){
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryInProd = categoryRepository.findByName(product.getCategory().getName());
        if(categoryInProd.isEmpty()){
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            categoryRepository.save(newCategory);
            product.setCategory(newCategory);
        }else{
            product.setCategory(categoryInProd.get());
        }

        return productRepo.save(product);
    }

    @Override
    public boolean updateProduct(long id, Product product) throws ProductNotFoundException {
        Optional<Product> productToUpdate =  productRepo.findById(id);
        if(productToUpdate.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId());
        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        updatedProduct.setCategory(categoryRepository.findByName(product.getCategory().getName()).get());

        productRepo.save(updatedProduct);
        return true;
    }

    @Override
    public boolean deleteProduct(long id) {
        try{
            productRepo.deleteById(id);
        }catch (Exception e){
            System.out.println("Exception while deleting : "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getSingleProduct(Long id){
        return productRepo.findById(id).get();
    }
}
