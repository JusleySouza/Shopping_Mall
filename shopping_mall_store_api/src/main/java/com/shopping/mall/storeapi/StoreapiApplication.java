package com.shopping.mall.storeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.Generated;

@Generated
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class StoreapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreapiApplication.class, args);
	}

}
