INSERT INTO cart(status) VALUES ('ACTIVE');

SET @last_cart_id = last_insert_id();

INSERT INTO cart_product(cart_id, product_id) VALUES (@last_cart_id , 1);
INSERT INTO cart_product(cart_id, product_id) VALUES (@last_cart_id , 2);
INSERT INTO cart_product(cart_id, product_id) VALUES (@last_cart_id , 3);