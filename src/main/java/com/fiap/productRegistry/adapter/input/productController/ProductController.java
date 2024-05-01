package com.fiap.productRegistry.adapter.input.productController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fiap.productRegistry.adapter.input.batchService.BatchService;
import com.fiap.productRegistry.adapter.input.entities.ProductRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductResponse;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateRequest;
import com.fiap.productRegistry.adapter.input.entities.ProductUpdateStockRequest;
import com.fiap.productRegistry.core.entities.Products;
import com.fiap.productRegistry.core.useCase.DeleteProduct;
import com.fiap.productRegistry.core.useCase.GetProductById;
import com.fiap.productRegistry.core.useCase.ListAllProducts;
import com.fiap.productRegistry.core.useCase.ProductRegistry;
import com.fiap.productRegistry.core.useCase.UpdateProduct;
import com.fiap.productRegistry.utils.ApplicationUtils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

	private final DeleteProduct deleteProduct;
	private final GetProductById getProductById;
	private final ListAllProducts listAllProducts;
	private final ProductRegistry productRegistry;
	private final UpdateProduct updateProduct;
	private final BatchService batchService;

	public ProductController(DeleteProduct deleteProduct, GetProductById getProductById,
			ListAllProducts listAllProducts, ProductRegistry productRegistry, UpdateProduct updateProduct, BatchService batchService) {
		this.deleteProduct = deleteProduct;
		this.getProductById = getProductById;
		this.listAllProducts = listAllProducts;
		this.productRegistry = productRegistry;
		this.updateProduct = updateProduct;
		this.batchService = batchService;
		
	}
	
	@GetMapping(value = "/startBatch")
	public BatchStatus startBatch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		ProductController.log.info("IN - Async routine batch processment");	    
		return batchService.batchAsyncRoutine();
	}

	@PostMapping
	public ResponseEntity<ProductResponse> registryProduct(@Valid @RequestBody ProductRequest request) {
		ProductController.log.info("IN - product registry");
		Products product = productRegistry.productRegistry(ApplicationUtils.toProductFromRequest(request));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		ProductController.log.info("OUT - product registry");
		return ResponseEntity.created(uri).body(ApplicationUtils.toProductResponseFromProduct(product));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
		ProductController.log.info("IN - get product by id");
		Products product = getProductById.getProductById(id);
		ProductController.log.info("OUT - get product by id");
		return ResponseEntity.ok().body(ApplicationUtils.toProductResponseFromProduct(product));
	}

	@GetMapping(value = "/productList")
	public ResponseEntity<List<ProductResponse>> listAllProducts() {
		ProductController.log.info("IN - get product list");
		List<Products> products = listAllProducts.listAllProducts();
		List<ProductResponse> productResponse = products.stream()
				.map(product -> ApplicationUtils.toProductResponseFromProduct(product)).collect(Collectors.toList());
		ProductController.log.info("OUT - get product list");
		return ResponseEntity.ok().body(productResponse);
	}

	@DeleteMapping(value = "/deleteProduct/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		ProductController.log.info("IN - delete product");
		deleteProduct.deleteProduct(id);
		ProductController.log.info("OUT - delete product");
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ProductResponse> udpdateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request){
		ProductController.log.info("IN - update product");
		Products products = updateProduct.updateProduct(id, ApplicationUtils.toProductFromUpdateRequest(request));
		ProductController.log.info("OUT - update product");
		return ResponseEntity.ok().body(ApplicationUtils.toProductResponseFromProduct(products));
	}
	
	@PutMapping(value = "/update/stock/{id}")
	public ResponseEntity<ProductResponse> udpdateProductStock(@PathVariable Long id, @RequestBody ProductUpdateStockRequest request){
		ProductController.log.info("IN - update stock");
		Products products = updateProduct.updateProduct(id, ApplicationUtils.toProductFromUpdateStockRequest(request));
		ProductController.log.info("OUT - update product");
		return ResponseEntity.ok().body(ApplicationUtils.toProductResponseFromProduct(products));
	}

}
