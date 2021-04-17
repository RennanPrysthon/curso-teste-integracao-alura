package br.com.alura.leilao.login;

import br.com.alura.leilao.leilao.LeiloesPage;
import br.com.alura.leilao.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        browser.navigate().to(URL_LOGIN);
    }

    public void preencheFormulario(String username, String pass) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(pass);
    }

    public LeiloesPage submitLoginForm() {
        this.browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(this.browser);
    }

    public boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public String getNomeUsuarioLogado() {
        try {
            return  browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
