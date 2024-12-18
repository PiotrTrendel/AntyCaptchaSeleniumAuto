package org.antycaptchatest;

import antycaptcha.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;
import static antycaptcha.utilities.DriverManager.*;

class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected GeneralExercisesPage genExPage;
    protected ThreeButtonsPage exOnePage;
    protected EditboxPage exTwoPage;
    protected DropdownListPage exThreePage;
    protected RadioButtonsPage exFourPage;


    @BeforeMethod
    public void setUp() {
        try {
            driver = getDriver(getConfigProperty("browser-name"));
            driver.manage().window().maximize();
            driver.get(getConfigProperty("base-url"));
            initializePages();
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up WebDriver or load the base URL", e);
        }
    }

    private void initializePages() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        genExPage = PageFactory.initElements(driver, GeneralExercisesPage.class);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            quitDriver(driver);
        }
    }

}