package Test;
import Common.BaseTest;
import Models.CartItem;
import org.testng.annotations.Test;
import utils.Constants;
import java.util.ArrayList;

public class TC05 extends BaseTest {
    @Test
    public void verifyProductDetailsMatchBetweenCartAndOrderPage() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        homePage.add3RandomProductsToCart();
        homePage.clickCartIcon();


        ArrayList<CartItem> list1 = cartPage.getAllCartItems();
       // System.out.println("Cart Items: " + list1);

        cartPage.ClickOrderButton();

        ArrayList<CartItem> list2 = orderPage.getAllOrderItems();
      //  System.out.println("Order Items: " + list2);

        softAssert.assertEquals(list1, list2, "Product details do not match between Cart and Order page");
        softAssert.assertAll();

    }
}
