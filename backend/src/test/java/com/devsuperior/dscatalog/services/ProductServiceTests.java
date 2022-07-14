package com.devsuperior.dscatalog.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.repositories.ProductRepository;

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
	- Utiliza-se então um objeto mockado (de mentinrinha) configurado para simular o comportamento dessas duas dependencias
	- Sua annotation é: @Mock*/
	@Mock
	private ProductRepository repository;
	
}
