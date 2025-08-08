package Test;

import Common.BaseTest;
import Common.TestListener;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constants;

@Listeners(TestListener.class)
public class TC06 extends BaseTest {

    @Test
    @Description("Verify that [Thành tiền] and [Tổng tiền] are calculated correctly for all products in the cart")
    public void VerifyThatCalculatedCorrectly() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        homePage.add3RandomProductsToCart();
        homePage.clickCartIcon();

        // Kiểm tra Subtotal từng dòng
        Assert.assertTrue(cartPage.isSubtotalCorrect(1), "Subtotal is incorrect on row 1");
        Assert.assertTrue(cartPage.isSubtotalCorrect(2), "Subtotal is incorrect on row 2");
        Assert.assertTrue(cartPage.isSubtotalCorrect(3), "Subtotal is incorrect on row 3");

        // Kiểm tra tổng tiền
        Assert.assertTrue(cartPage.isTotalPriceCorrect(), "Total price is not equal to the sum of all subtotals");
        softAssert.assertAll();
    }


}
