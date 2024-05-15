package com.fiap.productRegistry.core.useCase;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;

import jakarta.persistence.EntityNotFoundException;

public class GetProductById {

	private ProductRepository productRepository;

	public GetProductById(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Products getProductById(Long id) {
				Products product = productRepository.getProductById(id);
				if(product.getStock().equals("0")) {
					throw new EntityNotFoundException("There is no product on stock");
				}
				return product;
	}

}
