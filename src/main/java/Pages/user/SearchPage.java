package Pages.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    protected WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By Searchboxlocator = By.xpath("//input[@placeholder='Bạn cần tìm sản phẩm nào?']");
    private By Searchinput = By.id("search");
    private By productLocator = By.xpath("//div[@id='search_show']//label//a");

    @Step("Search for product with keyword: {keyword}")
    public void searchProducts(String keyword) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(Searchboxlocator));
        searchBox.click();
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(Searchinput));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }


    @Step("Verify all search results match the keyword: {keyword}")
    public boolean areAllResultsMatchingKeyword(String keyword) {
        List<WebElement> productElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLocator));

        for (WebElement element : productElements) {
            String name = element.getText().trim().toLowerCase();
            if (!name.contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }


    @Step("Select product at index: {index}")
    public void selectProductByIndex(int index) {
        String xpath = String.format("(//div[@id='search_show']//label/a)[%d]", index);
        By productLocator = By.xpath(xpath);
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        product.click();
    }

}
