package Test;


import Pages.admin.AddProductsPage;
import Pages.admin.Categories;
import Pages.admin.Product;
import Pages.admin.ViewProductPage;
import Pages.user.HomePage;
import Pages.user.LoginPage;
import com.github.javafaker.Faker;
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

    @Test (description = "TC_08 - Add Product Successfully")
    public void testAddProductSuccessfully() throws InterruptedException {
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");
        int idProduct = viewProductPage.getIdProduct();

        // Step 4
        viewProductPage.clickAddProductButton();
        // Step 5 ~ Step 11
        String imagePath = Constants.IMAGE_PATH_PNG;
        String nameProduct = faker.lorem().characters(7);
        String price = faker.number().digits(5).toString();
        String quality = faker.number().digits(3).toString();
        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";
        String specification = faker.gameOfThrones().character();

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);

        // Step 12
        softAssert.assertEquals(addProductsPage.getAlertMessageSuccess(),
                "Cập nhật thành công", "Không tìm thấy thông báo thành công"
        );


        categories.goToCategoryByName("Products");
        String nameProductAfterAdd = viewProductPage.getNameProduct();
        String priceProductAfterAdd = viewProductPage.getPriceProduct();
        String qualityProductAfterAdd = viewProductPage.getQualityProduct();
        String saleProductAfterAdd = viewProductPage.getSaleProduct();
        String manufactureProductAfterAdd = viewProductPage.getMunafacturesProduct();

        softAssert.assertTrue(product.getName().equals(nameProductAfterAdd) &&
                        product.getPrice().equals(priceProductAfterAdd) && product.getQuality().equals(qualityProductAfterAdd)  &&
                        product.getSales().equals(saleProductAfterAdd)  && product.getManufacture().equals(manufactureProductAfterAdd),
                "Sản phẩm mới được thêm vào list Prodcut không thành công");

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
        faker = new Faker();


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

    Faker faker;
}
