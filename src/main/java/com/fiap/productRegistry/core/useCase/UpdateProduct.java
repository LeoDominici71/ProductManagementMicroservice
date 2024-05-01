package com.fiap.productRegistry.core.useCase;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;

public class UpdateProduct {
	
	private ProductRepository productRepository;

	public UpdateProduct(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Products updateProduct(Long id, Products products) {
		return productRepository.updateProduct(id, products);
	}

}
