package com.devsuperior.dscatalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//QUERY METHODS: Mais utilizado quando for consultas simples igual os dois exemplo abaixo. Quando for consultas mais complexas aí é bom utilizar consultas customizadas utilizando o annotation: @Query 
	//Busca no banco um usuário por email
	public User findByEmail(String email); 
	
	//Busca todos o primeiros nomes de usuários no banco utilizando o padrão (findBy)Nome do atributo da classe desejada
	List<User> findByFirstName(String firstName);
}
