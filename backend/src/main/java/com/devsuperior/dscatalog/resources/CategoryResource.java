package com.devsuperior.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories") // -> Podemos colocar qual a rota REST do recurso. Adotar padrão do recurso no plural
public class CategoryResource { 
	
	//Cria uma DEPENDENCIA com o CategoryService
	@Autowired
	private CategoryService service;
	
	//OPERAÇÕES DE CRUD:
	
	//Busca
	//Primeiro EndPoint, ou seja, a primeira rota que responderá alguma coisa
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAll(
			//Configura o valor do parâmetro com o annotation @RequestParam. E é diferente de @PathVariable, pois é os parâmetros que vai na URL com a barra e serve melhor para dados obrigatórios, já RequestParam é melhor para dados Opcionais na URL.
			//PAGE = Define o número da página inicial, por padrão começa no 0
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			
			//LINES_PER_PAGE = Quantidades de Registros por Página
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			
			//DIRECTION = Será a ordem de ordenação podendo ser DECRESCENTE = DESC ou ASCENDENTE = ASC.
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			
			//ORDER_BY = Nome do atributo no qual será ordenado a busca
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) { //findAll -> Busca todas as categorias
		
		//.of() é um método builder para definir os parâmetros de instânciação
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<CategoryDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list); 
	}
	
	//Segundo EndPoint que vai responder uma categoria por ID
	@GetMapping(value = "/{id}") //Vai acrescentar o "id" na frente da ROTA BÁSICA definida acima "/categories" ficando então "categories/id
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) { //O Annotation @PathVariable faz com que o id passado como parâmetro case com o id passado na rota
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto); 
	}
	
	//INSERE
	@PostMapping //Por padrão no REST para inserir um novo recurso temq ue utilizar o método POST
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) { //@RequestBody -> Para que o objeto enviado na requisição case com o Objeto declarado no método
		dto = service.insert(dto);
		//Quando se obtem o cód 201 de objeto criado no .created() convém também retornar um HEADER contendo o endereço. Aproveitando os recursos do FrameWork Spring é possível fazer dessa forma:
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto); //Código padrão de RECURSO CRIADO COM SUCESSO é o 201
	}
	
	@PutMapping(value = "/{id}") //Para atualizar utiliza o método PUT, sendo ele um método não indepotente, a annotation é a: @PutMapping
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) { //No caso de atualizar um elemento será uma junção dos métodos de busca e inserção acima, pois tem que BUSCAR o ID do produto que será atualizado e depois INSERIR os novos dados
		
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) { 
		
		service.delete(id);
		return ResponseEntity.noContent().build(); //Retorna o código 204 que é um código HTTP que quer dizer que a deleção deu certo e o corpo da resposta esta vazia
	}
}
/*
**************PRIMEIRO RECURSO REST DA APLICAÇÃO****************

* RESOURCE:
	- Implementa o controlador REST

* API = Aplication Programe Interface
	- São os recursos que serão disponibilizados para as aplicações utilizarem (WEB, MOBILE, ETC...)
	- A API é implementada por meio dos CONTROLADORES REST
		- O termo conceitual para esses recursos é RESOURCE
		- É como se os recursos fosse o conceito e os controladores a forma de se implementar esses conceitos
		
* CONVENÇÃO DE NOMES = CategoryResourse
	- Por convenção sempre que um recurso tem a ver com uma entidade, se coloca o nome da entidade depois a palavra resource(Recurso) = CategoryResource.
	- Isso identifica que será um recurso para essa classe Category que é uma entidade do projeto

* ANNOTATIONS:
	- Para se configurar no Spring que essa classe CategoryResource será um CONTROLADOR REST
		- utiliza-se um annotation: @RestController
	
	- Um annotation é uma forma enxuta e simples de configurar alguma coisa no código, ou seja, algo que já esta implementado(já existe) no Spring
		- Por exemplo os desenvolvedores do SPRING já implementou nesse annotation tudo o que for necessário para transformar uma classe em um recurso REST
		- Então para utilizar a estrutura implementada basta utilizar o annotation @RestController
	
	- O annotation então irá efetuar um pré-processamento em segundo plano no momento da execução para essa classe se tornar um recurso rest e ser disponibilizada como tal
	- Spring utiliza muito annotation para o código ficar mais limpo e não precisar ficar implementando muito lógica de programação de forma manual no código
	
* ENDPOINT:
	- É uma rota que responderá alguma coisa
	- Por exemplo:
		- Nesse recurso /categories teremos várias rotas EndPoint como:
			- Salvar uma categoria
			- Editar uma categoria
			- Buscar uma categoria
			- etc...

* RESPONSE ENTITY:
	- É um Objeto do Spring que encapsula uma resposta HTTP
	- É um tipo Generac
		- Podendo definir entre <> qual é o tipo de dados que terá no corpo da resposta
			- Exemplo: ResponseEntity<Category>
	
	- Para Instanciar o ResponseEntity utiliza-se os próprios builders dele:
		- .ok() -> Ele deixa responder um resposta do tipo 200 que significa que a requisição foi realizada com sucesso
		- .body() -> Define o corpo da resposta que no caso do primeiro EndPoint é a variável list
		- .ok() -> Também aceita a declaração do corpo diretamente por meio de uma sobrecarga
		- No caso do primeiro EndPoint como foi criado uma lista de categorias o tipo do ResponseEntity deverá ser uma lista de categorias também
			- ResponseEntity<List<Category>>
	
 	- Para configurar que o método findAll é um WEB SERVICE/ENDPOINT da classe CATEGORY:
		- Devemos utilizar outro annotation:
			- @GetMapping
			
------------------------------------------------------------------------------------------------------------------------------------------------------------------------
* PARTE I: Por fim nessa aula ir no postman e realizar uma requisição GET: http://localhost:8080/categories com a aplicação executando.
	- No postman terá que aparecer ao executar a requisição as duas categorias que já irá aparecer em formato JSON:
	[
    	{
        	"id": 1,
        	"name": "Books"
    	},
    	{
        	"id": 2,
        	"name": "Eletronics"
    	}
	]
			
------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
* Integrando o banco de dados H2
 - Será o banco de dados de TESTE que será utilizado nesse projeto inicial do SpringBoot
	
*/