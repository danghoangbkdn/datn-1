package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dto.OrderDTO;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/orders")
@PreAuthorize("isAuthenticated()")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private Converter<Order, OrderDTO> orderDTOConverter;

    @GetMapping("/statuses")
    public List<String> getAllStatusOrder() {
        return Arrays.stream(orderService.getAllStatusOrder()).map(statusOrder -> statusOrder.getStatus()).collect(Collectors.toList());
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderDTOConverter.convert(orderService.getAll());
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable int id) {
        return orderDTOConverter.convert(orderService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getByUser(@PathVariable int userId) {
        return orderDTOConverter.convert(orderService.getByUserId(userId));
    }

    @PostMapping("/user/{userId}")
    public OrderDTO post(@PathVariable int userId, @RequestBody OrderDTO orderDTO) {
        return orderDTOConverter.convert(orderService.postOrder(userId, orderDTO));
    }

    @PutMapping("/{id}")
    public OrderDTO put(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        return orderDTOConverter.convert(orderService.putOrder(0, id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.deleteOrderById(0, id);
    }
}
