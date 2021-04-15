package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fecharPagina();
    }

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormulario("fulano", "pass");
        paginaDeLogin.submitLoginForm();

        assertFalse(paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano",paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preencheFormulario("Invalido", "Invalido");
        paginaDeLogin.submitLoginForm();

        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertNull(paginaDeLogin.getNomeUsuarioLogado());
        assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaDeLogin.navegar("http://localhost:8080/leiloes/2");

        assertTrue(paginaDeLogin.isPaginaDeLogin());
        assertFalse(paginaDeLogin.contemTexto("Leilões cadastrados"));
    }
}

