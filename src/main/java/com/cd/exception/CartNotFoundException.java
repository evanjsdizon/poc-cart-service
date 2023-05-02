package com.cd.exception;

public class CartNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4393492614626637492L;

	public CartNotFoundException() {
		super("Cart cannot be found.");
	}
	
	public CartNotFoundException(int id) {
		super(String.format("Cart with id=[%s] cannot be found.", id));
	}

	public CartNotFoundException(String message) {
		super(message);
	}

}
