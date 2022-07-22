package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserUpdateValid;

//Classe que herda de UserDTO, ou seja tem todas as funcionalidades do UserDTO porém com a adição de colocar possuir também o atributo password
@UserUpdateValid //Essa Annotation que vai processar em segundo plano a verificação se o email inserido já existe no banco, aproveitando todo o ciclo de vida da validação que estamos utilizando
public class UserUpdateDTO extends UserDTO {
	private static final long serialVersionUID = 1L;
	
}
