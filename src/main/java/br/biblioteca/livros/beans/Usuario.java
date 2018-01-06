package br.biblioteca.livros.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Login é obrigatório.")
	@Size(min = 3, max = 20, message = "Login deve ter entre 3 a 20 caracteres.")
	private String username;
	
	@NotNull(message = "Senha é obrigatório.")
	@Size(min = 6, max = 20, message = "Senha deve ter entre 6 a 20 caracteres.")
	private String passsword;
	
	@OneToMany(mappedBy="usuario")
	private List<Review> reviews = new ArrayList<>();
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}
	
	
	
	
	
	

}
