package antycaptcha.pages;

import antycaptcha.utilities.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ConfigManager.getConfigPropertyLong("waits.timeout-seconds"));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    protected void typeText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }

    protected boolean isElementVisible(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementClickable(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectDropdownOption(By locator, String optionText) {
        WebElement element = waitForElementToBeVisible(locator);
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(element);
        select.selectByVisibleText(optionText);
    }
}