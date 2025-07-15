package Test;

import Common.BaseTest;
import Pages.user.*;
import Pages.user.SearchPage;
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
import java.util.List;

public class TC02 extends BaseTest {

    @Test
    @Description("Verify that users can search for a product by keyword, confirm the product matches the keyword and can view its detail page")
    public void verifyUserCanSearchProductByKeyword() throws InterruptedException {
        driver.get(Constants.URL);
        String keyword = "samsung";
        searchPage.searchProducts(keyword);
        // ktr tat ca cac sp hiển thị có chứa keyword ko
        softAssert.assertTrue(searchPage.areAllResultsMatchingKeyword(keyword), "Some product names do not match keyword: " + keyword);
        searchPage.selectProductByIndex(2);
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("product_details"), "Detail page URL is invalid" + currentUrl);
        Thread.sleep(100);
        softAssert.assertAll();
    }
}
