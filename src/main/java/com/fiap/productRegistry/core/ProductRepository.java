package com.fiap.productRegistry.core;

import java.util.List;

import com.fiap.productRegistry.core.entities.Products;

public interface ProductRepository {
	
	Products productRegistry(Products product);
	
	List<Products> listAllProducts();
	
	Products getProductById(Long id);
	
	void deleteProduct(Long id);
	
	Products updateProduct(Long id, Products product);

}
