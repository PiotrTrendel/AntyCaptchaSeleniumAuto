package antycaptcha.pages;

import antycaptcha.pages.base.Solutions;
import antycaptcha.utilities.AttributeExtractor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ThreeButtonsPage extends Solutions {

    @FindBy(xpath = "//td[contains(text(),'Trail set to:')]")
    private WebElement trail;

    @FindBy(id = "btnButton1")
    private WebElement firstAnswerButton;

    @FindBy(id = "btnButton2")
    private WebElement secondAnswerButton;

    @FindBy(id = "solution")
    private WebElement checkSolutionButton;

    @FindBy(xpath = "//code[@class='wrap']")
    private WebElement solutionText;


    private List<String> getTrailSteps(WebDriver driver) {
        AttributeExtractor trailExtractor = new AttributeExtractor();
        String trailText = getText(driver, trail);
        return List.of(trailExtractor.extractTrail(trailText).split("(?<=\\d)(?=b)"));
    }

    private void clickButtonByStep(WebDriver driver, String step, boolean isTrailCorrect) {
        switch (step) {
            case "b1" -> clickElement(driver, isTrailCorrect ? firstAnswerButton : secondAnswerButton);
            case "b2" -> clickElement(driver, isTrailCorrect ? secondAnswerButton : firstAnswerButton);
            default -> System.err.println("Button not recognized: " + step);
        }
    }

    private void followTheTrail(WebDriver driver, boolean isTrailCorrect) {
        List<String> steps = getTrailSteps(driver);
        scrollTo(driver, checkSolutionButton);
        steps.forEach(step -> clickButtonByStep(driver, step, isTrailCorrect));
    }

    @Override
    protected void doExercise(WebDriver driver, Boolean isTrailCorrect){
        followTheTrail(driver, isTrailCorrect);
    }

    public void clickButtonsFollowingTrail(WebDriver driver) {
        doExercise(driver, true);
    }

    public void clickWrongButtons(WebDriver driver) {
        doExercise(driver, false);
    }

    public void checkSolution(WebDriver driver) {
        clickElement(driver, checkSolutionButton);
    }

    @Override
    protected String getSolutionText(WebDriver driver) {
        return getText(driver, solutionText);
    }

}
