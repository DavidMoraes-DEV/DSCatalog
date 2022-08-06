package com.devsuperior.dscatalog.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.tests.Factory;
import com.devsuperior.dscatalog.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TokenUtil tokenUtil;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	private String username;
	private String password;
	
	@BeforeEach
	void setUp() throws Exception {
	
		existingId = 1L;
		nonExistingId = 100L;
		countTotalProducts = 25L;
		username = "maria@gmail.com";
		password = "123456";
	}
	
	@Test
	public void findAllShouldReturnSortedPageWhenSortByName() throws Exception{
		
		ResultActions result = mockMvc
				.perform(get("/products?page=0&size=12&sort=name,asc", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalProducts));
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].name").value("Macbook Pro"));
		result.andExpect(jsonPath("$.content[1].name").value("PC Gamer"));
		result.andExpect(jsonPath("$.content[2].name").value("PC Gamer Alfa"));
		
	}
	
	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ProductDTO productDTO = Factory.createProductDTO();
		String jsonBody = objectMapper.writeValueAsString(productDTO); //converte o objeto JAVA product DTO em uma string para o JSON com o método .writeValueAsString()
		
		String expectedName = productDTO.getName(); //Salva o nome anterior antes de salvar
		String expectedDescription = productDTO.getDescription();
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", existingId)
			.header("Authorization", "Bearer " + accessToken)		
			.content(jsonBody) //Informa o corpo da requisição
			.contentType(MediaType.APPLICATION_JSON) //Define o tipo do corpo da requisição que também vai ser do tipo JSON
			.accept(MediaType.APPLICATION_JSON)); 
	
		//Verifica se a resposa veio OK com os valores que deveriao atualizar no banco
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedName));
		result.andExpect(MockMvcResultMatchers.jsonPath("$.description").value(expectedDescription));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ProductDTO productDTO = Factory.createProductDTO();
		String jsonBody = objectMapper.writeValueAsString(productDTO); //converte o objeto JAVA product DTO em uma string para o JSON com o método .writeValueAsString()
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", nonExistingId)
			.header("Authorization", "Bearer " + accessToken)		
			.content(jsonBody) //Informa o corpo da requisição
			.contentType(MediaType.APPLICATION_JSON) //Define o tipo do corpo da requisição que também vai ser do tipo JSON
			.accept(MediaType.APPLICATION_JSON)); 
	
		//Verifica se a resposa veio OK com os valores que deveriao atualizar no banco
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
