package com.devsuperior.dscatalog.resource;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import com.devsuperior.dscatalog.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private PageImpl<ProductDTO> page;
	private Product product;
	private ProductDTO productDTO;
	private String username;
	private String password;
	
	@BeforeEach
	void setUp () throws Exception {
		 
		existingId = 1L;
		nonExistingId = 100L;
		dependentId = 2L;
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO(product);
		page = new PageImpl<>(List.of(productDTO));
		username = "admin@dmdeveloper.com";
		password = "123456";
		
		when(service.findAllPaged(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page); //Simulando um comportamento
		
		when(service.findById(existingId)).thenReturn(productDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.insert(ArgumentMatchers.any())).thenReturn(productDTO);		
		
		when(service.update(ArgumentMatchers.eq(existingId), ArgumentMatchers.any())).thenReturn(productDTO);
		when(service.update(ArgumentMatchers.eq(nonExistingId), ArgumentMatchers.any())).thenThrow(ResourceNotFoundException.class);
		
		Mockito.doNothing().when(service).delete(existingId);
		Mockito.doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		Mockito.doThrow(DataBaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void findAllShoudReturnPage() throws Exception {
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products")
			.accept(MediaType.APPLICATION_JSON)); 
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void findByIsShouldReturnProductWhenIdExists() throws Exception {
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products/{id}", existingId)
			.accept(MediaType.APPLICATION_JSON)); 

		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void findByIsShouldReturnNotFouldWhenIdDoesNotExists() throws Exception {
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.get("/products/{id}", nonExistingId)
			.accept(MediaType.APPLICATION_JSON)); 

		result.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void insertShouldReturnProductDTOCreated() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		String jsonBody = objectMapper.writeValueAsString(productDTO); 
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.post("/products")
			.header("Authorization", "Bearer " + accessToken)		
			.content(jsonBody)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)); 
	
		result.andExpect(MockMvcResultMatchers.status().isCreated());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		String jsonBody = objectMapper.writeValueAsString(productDTO);
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", existingId)
			.header("Authorization", "Bearer " + accessToken)		
			.content(jsonBody)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)); 
	
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		String jsonBody = objectMapper.writeValueAsString(productDTO); 
		
		ResultActions result = mockMvc
			.perform(MockMvcRequestBuilders.put("/products/{id}", nonExistingId)
			.header("Authorization", "Bearer " + accessToken)		
			.content(jsonBody)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)); 
	
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
				
	}
	
	@Test
	public void deleteShouldReturnNotContentWhenIdExists() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/products/{id}", existingId)
				.header("Authorization", "Bearer " + accessToken)		
				.accept(MediaType.APPLICATION_JSON)); 
		
		result.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
		
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
		
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/products/{id}", nonExistingId)
				.header("Authorization", "Bearer " + accessToken)		
				.accept(MediaType.APPLICATION_JSON)); 
		
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
}
