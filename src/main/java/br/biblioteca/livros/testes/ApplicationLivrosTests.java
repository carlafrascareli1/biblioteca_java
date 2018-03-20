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
public class ApplicationLivrosTests {
	
	@Autowired
	LivroRepository livroRepository;

	@Autowired
	AutorRepository autorRepository;

	@Before
	public void setUp() {
	}
	
	@Test
	public void buscaLivrosCadastrados() {
		
		Page<Livro> livros = this.livroRepository.findAll(new PageRequest(0, 10));
		assertThat(livros.getTotalElements()).isGreaterThan(1L);
		
	}
	
	@Test
	public void buscaLivroGuarani() {
		Livro livroEncontrado = this.livroRepository.findByNome("O Guarani");
		assertThat(livroEncontrado).isNotNull();
		assertThat(livroEncontrado.getNome()).isEqualTo("O Guarani");
	}
	
	@Test
	public void insereLivroHamlet() {
		Autor autor = this.autorRepository.findByNome("William Shakespeare");
		
		Livro livroCadastrado = new Livro();
		livroCadastrado.setNome("Hamlet");
		livroCadastrado.setCapa("capa");
		livroCadastrado.setAutor(autor);
		livroCadastrado.setIsbn("ABC");
		livroCadastrado.setQuantidade(50);
		livroCadastrado.setId(new Long(7));
		
		this.livroRepository.save(livroCadastrado);
		
		Livro livroEncontrado = this.livroRepository.findByNome("Hamlet");
		assertThat(livroEncontrado).isNotNull();
		assertThat(livroEncontrado.getNome()).isEqualTo("Hamlet");
	}
	
	
	@Test
	public void deleteLivroDiarioBanana() {
		Livro livroCadastrado = this.livroRepository.findByNome("Diário de um banana");
		this.livroRepository.delete(livroCadastrado);
		
		Livro livroNaoEncontrado = this.livroRepository.findByNome("Diário de um banana");
		assertThat(livroNaoEncontrado).isNull();
	}
}

