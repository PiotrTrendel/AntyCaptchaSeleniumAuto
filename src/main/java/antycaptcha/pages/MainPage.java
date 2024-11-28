package antycaptcha.pages;

import antycaptcha.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@href='/general_exercises']")
    protected WebElement exercisesButton;

    public void clickExercisesButton(WebDriver driver) {
        clickElement(driver, exercisesButton);
    }

}
