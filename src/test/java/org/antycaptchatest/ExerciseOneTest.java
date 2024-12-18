package org.antycaptchatest;

import antycaptcha.pages.ThreeButtonsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseOneTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    void getToExerciseOne() {
        mainPage.clickExercisesButton(driver);
        genExPage.clickExerciseButton(driver, 1);
        exOnePage = PageFactory.initElements(driver, ThreeButtonsPage.class);
    }

    @Test (description = "Solution is properly validated if user followed the trail")
    public void performExerciseOnePositive() {
        exOnePage.clickButtonsFollowingTrail(driver);
        exOnePage.checkSolution(driver);
        Assert.assertTrue(exOnePage.isSolutionValid(driver, true), "Buttons were clicked in wrong order");
    }

    @Test (description = "Solution is incorrectly validated if user didn't follow the trail", priority = 1)
    public void performExerciseOneNegative() {
        exOnePage.clickWrongButtons(driver);
        exOnePage.checkSolution(driver);
        Assert.assertTrue(exOnePage.isSolutionValid(driver, false), "Buttons were clicked in good order but shouldn't be");
    }
}
