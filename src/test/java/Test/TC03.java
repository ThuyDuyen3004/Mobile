package Test;

import Common.BaseTest;
import Common.TestListener;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constants;

@Listeners(TestListener.class)
public class TC03 extends BaseTest {
    @Issue("F002")
    @Test
    @Description("Verify that the products in the cart remain intact and are not lost or changed when the user exits the account")
    public void verifyCartPersistenceAfterLogout() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        // Add random product to cart
        homePage.clickButtonAddToCartRandom();

        cartPage.closeCartForm();


        homePage.logout();

        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);


        int actualQuantity = homePage.QuatityInCart();
        softAssert.assertEquals(actualQuantity, 1, "Expected quantity in cart = 1 but got: " + actualQuantity);

        softAssert.assertAll();
    }


}
