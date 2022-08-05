package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository //Ao colocar esse Annotation os Objetos da interface CategoryRepository passam a ser gerenciados pelo Spring
public interface ProductRepository extends JpaRepository<Product, Long>{

	/*
	* Filtra os produto por categoria utilizando o JQPL com um macete de utilizar o INNER JOIN e IN.
		- Dando um apelido para o Product (obj)
		- E outro apelido para o obj.categories
		- Então essa parte do código JPQL colocando: 
			- Product obj INNER JOIN obj.categories catg
				- O apelido (catg) para a coleção de categorias dos produtos é essencial para funcionar a operação
				- Se não colocar o apelido para as categorias irá dar um ERRO DE SINTAX de SQL
					- Coloca o Product com sua categorias
					- Forçando o JPA a fazer uma consulta para fazer o INNER JOIN das duas tabelas
	*/
	@Query("SELECT obj FROM Product obj INNER JOIN obj.categories catg WHERE "
			+ ":category IN catg")
	Page<Product> findAllProductCategory(Category category, Pageable pageable);
}