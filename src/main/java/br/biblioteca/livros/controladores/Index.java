package br.biblioteca.livros.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Usuario;

@Controller
public class Index {
	
	//@Autowired
	//private LivroRepository livroRepository;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login", "userForm", new Usuario());
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		return new ModelAndView("registration", "usuarioForm", new Usuario());
	}
	
	/*@GetMapping("/livros")
	public ModelAndView livros() {

		Iterable<Livro> livros = livroRepository.findAll();
		
		for (Livro livro : livros) {
			
			System.out.println(livro.getNome());
		}
		
		return new ModelAndView("index");
	}
	
	@GetMapping("/livros")
	public ModelAndView livros() {
		Iterable<Livro> livros = livroRepository.findAll();
		return new ModelAndView("livros", "livros", livros);
	}*/
}
