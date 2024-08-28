package br.com.alura.codechella.domain.entities;

import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456789-99", "Lucas", LocalDate.parse("1800-09-20"), "email@email.com"));
    }
}
