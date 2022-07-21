package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

//Classe das categorias dos produtos
@Entity  //Esse annotation indica que essa classe é uma entidade de domínio que vai corresponder a uma tabela
@Table(name = "tb_category") //Essa annotation indica que será renomeado a tabela para o valor definido entre () quando for criado a tabela no banco de dados
public class Category implements Serializable{ //Serializable -> É o padrão do JAVA para o objeto ser convertido em sequência de bytes para que o objeto seja possível de se gravar em arquivos, trafegar na rede e etc...
	private static final long serialVersionUID = 1L; //Atributo padrão da interface SERIALIZABLE
	
	//AtributOs
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Annotation que define o Id para ser auto-incrementada
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Esse annotation define que será armazenado o instante no padrão UTC e não no formato do horário local GMT
	private Instant createdAt; //Atributo do instante que o registro foi criado pela primeira vez
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") 
	private Instant updateAt;
	
	@ManyToMany(mappedBy = "categories") //Vincula um dado obj do tipo category para ter condição de dar um .getProducts para acessar os produtos dessa categoria, pois o JPA vai no banco e busca esses produtos
	private Set<Product> products = new HashSet<>();
	
	//Construtores
	public Category() {
	}
	
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
	
	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	//Método auxiliar para que sempre que for chamado o metodo .save() antes de salvar a variável createdAt vai receber o instante atual que foi criado o objeto
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}
	
	//Método auxiliar para que sempre que for chamado o metodo .save() antes de salvar a variável updateAt vai receber o instante atual que foi atualizado o objeto
	@PreUpdate
	public void preUpdate() {
		updateAt = Instant.now();
	}
	
	public Set<Product> getProducts() {
		return products;
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