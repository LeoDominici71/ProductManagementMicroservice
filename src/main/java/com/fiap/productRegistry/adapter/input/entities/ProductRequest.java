package com.fiap.productRegistry.adapter.input.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
	
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private String stock;
	@NotNull
	private Double price;

}
