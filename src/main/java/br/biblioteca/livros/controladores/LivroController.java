package br.biblioteca.livros.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	@GetMapping("")
	public ModelAndView index() {
		return this.livros();
	}
	
	@GetMapping("/list")
	public ModelAndView livros() {

		Iterable<Livro> livros = livroRepository.findAll();
		return new ModelAndView("livros/list", "livros", livros);

	}
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Livro livro) {
		return "livros/form";
	} 

	@PostMapping("/")
	public ModelAndView create(@Valid Livro livro, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("livros/form");
		}
		
		livro = this.livroRepository.save(livro);
	   	return new ModelAndView("redirect:/livros/");
	} 
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Livro livro = this.livroRepository.findOne(id);
		return new ModelAndView("livros/form", "livro", livro);
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Livro livro = this.livroRepository.findOne(id);
		this.livroRepository.delete(livro);
		return new ModelAndView("redirect:/livros/list");
	}
	
	
	
}
