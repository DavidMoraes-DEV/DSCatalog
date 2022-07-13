package com.devsuperior.dscatalog.entities;

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
			- ou seja, tudo que for programado aqui pelo banco de dados de teste H2 funcionará para outros bancos de dados como o MySQL, POSTMAN, ORAGLE e etc

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
 	- Registra um repositório também para o mecanismo de Injeção de dependencia Automatizada	
	
* O arquivo application.properties da pasta src/main/resources
 	- É o arquivo que tem as configurações do projeto que vão funcionar para todos os PERFIS
 
 	* O que é um PERFIL?
 		- Quando se desenvolve uma aplicação utiliza-se VÁRIOS PERFIS (Pelo menos 3 perfil diferente):
 			- Perfil de TEST
 				- Esse perfil serve para rodar um banco de dados PROVISÓRIO
 				- Toda vez que rodar a aplicação o banco de dados será REINICIADO, ZERADO e colocado no mesmo ESTADO INICIAL
 				- Isso é muito importante para teste com um banco de dados bem controlado
 				- Nesse estamos utilizando o banco de dados H2 podendo definir uma INSTANCIA INICIAL do banco (seeding) semear o dados do banco com dados iniciais
 				
 			- Perfil de desenvolvimento para TEST antes da PRODUÇÃO (PERFIL DE DEV)
 				- É um perfil que SERÁ CONECTADO igual ao banco de dados de PRODUÇÃO banco que rodará na NUVEM
 				- Por exemplo se o banco de PRODUÇÃO for o POSTGREES o perfil de DEV também será configurado para o postgrees na mesma versão
 				- Para testar o banco localmente e se der tudo certo viramos a chave para o de produção
 				
 			- Perfil de PRODUÇÃO
 				- Banco que roda na NUVEM

	- spring.profiles.active=test -> no Arquivo application.properties: Indica que o banco atualmente é do perfil do tipo TEST

* A DIFERENÇA entre o .findById() e o .getOne() é:
			- O .findById() efetiva o acesso ao banco de dados indo no banco e traz o objeto do id informado
			- O .getOne() não toca no banco de dados, ele instancia um objeto provisório com os dados e apenas quando mandar SALVAR aí sim ele vai no banco e atualiza os dados:
			- O .getOne() então evita o acesso desnecessáriode de ir no banco duas vezes com o findById() para buscar o Id e depois atualizar os dados.
		

*/