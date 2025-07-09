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
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;
import java.util.Random;
public class AddProductNegativeTests {

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void TC10_invalidPrice() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/Screenshot_2025-04-21_212042.png";
        String nameProduct = new Random().nextInt(1000) + "Test Product";

        product = new Product(nameProduct,
                "abc", "500", "50", "Apple",
                "hihihi", imagePath);

        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Price"),
                "Giá phải là số và bé hơn 1 tỉ", "Invalid price should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void TC11_negativeQuantity() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/Screenshot_2025-04-21_212042.png";
        String nameProduct = new Random().nextInt(1000) + "Test Product";

        product = new Product(nameProduct,
                "abc", "-10", "50", "Apple",
                "hihihi", imagePath);

        addProductsPage.addProduct(product);

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void TC12_invalidImageType() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/2025-05-18_15h14_09.pdf";
        String nameProduct = new Random().nextInt(1000) + "Test Product";

        product = new Product(nameProduct,
                "500", "10", "50", "Apple",
                "hihihi", imagePath);

        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getSecondDangerTextByLabel("Image"),
                "Ảnh ko đúng định dạng", "Invalid image type should show error");

        String alert = addProductsPage.getAlertMessageError();
        softAssert.assertEquals(alert, "ko thành cong",
                "Missing required fields should show error");

        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void TC13_emptySpecification() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/Screenshot_2025-04-21_212042.png";
        String nameProduct = new Random().nextInt(1000) + "Test Product";

        product = new Product(nameProduct,
                "500", "10", "50", "Apple",
                "", imagePath);

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

}
