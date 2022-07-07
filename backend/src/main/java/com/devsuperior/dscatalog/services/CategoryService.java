package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

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
	public List<Category> findAll() {
		//Após colocar o Annotation @Autowired na dependencia para injetar automaticamente esse dependencia é possível acessar os métodos do repository
		return repository.findAll();
	}
	
}
