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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.resources.ProductResource;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductResource.class)
public class ProductResourceTests {

	@Autowired
	private MockMvc mockMvc; //Para fazer testes na camada web precisamos fazer requisições chamando o endPoint utilizando o MockMvc 
	
	@MockBean //Troca o componente e realiza o mock do componente (simula)
	private ProductService service;
	
	@Autowired
	private ObjectMapper objectMapper; //Objeto auxiliar pois o corpo de requisições não é um objeto JAVA e sim um objeto JSON por isso usaremos esse objeto auxiliar para converter no formato texto JSON
	
	//Setando e simulando o compotamento do service no setUp e atribuido valores para algumas variáveis necessárias para os testes
	private Long existingId;
	private Long nonExistingId;
	private PageImpl<ProductDTO> page;
	private Product product;
	private ProductDTO productDTO;
	
	@BeforeEach
	void setUp () throws Exception {
		 
		existingId = 1L;
		nonExistingId = 100L;
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO(product);
		page = new PageImpl<>(List.of(productDTO));
		
		when(service.findAllPaged(ArgumentMatchers.any())).thenReturn(page); //Simulando um comportamento
		
		when(service.findById(existingId)).thenReturn(productDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.update(ArgumentMatchers.eq(existingId), ArgumentMatchers.any())).thenReturn(productDTO);
		when(service.update(ArgumentMatchers.eq(nonExistingId), ArgumentMatchers.any())).thenThrow(ResourceNotFoundException.class);
	}
	
	@Test
	public void findAllShoudReturnPage() throws Exception{
		
		//.perform() que faz a requisição
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products")
			.accept(MediaType.APPLICATION_JSON)); //Negociação de conteúdo, especifica nesse caso que vai aceitar como resposta conteúdo do tipo JSON
		
		result.andExpect(MockMvcResultMatchers.status().isOk()); //Com o MockMvc já é possível fazer as assertions depois de chamar a requisição com o .andExpect() por exemplo
		
	}
	
	@Test
	public void findByIsShouldReturnProductWhenIdExists() throws Exception {
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products/{id}", existingId)
			.accept(MediaType.APPLICATION_JSON)); 

		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()); //Nesse caso o "$" VAI acessar o objeto da resposta, com se estive acessando aquele objeto JSON mostrado nos testes do postman, testando se aquele id EXISTE
	}
	
	@Test
	public void findByIsShouldReturnNotFouldWhenIdDoesNotExists() throws Exception {
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products/{id}", nonExistingId)
			.accept(MediaType.APPLICATION_JSON)); 

		result.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
		
		//CORPO da requisição HTTP
		String jsonBody = objectMapper.writeValueAsString(productDTO); //converte o objeto JAVA product DTO em uma string para o JSON com o método .writeValueAsString()
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", existingId)
			.content(jsonBody) //Informa o corpo da requisição
			.contentType(MediaType.APPLICATION_JSON) //Define o tipo do corpo da requisição que também vai ser do tipo JSON
			.accept(MediaType.APPLICATION_JSON)); 
	
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(productDTO); //converte o objeto JAVA product DTO em uma string para o JSON com o método .writeValueAsString()
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", nonExistingId)
			.content(jsonBody) //Informa o corpo da requisição
			.contentType(MediaType.APPLICATION_JSON) //Define o tipo do corpo da requisição que também vai ser do tipo JSON
			.accept(MediaType.APPLICATION_JSON)); 
	
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
				
	}
}
