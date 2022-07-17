package com.devsuperior.dscatalog.resource;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.resources.ProductResource;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.tests.Factory;

@WebMvcTest(ProductResource.class)
public class ProductResourceTests {

	@Autowired
	private MockMvc mockMvc; //Para fazer testes na camada web precisamos fazer requisições chamando o endPoint utilizando o MockMvc 
	
	@MockBean //Troca o componente e realiza o mock do componente (simula)
	private ProductService service;
	
	//Setando e simulando o compotamento do service no setUp e atribuido valores para algumas variáveis necessárias para os testes
	PageImpl<ProductDTO> page;
	Product product;
	ProductDTO productDTO; 
	
	@BeforeEach
	void setUp () throws Exception {
		 
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO(product);
		page = new PageImpl<>(List.of(productDTO));
		
		when(service.findAllPaged(ArgumentMatchers.any())).thenReturn(page); //Simulando um comportamento
	}
	
	@Test
	public void findAllShoudReturnPage() throws Exception{
		
		//.perform() que faz a requisição
		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
			.andExpect(MockMvcResultMatchers.status().isOk()); //Com o MockMvc já é possível fazer as assertions depois de chamar a requisição com o .andExpect() por exemplo
		
	}
	}
