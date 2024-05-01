package com.fiap.productRegistry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.useCase.DeleteProduct;
import com.fiap.productRegistry.core.useCase.GetProductById;
import com.fiap.productRegistry.core.useCase.ListAllProducts;
import com.fiap.productRegistry.core.useCase.ProductRegistry;
import com.fiap.productRegistry.core.useCase.UpdateProduct;

@Configuration
public class BeansConfig {

	@Bean
	public DeleteProduct deleteProduct(ProductRepository productRepository) {
		return new DeleteProduct(productRepository);
	}

	@Bean
	public GetProductById getProductById(ProductRepository productRepository) {
		return new GetProductById(productRepository);
	}

	@Bean
	public ListAllProducts listAllProducts(ProductRepository productRepository) {
		return new ListAllProducts(productRepository);
	}

	@Bean
	public ProductRegistry productRegistry(ProductRepository productRepository) {
		return new ProductRegistry(productRepository);
	}
	
	@Bean
	public UpdateProduct updateProduct(ProductRepository productRepository) {
		return new UpdateProduct(productRepository);
	}
}
