package Test;

import Common.BaseTest;
import Pages.user.*;
import Pages.user.CartPage;
import Pages.user.HomePage;
import Pages.user.LoginPage;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TC03 extends BaseTest {
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
