package org.antycaptchatest;

import antycaptcha.pages.EditboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseTwoTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    void getToExerciseTwo() {
        mainPage.clickExercisesButton(driver);
        genExPage.clickExerciseButton(driver, 2);
        exTwoPage = PageFactory.initElements(driver, EditboxPage.class);
    }

    @Test(description = "Solution is properly validated if user entered correct text to editbox")
    public void performExerciseTwoPositive() {
        exTwoPage.enterValidText(driver);
        exTwoPage.checkSolution(driver);
        Assert.assertTrue(exTwoPage.isSolutionValid(driver, true), "Text entered is incorrect");
    }

    @Test (description = "Solution is incorrectly validated if user entered incorrect text to editbox", priority = 1)
    public void performExerciseTwoNegative() {
        exTwoPage.enterInvalidText(driver);
        exTwoPage.checkSolution(driver);
        Assert.assertTrue(exTwoPage.isSolutionValid(driver, false), "Text entered is correct but shouldn't be");
    }
}
