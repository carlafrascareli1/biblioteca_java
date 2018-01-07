package br.biblioteca.livros.controladores;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploaded-images/**")
				.addResourceLocations("file:/C:/temp/biblioteca/uploaded-images/");
		
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}

}
