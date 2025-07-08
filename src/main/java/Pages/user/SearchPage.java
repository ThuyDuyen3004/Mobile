package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By ProductNameInSearchDisplay = By.xpath("//div[@id='search_show']//label/a[@href]");
    public void SelectProduct() {
        driver.findElement(ProductNameInSearchDisplay).click();
    }
    public List<String> searchProductsByKeyword(String keyword) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Bạn cần tìm sản phẩm nào?']")));
        searchBox.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchInput.clear();
        searchInput.sendKeys(keyword);

        By productLocator = By.xpath("//div[@id='search_show']//label//a");
        List<WebElement> productElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLocator));

        System.out.println("Tìm thấy " + productElements.size() + " sản phẩm");

        List<String> result = new ArrayList<>();
        for (WebElement element : productElements) {
            String name = element.getText().trim();
            if (name.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(name);
            }
        }
        return result;
    }
}
