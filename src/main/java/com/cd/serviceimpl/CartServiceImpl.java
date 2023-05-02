package com.cd.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dto.CartStatus;
import com.cd.entity.Cart;
import com.cd.entity.CartProduct;
import com.cd.exception.CartNotFoundException;
import com.cd.exception.CartProductNotFoundException;
import com.cd.exception.ProductNotFoundException;
import com.cd.mapper.CartMapper;
import com.cd.proto.AddCartRequest;
import com.cd.proto.AddCartResponse;
import com.cd.proto.AddToCartRequest;
import com.cd.proto.AddToCartResponse;
import com.cd.proto.CartServiceGrpc;
import com.cd.proto.DeleteFromCartRequest;
import com.cd.proto.DeleteFromCartResponse;
import com.cd.proto.GetCartRequest;
import com.cd.proto.GetCartResponse;
import com.cd.proto.Response;
import com.cd.proto.ResponseCode;
import com.cd.util.ProductTransformer;

import io.grpc.stub.StreamObserver;

@Transactional
public class CartServiceImpl extends CartServiceGrpc.CartServiceImplBase {

	private final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	
	private static final Response OK_RESPONSE = Response.newBuilder().setCode(ResponseCode.OK).build();
	
	private static final Response ERROR_RESPONSE = Response.newBuilder().setCode(ResponseCode.ERROR).build();
	
	private static final Response NOT_FOUND_RESPONSE = Response.newBuilder().setCode(ResponseCode.NOT_FOUND).build();
	
	private CartMapper mapper;
	
	public CartServiceImpl(CartMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void addCart(AddCartRequest request, StreamObserver<AddCartResponse> responseObserver) {
		AddCartResponse response = null;
		try {
			mapper.addCart(new Cart(CartStatus.ACTIVE));
			var cartId = mapper.getLastInsertId();
			response = AddCartResponse.newBuilder()
					.setCartId(cartId)
					.setResponse(OK_RESPONSE)
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response = AddCartResponse.newBuilder()
					.setResponse(ERROR_RESPONSE)
					.build();
		}
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getCart(GetCartRequest request, StreamObserver<GetCartResponse> responseObserver) {	
		GetCartResponse response = null;
		try {
			if (mapper.findCartById(request.getCartId()) == null) {
				throw new CartNotFoundException(request.getCartId());
			}
			var entityProducts = mapper.findCartProductsByCartId(request.getCartId());
			var protoProducts = entityProducts.stream()
					.map(ProductTransformer::toProto)
					.toList();
	
			response = GetCartResponse.newBuilder()
					.setCartId(request.getCartId())
					.addAllProducts(protoProducts)
					.setResponse(OK_RESPONSE)
					.build();
		
		} catch (CartNotFoundException cnfe) {
			log.error(cnfe.getMessage());
			response = GetCartResponse.newBuilder()
					.setResponse(NOT_FOUND_RESPONSE)
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response = GetCartResponse.newBuilder()
					.setResponse(ERROR_RESPONSE)
					.build();
		}
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void addToCart(AddToCartRequest request, StreamObserver<AddToCartResponse> responseObserver) {
		AddToCartResponse response = null;
		try {
			var cart = mapper.findCartById(request.getCartId());
			if (cart == null) {
				throw new CartNotFoundException(request.getCartId());
			}
			var product = mapper.findProductById(request.getProductId());
			if (product == null) {
				throw new ProductNotFoundException(request.getProductId());
			}
			
			mapper.addToCart(new CartProduct(request.getCartId(), request.getProductId()));
			var cartProductId = mapper.getLastInsertId();
			var protoProduct = ProductTransformer.toProto(product, cartProductId);
			
			response = AddToCartResponse.newBuilder()
					.setProduct(protoProduct)
					.setResponse(OK_RESPONSE)
					.build();
		} catch (CartNotFoundException | ProductNotFoundException nfe) {
			log.error(nfe.getMessage());
			response = AddToCartResponse.newBuilder()
					.setResponse(NOT_FOUND_RESPONSE)
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response = AddToCartResponse.newBuilder()
					.setResponse(ERROR_RESPONSE)
					.build();
		}
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void deleteFromCart(DeleteFromCartRequest request, StreamObserver<DeleteFromCartResponse> responseObserver) {
		DeleteFromCartResponse response = null;
		try {
			var cart = mapper.findCartById(request.getCartId());
			if (cart == null) {
				throw new CartNotFoundException(request.getCartId());
			}
			var cartProduct = mapper.findCartProductById(request.getCartProductId());
			if (cartProduct == null) {
				throw new CartProductNotFoundException(request.getCartProductId());
			}
			
			mapper.deleteFromCart(request.getCartProductId());
			response = DeleteFromCartResponse.newBuilder()
					.setResponse(OK_RESPONSE)
					.build();
		} catch (CartNotFoundException | CartProductNotFoundException nfe) {
			log.error(nfe.getMessage());
			response = DeleteFromCartResponse.newBuilder()
					.setResponse(NOT_FOUND_RESPONSE)
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response = DeleteFromCartResponse.newBuilder()
					.setResponse(ERROR_RESPONSE)
					.build();
		}
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
