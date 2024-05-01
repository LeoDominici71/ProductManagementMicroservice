package com.fiap.productRegistry.adapter.input.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateStockRequest {
	
	@NotNull
	private String stock;

}
