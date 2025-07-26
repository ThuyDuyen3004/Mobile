package Common;

import Models.Product;
import Pages.admin.AddProductsPage;
import Pages.admin.Categories;
import Pages.admin.ViewProductPage;
import Pages.user.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
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

    protected Categories categories;
    protected AddProductsPage addProductsPage;
    protected ViewProductPage viewProductPage;

    protected Product product;
    protected Faker faker;

    public WebDriver getDriver() {
        return driver;
    }

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

        categories = new Categories(driver, wait);
        addProductsPage = new AddProductsPage(driver, wait);
        viewProductPage = new ViewProductPage(driver, wait);

        faker = new Faker();

    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] takeScreenshot(String testName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        driver.quit();

    }


}
