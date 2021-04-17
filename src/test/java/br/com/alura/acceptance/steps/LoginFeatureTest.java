package br.com.alura.acceptance.steps;

import br.com.alura.leilao.login.LoginPage;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class LoginFeatureTest {
    private LoginPage page;
    private Usuario usuario;

    @Before
    public void before() {
        page = new LoginPage();
    }

    @After
    public void after() {
        this.page.fecharPagina();
    }

    @Dado("o usuario valido")
    public void o_usuario_valido() {
        this.usuario = new Usuario("fulano", "fulano@gmail.com", "pass");
    }

    @Quando("realiza login")
    public void realiza_login() {
        this.page.preencheFormulario(this.usuario.getNome(), this.usuario.getSenha());
        this.page.submitLoginForm();
    }

    @Entao("eh redirecionado para a pagina de leiloes")
    public void eh_redirecionado_para_a_pagina_de_leiloes() {
        assertFalse(this.page.isPaginaDeLogin());
        assertEquals("http://localhost:8080/leiloes", this.page.getUrl());
    }

    @Dado("o usuario invalido")
    public void o_usuario_invalido() {
        this.usuario = new Usuario("invalido", "invalido@gmail.com", "invalido");
    }

    @Entao("continua na pagina de login")
    public void continua_na_pagina_de_login() {
        assertTrue(this.page.isPaginaDeLogin());
    }
}
