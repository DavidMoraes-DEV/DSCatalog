package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;
	
	//Testa se realmente esta deletando no ProductService pelo id QUANDO o id existe
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		long exintingId = 1L; 
		
		repository.deleteById(exintingId); //Deleta o Product pelo id
		Optional<Product> result = repository.findById(exintingId); //busca pelo id do Product que acabou de ser deletado para testar se realmente foi deletado
		
		Assertions.assertFalse(result.isPresent()); //O assertFalse() espera um result = FALSE e o .isPresent() testa se existe um objeto dentro do Optional<Product>, que nesse caso não é para existir pois foi deletado antes
	}
	
	//Testa se realmente lança a exceção quando a deleção for chamada mas o ID NÃO EXISTE
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			
			long nonExintingId = 100L;
			
			repository.deleteById(nonExintingId);
		});
		
	}
}
