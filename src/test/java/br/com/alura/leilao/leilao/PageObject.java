package br.com.alura.leilao.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model
        if (browser == null) {
            this.browser = new ChromeDriver(options);
        } else {
            this.browser = browser;
        }
    }

    public void fecharPagina() {
        this.browser.quit();
    }

    public String getUrl(){
        return this.browser.getCurrentUrl();
    }

    public void navegar(String s) {
        browser.navigate().to(s);
    }

    public boolean contemTexto(String s) {
        return browser.getPageSource().contains(s);
    }
}

