package Common;

import Pages.user.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CartPage cartPage;
    protected OrderPage orderPage;
    protected PaymentPage paymentPage;
    protected SearchPage searchPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        paymentPage = new PaymentPage(driver);
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}
