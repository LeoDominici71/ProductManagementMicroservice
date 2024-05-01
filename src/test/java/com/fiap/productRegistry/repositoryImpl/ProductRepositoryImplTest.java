package com.fiap.productRegistry.repositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;
import com.fiap.productRegistry.adapter.output.postgreRepository.repository.ProductsRepository;
import com.fiap.productRegistry.adapter.output.postgreRepository.repository.impl.ProductRepositoryImpl;
import com.fiap.productRegistry.core.entities.Products;
import com.fiap.productRegistry.factory.FactoryMethods;

@ExtendWith(SpringExtension.class)
public class ProductRepositoryImplTest {

	@InjectMocks
	private ProductRepositoryImpl service;

	@Mock
	private ProductsRepository repository;

	@Test
	public void testRegisterProduct() {
		// Arrange
		Products product = FactoryMethods.createProducts();
		ProductEntity productEntity = FactoryMethods.createProductEntity();

		// Act
		Mockito.when(repository.save(ArgumentMatchers.any(ProductEntity.class))).thenReturn(productEntity);
		Products productTest = service.productRegistry(product);

		// Assert
		Assertions.assertNotNull(product);
		Assertions.assertEquals(product.getName(), productTest.getName());
		verify(repository, Mockito.times(1)).save(productEntity);
	}

	@Test
	public void testListAllProducts() {
		// Arrange
		Products product = FactoryMethods.createProducts();
		ProductEntity productEntity = FactoryMethods.createProductEntity();
		List<ProductEntity> listProductEntity = new ArrayList<>();
		listProductEntity.add(productEntity);

		// Act
		Mockito.when(repository.findAll()).thenReturn(listProductEntity);
		List<Products> productTest = service.listAllProducts();

		// Assert
		Assertions.assertNotNull(listProductEntity);
		Assertions.assertEquals(product.getName(), productTest.get(0).getName());
		verify(repository, Mockito.times(1)).findAll();
	}

	@Test
	public void testGetProductById() {
		// Arrange
		Products product = FactoryMethods.createProducts();
		Optional<ProductEntity> productEntity = FactoryMethods.createProductEntityOptional();

		// Act
		Mockito.when(repository.findById(1L)).thenReturn(productEntity);
		Products productTest = service.getProductById(1L);

		// Assert
		Assertions.assertNotNull(product);
		Assertions.assertEquals(product.getName(), productTest.getName());
		verify(repository, Mockito.times(1)).findById(1L);
	}

	@Test
	public void testDeleteProduct() {
		

		// Act
		Assertions.assertDoesNotThrow(() -> {
			repository.deleteById(1L);
		});

		// Assert
		Mockito.verify(repository, times(1)).deleteById(1L);

	}
	
	@Test
	public void testUpdateProduct() {
		// Arrange
		Products product = FactoryMethods.createProducts();
		ProductEntity productEntity = FactoryMethods.createProductEntity();
		Optional<ProductEntity> optionalProductEntity = FactoryMethods.createProductEntityOptional();

		// Act
		when(repository.findById(1L)).thenReturn(optionalProductEntity);
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(productEntity);

		Products result = service.updateProduct(1L, product);
		
		//Assert
		assertNotNull(result);
		verify(repository, Mockito.times(1)).findById(1L);
		verify(repository, Mockito.times(1)).save(productEntity);

	}

}
