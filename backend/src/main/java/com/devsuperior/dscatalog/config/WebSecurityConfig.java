package com.devsuperior.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Essa outra sobrecarga configura qual é o Algorítmo que criptografa a senha e tem que configurar também quem é o UserDetailsService
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); //Dessa forma o Spring Security quando for fazer a autenticação saberá como buscará o usuário por EMAIL atravéz do userDetailsService e como ele terá que analizar a senha criptografada atravéz do passwordEncoder 
	}

	//Configuração para liberar todos os caminhos da aplicação
	@Override
	public void configure(WebSecurity web) throws Exception { //actuator é outra biblioteca que o Spring Cloud OAuth2 utiliza para passar nas requisições
		web.ignoring().antMatchers("/actuator/**");
	}

	@Override
	@Bean //Devemos sobrescrever o método e adicionar o annotations @Bean de forma explicita para que o AutenticationManager seja também um componente disponível no sistema para quando for implementar o AutenticationServer ele esteja disponível pois será preciso utiliza-lo
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
}
