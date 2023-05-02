package com.cd.entity;

public class CartProduct {

	private int id;
	
	private int cartId;
	
	private int productId;

	public CartProduct() {
		super();
	}

	public CartProduct(int productId) {
		super();
		this.productId = productId;
	}

	public CartProduct(int cartId, int productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
