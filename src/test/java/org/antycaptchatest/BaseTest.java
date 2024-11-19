package antycaptchatest;

import antycaptcha.pages.GeneralExercisesPage;
import antycaptcha.pages.MainPage;
import antycaptcha.pages.ThreeButtonsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static antycaptcha.utilities.ConfigManager.getConfigProperty;
import static antycaptcha.utilities.DriverManager.getDriver;

class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected GeneralExercisesPage genExPage;
    protected ThreeButtonsPage exOnePage;

    @BeforeSuite
    public void initializePages() {
        driver = getDriver(getConfigProperty("browser-name"));
        mainPage = PageFactory.initElements(driver, MainPage.class);
        genExPage = PageFactory.initElements(driver, GeneralExercisesPage.class);
        exOnePage = PageFactory.initElements(driver, ThreeButtonsPage.class);
    }

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.get(getConfigProperty("base-url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}