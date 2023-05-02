DROP TABLE IF EXISTS `cart_product`;
DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cart_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `cart_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_product__cart__cart_id` (`cart_id`),
  CONSTRAINT `FK_cart_product__cart__cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
  -- KEY `FK_cart_product__product__product_id` (`product_id`),
  -- CONSTRAINT `FK_cart_product__product__product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);