package br.biblioteca.livros.testes;



import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class ApplicationEmprestimoTests {
	
	@Autowired
	LivroRepository livroRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;

	@Before
	public void setUp() {
	}
	
	@Test
	public void buscaEmprestimosCadastrados() {
		
		Page<Emprestimo> emprestimos = this.emprestimoRepository.findAll(new PageRequest(0, 10));
		assertThat(emprestimos.getTotalElements()).isGreaterThan(1L);
		
	}
	
	@Test
	public void emprestaLivro() {
		Cliente cliente = this.clienteRepository.findByNome("Carla");
		assertThat(cliente).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Carla");
		
		Livro livro = this.livroRepository.findByNome("O Guarani");
		assertThat(livro).isNotNull();
		assertThat(livro.getNome()).isEqualTo("O Guarani");
		
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		
		Emprestimo emprestimoCad = new Emprestimo();
		try {
			emprestimoCad.setDataEmprestimo(df.parse("02/03/2018"));
		} catch (ParseException e) {
		}
		emprestimoCad.setId(new Long(4));
		
		this.emprestimoRepository.save(emprestimoCad);
	}
	
	@Test
	public void devolveLivro() {
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		
		Emprestimo emprestimoCad = emprestimoRepository.findOne(new Long(3));
		try {
			emprestimoCad.setDataDevolucao(df.parse("18/03/2018"));
		} catch (ParseException e) {
		}
		this.emprestimoRepository.save(emprestimoCad);
	}
	
}


