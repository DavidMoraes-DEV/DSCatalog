package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;
	
	private long exintingId;
	private long nonExintingId;
	private long countTotalProducts;
	
	//FIXTURES: Evita ter que ficar estanciado a mesma variável em todos os testes repetidamente, é uma maneira de deixar mais organizado o código
	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExintingId = 100L;
		countTotalProducts = 25L;
	}
	
	//Retorna um Optional<Product> não vazio quando o id existir
	@Test
	public void findByIdShouldReturnOptionalObjectWhenIdExists() {
		Optional<Product> result = repository.findById(exintingId);
		Assertions.assertTrue(result.isPresent());
	}
	
	//Retorna um Optional<Product> vazio quando o id NÃO existir
		@Test
		public void findByIdShouldReturnEmptyOptionalObjectWhenIdNotExists() {
			Optional<Product> result = repository.findById(nonExintingId);
			Assertions.assertFalse(result.isPresent());
		}
	
	
	//Teste se o .save() realmente esta salvando com o id é null, porque é autoicrementado no banco de dados
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Product product = Factory.createProduct();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId()); //Verifica se esta realmente persistindo o dado
		Assertions.assertEquals(countTotalProducts+1, product.getId()); //Verifica se esta realmente incrementando o id
	}
	
	//Testa se realmente esta deletando no ProductService pelo id QUANDO o id existe
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		 
		
		repository.deleteById(exintingId); //Deleta o Product pelo id
		Optional<Product> result = repository.findById(exintingId); //busca pelo id do Product que acabou de ser deletado para testar se realmente foi deletado
		
		Assertions.assertFalse(result.isPresent()); //O assertFalse() espera um result = FALSE e o .isPresent() testa se existe um objeto dentro do Optional<Product>, que nesse caso não é para existir pois foi deletado antes
	}
	
	//Testa se realmente lança a exceção quando a deleção for chamada mas o ID NÃO EXISTE
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			
			nonExintingId = 100L;
			
			repository.deleteById(nonExintingId);
		});	
	}
	
}
