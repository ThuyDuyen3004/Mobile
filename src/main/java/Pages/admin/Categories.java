package Pages.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Categories {
    private WebDriver driver;
    private WebDriverWait wait;
    private By categoriesLocator = By.xpath("//ul[@class='sidebar-menu']/li/a/span");

    public Categories(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    @Step("Go to category by name: {categoryName}")
    public void goToCategoryByName(String categoryName) {
        List<WebElement> categories = driver.findElements(categoriesLocator);
        for (WebElement category : categories) {
            if (category.getText().trim().contentEquals(categoryName.trim())) {
                category.click();

                break;
            }
        }
    }

    public void openProductPage() {
        goToCategoryByName("Products");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='box-title']")));
    }
}
