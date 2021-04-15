package br.com.alura.leilao.leilao;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {
    private WebDriver browser;
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fecharPagina() {
        this.browser.quit();
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }
}
