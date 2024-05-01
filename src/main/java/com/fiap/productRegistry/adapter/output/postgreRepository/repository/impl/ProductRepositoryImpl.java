package com.fiap.productRegistry.adapter.output.postgreRepository.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;
import com.fiap.productRegistry.adapter.output.postgreRepository.repository.ProductsRepository;
import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.core.entities.Products;
import com.fiap.productRegistry.exceptionHandler.TransportDataException;
import com.fiap.productRegistry.utils.ApplicationUtils;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductsRepository repository;

	public ProductRepositoryImpl(ProductsRepository repository) {
		this.repository = repository;
	}

	@Override
	public Products productRegistry(Products product) {
		return ApplicationUtils.toProduct(repository.save(ApplicationUtils.toProductEntity(product)));

	}

	@Override
	public List<Products> listAllProducts() {
		List<ProductEntity> listEntity = repository.findAll();
		return listEntity.stream().map(productEntity -> ApplicationUtils.toProduct(productEntity))
				.collect(Collectors.toList());
	}

	@Override
	public Products getProductById(Long id) {
		try {
			Optional<ProductEntity> optionalProduct = repository.findById(id);
			ProductEntity productEntity = optionalProduct
					.orElseThrow(() -> new EntityNotFoundException("Product id not found"));

			return ApplicationUtils.toProduct(productEntity);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Product id not found");

		}
	}

	@Override
	public void deleteProduct(Long id) {
		repository.deleteById(id);

	}

	@Override
	public Products updateProduct(Long id, Products product) {
		Optional<ProductEntity> optionalProduct = repository.findById(id);
		ProductEntity productEntity = optionalProduct
				.orElseThrow(() -> new EntityNotFoundException("Product id not found"));
		try {
			ProductEntity productEntitySaved = repository.save(
					ApplicationUtils.updateProductEntity(productEntity, ApplicationUtils.toProductEntity(product)));
			return ApplicationUtils.toProduct(productEntitySaved);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Product id not found");

		}
	}

}
