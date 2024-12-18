package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditboxPage extends Solutions {

    @FindBy(xpath = "//td[contains(text(),\"Enter text\")]/code[1]")
    private WebElement textToRetype;

    @FindBy(id = "t14")
    private WebElement editbox;

    @FindBy(id = "btnButton1")
    private WebElement buttonOne;

    @FindBy(id = "solution")
    private WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    private WebElement solutionText;

    private String getTextToRetype(WebDriver driver, Boolean isTextCorrect) {
        if (isTextCorrect) {
            return getText(driver, textToRetype);
        } else {
            return getText(driver, textToRetype) + "123";
        }
    }

    private void retypeText(WebDriver driver, Boolean isTextCorrect) {
        typeText(driver, editbox, getTextToRetype(driver, isTextCorrect));
    }

    @Override
    protected void doExercise(WebDriver driver, Boolean isTextCorrect){
        retypeText(driver, isTextCorrect);
        clickElement(driver, buttonOne);
    }

    public void checkSolution(WebDriver driver) {
        clickElement(driver, checkSolutionButton);
    }

    public void enterValidText(WebDriver driver) {
        doExercise(driver, true);
    }

    public void enterInvalidText(WebDriver driver) {
        doExercise(driver, false);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }

}
