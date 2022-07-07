package com.devsuperior.dscatalog;

public class ConceitosImportantes {
}
/*
 
* DTO = Objetos Auxiliares para trafegar os dados

* spring-boot-starter-data-jpa (No arquivo POM.XML)
	- Vem implementado pronto para uso no SubProjeto Spring Data JPA que é definido como dependência no Arquivo POM.XML para ser importado
	- Traz uma implementação do acesso a dados básico 

* Na INTERFACE CategoryRepositery:
	- JpaRepository<T, ID>: É um tipo genérico, esperando 2 parâmetros <T(Tipo da Entidade), ID(Tipo do Id)
		- Nesse caso o tipo da entidade é Category e o tipo do ID é long conforme definido na entidade Category
		- Apenas com essa definição de INTERFACE herdando/extendendo de JpaRepository ja obtemos muitas operações prontas para acessar o banco de dados
		- Funciona para qualquer banco de dados relacional que tenha implementação da JPA no Spring
			- ou seja, tudo que for programado aqui pelo banco de dados de teste H2 funcionará oara outros bancos de dados como o MySQL, POSTMAN, ORAGLE e etc

* Para mais informações de PADRÃO CAMADAS assistir o vídeo do prof Nélio: back end, front end, padrão camadas, MVC, REST no Youtube

* CAMADA DE SERVIÇO
	- É a camada que vai chamar alguma funcionalidade da CAMADA DE ACESSO A DADOS

* Annotation @Service:
	- irá registrar essa classe como um componente que irá participar do sistema de injeção de dependencia automatizada do Spring
	- Ou seja: Quem vai gerenciar as instâncias das dependencias da classe Category vai ser o Spring(Todo frameWork moderno tem isso, algum mecanismo de injeção de dependencia automatizada)

* Annotation @Component:
	- Registra um componente genérico que não tenha algum significado específico
	- Também é um mecanismo de Injeção de dependencia Automatizada

* Annotation @Repository
* 	- Registra um repositório também para o mecanismo de Injeção de dependencia Automatizada	
	
*	

*/