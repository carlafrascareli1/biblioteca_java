package br.biblioteca.livros.testes;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.*;
import br.biblioteca.livros.repository.*;

import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationAutoresTests {
	
	@Autowired
	AutorRepository autorRepository;

	@Before
	public void setUp() {
	}
	
	@Test
	public void buscaAutoresCadastrados() {
		
		Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
		assertThat(autores.getTotalElements()).isGreaterThan(1L);
		
	}
	
	@Test
	public void buscaAutorShakespeare() {
		Autor autorEncontrado = this.autorRepository.findByNome("William Shakespeare");
		assertThat(autorEncontrado).isNotNull();
		assertThat(autorEncontrado.getNome()).isEqualTo("William Shakespeare");
	}
	
	@Test
	public void insereAutorFernandoPessoa() {
		Autor autorCadastrado = new Autor();
		autorCadastrado.setNome("Fernando Pessoa");
		autorCadastrado.setId(new Long(6));
		
		this.autorRepository.save(autorCadastrado);
		
		Autor autorEncontrado = this.autorRepository.findByNome("Fernando Pessoa");
		assertThat(autorEncontrado).isNotNull();
		assertThat(autorEncontrado.getNome()).isEqualTo("Fernando Pessoa");
	}
	
	
	@Test
	public void deleteAutorAugustoCury() {
		Autor autorCadastrado = this.autorRepository.findByNome("Augusto Cury");
		this.autorRepository.delete(autorCadastrado);
		
		Autor autorNaoEncontrado = this.autorRepository.findByNome("Augusto Cury");
		assertThat(autorNaoEncontrado).isNull();
	}
}
