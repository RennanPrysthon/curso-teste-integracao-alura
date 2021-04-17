package br.com.alura.acceptance.steps;

import br.com.alura.leilao.leilao.CadastroLeilaoPage;
import br.com.alura.leilao.leilao.LeiloesPage;
import br.com.alura.leilao.login.LoginPage;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeilaoFeatureTest {

    private LoginPage loginPage;
    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage cadastroLeilaoPage;
    private Usuario usuario;
    private Leilao leilao;

    @After
    public void after() {
        this.loginPage.fecharPagina();
        this.cadastroLeilaoPage.fecharPagina();
        this.leiloesPage.fecharPagina();
    }

    @Dado("um usuario logado")
    public void um_usuario_logado() {
        this.usuario = new Usuario("fulano", "fulano@gmail.com", "pass");
        this.loginPage = new LoginPage();
        this.loginPage.preencheFormulario(this.usuario.getNome(), this.usuario.getSenha());
        this.leiloesPage = this.loginPage.submitLoginForm();
    }

    @Quando("acessa a pagina de novo leilao")
    public void acessa_a_pagina_de_novo_leilao() {
        this.cadastroLeilaoPage = this.leiloesPage.carregarFormulario();
    }

    @Quando("preenche o formulario com dados validos")
    public void preenche_o_formulario_com_dados_validos() {
        this.leilao = new Leilao("Tablet");
        this.leiloesPage = this.cadastroLeilaoPage.cadastrarLeilao(this.leilao.getNome(),"50.0", "20/02/2021");
    }

    @Entao("volta para a pagina de leiloes")
    public void volta_para_a_pagina_de_leiloes() {
        assertTrue(leiloesPage.getUrl().equals("http://localhost:8080/leiloes"));
    }

    @Entao("o novo leilao aparece na tabela")
    public void o_novo_leilao_aparece_na_tabela() {
        assertTrue(leiloesPage.isLeilaoCadastrado("Tablet", "50.00", "20/02/2021"));
    }
}
