package com.menneck.bookish.Service;

import com.menneck.bookish.DTO.ProductDTO;
import com.menneck.bookish.Model.Category;
import com.menneck.bookish.Model.Product;
import com.menneck.bookish.Repository.CategoryRepository;
import com.menneck.bookish.Repository.ProductRepository;
import com.menneck.bookish.Service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        Optional<Product> productOptinal = productRepository.findById(id);
        return productOptinal.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void create(ProductDTO productDTO) {
        productRepository.save(createProduct(productDTO));
    }

    private Product createProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        List<Category> existingCategory = categoryRepository.findAll();
        Optional<Category> categoryOpt = existingCategory.stream().filter(c -> c.getName().equals(productDTO.getCategories().getName())).findFirst();

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            product.getCategories().add(category);
        } else {
            product.getCategories().add(productDTO.getCategories());
        }

        return product;
    }
}
