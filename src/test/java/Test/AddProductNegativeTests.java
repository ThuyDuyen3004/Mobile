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

    @Test(description = "TC09 - Add Product with Empty Fields")
    public void TC09_emptyFields() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        addProductsPage.clickSaveButton();
        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Name"),
                "Vui lòng nhập Họ và tên", "Missing name should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Price"),
                "Vui lòng nhập giá sản phẩm", "Missing price should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Quality"),
                "Vui lòng nhập số lượng sản phẩm", "Missing quality should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Sale"),
                "Vui lòng nhập giảm giá sản phẩm", "Missing sales should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Manufactures"),
                "Vui lòng chọn hãng sản xuất", "Missing Manufactures should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Image"),
                "Vui lòng chọn ảnh", "Missing image should show error");

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(description = "TC10 - Add Product with Invalid Price")
    public void TC10_invalidPrice() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = Constants.IMAGE_PATH_PNG;
        String nameProduct = faker.lorem().characters(7);
        String price = "abc"; // Invalid price
        String quality = faker.number().digits(3).toString();
        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";
        String specification = faker.gameOfThrones().character();

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Price"),
                "Giá phải là số và bé hơn 1 tỉ", "Invalid price should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(description = "TC11 - Add Product with Negative Quantity")
    public void TC11_negativeQuantity() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = Constants.IMAGE_PATH_PNG;
        String nameProduct = faker.lorem().characters(7);
        String price = faker.number().digits(5).toString();
        String quality = String.valueOf((faker.number().numberBetween(-999, -100))); // Negative quality
        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";
        String specification = faker.gameOfThrones().character();

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(description = "TC12 - Add Product with Invalid Image Type")
    public void TC12_invalidImageType() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = Constants.IMAGE_PATH_PDF;
        String nameProduct = faker.lorem().characters(7);
        String price = faker.number().digits(5).toString();
        String quality = faker.number().digits(2).toString();
        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";
        String specification = faker.gameOfThrones().character();

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Image"),
                "Ảnh ko đúng định dạng", "Invalid image type should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(description = "TC13 - Add Product with Empty Specification")
    public void TC13_emptySpecification() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = Constants.IMAGE_PATH_PNG;
        String nameProduct = faker.lorem().characters(7);
        String price = faker.number().digits(5).toString();
        String quality = faker.number().digits(2).toString();
        String sale = faker.number().digits(2).toString();
        String manufacture = "Apple";
        String specification = ""; // Empty specification

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

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
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");
        viewProductPage.clickAddProductButton();


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
