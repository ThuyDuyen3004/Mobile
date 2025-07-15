
package Test;

import Common.BaseTest;
import Pages.user.*;
import com.github.javafaker.Faker;
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
import java.util.Locale;
import java.util.Random;

public class TC04 extends BaseTest {

    @Test
    @Description("Verify that the order form displays the correct validation messages when entering invalid data or leaving fields empty")
    public void verifyOrderFormValidationMessages() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        // random
        homePage.clickButtonAddToCartRandom();

        cartPage.ClickOrderButton();
        Faker faker = new Faker(new Locale("vi"));
        String validName = faker.name().fullName();
        String validAddress = faker.address().fullAddress();
        String validPassword = Constants.PASSWORD;

        // Step 5 - Leave the [Họ và tên] field empty
        orderPage.EnterFullname("");

        // Step 6
        orderPage.EnterAddress(validAddress);
        orderPage.EnterPassword(validPassword);

        softAssert.assertEquals(orderPage.getWarningMessage(), "Họ và tên không được để trống", "Sai message ở step 5");

        // Step 7 - Tên hợp lệ
        orderPage.EnterFullname(validName);

        // Step 8 - Địa chỉ rỗng
        orderPage.EnterAddress("");

        softAssert.assertEquals(orderPage.getWarningMessage(), "Địa chỉ không được để trống", "Sai message ở step 8");

        // Step 9 - Địa chỉ hợp lệ
        orderPage.EnterAddress(validAddress);

        // Step 10 - Mật khẩu rỗng
        orderPage.EnterPassword("");

        softAssert.assertEquals(orderPage.getWarningMessage(), "Mật khẩu không được để trống", "Sai message ở step 10");

        // Step 11 - Mật khẩu hợp lệ
        orderPage.EnterPassword(validPassword);

        // Step 12 - Tên toàn số
        orderPage.EnterFullname(faker.number().digits(10));

        softAssert.assertEquals(orderPage.getWarningMessage(), "Chỉ nhập ký tự chữ", "Sai message ở step 12");

        // Step 13 - Tên có ký tự đặc biệt
        orderPage.EnterFullname("@$#*&^" + faker.lorem().characters(5));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Chỉ nhập ký tự chữ", "Sai message ở step 13");

        // Step 14 - Tên có 6 ký tự
        orderPage.EnterFullname(faker.lorem().characters(6));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Họ và tên phải trên 6 ký tự", "Sai message ở step 14");

        // Step 15 - Tên hợp lệ
        orderPage.EnterFullname(validName);

        // Step 16 - Địa chỉ toàn số
        orderPage.EnterAddress(faker.number().digits(20));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Địa chỉ chứa chữ cái và số", "Sai message ở step 16");

        // Step 17 - Địa chỉ có ký tự đặc biệt
        orderPage.EnterAddress("@$#*&^" + faker.lorem().characters(5));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Địa chỉ chứa chữ cái và số", "Sai message ở step 17");

        // Step 18 - Địa chỉ chỉ 14 ký tự
        orderPage.EnterAddress(faker.lorem().characters(14));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Địa chỉ phải trên 15 ký tự", "Sai message ở step 18");

        // Step 19 - Địa chỉ 15 ký tự
        orderPage.EnterAddress(faker.lorem().characters(15));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Địa chỉ phải trên 15 ký tự", "Sai message ở step 19");

        // Step 20 - Địa chỉ hợp lệ
        orderPage.EnterAddress(validAddress);

        // Step 21 - Mật khẩu sai
        orderPage.EnterPassword(faker.number().digits(5));
        softAssert.assertEquals(orderPage.getWarningMessage(), "Mật khẩu không đúng", "Sai message ở step 21");

        softAssert.assertAll();
    }

}
