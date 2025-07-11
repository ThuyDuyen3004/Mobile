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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TC06 {

    @Test
    @Description("Verify that [Thành tiền] and [Tổng tiền] are calculated correctly for all products in the cart")
    public void VerifyThatCalculatedCorrectly() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        homePage.add3RandomProductsToCart();
        homePage.clickCartIcon();

        // Kiểm tra Subtotal từng dòng
        Assert.assertTrue(cartPage.isSubtotalCorrect(1), "Subtotal is incorrect on row 1");
        Assert.assertTrue(cartPage.isSubtotalCorrect(2), "Subtotal is incorrect on row 2");
        Assert.assertTrue(cartPage.isSubtotalCorrect(3), "Subtotal is incorrect on row 3");
        // Kiểm tra tổng tiền
        Assert.assertTrue(cartPage.isTotalPriceCorrect(), "Total price is not equal to the sum of all subtotals");
        softAssert.assertAll();
    }

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
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
