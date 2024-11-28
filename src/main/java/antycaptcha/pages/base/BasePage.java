package antycaptcha.pages.base;

import antycaptcha.utilities.ByConverter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public class BasePage {

    private WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getConfigProperty("waits.timeout-seconds")));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForElementToBePresent(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getConfigProperty("waits.timeout-seconds")));
        return wait.until(ExpectedConditions.presenceOfElementLocated(ByConverter.toByVal(element)));
    }

    private WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getConfigProperty("waits.timeout-seconds")));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(WebDriver driver, WebElement element) {
        waitForElementToBeClickable(driver, element).click();
    }

    protected void typeText(WebDriver driver, WebElement element, String text) {
        waitForElementToBeVisible(driver, element).clear();
        element.sendKeys(text);
    }

    protected String getText(WebDriver driver, WebElement element) {
        return waitForElementToBeVisible(driver, element).getText();
    }

    protected void selectDropdownOption(WebDriver driver, WebElement element, String optionText) {
        Select select = new Select(waitForElementToBeVisible(driver, element));
        select.selectByVisibleText(optionText);
    }

    protected void selectDropdownOptionWithExclusion(WebDriver driver, WebElement element, String optionTextToBeExcluded) {
        Select select = new Select(waitForElementToBeVisible(driver, element));
        List<WebElement> options = select.getOptions();

        for (int i = 0; i < options.size(); i++) {
            if (!options.get(i).getText().equals(optionTextToBeExcluded)) {
                select.selectByIndex(i);
                System.out.println("Next option selected: " + options.get(i).getText());
                break;
            }
        }
    }

    protected void scrollTo(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}