package com.fiap.productRegistry.core.useCase;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;

public class ProductRegistry {

	private ProductRepository productRepository;

	public ProductRegistry(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Products productRegistry(Products products) {
		return productRepository.productRegistry(products);
	}

}
