package br.com.alura.leilao.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    private WebDriver browser;

    @BeforeEach
    public void instantiateBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model

        this.browser = new ChromeDriver(options);
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        assertEquals("fulano",browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();
    }

}

