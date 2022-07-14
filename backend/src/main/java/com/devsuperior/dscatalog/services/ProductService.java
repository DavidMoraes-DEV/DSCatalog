package com.devsuperior.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service 
public class ProductService {

	@Autowired 
	private ProductRepository repository;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		
		Page<Product> list = repository.findAll(pageable);
		
		Page<ProductDTO> listDto = list.map(x -> new ProductDTO(x));
		
		return listDto;
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
		
		return new ProductDTO(entity, entity.getCategories());
	}

	//A diferença em inserir e atualizar um produto em relação com a categoria é que vai mudar os dados que serão utilizados, pois na categoria era apenas o nome e os produtos tem mais dados diferentes para incluir
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product();
		copyDtoToEntity(dto, entity); //Utilizamos então um método auxiliar para realizar a cópia dos dados do produto para não utilizar vários SETs nesse método
		
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id); 
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			return new ProductDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation");
		}
	}
	
	//Associa categorias que é uma entidade dentro de outra entidade que é PRODUCT para que sejá salva(inserida) ou atualizadas
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		//Não se copia o Id, porque não se troca ele na hora de ATUALIZAR e também não informa ele na hora de INSERIR, por isso o id não entra no método de copiar os dados
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear(); //Limpa o conjunto de categorias que podem estar na entidade
		for(CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category); //Coleção do tipo Product pronta para ser inserida ou atualizada
		}
	}
}