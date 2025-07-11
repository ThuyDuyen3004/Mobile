package Test;
import Pages.user.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;
import java.time.Duration;

public class TC05 {
    @Test
    public void verifyProductDetailsMatchBetweenCartAndOrderPage() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        homePage.add3RandomProductsToCart();
        homePage.clickCartIcon();
        cartPage.clickOrderButton();
        // Gọi hàm so sánh từ OrderPage
        boolean match1 = orderPage.isProductInfoMatchingWithCart(1, cartPage);
        orderPage.clickComebackbutton();
        cartPage.clickOrderButton();
        boolean match2 = orderPage.isProductInfoMatchingWithCart(2, cartPage);
        orderPage.clickComebackbutton();
        cartPage.clickOrderButton();
        boolean match3 = orderPage.isProductInfoMatchingWithCart(3, cartPage);
        softAssert.assertTrue(match1, "Product info doesn't match at row 1");
        softAssert.assertTrue(match2, "Product info doesn't match at row 2");
        softAssert.assertTrue(match3, "Product info doesn't match at row 3");

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
