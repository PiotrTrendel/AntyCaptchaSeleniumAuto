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
        Assert.assertTrue(exOnePage.isSolutionCorrect(driver), "coś tam coś tam");
    }

    @Test (description = "Solution is incorrectly validated if user didn't follow the trail", priority = 1)
    public void performExerciseOneNegative() {
        //1. wejście na stronę
        //2. wykonanie zadania/części zadania
        //3. assercja
        Assert.assertTrue(exOnePage.isSolutionIncorrect(driver));
    }
}
