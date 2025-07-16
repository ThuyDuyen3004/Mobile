package Test;

import Common.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

public class TC14 extends BaseTest {
    @Test(description = "TC14 - Verify Cancel Button in Add Product Form")
    public void TC14_verifyCancelButtonInAddProductForm() throws InterruptedException {
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

        addProductsPage.clickCancelButton();
        softAssert.assertEquals(viewProductPage.getTitleViewProductPage(), "View Products",
                "Không tìm thấy tiêu đề trang 'View Products' sau khi nhấn button Cancel");

        softAssert.assertAll();
    }
    
//    @BeforeClass
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get(Constants.URL);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        softAssert = new SoftAssert();
//
//        categories = new Categories(driver, wait);
//        addProductsPage = new AddProductsPage(driver, wait);
//        viewProductPage = new ViewProductPage(driver, wait);
//        loginPage = new LoginPage(driver);
//        homePage = new HomePage(driver);
//
//    }
//
//    @AfterClass
//    public void tearDown() {
//
//        driver.quit();
//
//    }
//    WebDriver driver;
//    WebDriverWait wait;
//    SoftAssert softAssert;
//    Categories categories;
//    AddProductsPage addProductsPage;
//    ViewProductPage viewProductPage;
//    HomePage homePage;
//    LoginPage loginPage;
}
