package com.fiap.productRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ProductRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductRegistryApplication.class, args);
	}

}
