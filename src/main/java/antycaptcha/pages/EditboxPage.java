package antycaptcha.pages;

import antycaptcha.pages.base.BasePage;
import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public class EditboxPage extends Solutions {

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(2) code:nth-child(1)")
    protected WebElement tdWithText;

    @FindBy(id = "t14")
    protected WebElement editbox;

    @FindBy(id = "btnButton1")
    protected WebElement buttonOne;

    @FindBy(id = "solution")
    protected WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    protected WebElement solutionText;

    private String getTextToRetype(WebDriver driver, Boolean isTextCorrect) {
        if (isTextCorrect) {
            return getText(driver, tdWithText);
        } else {
            return getText(driver, tdWithText) + "123";
        }
    }

    private void retypeText(WebDriver driver, Boolean isTextCorrect) {
        typeText(driver, editbox, getTextToRetype(driver, isTextCorrect));
    }

    @Override
    protected void checkSolution(WebDriver driver, Boolean isTextCorrect){
        retypeText(driver, isTextCorrect);
        clickElement(driver, buttonOne);
        clickElement(driver, checkSolutionButton);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }

}
