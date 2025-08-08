package Test;

import Common.BaseTest;
import Common.TestListener;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constants;

@Listeners(TestListener.class)
public class TC01 extends BaseTest {
    @Issue("F001")
    @Test
    @Description("Verify that the user can purchase a product successfully when all information fields are valid")
    public void VerifySuccessfulPurchase() throws InterruptedException {
        driver.get(Constants.URL);

        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        // random
        homePage.clickButtonAddToCartRandom();
        cartPage.ClickOrderButton();

        // Đổi sang OrderPage để nhập password
        orderPage.EnterPassword(Constants.PASSWORD);
        orderPage.clickPayMentButton();


        // Lấy tên user từ fullname
        String username = orderPage.getFullName();

        // Lấy text cảm ơn
        String thankYouText = paymentPage.getThankYouText();

        String expectedThankYou = "Cảm ơn bạn " + username + " đã tin dùng sản phẩm của chúng tôi";
        Assert.assertEquals(thankYouText, expectedThankYou, "Thông báo cảm ơn không khớp!");

        // Lấy text liên hệ
        String contactText = paymentPage.getContactText();

        String expectedContact = "Chúng tôi sẽ liên hệ với bạn trong vòng 5 phút. Và giao hàng trong 30 phút.";
        Assert.assertEquals(contactText, expectedContact);

        softAssert.assertAll();
    }
}