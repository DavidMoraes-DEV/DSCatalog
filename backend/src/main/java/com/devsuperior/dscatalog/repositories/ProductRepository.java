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
			
		- FORMA NORMAL CONJUNTIVA:
			- É um conjunto de condições unidas por E(AND) e não se mistura E(AND) com OU(OR), porém cada consição UNIDAS por AND pode ter OR dentro da condição entre ()
				- VANTEGEM: Como a condição AND para resultar em FALSO basta uma das condições der FALSO o restante da consulta nem será executada pois já resultará em FALSO
					- quando uma das condições der FALSO como por exemplo a PRIMEIRA consulta já der FALSO o restante não será executada
		
		- Para EVITAR o erro de não buscar os produtos devido os parâmetros ter letras maúsculas e minúsculas colocamos o UPPER ou LOWER para
			- converter tudo para maísculo ou minúsculo evitando não encontrar a palavra buscada mesmo que exista devido a diferença entre as letras maíusculas e minúsculas

		- Para buscar palavrar com caracteres especiais como espaço em branco ou @ do email e etc.
			- Utiliza-se o encondeURIComponent do JAVASCRIPT para converter esses caracteres especiais em encode
				- EXEMPLO: 
					- Buscar (PC Gamer)
						- Utilizaríamos o let x = encodeURIComponent("PC Gamer"), que ficaria então:
							- "PC%20Gamer" ao chamar a variável "x"
					
					- Buscar ("maria@gmail.com")
						- Utilizando o let y encodeURIComponent("maria@gmail.com") ficaria:
							- "maria%40gmail.com" ao chamar a variável "y"
							
			- IMPORTANTE: Na consulta já passar ENCODADO tipo: PC%20Gamer
		
		- SE Não informar o nome opcional como "PC Gamer" e a consultar vier VAZIA com o LIKE irá funcionar porém se houver vários espaços em branco ao enviar a consulta retornará uma lista VAZIA novamente
			- Para tratar isso podemos utilizar o Trim na hora de passa-lo do Resource/Controller para o Service, pois ele serve:
				- Para remover espaços em branco antes e depois da String enviada para consulta
				- Então se a consulta for eviada apenas com espaços em branco o Trim irá remover eles e ficará vazio onde o LIKE irá executar sem problemas e retornará os produtos independente do nome específico para buscar
						
	*/
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories catg WHERE "
			+ "( :category IS NULL OR :category IN catg) AND"
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')) )")
	Page<Product> findAllProductCategory(Category category, String name, Pageable pageable);
}