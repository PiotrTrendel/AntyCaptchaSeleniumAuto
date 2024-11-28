package antycaptcha.pages;

import antycaptcha.pages.base.BasePage;
import antycaptcha.pages.base.Solutions;
import antycaptcha.utilities.AttributeExtractor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public class ThreeButtonsPage extends Solutions {

    @FindBy(xpath = "//tr[5]/td[3]")
    protected WebElement trail;

    @FindBy(xpath = "//button[@id='btnButton1']")
    protected WebElement firstAnswerButton;

    @FindBy(xpath = "//button[@id='btnButton2']")
    protected WebElement secondAnswerButton;

    @FindBy(xpath = "//button[@id='solution']")
    protected WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    protected WebElement solutionText;


    private List<String> getTrailSteps(WebDriver driver) {
        AttributeExtractor trailExtractor = new AttributeExtractor();
        return Arrays.stream(trailExtractor.extractTrail(getText(driver, trail)).split("(?<=\\d)(?=b)")).toList();
    }

    private void clickFirstAnswer(WebDriver driver) {
        clickElement(driver, firstAnswerButton);
    }

    private void clickSecondAnswer(WebDriver driver) {
        clickElement(driver, secondAnswerButton);
    }

    private void followTheTrail(WebDriver driver, Boolean isTrailCorrect) {
        List<String> correctAnswers = getTrailSteps(driver);
        scrollTo(driver, checkSolutionButton);
        for (String answer : correctAnswers) {
            switch (answer) {
                case "b1":
                    if (!isTrailCorrect) {
                        clickSecondAnswer(driver);
                    } else {
                        clickFirstAnswer(driver);
                    }
                    break;
                case "b2":
                    if (!isTrailCorrect) {
                        clickFirstAnswer(driver);
                    } else {
                        clickSecondAnswer(driver);
                    }
                    break;
                default:
                    System.out.println("no such step");
            }
        }
    }

    @Override
    protected void checkSolution(WebDriver driver, Boolean isTrailCorrect){
        followTheTrail(driver, isTrailCorrect);
        clickElement(driver, checkSolutionButton);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }

}
