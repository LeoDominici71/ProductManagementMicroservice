package com.fiap.productRegistry.core.entities;

import java.util.Objects;

public class Products {

	private Long id;
	private String name;
	private String description;
	private String stock;
	private Double price;

	public Products() {
		super();
	}

	public Products(Long id, String name, String description, String stock, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
