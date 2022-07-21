package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Classe de configuração dentro de um projeto Spring são responsáveis por manter alguma configuração, criar algum  componente específico e etc...
@Configuration
public class AppConfig {

	//@Beam: É um componente do Spring, porém ao invés de ser um annotation de classe como o @Service ele é um annotation de métodos
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //Retorna uma instancia de BCryptPasswordEncoder com o annotation Bean dizendo para o Spring que essa instância será um componente gerenciado pelo SpringBoot, podendo injeta-lo em outras classes/componentes
	}
}
