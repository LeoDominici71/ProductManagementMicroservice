package com.fiap.productRegistry.adapter.output.postgreRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.productRegistry.adapter.output.postgreRepository.entities.ProductEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity , Long>{
	

}
