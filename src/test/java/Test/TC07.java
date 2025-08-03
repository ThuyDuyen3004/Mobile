package Test;

import Common.BaseTest;
import Common.TestListener;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constants;

@Listeners(TestListener.class)
public class TC07 extends BaseTest {
    @Issue("F004")
    @Test
    @Description("Verify that the product list page correctly displays products that match the selected price filter options.")
    public void CheckFilterOptions() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        ;
        homePage.clickPhoneCategory();

        // Kiểm tra sắp xếp từ thấp đến cao
        homePage.clickXemthem();
        homePage.selectGiaThapDenCao();
        boolean isSorted1 = homePage.isSortedByGiaThapDenCao();
        softAssert.assertTrue(isSorted1, "The list is not sorted correctly in ascending order of price.");

        // Kiểm tra sắp xếp từ cao đến thấp
        homePage.clickXemthem();
        homePage.selectGiaCaoDenThap();
        boolean isSorted2 = homePage.isSortedByGiaCaoDenThap();
        softAssert.assertTrue(isSorted2, "The list is not sorted correctly in descending order of price.");

        softAssert.assertAll();
    }

}
