package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.dto.OrderDTO;
import com.hoangdang.BookStore.models.dto.OrderProductDTO;
import com.hoangdang.BookStore.repositories.EntityRepository;
import com.hoangdang.BookStore.repositories.OrderProductRepository;
import com.hoangdang.BookStore.repositories.OrderRepository;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.contants.StatusOrder;
import com.hoangdang.BookStore.models.compositKeys.OrderProductKey;
import com.hoangdang.BookStore.services.OrderService;
import com.hoangdang.BookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private Converter<OrderDTO, Order> orderConverter;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private Converter<OrderProductDTO, OrderProduct> orderProductConverter;

    @Override
    public StatusOrder[] getAllStatusOrder() {
        return StatusOrder.values();
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(int id) {
        verifyOrderIdExist(id);
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getByUserId(int userId) {
        userService.verifyUserIdExist(userId);
        return orderRepository.findAllByUser(userRepository.getOne(userId));
    }

    @Override
    @Transactional
    public Order postOrder(int userId, OrderDTO orderDTO) {
        orderDTO.setUserId(userId);
        orderDTO.setStatus(StatusOrder.DANG_XU_LY.getStatus());
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setTotalCharges(orderDTO.getOrderProducts().stream().mapToDouble(op -> op.getPrice()*op.getQuantity()).sum());

        long countOrderOver = orderDTO.getOrderProducts().stream()
                .filter(orderProduct -> orderProduct.getQuantity() > entityRepository.findFirstByProductId(orderProduct.getProductId()).getQuantity())
                .count();
        if (countOrderOver > 0) {
            orderDTO.setStatus(StatusOrder.HUY_DON.getStatus());
        }
        Order order = orderRepository.save(orderConverter.convert(orderDTO));

        orderDTO.getOrderProducts().forEach(orderPr -> {
            orderPr.setOrderId(order.getId());
            orderProductRepository.save(orderProductConverter.convert(orderPr));
        });


        if (!StatusOrder.HUY_DON.getStatus().equals(order.getStatus())) {
            for (OrderProductDTO orderProduct : orderDTO.getOrderProducts()) {
                Entity entity = entityRepository.findFirstByProductId(orderProduct.getProductId());
                entity.setQuantity(entity.getQuantity() - orderProduct.getQuantity());
                entityRepository.save(entity);
            }
        }
        return order;
    }

    @Override
    public Order putOrder(int userId, int id, OrderDTO orderDTO) {
        orderDTO.setId(id);

        if (StatusOrder.HUY_DON.getStatus().equals(orderDTO.getStatus())) {
            for (OrderProductDTO orderProduct : orderDTO.getOrderProducts()) {
                Entity entity = entityRepository.findFirstByProductId(orderProduct.getProductId());
                entity.setQuantity(entity.getQuantity() + orderProduct.getQuantity());
                entityRepository.save(entity);
            }
        }

        return orderRepository.save(orderConverter.convert(orderDTO));
    }

    @Override
    public void deleteOrderById(int userId, int id) {
        verifyOrderIdExist(id);
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(id);
        orderProducts.forEach(orderProduct -> {
            orderProductRepository.deleteById(new OrderProductKey(id, orderProduct.getProductId()));
        });
        orderRepository.deleteById(id);
    }

    private void verifyOrderIdExist(int id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException(String.format("Order id %d is not found", id));
        }
    }
}
