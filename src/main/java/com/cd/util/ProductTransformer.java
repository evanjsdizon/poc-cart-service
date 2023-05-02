package com.cd.util;

import com.cd.proto.Product;

public class ProductTransformer {

	private ProductTransformer() {}
	
	public static Product toProto(com.cd.entity.Product product) {
		if (product == null) {
			return null;
		}
		return Product.newBuilder()
				.setId(product.getId())
				.setName(product.getName())
				.setDescription(product.getDescription())
				.setPrice(product.getPrice().doubleValue())
				.setStock(product.getStock())
				.build();
	}
	
	public static Product toProto(com.cd.entity.Product product, int cartProductId) {
		if (product == null) {
			return null;
		}
		return Product.newBuilder()
				.setId(cartProductId)
				.setName(product.getName())
				.setDescription(product.getDescription())
				.setPrice(product.getPrice().doubleValue())
				.setStock(product.getStock())
				.build();
	}
}
