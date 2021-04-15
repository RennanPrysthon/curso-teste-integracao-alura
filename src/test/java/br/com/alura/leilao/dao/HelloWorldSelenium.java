package br.com.alura.leilao.dao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelloWorldSelenium {

    @Test
    public void hello() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver browser = new ChromeDriver(options);
        browser.navigate().to("http://localhost:8080/leilao");
        browser.quit();
    }

}
