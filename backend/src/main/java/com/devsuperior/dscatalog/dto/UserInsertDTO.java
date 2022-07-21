package com.devsuperior.dscatalog.dto;

//Classe que herda de UserDTO, ou seja tem todas as funcionalidades do UserDTO porém com a adição de colocar possuir também o atributo password
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
