package Test;

import Common.BaseTest;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import utils.Constants;

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
        softAssert.assertAll();
    }
}
