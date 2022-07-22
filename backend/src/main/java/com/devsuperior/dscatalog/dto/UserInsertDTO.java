package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;

//Classe que herda de UserDTO, ou seja tem todas as funcionalidades do UserDTO porém com a adição de colocar possuir também o atributo password
@UserInsertValid //Essa Annotation que vai processar em segundo plano a verificação se o email inserido já existe no banco, aproveitando todo o ciclo de vida da validação que estamos utilizando
public class UserInsertDTO extends UserDTO {
	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserInsertDTO () {
		super();
	}

	public UserInsertDTO(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
