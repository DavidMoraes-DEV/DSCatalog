package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

//Classe das categorias dos produtos
public class Category implements Serializable{ //Serializable -> É o padrão do JAVA para o objeto ser convertido em sequência de bytes para que o objeto seja possível de ser grava em arquivos trafegar na rede e etc...
	private static final long serialVersionUID = 1L; //Atributo padrão da interface SERIALIZABLE
	
	//Atributos
	private Long id;
	private String name;
	
	//Construtor
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Parâmetro de comparação entre duas categorias de produtos com equals e hashCode comparando apenas por ID, ou seja, a categoria será igual se o id for igual independente do nome
	@Override
	public int hashCode() { //É rápido porém raramente objetos diferentes podem gerar o mesmo código hashCode então não é 100%
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) { //É lento porém a comparação é 100% garantida não gera igualdade se os objetos for diferentes, por isso utiliza-se por padrão o hashCode para comparar e o equals para confirmar
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

}