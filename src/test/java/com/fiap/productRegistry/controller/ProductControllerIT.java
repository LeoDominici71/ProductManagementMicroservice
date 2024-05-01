package com.fiap.productRegistry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.productRegistry.adapter.input.entities.ProductRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateStockRequest;
import com.fiap.productRegistry.core.ProductRepository;
import com.fiap.productRegistry.factory.FactoryMethods;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRegistrarUmProduto() throws Exception {
		// Arrange
		ProductRequest request = FactoryMethods.createProductRequest();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/api/products").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.name").exists());
		response.andExpect(jsonPath("$.description").exists());
		response.andExpect(jsonPath("$.stock").exists());
		response.andExpect(jsonPath("$.price").exists());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarUmProdutoComVariaveisNulas() throws Exception {
		// Arrange
		ProductRequest request = FactoryMethods.createProductRequestComValorNulo();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/api/products").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isUnprocessableEntity());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveBuscarProdutoPorId() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());

		// Act
		ResultActions response = mockMvc.perform(get("/api/products/{id}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	public void naoDeveBuscarProdutoPorIdQuandoOIdNaoExiste() throws Exception {
		// Act
		ResultActions response = mockMvc.perform(get("/api/products/{id}", 5L).accept(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());
	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRetornarListaDeProdutos() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());
		productRepository.productRegistry(FactoryMethods.createProducts2());

		// Act
		ResultActions response = mockMvc.perform(get("/api/products/productList").accept(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].name").exists());
		response.andExpect(jsonPath("$[0].description").exists());
		response.andExpect(jsonPath("$[0].stock").exists());
		response.andExpect(jsonPath("$[0].price").exists());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveAtualizarUmProduto() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());
		ProductUpdateRequest request = FactoryMethods.createProductUpdate();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc.perform(
				put("/api/products/update/{id}", 1L).content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$.name").exists());
		response.andExpect(jsonPath("$.description").exists());
		response.andExpect(jsonPath("$.stock").exists());
		response.andExpect(jsonPath("$.price").exists());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveAtualizarUmEstoque() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());
		ProductUpdateStockRequest request = FactoryMethods.createProductStockUpdate();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc.perform(
				put("/api/products/update/stock/{id}", 1L).content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());

		response.andExpect(jsonPath("$.stock").exists());

	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveDeletarUmProduto() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());

		// Act
		ResultActions response = mockMvc.perform(delete("/api/products/deleteProduct/{id}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNoContent());

	}
	
	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveAtualizarUmProdutoQueOIdNaoExiste() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());
		ProductUpdateRequest request = FactoryMethods.createProductUpdate();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc.perform(
				put("/api/products/update/{id}", 4L).content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());


	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveAtualizarUmEstoqueComIdDesconhecido() throws Exception {
		// Arrange
		productRepository.productRegistry(FactoryMethods.createProducts());
		ProductUpdateStockRequest request = FactoryMethods.createProductStockUpdate();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc.perform(
				put("/api/products/update/stock/{id}", 4L).content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());


	}

}
