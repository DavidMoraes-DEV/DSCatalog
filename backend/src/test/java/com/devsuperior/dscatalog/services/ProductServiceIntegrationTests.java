package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
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
@Transactional //Retorna o banco ao seu estado original depois de cada teste para as mudanças dos testes influenciar no resultado dos outros testes tratando cada teste como uma transação individual
public class ProductServiceIntegrationTests {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	private String nameCategory;
	
	@BeforeEach
	void setUp() throws Exception {
	
		existingId = 1L;
		nonExistingId = 100L;
		countTotalProducts = 25L;
		nameCategory = "PC%20Gamer";
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
	
	//Teste básico passando o numero da pagina = 0 e com 10 elementos verificando se realmente esta voltando uma pagina
	@Test
	public void findAllPagedShouldReturnPagedWhenPage0Size10() {
		
		PageRequest pageRequest = PageRequest.of(0, 10); //PageRequest é a classe que implementa o pageable
		Page<ProductDTO> result = service.findAllPaged(1L, nameCategory, pageRequest);
		
		Assertions.assertFalse(result.isEmpty()); //Testa se a página esta vazia, como colocamos assertFalse se der FALSE vai passar no teste
		Assertions.assertEquals(0, result.getNumber()); //Testa se a pagina é a de numero 0
		Assertions.assertEquals(10, result.getSize()); //Testa se a quantidade de elementos da pagina é realmente 10
		Assertions.assertEquals(countTotalProducts, result.getTotalElements()); //Testa se o total de produtos buscado é igual ao numero máximo de produtos no banco que no caso é 25 produtos	
	}
	
	@Test
	public void findAllPagedShouldReturnPagedWhenPageDoesNotExists() {
		
		PageRequest pageRequest = PageRequest.of(50, 10); 
		Page<ProductDTO> result = service.findAllPaged(1L, nameCategory, pageRequest);
		
		Assertions.assertTrue(result.isEmpty()); //Nesse teste quando a página não existir agora sim a pagina vai retornar vazia
	}
	
	@Test
	public void findAllPagedShouldReturnSortedPageWhenSortByName() {
		
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name")); //Instanciando um PageRequest passando: a pagina(0), a quantidade de elementos(10) e o critério de ordenação(Sort.by("name"))
		Page<ProductDTO> result = service.findAllPaged(1L, nameCategory, pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Macbook Pro", result.getContent().get(0).getName());
		Assertions.assertEquals("PC Gamer", result.getContent().get(1).getName());
		Assertions.assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
	}
}
