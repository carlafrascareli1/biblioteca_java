package br.biblioteca.livros.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;
import br.biblioteca.livros.services.*;
import br.biblioteca.livros.validator.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private SecurityServiceImpl securityService = new SecurityServiceImpl();
	@Autowired
	private LoginValidator loginValidator = new LoginValidator();
 
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("")
	public ModelAndView index() {
		return this.usuarios();
	}
	
	
	@PostMapping(value = "/registration")
	public ModelAndView registrationform(@Valid @ModelAttribute("usuarioForm") Usuario usuario, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("/registration");
		}

		usuario = this.usuarioRepository.save(usuario);
		
		try {
			securityService.login(usuario.getUsername(), usuario.getPasssword());
			return new ModelAndView("redirect:/");
		} catch (Exception e) {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@PostMapping("/autentication")
	public ModelAndView autentication(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult) {
		loginValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("login");
		}
		securityService.login(userForm.getUsername(), userForm.getPassword());
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/usuarios/login";
	}

	@GetMapping("/list")
	public ModelAndView usuarios() {

		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		return new ModelAndView("usuarios/list", "usuarios", usuarios);

	}

	@GetMapping("/novo")
	public String createForm(@ModelAttribute Usuario usuario) {
		return "usuarios/form";
	}

	@PostMapping("/")
	public ModelAndView create(@Valid Usuario usuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("usuarios/form");
		}

		usuario = this.usuarioRepository.save(usuario);
		return new ModelAndView("redirect:/usuarios/");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Usuario usuario = this.usuarioRepository.findOne(id);
		return new ModelAndView("usuarios/form", "usuario", usuario);
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Usuario usuario = this.usuarioRepository.findOne(id);
		this.usuarioRepository.delete(usuario);
		return new ModelAndView("redirect:/usuarios/list");
	}
}
