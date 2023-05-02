package com.cd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cd.mapper.CartMapper;
import com.cd.serviceimpl.CartServiceImpl;

import io.grpc.ServerBuilder;

@SpringBootApplication
public class PocCartServiceApplication implements CommandLineRunner {

	@Value("${app.grpc-port}")
	private int grpcPort;
	
	private CartMapper mapper;
	
	public PocCartServiceApplication(CartMapper mapper) {
		this.mapper = mapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PocCartServiceApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		var server = ServerBuilder
				.forPort(grpcPort)
				.addService(new CartServiceImpl(mapper))
				.build();

		server.start();
		server.awaitTermination();
	}
}
