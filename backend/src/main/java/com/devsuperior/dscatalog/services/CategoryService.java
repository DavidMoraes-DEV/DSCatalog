package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service 
public class CategoryService {

	/*
	* Para que esse método findAll da classe CategoryService consiga acessar o Repository e chamar as categorias lá do banco de dados é preciso primeiramente
		- Criar uma dependencia com o CategoryRepository
		- E para injetar a dependência no método é precisa também ir na classe "CategoryRepository" e colocar um annotation de componentização @Repository
			- Para registrar ele como um componente injetável pelo mecanismo de injeção de dependência do Spring
	*/
	@Autowired //Responsável por possibilitar que seja injetada automaticamente uma instancia gerenciada pelo Spring
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //Garante a integridade da transação, pois o Framework envolve tuda a operação em uma transação. E readOnly = true evita que faça o lock no banco de dados, ou seja não precisa travar o banco apenas para fazer uma leitura
	public List<CategoryDTO> findAll() {
		//Após colocar o Annotation @Autowired na dependencia para injetar automaticamente esse dependencia é possível acessar os métodos do repository
		List<Category> list = repository.findAll();
		
		//Converte o tipo Category em um novo tipo CategoryDTO
		List<CategoryDTO> listDto = list.stream()
				.map(x -> new CategoryDTO(x))
				.collect(Collectors.toList());
		
		return listDto;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		//Optional é uma abordagem para evitar trabalhar com valor NULO
		Optional<Category> obj = repository.findById(id);
		//Category entity = obj.get(); //O método . get() do Optional obtem o objeto que esta dentro do Optional
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found")); //Para conseguir tratar a excessão caso o ID buscado não exista e de o erro 500 no banco de dados para não mostrar o erro 500 utilizamos o orElseThrow
		return new CategoryDTO(entity);
	}	
}