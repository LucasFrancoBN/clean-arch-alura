package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;

public class DeletarUsuario {
    private final RepositorioDeUsuario repositorioUsuario;

    public DeletarUsuario(RepositorioDeUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void deletarUsario(String cpf) {
        repositorioUsuario.deletarUsuarioPorCpf(cpf);
    }
}
