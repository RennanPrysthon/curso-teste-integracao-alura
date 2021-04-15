package br.com.alura.leilao.leilao;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class LeiloesTest {
    private LeiloesPage leiloesPage;

    @AfterEach
    public void afterEach() {
        this.leiloesPage.fecharPagina();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormulario("fulano", "pass");
        this.leiloesPage = loginPage.submitLoginForm();

        CadastroLeilaoPage paginaCadastro = this.leiloesPage.carregarFormulario();


    }
}
