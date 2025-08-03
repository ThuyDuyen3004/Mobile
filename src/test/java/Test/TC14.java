package Test;

import Common.BaseTest;
import Common.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

@Listeners(TestListener.class)
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


}
