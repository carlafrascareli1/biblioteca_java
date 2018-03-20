package br.biblioteca.livros.testes;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.*;
import br.biblioteca.livros.services.UserServiceImpl;

import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationUsuariosTests {
	
	@Autowired
	UserServiceImpl usuarioRepository;

	@Before
	public void setUp() {
	}
	
	@Test
	public void buscaUsuariosCadastrados() {
		
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		assertThat(usuarios.size()).isGreaterThan(0);
	}
	
	@Test
	public void buscaUsuarioCarla() {
		Usuario usuarioEncontrado = this.usuarioRepository.findByUsername("carla");
		assertThat(usuarioEncontrado).isNotNull();
		assertThat(usuarioEncontrado.getUsername()).isEqualTo("carla");
	}
	
	@Test
	public void insereUsuarioMaria() {
		Usuario usuarioCadastrado = new Usuario();
		usuarioCadastrado.setUsername("maria");
		usuarioCadastrado.setPasssword("aaaaaaaa");
		usuarioCadastrado.setId(new Long(3));
		
		this.usuarioRepository.save(usuarioCadastrado);
		
		Usuario usuarioEncontrado = this.usuarioRepository.findByUsername("maria");
		assertThat(usuarioEncontrado).isNotNull();
		assertThat(usuarioEncontrado.getUsername()).isEqualTo("maria");
	}
	
	
}
