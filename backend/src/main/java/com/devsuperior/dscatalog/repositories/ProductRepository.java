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
			
			- Para evitar o erro de buscar uma lista VAZIA quando a categoria for 0 ou NULL(Quando não ESPECIFICAR o ID da categoria na consulta)
				- Utiliza-se o IS NULL OR
					- Para que se der verdadeiro nem irá executar o restante pois já foi definido que a categoria é nula 
						- Não buscará os produtos com as categorias e retornará apenas os produtos com as categorias VAZIAS
						- Ficando então a consulta: WHERE ( :category IS NULL OR :category IN catg)
							- Mas nesse caso retornará uma LINHAA A MAIS pois o JOIN volta as linhas repetidas
							- Para EVITAR ESSAS REPETIÇÕES utilizamos a claúsula DISTINCT para não haver repetições de produtos
						
	*/
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories catg WHERE "
			+ "( :category IS NULL OR :category IN catg)")
	Page<Product> findAllProductCategory(Category category, Pageable pageable);
}