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
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;

public class AddProductNegativeTests {

    @Test(description = "Add product failed")
    public void addProductFailed() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");
        viewProductPage.clickAddProductButton();


        // TC 09 - Add Product with Empty Fields
        addProductsPage.clickSaveButton();
        softAssert.assertEquals(addProductsPage.getTextByLabel("Name"),
                "Vui lòng nhập Họ và tên", "Missing name should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Price"),
                "Vui lòng nhập giá sản phẩm", "Missing price should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Quality"),
                "Vui lòng nhập số lượng sản phẩm", "Missing quality should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Sale"),
                "Vui lòng nhập giảm giá sản phẩm", "Missing sales should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Manufactures"),
                "Vui lòng chọn hãng sản xuất", "Missing Manufactures should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Image"),
                "Vui lòng chọn ảnh", "Missing image should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Empty Fields");

        // Data for the next test
        String imagePath = Constants.IMAGE_PATH_PNG;
        String imagePathPDF = Constants.IMAGE_PATH_PDF;
        String nameProduct = faker.lorem().characters(7);

        String priceInvalid = "abc"; // Invalid price
        String price = faker.number().digits(5).toString();

        String quality = faker.number().digits(3).toString();
        String qualityNegative = String.valueOf((faker.number().numberBetween(-999, -100))); // Negative quality


        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";

        String specification = faker.gameOfThrones().character();
        String specificationEmpty = ""; // Empty specification

        //TC 10 - Add Product with Invalid Price
        product = new Product(nameProduct,
                priceInvalid, quality, sale, manufacture,
                specification, imagePath);
        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getTextByLabel("Price"),
                "Giá phải là số và bé hơn 1 tỉ", "Invalid price should show error");

        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Invalid Price");

        // TC 11 - Add Product with Negative Quantity
        product = new Product(nameProduct,
                price, qualityNegative, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Negative Quantity");

        // Close the alert message
        addProductsPage.closeAlertMessageSuccess();

        // TC 12 - Add Product with Invalid Image Type
        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePathPDF);

        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getTextByLabel("Image"),
                "Ảnh ko đúng định dạng", "Invalid image type should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Invalid Image Type");

        // TC 13 - Add Product with Empty Specification
        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specificationEmpty, imagePath);

        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Empty Specification");

        softAssert.assertAll();
    }


    @BeforeClass
    public void setUp() throws InterruptedException {

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

    @AfterClass
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
