package com.fiap.productRegistry.core.useCase;

import java.util.List;

import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;

public class ListAllProducts {
	
	private ProductRepository productRepository;

	public ListAllProducts(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Products> listAllProducts() {
		return productRepository.listAllProducts();
	}


}
