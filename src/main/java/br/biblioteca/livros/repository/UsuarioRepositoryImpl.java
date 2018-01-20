package br.biblioteca.livros.repository;

import org.springframework.beans.factory.annotation.Autowired;


import br.biblioteca.livros.beans.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
	
	@Autowired
	UsuarioRepository usuariotRepository;

	@Override
	public Usuario findByUsername(String username) {
		Usuario user = null;

		for (Usuario u : usuariotRepository.findAll()) {
			if (u.getUsername().equals(username)) {
				user = u;
			}

		}
		
		System.out.println("lido " + user);

		return user;
	}

}
