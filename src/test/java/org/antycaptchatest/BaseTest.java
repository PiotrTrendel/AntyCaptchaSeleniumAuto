package org.antycaptchatest;

import antycaptcha.pages.*;
import antycaptcha.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

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


    @BeforeMethod(dependsOnMethods = "setUp")
    public void initializePages() {
        mainPage = PageFactory.initElements(driver, MainPage.class);
        genExPage = PageFactory.initElements(driver, GeneralExercisesPage.class);
    }

    @BeforeMethod
    public void setUp() {
        driver = getDriver(getConfigProperty("browser-name"));
        driver.manage().window().maximize();
        driver.get(getConfigProperty("base-url"));
    }

    @AfterMethod
    public void tearDown() {
        quitDriver(driver);
        cleanUp();
    }
}