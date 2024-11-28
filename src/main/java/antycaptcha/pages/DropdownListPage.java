package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropdownListPage extends Solutions {

    @FindBy(xpath = "//code[@class='wrap']")
    protected WebElement solutionText;

    @FindBy(xpath = "//tr[2]/td[2]/code")
    protected WebElement colourToBeSelected;

    @FindBy(id = "s13")
    protected WebElement colourDropdown;

    @FindBy(id = "solution")
    protected WebElement checkSolutionButton;


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
    protected void checkSolution(WebDriver driver, Boolean isConditionMet) {
        selectColourFromDropdown(driver, isConditionMet);
        clickElement(driver, checkSolutionButton);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }
}
