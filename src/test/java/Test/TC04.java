package Test;

import Pages.user.*;
import Pages.user.CartPage;
import Pages.user.HomePage;
import Pages.user.LoginPage;
import Pages.user.OrderPage;
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

public class TC04 {
    @Test
    @Description("Verify that the order form displays the correct validation messages when entering invalid data or leaving fields empty")
    public void verifyOrderFormValidationMessages() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        List<WebElement> allButtons = homePage.getAllAddToCartButtons();
        WebElement randomButton = allButtons.get(new Random().nextInt(allButtons.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
        cartPage.ClickOrderButton();

        // Step 5 - Leave the [Họ và tên] field empty
        orderPage.EnterFullname("");
        // step 6
        orderPage.EnterAddress("Ngu Hanh Son dictrict");
        orderPage.EnterPassword("3042004a3A@");
        String msg1 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg1, "Họ và tên không được để trống", "Sai message ở step 5");

        // Step 7 Enter valid data in the [Full name] field
        orderPage.EnterFullname("Thuy Duyen");

        // Step 8 Leave the [Địa chỉ] field empty
        orderPage.EnterAddress("");
        String msg2 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg2, "Địa chỉ không được để trống", "Sai message ở step 8");

        // Step 9 Enter valid data in the [Địa chỉ] field
        orderPage.EnterAddress("Ngu Hanh Son dictrict");

        // Step 10 Leave the [Mật khẩu] field empty
        orderPage.EnterPassword("");
        String msg3 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg3, "Mật khẩu không được để trống", "Sai message ở step 10");

        // Step 11 Enter valid data in the [Mật khẩu] field
        orderPage.EnterPassword("3042004a3A@");

        // Step 12 Enter numeric characters in the [Họ và tên] field
        orderPage.EnterFullname("123456789");
        String msg4 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg4, "Chỉ nhập ký tự chữ", "Sai message ở step 12");

        // Step 13 Enter special characters in the [Họ và tên] field
        orderPage.EnterFullname("@$#*&^fhuudiidi");
        String msg5 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg5, "Chỉ nhập ký tự chữ", "Sai message ở step 13");

        // Step 14 Enter 6 characters in the [Họ và tên] field
        orderPage.EnterFullname("abcbds");
        String msg6 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg6, "Họ và tên phải trên 6 ký tự", "Sai message ở step 14");

        // Step 15 Enter valid [Họ và tên]
        orderPage.EnterFullname("Thuy Duyen");

        // Step 16 Enter numeric characters in the [Địa chỉ] field
        orderPage.EnterAddress("12345678910111234588");
        String msg7 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg7, "Địa chỉ chứa chữ cái và số", "Sai message ở step 16");

        // Step 17 Enter special characters in the [Địa chỉ] field
        orderPage.EnterAddress("@$#*&^fhuudiidi");
        String msg8 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg8, "Địa chỉ chứa chữ cái và số", "Sai message ở step 17");

        // Step 18 Enter 14 characters in the [Địa chỉ] field
        orderPage.EnterAddress("aaaaaaaaaaaaaa");
        String msg9 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg9, "Địa chỉ phải trên 15 ký tự", "Sai message ở step 18");

        // Step 19 Enter 15 characters in the [Địa chỉ] field
        orderPage.EnterAddress("aaaaaaaaaaaaaaa");
        String msg10 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg10, "Địa chỉ phải trên 15 ký tự", "Sai message ở step 19");

        // Step 20 Enter valid in [Địa chỉ] field
        orderPage.EnterAddress("Ngu Hanh Son dictrict");

        // Step 21 Enter incorrect [Mật khẩu] field
        orderPage.EnterPassword("12345");
        String msg11 = orderPage.getWarningMessage();
        softAssert.assertEquals(msg11, "Mật khẩu không đúng", "Sai message ở step 21");

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
