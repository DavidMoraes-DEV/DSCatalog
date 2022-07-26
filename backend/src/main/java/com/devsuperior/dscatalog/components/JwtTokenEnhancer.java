package com.devsuperior.dscatalog.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userRepository.findByEmail(authentication.getName());
		
		//Define as informações desejadas para ser adicionadas ao TOKEN com o MAP
		Map<String, Object> map = new HashMap<>();
		map.put("userFirtName", user.getFirstName()); //Adiciona o primeiro nome
		map.put("userId", user.getId()); //Adiciona o Id do usuário
		
		//Fazer um DownCatch pois apenas o tipo específico DefaultOAuth2AccessToken que possui o método .setAdditionalInformation() para adicionar as informações ao token
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accessToken;
		
		token.setAdditionalInformation(map); //Adiciona as informações definidas no MAP ao TOKEN
		
		//Para que essas informações sejam válidas é preciso atualizar o authorizationService
		return token;
	}

}
