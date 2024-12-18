package antycaptcha.pages;

import antycaptcha.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GeneralExercisesPage extends BasePage {

    @FindBy(className = "btn_exercise")
    private List<WebElement> exercisesButtons;


    public void clickExerciseButton(WebDriver driver, int exerciseNumber) {
        try {
            clickElement(driver, exercisesButtons.get(exerciseNumber - 1));
        } catch (Exception e) {
            throw new RuntimeException("No such exercise", e);
        }
    }

}
