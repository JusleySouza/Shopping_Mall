package com.shopping.mall.productsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.Generated;

@Generated
@SpringBootApplication
@EnableDiscoveryClient
public class ProductsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsapiApplication.class, args);
	}
	
}
