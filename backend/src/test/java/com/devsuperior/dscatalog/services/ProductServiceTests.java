package com.devsuperior.dscatalog.services;

//Importes STATICOS do mockito para chamos automaticamente sem precisar digitar o nome da classe POR EXEMPLO: Mockito.doNothing() 
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;

/*
* Testes de Unidade:
	- É um teste que testa somente a classe expecifica sem carregar suas dependencia
		- Por exemplo o a classe ProductService possui dependencias com Repository e CategoryRepository
	
	- Se fosse carregado as dependencia não  seri uma teste de unidade e sim um teste de integração, pois seria testado de forma integrado o Service com o Repository
	- Para ficar um teste mais isolado e masi rápido é que se utiliza o teste de Unidade utilizando o Mockito
		- Esses testes de Unidade são muito importantes para validar aquele componente específicos.
*/

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	// NÃO COLOCAR a annotation @Autowired, pois iria injetar o service normal com as dependencia e tudo mais.
	@InjectMocks
	private ProductService service;
	
	/* Como o ProductService possui dependencia com o PorductRepository e o CategoryRepository e não podemos utiliza-los por ser um teste de Unidade conforme descrito acima
	- Utiliza-se então um objeto mockado (de mentinrinha) configurado para simular o comportamento dessas duas dependencias*/
	@Mock
	private ProductRepository repository; //Simulando a dependencia repository com @Mock. Quando se faz um mock É NECESSÁRIO configurar o comportamento simulado desse mock que deve ser igual ao objeto real que estamos simulando 
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Product> page;
	private Product product;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 100L;
		dependentId = 4L;
		product = Factory.createProduct();
		page = new PageImpl<>(List.of(product)); //É um objeto de pagina do Spring contendo uma lista simples com um produto
		
		//Quando o método for VOID primeiro coloca-se a AÇÃO(Exemplo: doNothing()) depois colaca o QUANDO(.when()...)
		//Quando o método NÃO for VOID ou seja, ele retorna alguma coisa aí é invertido primeiro coloca o QUANDO(when()) e depois colaca a AÇÃO(doNothing())
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page); //Configuração do comportamento do .findAll() sendo que ArgumentMatchers.any() Define que pode ser qualquer objeto depois fazemos um catch para o tipo Pageable falando que retorno o page no .thenReturn(page)
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product); //Configuração do .save()
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product)); //Configurando o .findById() retornando um Optional(product)
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty()); //Configurando o .findById() retornando um Optional VAZIO quando o ID não existir
		
		doNothing().when(repository).deleteById(existingId); //Configuração do comportamento do .deleteById() do objeto mockado que criamos que diz que não é para fazer nada ou retornar nada com .doNothing() quando o ID EXISTIR
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId); //Configuração do comportamento do .deleteById() do objeto mockado quando o ID NÃO EXISTIR que será lançado uma exceção do tipo EmptyResult...
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId); //Configuração do comportamento do .deleteById() quando se tenta deletar um ID que outro objeto depende dele como por exemplo o id de uma categoria de protudos deixando o produto sem categoria e isso não pode ocorrer
	}
	
	@Test
	public void findAllPagedShouldReturnPage() {
		
		Pageable pageable = PageRequest.of(0, 10); //Página 0, com o tamanho 10
		Page<ProductDTO> result = service.findAllPaged(pageable);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, times(1)).findAll(pageable);
	}
	
	//Teste para quando o ID deletado for de um objeto que outro objeto depende simulado com o mockito
	@Test
	public void deleteShouldThrowDataBaseExceptionWhenWhenDependentId() {
			
		Assertions.assertThrows(DataBaseException.class, () -> {
			service.delete(dependentId);	
		});
			
		verify(repository, times(1)).deleteById(dependentId);
	}
	
	//Teste para quando o ID NÃO EXISTIR simulado com o mockito
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);	
		});
		
		verify(repository, times(1)).deleteById(nonExistingId);
	}
	
	//Teste para quando o ID EXISTIR o metodo .delete() simulado pelo mockito deve nao fazer nada
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);	
		});
		
		verify(repository, times(1)).deleteById(existingId);
	}
	
	
	
}
