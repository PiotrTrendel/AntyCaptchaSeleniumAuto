package org.antycaptchatest;

import antycaptcha.pages.DropdownListPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseThreeTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    void getToExerciseThree() {
        mainPage.clickExercisesButton(driver);
        genExPage.clickExerciseButton(driver, 3);
        exThreePage = PageFactory.initElements(driver, DropdownListPage.class);
    }

    @Test(description = "Solution is properly validated if user selected matching colour from dropdown list")
    public void performExerciseThreePositive() {
        exThreePage.selectValidColour(driver);
        exThreePage.checkSolution(driver);
        Assert.assertTrue(exThreePage.isSolutionValid(driver, true),"Wrong colour was selected");
    }

    @Test (description = "Solution is incorrectly validated if user selected mismatching colour from dropdown list", priority = 1)
    public void performExerciseThreeNegative() {
        exThreePage.selectInvalidColour(driver);
        exThreePage.checkSolution(driver);
        Assert.assertTrue(exThreePage.isSolutionValid(driver, false),"Correct colour was selected but shouldn't be");
    }
}
