package antycaptcha.pages.base;

import org.openqa.selenium.WebDriver;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public abstract class Solutions extends BasePage{

    protected abstract void doExercise(WebDriver driver, Boolean isConditionMet);

    protected abstract String getSolutionText(WebDriver driver);

    public boolean isSolutionValid(WebDriver driver, boolean isConditionMet) {
        String expectedSolution = getConfigProperty(isConditionMet ? "positive-solution" : "negative-solution");
        String actualSolution = getSolutionText(driver);
        if (actualSolution.equals(expectedSolution)) {
            System.out.println(isConditionMet ? "Solution is correct." : "Solution is correctly identified as incorrect.");
            return true;
        } else {
            System.err.println(isConditionMet ? "Solution was incorrect." : "Please verify if solution was incorrectly identified as correct.");
            return false;
        }
    }
}
