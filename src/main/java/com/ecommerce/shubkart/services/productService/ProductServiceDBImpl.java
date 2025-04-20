package com.ecommerce.shubkart.services.productService;

import com.ecommerce.shubkart.exceptions.ProductNotFoundException;
import com.ecommerce.shubkart.models.Category;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.repositories.CategoryRepository;
import com.ecommerce.shubkart.repositories.ProductRepository;
import com.ecommerce.shubkart.services.RedisService.RedisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService{
    private ProductRepository productRepo;
    private CategoryRepository categoryRepository;

    private RedisService redisService;

    public ProductServiceDBImpl(ProductRepository productRepo, CategoryRepository categoryRepository, RedisService redisService){
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
        this.redisService = redisService;
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
        Product product = redisService.get("productID_"+id, Product.class);
        if(product!=null){
            return product;
        }else{
            Product product1 = productRepo.findById(id).get();
            if(product1!=null){
                redisService.set("productID_"+id, product1, 5l);
            }
            return product1;
        }
    }
}
