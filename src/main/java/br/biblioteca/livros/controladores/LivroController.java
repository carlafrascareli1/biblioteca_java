package br.biblioteca.livros.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
	
	private FileSaver fileSaver = new FileSaver();
	
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

	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("livro")  @Valid Livro livro, BindingResult bindingResult, Model model
			, @RequestParam("capaUrl") MultipartFile capaUrl) {
		
		if(capaUrl.getOriginalFilename().equals("")) {
			model.addAttribute("message", "A capa não pode ser vazia");
			return new ModelAndView("livros/form");
		}else {
			if(capaUrl.getContentType().equals("image/jpeg")){
				String webPath = fileSaver.write("uploaded-images",capaUrl);
				livro.setCapa(webPath);
			}else{
				model.addAttribute("message", "Arquivo em formato errado. Permitido apenas jpg");
				return new ModelAndView("livros/form");
			}
		}
		
		if (bindingResult.hasErrors()  || model.containsAttribute("capa")) {
			return new ModelAndView("livros/form");
		}
		
		livro = this.livroRepository.save(livro);
	    return new ModelAndView("redirect:/livros/list");
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
