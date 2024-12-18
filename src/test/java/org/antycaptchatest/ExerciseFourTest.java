package org.antycaptchatest;

import antycaptcha.pages.RadioButtonsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseFourTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    void getToExerciseFour() {
        mainPage.clickExercisesButton(driver);
        genExPage.clickExerciseButton(driver, 4);
        exFourPage = PageFactory.initElements(driver, RadioButtonsPage.class);
    }

    @Test(description = "Solution is properly validated if user selected matching colour from dropdown list")
    public void performExerciseFourPositive() {
        exFourPage.clickRequiredRadioButtons(driver);
        exFourPage.checkSolution(driver);
        Assert.assertTrue(exFourPage.isSolutionValid(driver, true), "Wrong buttons were clicked");
    }

    @Test (description = "Solution is incorrectly validated if user selected mismatching colour from dropdown list", priority = 1)
    public void performExerciseFourNegative() {
        exFourPage.clickWrongRadioButtons(driver);
        exFourPage.checkSolution(driver);
        Assert.assertTrue(exFourPage.isSolutionValid(driver, false), "Correct buttons were clicked but shouldn't be");
    }
}
