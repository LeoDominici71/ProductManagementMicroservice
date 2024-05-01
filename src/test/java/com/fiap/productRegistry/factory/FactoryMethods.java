package com.fiap.productRegistry.factory;

import java.util.Optional;

import com.fiap.productRegistry.adapter.input.entities.ProductRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateStockRequest;
import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;
import com.fiap.productRegistry.core.entities.Products;

public class FactoryMethods {
	
	public static ProductRequest createProductRequest() {
		ProductRequest request = new ProductRequest();
		request.setName("Teste");
		request.setDescription("Teste");
		request.setPrice(20.0);
		request.setStock("1");
		return request;

	}
	
	public static ProductRequest createProductRequestComValorNulo() {
		ProductRequest request = new ProductRequest();
		request.setName("Teste");
		request.setPrice(20.0);
		request.setStock("1");
		return request;

	} 
	
	public static Products createProducts() {
		Products request = new Products();
		request.setName("Teste");
		request.setDescription("Teste");
		request.setPrice(20.0);
		request.setStock("1");
		return request;

	} 
	
	public static ProductEntity createProductEntity() {
		ProductEntity request = new ProductEntity();
		request.setName("Teste");
		request.setDescription("Teste");
		request.setPrice(20.0);
		request.setStock("1");
		return request;

	} 
	
	public static Optional<ProductEntity> createProductEntityOptional() {
		ProductEntity request = new ProductEntity();
		request.setName("Teste");
		request.setDescription("Teste");
		request.setPrice(20.0);
		request.setStock("1");
		return Optional.ofNullable(request);

	} 
	
	public static Products createProducts2() {
		Products request = new Products();
		request.setName("Teste1");
		request.setDescription("Teste1");
		request.setPrice(20.0);
		request.setStock("1");
		return request;

	} 
	
	public static ProductUpdateRequest createProductUpdate() {
		ProductUpdateRequest request = new ProductUpdateRequest();
		request.setName("Teste");
		request.setDescription("Teste");
		request.setPrice(25.0);
		request.setStock("1");
		return request;

	} 
	
	public static ProductUpdateStockRequest createProductStockUpdate() {
		ProductUpdateStockRequest request = new ProductUpdateStockRequest();
		
		request.setStock("2");
		return request;

	} 

}
