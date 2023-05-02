package com.cd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cd.entity.Cart;
import com.cd.entity.CartProduct;
import com.cd.entity.Product;

@Mapper
public interface CartMapper {
	
	@Insert("INSERT INTO cart(status) VALUES(#{status})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int addCart(Cart cart);
	
	@Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
	
	@Select("SELECT * FROM cart WHERE id = #{id}")
	Cart findCartById(@Param("id") int id);
	
	@Select("SELECT cp.id, p.name, p.description, p.price, p.stock "
			+ "FROM product p JOIN cart_product cp ON p.id = cp.product_id WHERE cp.cart_id = #{id}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"),
			@Result(property = "description", column = "description"),
			@Result(property = "price", column = "price"),
			@Result(property = "stock", column = "stock"),
	})
	List<Product> findCartProductsByCartId(@Param("id") int id);
	
	@Insert("INSERT INTO cart_product(cart_id, product_id) VALUES(#{cartId}, #{productId})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int addToCart(CartProduct cartProduct);
	
	@Select("SELECT * FROM product WHERE id = #{id}")
	Product findProductById(@Param("id") int id);
	
	@Delete("DELETE FROM cart_product WHERE id = #{id}")
	void deleteFromCart(@Param("id") int id);
	
	@Select("SELECT * FROM cart_product WHERE id = #{id}")
	Product findCartProductById(@Param("id") int id);
}
