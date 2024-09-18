package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.util.List;

public class ListarUsuarios {
    private final RepositorioDeUsuario repositorioUsuario;

    public ListarUsuarios(RepositorioDeUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<Usuario> obterTodosUsuarios() {
        return this.repositorioUsuario.listarTodos();
    }
}
