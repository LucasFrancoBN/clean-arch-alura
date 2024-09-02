package br.com.alura.codechella.domain.entities;

import br.com.alura.codechella.domain.entities.usuario.FabricaDeUsuario;
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

    @Test
    void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabricaDeUsuario = new FabricaDeUsuario();
        Usuario usuario = fabricaDeUsuario.comNomeCpfNascimento(
                "Emily",
                "785.412.365-00",
                LocalDate.parse("2000-05-13"));
        fabricaDeUsuario.incluiEndereco("12345-999", 40, "apto");



        Assertions.assertEquals("Emily", usuario.getNome());
        Assertions.assertEquals("785.412.365-00", usuario.getCpf());
        Assertions.assertEquals(LocalDate.parse("2000-05-13"), usuario.getNascimento());
    }

    @Test
    void deveGerarExcecao_QuandoUsuarioTiverMenosDeDezoitoAnosDeIdade() {
        FabricaDeUsuario fabricaDeUsuario = new FabricaDeUsuario();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> fabricaDeUsuario.comNomeCpfNascimento(
                    "Emily",
                    "785.412.365-00",
                    LocalDate.parse("2020-05-13")
                )
        );
    }


}
