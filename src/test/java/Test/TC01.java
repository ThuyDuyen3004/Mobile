package Test;

import Pages.user.*;
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

public class TC01 {

    @Test
    @Description("Verify that the user can purchase a product successfully when all information fields are valid")
    public void VerifySuccessfulPurchase() throws InterruptedException{
        driver.get(Constants.URL);

        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        // random
        List<WebElement> allButtons = homePage.getAllAddToCartButtons();
        WebElement randomButton = allButtons.get(new Random().nextInt(allButtons.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
        cartPage.ClickOrderButton();

        // Đổi sang OrderPage để nhập password
        orderPage.EnterPassword("3042004a3A@");

        // Lấy tên user từ fullname
        String username = orderPage.getFullName();

        // Lấy text cảm ơn
        String thankYouText = paymentPage.getThankYouText();
        System.out.println("ThankYouText: [" + thankYouText + "]");

        String expectedThankYou = "Cảm ơn bạn " + username + " đã tin dùng sản phẩm của chúng tôi";
        Assert.assertEquals(thankYouText, expectedThankYou, "Thông báo cảm ơn không khớp!");

        // Lấy text liên hệ
        String contactText = paymentPage.getContactText();
        System.out.println("ContactText: [" + contactText + "]");

        String expectedContact = "Chúng tôi sẽ liên hệ với bạn trong vòng 5 phút. Và giao hàng trong 30 phút.";
        Assert.assertEquals(contactText, expectedContact);

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
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
