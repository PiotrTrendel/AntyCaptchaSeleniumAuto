package antycaptcha.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public class BasePage {

    private long getTimeout() {
        return Long.parseLong(getConfigProperty("waits.timeout-seconds"));
    }

    private WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, getTimeout());
    }

    private WebElement waitForVisibility(WebDriver driver, WebElement element) {
        try {
            return getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException("Element is not visible: " + element, e);
        }
    }

    private WebElement waitForPresence(WebDriver driver, By locator) {
        try {
            return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("No such locator: " + locator, e);
        }
    }

    private WebElement waitForClickability(WebDriver driver, WebElement element) {
        try {
            return getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new RuntimeException("Element is not clickable: " + element, e);
        }
    }

    protected void clickElement(WebDriver driver, WebElement element) {
        waitForClickability(driver, element).click();
    }

    protected void typeText(WebDriver driver, WebElement element, String text) {
        WebElement visibleElement = waitForVisibility(driver, element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected String getText(WebDriver driver, WebElement element) {
        return waitForVisibility(driver, element).getText();
    }

    protected void selectDropdownOption(WebDriver driver, WebElement dropdown, String optionText) {
        Select select = new Select(waitForVisibility(driver, dropdown));
        select.selectByVisibleText(optionText);
    }

    protected void selectDropdownOptionWithExclusion(WebDriver driver, WebElement dropdown, String excludedOptionText) {
        Select select = new Select(waitForVisibility(driver, dropdown));
        List<WebElement> options = select.getOptions();
        options.stream().filter(option -> !option.getText().equals(excludedOptionText)).findFirst().ifPresent(option -> {
            select.selectByVisibleText(option.getText());
            System.out.println("Option chosen: " + option.getText());
        });
    }

    protected void scrollTo(WebDriver driver, WebElement element) {
        WebElement visibleElement = waitForVisibility(driver, element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", visibleElement);
    }
}