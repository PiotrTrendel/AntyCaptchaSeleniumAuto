package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropdownListPage extends Solutions {

    @FindBy(css = ".wrap")
    private WebElement solutionText;

    @FindBy(xpath = "//td[contains(text(),'choose')]//code")
    private WebElement colourToBeSelected;

    @FindBy(id = "s13")
    private WebElement colourDropdown;

    @FindBy(id = "solution")
    private WebElement checkSolutionButton;


    private String getColourToBeSelected(WebDriver driver) {
        return getText(driver, colourToBeSelected);
    }

    private void selectColourFromDropdown(WebDriver driver, Boolean isConditionMet) {
        if (isConditionMet) {
            selectDropdownOption(driver, colourDropdown, getColourToBeSelected(driver));
        } else {
            selectDropdownOptionWithExclusion(driver, colourDropdown, getColourToBeSelected(driver));
        }
    }

    @Override
    protected void doExercise(WebDriver driver, Boolean isConditionMet) {
        selectColourFromDropdown(driver, isConditionMet);
    }

    public void checkSolution(WebDriver driver) {
        clickElement(driver, checkSolutionButton);
    }

    public void selectValidColour(WebDriver driver) {
        doExercise(driver, true);
    }

    public void selectInvalidColour(WebDriver driver) {
        doExercise(driver, false);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }
}
