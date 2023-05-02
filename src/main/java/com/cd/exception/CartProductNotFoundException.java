package com.cd.exception;

public class CartProductNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -925431074969124950L;

	public CartProductNotFoundException() {
		super("Cart product cannot be found.");
	}
	
	public CartProductNotFoundException(int id) {
		super(String.format("Product with id=[%s] cannot be found.", id));
	}

	public CartProductNotFoundException(String message) {
		super(message);
	}

}
