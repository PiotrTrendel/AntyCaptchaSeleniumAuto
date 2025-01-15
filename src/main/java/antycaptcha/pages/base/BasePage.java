package antycaptcha.pages.base;

import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

@Slf4j
public class BasePage {

    private long getTimeout() {
        long timeout = Long.parseLong(getConfigProperty("waits.timeout-seconds"));
        return timeout;
    }

    private WebDriverWait getWebDriverWait(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, getTimeout());
        return wait;
    }

    private WebElement waitForVisibility(WebDriver driver, WebElement element) {
        try {
            log.info("Waiting for visibility of element: " + element);
            return getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.error("Failed to find element visibility: " + element);
            throw new RuntimeException("Element is not visible: " + element, e);
        }
    }

    private WebElement waitForPresence(WebDriver driver, By locator) {
        try {
            log.info("Waiting for presence of element with locator: " + locator);
            return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            log.error("Failed to find element with locator: " + locator);
            throw new RuntimeException("No such locator: " + locator, e);
        }
    }

    private WebElement waitForClickability(WebDriver driver, WebElement element) {
        try {
            log.info("Waiting for element to be clickable: " + element);
            return getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Failed to find clickable element: " + element);
            throw new RuntimeException("Element is not clickable: " + element, e);
        }
    }

    protected void clickElement(WebDriver driver, WebElement element) {
        log.info("Clicking element: " + element);
        waitForClickability(driver, element).click();
    }

    protected void typeText(WebDriver driver, WebElement element, String text) {
        WebElement visibleElement = waitForVisibility(driver, element);
        log.info("Typing text into element: " + element + ", Text: " + text);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected String getText(WebDriver driver, WebElement element) {
        log.info("Fetching text from element: " + element);
        String text = waitForVisibility(driver, element).getText();
        log.info("Text fetched: " + text);
        return text;
    }

    protected void selectDropdownOption(WebDriver driver, WebElement dropdown, String optionText) {
        log.info("Selecting option from dropdown: " + dropdown + ", Option: " + optionText);
        Select select = new Select(waitForVisibility(driver, dropdown));
        select.selectByVisibleText(optionText);
    }

    protected void selectDropdownOptionWithExclusion(WebDriver driver, WebElement dropdown, String excludedOptionText) {
        log.info("Selecting option from dropdown with exclusion: " + dropdown + ", Excluded Option: " + excludedOptionText);
        Select select = new Select(waitForVisibility(driver, dropdown));
        List<WebElement> options = select.getOptions();
        options.stream().filter(option -> !option.getText().equals(excludedOptionText)).findFirst().ifPresent(option -> {
            select.selectByVisibleText(option.getText());
            log.info("Option chosen: " + option.getText());
        });
    }

    protected void scrollTo(WebDriver driver, WebElement element) {
        log.info("Scrolling to element: " + element);
        WebElement visibleElement = waitForVisibility(driver, element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", visibleElement);
    }
}