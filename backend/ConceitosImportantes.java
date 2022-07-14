package com.devsuperior.dscatalog.entities;

public class ConceitosImportantes {
}
/*
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
PARTE 1 - CRIAÇÃO DE UM CRUD 
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
PARTE 2 - TESTES AUTOMATIZADOS
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* RESUMO:
		
* Fundamentos de testes automatizados:
	- Tipos de testes
	- Benefícios
	- TDD - Test Driven Development
	- Boas práticas e padrões

* JUnit
	- Principal BIBLIOTECA para testes automatizados no JAVA
	- Básico (Vanilla: Sem utilizar Framework)
	- Spring Boot (Utilizando framework)
		- Repositories
		- Services
		- Resources (web)
		- Integração

* Mockito & MockBean
	- Faz objetos simulados para teste de unidade que não pode instanciar um outro componente que é dependencia. Então devemos simular o comportamento da dependencia, fazendo isso mockando os objetos
	- @Mock
	- @InjectMocks
	- Mockito.when / thenReturn / doNothing / doThrow
	- ArgumentMatches
	- Mockito.verify
	- @MockBean
	- @MockMvc	

* Tipos de testes:
	- UNITÁRIO:
		- Teste feito pelo desenvolvedor, responsável por validar o comportamento de unidades funcionais de código.
		- Nesse contexto, entende-se como unidade funcional qualquer porção de código que  através de algum estímulo seja capaz de gerar um comportamento esperado (na prática: métodos de uma classe).
		- Um teste unitário não pode acessar outros componetes ou recursos externos (arquivos, bd, rede, web services, etc).
	
	- INTEGRAÇÃO:
		- Teste focado em verificar se a comunicação entre componeentes / módulos da aplicação, e também recursos externos, estão interagindo entre si corretamente.
	
	- FUNCIONAL:
		- É um teste do ponto de vista do usuário (caso de uso), se uma determinada funcionalidade está executando corretamente, produzindo resultado ou comportamento desejado pelo usuário.

* BENEFÍCIOS DOS TESTES:
	- Detectar facilmente se mudanças violaram as regras.
	- É uma forma de documentação (comportamento e entradas/saídas esperadas)
	- Redução de custos em manutenções, especialmente em fases avançadas
	- Melhora design da solução, pois aplicação testável precisa ser bem delineada

* TDD - Test Driven Development
	- É um MÉTODO DE DESENVOLVER SOFTWARE. Consiste em um desenvolvimento guiado pelos testes.
		- IMPORTANTE ENTENDER:
			- Não é pelo fato de que o software possui testes automatizados que necessariamente ele foi desenvolvido usando o TDD como método de trabalho.
			- TDD então diz respeito a forma como a equipe vai trabalhar, e não se o projeto tem ou não testes automatizados e se bons ou não
			- TDD é um procedimento
	- Princípios / vantagens:
		- Foco nos requisitos
			- Por que o TDD faz com que na hora de desenvolver um projeto que tem testes, PRIMEIRO se escreve os TESTE e depois faz os CÓDIGOS e a LÓGICA que faz aqueles testes funcionarem.
			- Então ao se preocupar em primeiro descrever os testes estamos se preocupando com o que que tem que acontecer e automatizamente é uma forma de escrever os requisitos do sistema.
		
		- Tende a melhorar o design do código, pois o código deverá ser testável
			- Quando esta desenvolvendo os testes preocupando que eles tem que existir para depois implementar o código e na hora de implementar a funcionalidade vai ter que ser implementada de uma forma que aquele componente implementado seja testável se preocupando com inverção de controle, injeção de dependência de forma desacoplada e etc
		
		- Incrementos no projeto têm menos chance de quebrar a aplicação
			- Fazendo os testes automatizados quando alterar a aplicação, vai ter que passar nos testes novamente também sempre tendo então um código que cumpra aquelas regras e passem nos testes

* PROCESSO BÁSICO:
	- 1: Escreva o teste como esperado (naturalmente que ele ainda estará falhandoo)
		- Exemplo: Tenho uma regra e com ela vai entrar "isso" e vai acontecer "isso" e o resultado esperado vai ser "abcd"
	- 2: Implemente o código necessário para que o teste passe
	- 3: Refatore o código conforme necessidade

* BOAS PRÁTICAS E PADRÕES:
	- Nomenclatura de um teste:
		- <AÇÃO> should <EFEITO> [when <CENÁRIO>]
		
	- Padrão AAA:
		- Arrange: instancie os objetos necessários
		- Act: Execute as ações necessárias
		- Assert: Declare o que deveria acontecer (resultado esperado)
	
	- Princípios da inversão de dependência (SOLID)
		- Se uma classe A depende de uma instância da classe B, não tem como testar a classe A isoladamente. Na verdade nem seria um teste unitário.
		- A inversão de controle ajuda na testabilidade, e garante o isolamento da unidade a ser testada.
		
	- Independência / isolamento
		- Um teste não pode depender de outros testes, nem da ordem de execução
		
	- Cenário único
		- O teste deve ter uma lógica simples, linear
		- O teste deve testar apenas um cenários
		- Não use condicionais e loops
		
	- Privisibilidade
		- O resultado de um teste deve ser sempre o mesmo para os mesmos dados
		- Não faça o resultado depender de coisas que variam, tais como timestamp atual e valores aleatórios
		
* JUnit - VISÃO GERAL:

	- Site oficial: junit.org/junit5
	- O primeiro passeo é criar uma classe de testes
	- A classe pode conter um ou mais métodos com a annotation @Test (É a annotation que identifica que é um teste)
	- Um método @Test DEVE ser VOID
	- O objetivo é que todos métodos @Test passem sem falhas
	- O que vai definir se um método @Test passa ou não são as "assertions" deste método
	- Se um ou mais falhas ocorrereem, estas são mostradas depois da execução do teste
	
* ANNOTATIONS USADAS NAS CLASSES DE TESTE COM SPRING BOOT
	- @SpringBootTest
		- Carrega o contexto da aplicão (teste de integração)
		- É um teste mais lendo pois carrega todo o contexto da aplicação
	
	- @SpringBootTest e @AutoConfigureMockMvc
		- Carrega o contexto da aplicação (teste de integração & web)
		- Porém trata as requisições sem subir no servidor
		- Utilizado quando precisa fazer teste de integração na camada web sem precisar subir o TomCat
	
	- @WebMvcTest(Classe.class)
		- Carrega o contexto, porém somente da camada web (teste de unidade: controlador)
		- Quando precisa testar um controlar da aplicação, um resource ou controller que irá carregar somente o contexto necessário para a camada WEB, não carreg por exemplo os componentes services, repositories.
		- Deixando o teste mais leve para poder testar as coisas da web sem precisar carregar todo o contexto
		
	- @ExtendWith(SpringExtension.class)
		- Não carrega o contexto, mas permite usar os recursos do Spring com JUnit (teste de unidade: service/component)
		- Utilizada para testes de forma bem rápida e leve sem carregar o contexto da aplicação com Spring Boot
		
	- @DataJpaTest
		- Carrega somente os componentes relacionados ao Spring Data JPA, cada teste é transacional e dá rollback ao final. (teste de unidade: repository)
		- Podendo testar os repositories, por exemplo testando uma uma consulta com o Spring H2, carregando apenas o que é relacionado ao Spring Data JPA
		- Sendo cada teste trasacional, ou seja, ele executa o que tem que executar e acessa o banco de dados e no final da um rollBack, sendo utilizado para testar os repositories

* FIXTURES:
	- É uma forma de organizar melhor o código dos testes e evitar repetições
	- JUnit 5
		- @BeforeAll
			- Preparação antes de todos testes da classe (método estático)
		
		- @AfterAll
			- Preparação depois de todos testes da classe (método estático)
		
		- @BeforeEach
			- Preparação antes de cada teste da classe
		
		- @AfterEach
			- Preparação depois de cada teste da classe



*/
