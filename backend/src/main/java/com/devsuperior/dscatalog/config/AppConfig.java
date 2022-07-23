package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//Classe de configuração dentro de um projeto Spring são responsáveis por manter alguma configuração, criar algum  componente específico e etc...
@Configuration
public class AppConfig {

	//@Beam: É um componente do Spring, porém ao invés de ser um annotation de classe como o @Service ele é um annotation de métodos
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //Retorna uma instancia de BCryptPasswordEncoder com o annotation Bean dizendo para o Spring que essa instância será um componente gerenciado pelo SpringBoot, podendo injeta-lo em outras classes/componentes
	}
	
	//Esses dois Beans: JwtAccessTokenConverter, JwtTokenStore são objetos que são capazes de acessar um Token JWT(ler, decodificar o token e criar um token codificando ele)
	//Esses dois beans serão instanciados na classe que será o AutorizationServer sendo ela nessa aplicação a classe: AuthorizationServerConfig
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("MY-JWT-SECRET"); //Registra a chave do token. Obs.: Declaração HardCode para passar o básico do funcionamento. Depois será passado para o arquivo properties
		return tokenConverter;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
