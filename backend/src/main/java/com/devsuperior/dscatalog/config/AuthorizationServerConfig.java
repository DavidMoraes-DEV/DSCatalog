package com.devsuperior.dscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.devsuperior.dscatalog.components.JwtTokenEnhancer;

@Configuration //Define que será uma classe de configuração
@EnableAuthorizationServer //Define que será essa classe que fará os processamentos em segundo plano representando o AuthorizationServer do OAuth2 
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	//Será preciso esses 4 Beans que foi definido anteriormente:
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private JwtTokenStore jwtTokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenEnhancer tokenEnhancer;
	
	//Define quem que vai autenticar(Será nesse caso o authenticationManager definido na classe WebSecurityConfig) e qual será o formato do TOKEN(que nesse caso será processado pelo TokenStore)
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain chain = new TokenEnhancerChain(); //Instancia um objeto do tipo TokenEnhancerChain
		chain.setTokenEnhancers(Arrays.asList(accessTokenConverter, tokenEnhancer)); //Cria uma lista com os elementos adicionais para serem acrescentadas ao token
		
		endpoints.authenticationManager(authenticationManager) //Definindo que será o SpringSecurity que fará a autenticação
		.tokenStore(jwtTokenStore) //Objetos responsáveis por processar o TOKEN
		.accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(chain); //Adiciona as informações adicionais ao token definidas na classe JwtTokenEnhancer
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()"); //permitAll() e isAuthenticated() -> é os nome dos métodos NÃO PODE FICAR ERRADO
	}

	//É no objeto clients desse método que vai ser definido como será a autenticação e quais serão os dados do cliente, pois para enviar para o AuthorizationServer temos que passar as credenciais da aplicação que são definidas nesse metodo
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() //Define que o processo será feito em memoria. 
		.withClient("dscatalog") //Define o clientId, ou seja o nome da APLICAÇÃO, quando a aplicação WEB for acessar o backEnd ela irá procurar por esse nome definido aqui. Utilizando uma variável de ambiente definida no começo da classe (clientId)
		.secret(passwordEncoder.encode("dscatalog1234")) //Define o clientSecret, hardCode provisório. Depois será colocado também no arquivo properties. Utilizando uma variável de ambiente definida no começo da classe (clientSecret)
		.scopes("read", "write") //Define o tipo de acesso que será dados como por exemplo: acesso apenas de leitura(read) ou escrita(white) ou os dois juntos que é o que foi definido
		.authorizedGrantTypes("password") //Define o GrantTypes que o mais utilizado é o password para acesso de login
		.accessTokenValiditySeconds(86400); //Define o Tempo de expiração do token em segundos, nesse caso definimos um tempo de 24hrs ou 86400 segundos. Utilizando uma variável de ambiente definida no começo da classe (jwtDuration)
	}
	
}
