package com.menneck.bookish.Service;

import com.menneck.bookish.DTO.ProductDTO;
import com.menneck.bookish.Model.Product;
import com.menneck.bookish.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
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
        product.setCategories(productDTO.getCategories());

        return product;

    }
}
