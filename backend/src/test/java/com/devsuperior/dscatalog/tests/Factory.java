package com.devsuperior.dscatalog.tests;

import java.time.Instant;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public class Factory {
	
	//Função da fábrica de objetos que retorna um produto com uma categoria Eletronics
	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
		product.getCategories().add(new Category(2L, "Eletronics"));
		return product;
	}
	
	//Função da fábrica de objetos que retorna um ProductDTO
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories()); //Pois no ProductDTO tem uma sobrecarga do construtor que recebe o objeto completo e as categorias desse objeto
	}
	
}
