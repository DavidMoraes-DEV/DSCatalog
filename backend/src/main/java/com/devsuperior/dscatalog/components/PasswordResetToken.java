package com.devsuperior.dscatalog.components;

public class PasswordResetToken {

	private String token;

	public PasswordResetToken() {
	}
	
	public PasswordResetToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
