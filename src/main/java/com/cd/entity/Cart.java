package com.cd.entity;

import com.cd.dto.CartStatus;

public class Cart {

	private int id;
	
	private CartStatus status;

	public Cart() {
		super();
	}

	public Cart(CartStatus status) {
		super();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
	}
}
