package antycaptcha.pages;

import antycaptcha.pages.base.BasePage;
import antycaptcha.utilities.AttributeExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GeneralExercisesPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn_exercise']")
    protected List<WebElement> exercisesButtons;


    private WebElement getElementFromXpath(WebDriver driver, int buttonIndex) {
        AttributeExtractor seedExtractor = new AttributeExtractor();
        String seed = seedExtractor.extractSeed(exercisesButtons);
        String xpathWithSeed = "//a[@href='/exercises/exercise" + buttonIndex + "?seed=" + seed + "']";
        WebElement element = driver.findElement(By.xpath(xpathWithSeed));
        return element;
    }

    public void clickExerciseButton(WebDriver driver, int exerciseNumber) {
        WebElement element = getElementFromXpath(driver, exerciseNumber);
        clickElement(driver, element);
    }

}
