package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.BadRequestException;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.dao.Review;
import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.repositories.*;
import com.hoangdang.BookStore.contants.RoleConstant;
import com.hoangdang.BookStore.contants.StatusOrder;
import com.hoangdang.BookStore.models.dto.ReviewDTO;
import com.hoangdang.BookStore.repositories.*;
import com.hoangdang.BookStore.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Converter<ReviewDTO, Review> reviewConverter;

    @Override
    public List<Review> getAllByProduct(int productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    @Override
    public Review getById(int id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public Review post(int userId, int productId, ReviewDTO reviewDTO) {
        verifyUserAllowedEdit(userId, productId);
        return reviewRepository.save(reviewConverter.convert(reviewDTO));
    }

    @Override
    public Review put(int userId, int id, ReviewDTO reviewDTO) {
        verifyReviewExist(id);
        verifyUserAllowedEdit(userId, id);

        reviewDTO.setId(id);
        return reviewRepository.save(reviewConverter.convert(reviewDTO));
    }

    @Override
    public void delete(int userId, int id) {
        verifyReviewExist(id);
        verifyUserAllowedEdit(userId, id);

        reviewRepository.deleteById(id);
    }

    private boolean verifyRoleUser(int userId) {
        return accountRepository.findByUserId(userId).getRole().getName().equals(RoleConstant.ROLE_USER.getRole());
    }

    private void verifyUserAllowedPost(int userId, int productId) {
        User user = userRepository.getOne(userId);

        if (verifyRoleUser(userId)) {
            List<OrderProduct> orderProductsUserGet = new ArrayList<>();
            orderRepository.findAllByUser(user).stream()
                    .filter(order -> StatusOrder.DA_GIAO_HANG.getStatus().equals(order.getStatus()))
                    .forEach(order -> orderProductsUserGet.addAll(orderProductRepository.findAllByOrderId(order.getId())));

            long countProductValid = orderProductsUserGet.stream().filter(op -> op.getProductId() == productId).count();

            if (countProductValid == 0) {
                throw new BadRequestException(String.format("User not allowed"));
            }
        }
    }

    private void verifyUserAllowedEdit(int userId, int id) {
        if (verifyRoleUser(userId) && userId != reviewRepository.getOne(id).getUser().getId()) {
            throw new BadRequestException(String.format("User not allowed"));
        }
    }

    private void verifyReviewExist(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new NotFoundException(String.format("Review id %d is not exist", id));
        }
    }

}
