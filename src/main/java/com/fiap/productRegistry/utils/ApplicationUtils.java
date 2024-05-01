package com.fiap.productRegistry.utils;

import com.fiap.productRegistry.adapter.input.entities.ProductRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductResponse;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateStockRequest;
import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;
import com.fiap.productRegistry.core.entities.Products;
import com.fiap.productRegistry.exceptionHandler.TransportDataException;

public class ApplicationUtils {

	public static ProductEntity toProductEntity(Products product) {
		ProductEntity productEntity = new ProductEntity();
		if(product.getName() != null) {
		productEntity.setName(product.getName());
		}
		if(product.getDescription() != null) {
		productEntity.setDescription(product.getDescription());
		}
		if(product.getPrice() != null) {
		productEntity.setPrice(product.getPrice());
		}
		if(product.getStock() != null) {
		productEntity.setStock(product.getStock());
		}
		return productEntity;

	}

	public static Products toProduct(ProductEntity productEntity) {
		try {
		Products product = new Products();
		product.setId(productEntity.getId());
		product.setName(productEntity.getName());
		product.setDescription(productEntity.getDescription());
		product.setPrice(productEntity.getPrice());
		product.setStock(productEntity.getStock());

		return product;
		}catch(TransportDataException e){
	    	throw new TransportDataException("Failed to transport data to product");
		}

	}

	public static ProductEntity updateProductEntity(ProductEntity productEntityAntigo,
			ProductEntity productEntityAtual) {
		if (productEntityAtual.getName() != null) {
			productEntityAntigo.setName(productEntityAtual.getName());
		}
		if (productEntityAtual.getDescription() != null) {
			productEntityAntigo.setDescription(productEntityAtual.getDescription());
		}
		if (productEntityAtual.getPrice() != null) {
			productEntityAntigo.setPrice(productEntityAtual.getPrice());
		}
		if (productEntityAtual.getStock() != null) {
			productEntityAntigo.setStock(productEntityAtual.getStock());
		}

		return productEntityAntigo;
	}

	public static Products toProductFromRequest(ProductRequest productEntity) {
		Products product = new Products();
		product.setName(productEntity.getName());
		product.setDescription(productEntity.getDescription());
		product.setPrice(productEntity.getPrice());
		product.setStock(productEntity.getStock());

		return product;

	}

	public static Products toProductFromUpdateRequest(ProductUpdateRequest productEntity) {
		Products product = new Products();
		product.setName(productEntity.getName());
		product.setDescription(productEntity.getDescription());
		product.setPrice(productEntity.getPrice());
		product.setStock(productEntity.getStock());

		return product;

	}
	
	public static Products toProductFromUpdateStockRequest(ProductUpdateStockRequest productEntity) {
		Products product = new Products();
	
		product.setStock(productEntity.getStock());

		return product;

	}

	public static ProductResponse toProductResponseFromProduct(Products product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setName(product.getName());
		productResponse.setDescription(product.getDescription());
		productResponse.setPrice(product.getPrice());
		productResponse.setStock(product.getStock());

		return productResponse;

	}

}
