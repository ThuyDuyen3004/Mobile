package Test;


import Pages.admin.AddProductsPage;
import Pages.admin.Categories;
import Pages.admin.Product;
import Pages.admin.ViewProductPage;
import Pages.user.HomePage;
import Pages.user.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;
import java.util.Random;

public class TC_08 {
    @Test
    public void testAddProductSuccessfully() throws InterruptedException {
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);
        Thread.sleep(3000);
        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");
        int idProduct = viewProductPage.getIdProduct();

        // Step 4
        viewProductPage.clickAddProductButton();
        // Step 5 ~ Step 11
        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/Screenshot_2025-04-21_212042.png";
        String nameProduct = new Random().nextInt(1000) + "Test Product";

        product = new Product(nameProduct,
                "100.00", "10", "5", "Apple",
                "This is a test product description", imagePath);

        addProductsPage.addProduct(product);

        // Step 12
        softAssert.assertEquals(addProductsPage.getAlertMessageSuccess(),
                "Cập nhật thành công", "Không tìm thấy thông báo thành công"
        );


        categories.goToCategoryByName("Products");

        int newIdProduct = viewProductPage.getIdProduct();
        softAssert.assertTrue(newIdProduct > idProduct,
                "Sản phẩm mới được thêm vào list Prodcut thành công");

        softAssert.assertAll();
    }


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();

        categories = new Categories(driver, wait);
        addProductsPage = new AddProductsPage(driver, wait);
        viewProductPage = new ViewProductPage(driver, wait);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;
    Categories categories;
    AddProductsPage addProductsPage;
    ViewProductPage viewProductPage;
    HomePage homePage;
    LoginPage loginPage;
    Product product;


}
