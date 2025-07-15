package Test;

import Common.BaseTest;
import Pages.user.*;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.Duration;

public class TC07 extends BaseTest {
    @Test
    @Description("Verify that the product list page correctly displays products that match the selected price filter options.")
    public void CheckFilterOptions() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);;
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

        softAssert.assertAll();}

}
