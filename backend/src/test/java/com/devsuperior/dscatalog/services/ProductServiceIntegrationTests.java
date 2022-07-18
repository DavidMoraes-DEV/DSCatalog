package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

/*
* TESTES DE INTEGRAÇÃO:
	- O que são testes de integração?
		- São testes Utilizados para testar de forma integrada mais de um componente
		- Podendo testar como os componentes estão conversando entre si para fazer um objetivo específico
		- É um teste muito mais lento pois precisa carregar o contexto da aplicação
		- Esse tipo de teste não é rodado toda hora como nos testes de Unidade
		- É rodado com menor frequência, pois é muito lento, sendo um teste que pode testar muito mais coisas
			- podendo testar por exemplo se as categorias dos produtos esta retornando de forma ordenada adequadamente considerando a instancia do banco
*/
@SpringBootTest //Utilizamos essa annotation porque agora precisamos carregar o contexto da aplicação
public class ProductServiceIntegrationTests {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception {
	
		existingId = 1L;
		nonExistingId = 100L;
		countTotalProducts = 25L;
	}
	
	//Testa se realmente esta deletando quando o Id existir
	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		
		service.delete(existingId);
		
		Assertions.assertEquals(countTotalProducts - 1, repository.count());
	}
	
	//Testa se realmente esta lançando uma exceção quando o Id NÃO EXISTIR
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
}
