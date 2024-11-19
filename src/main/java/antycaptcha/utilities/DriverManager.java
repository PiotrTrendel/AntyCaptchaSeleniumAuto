package main.java.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    public WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }


    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public void cleanUp() {
        WebDriverManager.chromedriver().clearResolutionCache();
        WebDriverManager.firefoxdriver().clearResolutionCache();
        WebDriverManager.edgedriver().clearResolutionCache();
    }
}