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
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service 
public class CategoryService {

	/*
	* Para que esse método findAll da classe CategoryService consiga acessar o Repository e chamar as categorias lá do banco de dados é preciso primeiramente
		- Criar uma dependencia com o CategoryRepository
		- E para injetar a dependência no método é preciso também ir na classe "CategoryRepository" e colocar um annotation de componentização @Repository
			- Para registrar ele como um componente injetável pelo mecanismo de injeção de dependência do Spring
	*/
	@Autowired //Responsável por possibilitar que seja injetada automaticamente uma instancia gerenciada pelo Spring
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) //Garante a integridade da transação, pois o Framework envolve toda a operação em uma transação. E readOnly = true evita que faça o lock no banco de dados, ou seja não precisa travar o banco apenas para fazer uma leitura
	public Page<CategoryDTO> findAllPaged(Pageable pageable) {
		//Após colocar o Annotation @Autowired na dependencia para injetar automaticamente esse dependencia é possível acessar os métodos do repository
		Page<Category> list = repository.findAll(pageable);
		
		//Converte o tipo Category em um novo tipo CategoryDTO
		Page<CategoryDTO> listDto = list.map(x -> new CategoryDTO(x));
		
		return listDto;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		//Optional é uma abordagem para evitar trabalhar com valor NULO
		Optional<Category> obj = repository.findById(id);
		//Category entity = obj.get(); //O método . get() do Optional obtem o objeto que esta dentro do Optional
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); //Para conseguir tratar a excessão caso o ID buscado não exista e de o erro 500 no banco de dados para não mostrar o erro 500 utilizamos o orElseThrow
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category();
		entity.setName(dto.getName());
		
		entity = repository.save(entity); //Por padrão o método . save() retorna uma referência para a entidade salva
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id); //Cria um objeto provisório para evitar acessar o banco duas vezes
			entity.setName(dto.getName()); //Atualiza os dados da entidade provisória que esta apenas na memória
			entity = repository.save(entity); //Salva os dados no banco
			return new CategoryDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	//Para o método DELETE não será preciso colocar o Annotation @Transactional, porque vamos capturar uma exceção que vai vim lá do banco de dados e se colocarmos o annotation não será possível capturar essa exceção
	public void delete(Long id) {
		/*
		 * Alguns problemas da DELEÇÃO:
		 	- Tentar deletar um objeto que não existe no BANCO irá acarretar um ERRO por isso iremos utilizar o try-catch e não utilizar a annotation Transactional
			- Problema muito comum em aplicação que acessa banco de dados:
				- Excluir a CATEGORIA de produtos
				- Ficando então produtos sem categoria o que causa um problema de INTEGRIDADE REFERENCIAL, pois os produtos dessa aplicação tem que ter uma categoria conforme diagrama do projeto
		*/
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation");
		}
	}
}