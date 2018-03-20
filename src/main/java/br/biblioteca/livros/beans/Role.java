package br.biblioteca.livros.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;;

@Entity
public class Role {
	
	@Id
	String role;
	
	@ManyToMany
	private List<Usuario> usuario;
	
	public Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + "]";
	}


}
