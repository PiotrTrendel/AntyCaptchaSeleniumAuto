package antycaptcha.pages.base;

import org.openqa.selenium.WebDriver;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;

public abstract class Solutions extends BasePage{

    protected abstract void checkSolution(WebDriver driver, Boolean isConditionMet);

    protected abstract String getSolutionText(WebDriver driver);

    public boolean isSolutionCorrect(WebDriver driver) {
        checkSolution(driver, true);
        String text = getSolutionText(driver);
        if (text.equals(getConfigProperty("positive-solution"))){
            return true;
        } else {
            System.out.println("Solution was incorrect.");
            return false;
        }
    }

    public boolean isSolutionIncorrect(WebDriver driver) {
        checkSolution(driver, false);
        String text = getSolutionText(driver);
        if (text.equals(getConfigProperty("negative-solution"))){
            return true;
        } else {
            System.out.println("Please verify if solution was incorrect");
            return false;
        }
    }
}
