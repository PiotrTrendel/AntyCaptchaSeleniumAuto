package antycaptcha.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class DriverManager {

    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
            browser = browser.toLowerCase(Locale.ROOT);
        }
            switch (browser) {
                case "chrome":
                    driver = createChromeDriver(false);
                    break;
                case "chrome-headless":
                    driver = createChromeDriver(true);
                    break;
                case "firefox":
                    driver = createFirefoxDriver(false);
                    break;
                case "firefox-headless":
                    driver = createFirefoxDriver(true);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser + ". Supported browsers: chrome, chrome-headless, firefox, firefox-headless, edge.");
        }
        return driver;
    }

        public static void quitDriver (WebDriver driver){
            if (driver != null) {
                driver.quit();
            }
        }
        private static WebDriver createChromeDriver ( boolean headless){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless", "--disable-gpu");
            }
            return new ChromeDriver(options);
        }
        private static WebDriver createFirefoxDriver ( boolean headless){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            return new FirefoxDriver(options);
        }

}