package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model
        this.browser = new ChromeDriver(options);
        browser.navigate().to(URL_LOGIN);
    }

    public void fecharPagina() {
        this.browser.quit();
    }

    public void preencheFormulario(String username, String pass) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(pass);
    }

    public void submitLoginForm() {
        this.browser.findElement(By.id("login-form")).submit();
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

    public void navegar(String s) {
        browser.navigate().to(s);
    }

    public boolean contemTexto(String s) {
        return browser.getPageSource().contains(s);
    }
}
