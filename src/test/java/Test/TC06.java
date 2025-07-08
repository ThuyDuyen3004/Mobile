package Test;

import Pages.user.*;
import Pages.user.CartPage;
import Pages.user.HomePage;
import Pages.user.LoginPage;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TC06 {
    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    OrderPage orderPage;
    PaymentPage paymentPage;
    SearchPage searchPage;

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

    @Test
    @Description("Verify that [Thành tiền] and [Tổng tiền] are calculated correctly for all products in the cart")
    public void VerifythatCalculatedCorrectly() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            List<WebElement> allButtons = homePage.getAllAddToCartButtons();


            WebElement randomButton = allButtons.get(random.nextInt(allButtons.size()));
            wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
            cartPage.CloseCartForm();
        }

        homePage.clickCartIcon();
        cartPage.ClickOrderButton();
        homePage.clickCartIcon();
        Thread.sleep(1000);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
