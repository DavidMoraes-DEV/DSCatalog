package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_user")
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER) //Isso força que sempre que buscar um usuário no banco já vai vim vinculado nele os perfis de usuário (Exigência do Spring Securiy)
	@JoinTable(name = "tb_user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(Long id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	//Retorna as informações de perfil que o usuário tem. O tipo de perfil dentro do SpringSecutiry chama: GrantedAuthority porém nesse projeto será apenas uma String
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //Converte a lista de perfis para uma lista de GrantedAuthority
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList());
	}

	//Retorna o identificador do usuário que nesse caso será o email mas pode ser o nome do usuário se prefirir desde que seja uma string
	@Override
	public String getUsername() {
		return email;
	}

	//Como não é prioridade validar esses testes iremos colocar tudo como TRUE, ou seja esta tudo ok com os testes
	//isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled() são métodos para controlar o usuário se tiver necessidade
	
	//Pode implementar uma lógica para verificar se a conta não esta expirada
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//Pode implementar uma lógica para verificar se o usuário esta bloqueado
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Pode implementar uma lógica para verificar se as credenciais do usuário esta expirada, como a senha espirar depois de um tempo determinado
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//Pode implementar uma lógica para verificar se o usuário esta habilitado ou não
	@Override
	public boolean isEnabled() {
		return true;
	}

}
