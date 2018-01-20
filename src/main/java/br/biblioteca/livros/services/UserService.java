package br.biblioteca.livros.services;

import br.biblioteca.livros.beans.Usuario;
import java.util.*;

public interface UserService {

	void save(Usuario user);


	List<Usuario> findAll();

	Usuario findByUsername(String username);

}
