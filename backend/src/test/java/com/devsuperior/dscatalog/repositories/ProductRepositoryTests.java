package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devsuperior.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;
	
	//Testa se realmente esta deletando no ProductService pelo id
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		long exintingId = 1L; 
		
		repository.deleteById(exintingId); //Deleta o Product pelo id
		Optional<Product> result = repository.findById(exintingId); //busca pelo id do Product que acabou de ser deletado para testar se realmente foi deletado
		
		Assertions.assertFalse(result.isPresent()); //O assertFalse() espera um result = FALSE e o .isPresent() testa se existe um objeto dentro do Optional<Product>, que nesse caso não é para existir pois foi deletado antes
	}
}
