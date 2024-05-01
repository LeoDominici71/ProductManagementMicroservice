package com.fiap.productRegistry.core.useCase;

import com.fiap.productRegistry.core.ProductRepository;

public class DeleteProduct {

	private ProductRepository productRepository;

	public DeleteProduct(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteProduct(id);
	}

}
