package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.AtualizarUsuario;
import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.DeletarUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final DeletarUsuario deletarUsuario;
    private final AtualizarUsuario atualizarUsuario;

    public UsuarioController(
            CriarUsuario criarUsuario,
            ListarUsuarios listarUsuarios,
            DeletarUsuario deletarUsuario,
            AtualizarUsuario atualizarUsuario
    ) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
        this.deletarUsuario = deletarUsuario;
        this.atualizarUsuario = atualizarUsuario;
    }

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto dto) {
        Usuario salvo = criarUsuario.cadastrarUsuario(
                new Usuario(
                        dto.cpf(),
                        dto.nome(),
                        dto.nascimento(),
                        dto.email()
                )
        );
        return new UsuarioDto(
            salvo.getCpf(),
            salvo.getNome(),
            salvo.getNascimento(),
            salvo.getEmail()
        );
    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return listarUsuarios.obterTodosUsuarios()
                .stream().map(u -> new UsuarioDto(
                            u.getCpf(),
                            u.getNome(),
                            u.getNascimento(),
                            u.getEmail()
                    )
                ).toList();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cpf) {
        this.deletarUsuario.deletarUsario(cpf);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable String cpf, @RequestBody UsuarioDto dto) {
        Usuario usuarioAtualizado = atualizarUsuario.atualizarUsuario(
                cpf,
                new Usuario(
                        dto.cpf(),
                        dto.nome(),
                        dto.nascimento(),
                        dto.email()
                )
        );
        return ResponseEntity.ok(
                new UsuarioDto(
                        usuarioAtualizado.getCpf(),
                        usuarioAtualizado.getNome(),
                        usuarioAtualizado.getNascimento(),
                        usuarioAtualizado.getEmail()
                )
        );
    }
}
