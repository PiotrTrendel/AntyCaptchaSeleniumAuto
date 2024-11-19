package antycaptchatest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExerciseOneTest extends BaseTest {

    @BeforeMethod
    void getToExerciseOne() {
        mainPage.clickExercisesButton(driver);
        genExPage.clickExerciseButton(driver, 1);
    }

    @Test (description = "Solution is properly validated if user followed the trail")
    public void performExerciseOnePositive() {
        Assert.assertTrue(exOnePage.isSolutionCorrect(driver));
    }

    @Test (description = "Solution is incorrectly validated if user didn't follow the trail")
    public void performExerciseOneNegative() {
        Assert.assertTrue(exOnePage.isSolutionIncorrect(driver));
    }
}
