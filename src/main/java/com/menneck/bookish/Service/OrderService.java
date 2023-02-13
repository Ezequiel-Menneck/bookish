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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        for (Product product : orderDTO.getProductList()) {
            List<Category> categoriesToBeRemoved = new ArrayList<>();
            List<Category> categoriesToBeAdded = new ArrayList<>();

            for (Category category : product.getCategories()) {
                Optional<Category> optionalCategory = existingCategory.stream().filter(c -> Objects.equals(c.getName(), category.getName())).findFirst();

                if (optionalCategory.isPresent()) {
                    Category isPresentCategory = optionalCategory.get();
                    categoriesToBeAdded.add(isPresentCategory);
                    Optional<Category> optionalCategoryInProduct = product.getCategories().stream().filter(c -> c.getName().equals(isPresentCategory.getName())).findFirst();
                    if (optionalCategoryInProduct.isPresent()) {
                        Category catToBeRemoved = optionalCategoryInProduct.get();
                        categoriesToBeRemoved.add(catToBeRemoved);
                    }
                }
            }
            product.getCategories().removeAll(categoriesToBeRemoved);
            product.getCategories().addAll(categoriesToBeAdded);

            order.getProductList().add(product);
        }
        return order;
    }
}