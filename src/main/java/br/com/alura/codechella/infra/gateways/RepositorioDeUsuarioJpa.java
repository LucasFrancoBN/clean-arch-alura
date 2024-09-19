package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioEntity;
import br.com.alura.codechella.infra.persistence.UsuarioRepository;

import java.util.List;

public class RepositorioDeUsuarioJpa implements RepositorioDeUsuario {
    private final UsuarioRepository repositorio;
    private final UsuarioEntityMapper mapper;

    public RepositorioDeUsuarioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = mapper.toEntity(usuario);
        return mapper.toDomain(repositorio.save(usuarioEntity));
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deletarUsuarioPorCpf(String cpf) {
        UsuarioEntity usuarioBuscadoCpf = repositorio.findByCpf(cpf);
        repositorio.deleteById(usuarioBuscadoCpf.getId());
    }

    @Override
    public Usuario atualizarUsuarioPorCpf(String cpf, Usuario usuario) {
        UsuarioEntity usuarioBuscadoCpf = repositorio.findByCpf(cpf);

        if(usuarioBuscadoCpf == null) {
            throw new RuntimeException("CPF do usuário não encontrado");
        }

        usuarioBuscadoCpf.setNome(usuario.getNome());

        return mapper.toDomain(repositorio.save(usuarioBuscadoCpf));
    }
}
