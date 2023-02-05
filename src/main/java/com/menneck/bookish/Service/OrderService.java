package com.menneck.bookish.Service;

import com.menneck.bookish.DTO.OrderDTO;
import com.menneck.bookish.Model.Category;
import com.menneck.bookish.Model.Order;
import com.menneck.bookish.Model.Product;
import com.menneck.bookish.Repository.CategoryRepository;
import com.menneck.bookish.Repository.OrderRepository;
import com.menneck.bookish.Service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void create(OrderDTO orderDTO) {
        orderRepository.save(createOrder(orderDTO));
    }

    private Order createOrder(OrderDTO orderDTO) {

        Order order = new Order();
        order.setPrice(orderDTO.getPrice());
        order.setSellers(orderDTO.getSeller());

        List<Category> existingCategory = categoryRepository.findAll();
        List<Product> productList = orderDTO.getProductList();
        List<Category> categoriesInProductList = productList.stream().iterator().next().getCategories();

        for (Product product : orderDTO.getProductList()) {
            for (int i = 0; i < categoriesInProductList.size(); i++) {
                System.out.println(categoriesInProductList.size());
                Category existentCategory = categoriesInProductList.get(0);
                String categoryName = categoriesInProductList.stream().iterator().next().getName();
                Optional<Category> categoryOpt = existingCategory.stream().filter(c -> c.getName().equals(categoryName)).findFirst();

                if (categoryOpt.isPresent()) {
                    Category categoryOptGet = categoryOpt.get();
                    product.getCategories().remove(existentCategory);
                    product.getCategories().add(categoryOptGet);
                }
            }
            order.getProductList().add(product);
        }

        return order;

    }

}
