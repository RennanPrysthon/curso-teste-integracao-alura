package br.com.alura.leilao.leilao;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeiloesTest {
    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage paginaCadastro ;

    @AfterEach
    public void afterEach() {
        this.leiloesPage.fecharPagina();
        this.paginaCadastro.fecharPagina();
    }

    @BeforeEach
    public void beforeEach() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormulario("fulano", "pass");
        this.leiloesPage = loginPage.submitLoginForm();
        paginaCadastro = this.leiloesPage.carregarFormulario();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia ".concat(hoje);
        String valor = "500.00";

        this.leiloesPage = paginaCadastro.cadastrarLeilao(nome, valor, hoje);

        assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.leiloesPage = paginaCadastro.cadastrarLeilao("", "", "");

        assertTrue(paginaCadastro.isPaginaAtual());
        assertTrue(paginaCadastro.isMensagensDeValidacaoVisiveis());
    }
}
