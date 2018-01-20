package br.biblioteca.livros.repository;

import br.biblioteca.livros.beans.Usuario;

public interface UsuarioRepositoryCustom {
    public Usuario findByUsername(String username);
}