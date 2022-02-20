CREATE DATABASE IF NOT EXISTS `bookstore`;
USE `bookstore`;

CREATE TABLE `bookstore`.`role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` nvarchar(500),
  `last_name` nvarchar(500),
  `avatar` nvarchar(500),
  `gender` boolean,
  `birthday` datetime,
  `phone` varchar(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`account` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `password` varchar(255),
  `token` varchar(255),
  `active` boolean,
  `create_at` datetime,
  `create_by` int,
  `update_at` datetime,
  `update_by` int,
  `user_id` int,
  `role_id` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`administrative_unit_code` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` nvarchar(255),
  `name` nvarchar(255),
  `name_level` nvarchar(255),
  `up_level` nvarchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`address` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `country` nvarchar(255),
  `province` nvarchar(255),
  `district` nvarchar(255),
  `ward` nvarchar(255),
  `street_name` nvarchar(255),
  `street_number` nvarchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`user_address` (
  `user_id` int,
  `address_id` int,
  `address_type` nvarchar(255),
  `is_default` boolean
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`category` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(500),
  `parent_category_id` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`product` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(500),
  `supplier` varchar(500),
  `description` text,
  `origin_price` double,
  `image` text
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`category_product` (
  `category_id` int,
  `product_id` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`cart` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`cart_product` (
  `cart_id` int,
  `product_id` int,
  `quantity` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `total_charges` double,
  `address` varchar(1000),
  `status` varchar(255),
  `shipping_fee` double,
  `deliver` varchar(500),
  `order_date` datetime,
  `delivery_date` datetime
);

CREATE TABLE `bookstore`.`order_product` (
  `order_id` int,
  `product_id` int,
  `quantity` int
);

CREATE TABLE `bookstore`.`storage` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(500),
  `address` text
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`entity` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `storage_id` int,
  `product_id` int,
  `entry_day` datetime,
  `quantity` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`review` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `product_id` int,
  `content` text,
  `rating` int,
  `create_at` datetime,
  `update_at` datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`promotion` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(500),
  `valid_from` datetime,
  `valid_until` datetime,
  `discount_type` varchar(255),
  `amount` int,
  `code` varchar(255),
  `quantity` int
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookstore`.`promotion_product` (
  `promotion_id` int,
  `product_id` int
);  

CREATE TABLE `bookstore`.`promotion_order` (
  `promotion_id` int,
  `order_id` int
);

CREATE TABLE `bookstore`.`question` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `product_id` int,
  `question` text,
  `answer` text,
  `create_at` datetime,
  `update_at` datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER DATABASE `bookstore` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE `account` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE;

ALTER TABLE `account` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_address` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_address` ADD FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE;

ALTER TABLE `category` ADD FOREIGN KEY (`parent_category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE;

ALTER TABLE `category_product` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE;

ALTER TABLE `category_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `cart` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `cart_product` ADD FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE;

ALTER TABLE `cart_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `order_product` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

ALTER TABLE `order_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `entity` ADD FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`) ON DELETE CASCADE;

ALTER TABLE `entity` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `review` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `review` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `promotion_order` ADD FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON DELETE CASCADE;

ALTER TABLE `promotion_order` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE;

ALTER TABLE `promotion_product` ADD FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON DELETE CASCADE;

ALTER TABLE `promotion_product` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `question` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `question` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;
