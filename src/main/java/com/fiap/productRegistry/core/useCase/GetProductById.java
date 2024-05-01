package com.fiap.productRegistry.core.useCase;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;

public class GetProductById {

	private ProductRepository productRepository;

	public GetProductById(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Products getProductById(Long id) {
		return productRepository.getProductById(id);
	}

}
