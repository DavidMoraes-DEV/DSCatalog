package com.devsuperior.dscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	//Constantes por questão de organização definindo as rotas
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"}; //Rota para os endPointers PUBLICOS(Acesso para todos sem a necessidade de já ter feito login
	private static final String[] OPERATOR_OR_ADMIN = {"/products/**", "/categories/**"}; //Rotas para ser liberadas apenas para usuários que tem os perfis de operador e admin
	private static final String[] ADMIN = {"/users/**"}; //Rotas específicas para usuários com o perfil admins
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//Evita o erro do H2 ao definir autorizações para as rotas e exigências de login
		//Se lê: SE nos profiles ativos contém o profile TEST irá executar o código para liberar o H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) { //Verifica se estiver rodando um profile TEST precisaremos liberar o H2
			http.headers().frameOptions().disable(); //Se o teste do if for verdadeiro irá liberar os frames do H2 para conseguirmos acessa-lo pelo localhost:8080
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() //antMatchers() é o método que define as autorizações passando a rota deseja, .permitAll() permite todos os acessos não exigindo a realização de login
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() //HttpMethod -> Define a liberação de acesso sem exigir login apenas quando for os métodos GETS como por exemplo no acesso ao menu dos produtos que apenas busca eles no banco de dados para ser exibidas na página. A acesso total .permitAll() é definido depois, apenas para os OPERATOR ou ADMIN
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN") //.hasAnyRole() Define o acesso apenas a usuários que possui algum dos dois perfis definidos dentro do método
		.antMatchers(ADMIN).hasRole("ADMIN") //Define que pode acessar a rota USERS apenas os usuários que possui perfil ADMIN
		.anyRequest().authenticated(); //anyRequest() -> Define que qualquer outra rota que não foi especificado antes nesse necessário ter feito o LOGIN definido ao utilizar o .authenticated()
	}

	//Configura o trabalho com o token do Resource Serve, sendo capaz de codificar esse token e analiza-lo por exemplo se esta expirado e etc...
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore); 	
	}
	
	
	
}
