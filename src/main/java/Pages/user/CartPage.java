package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By DatHangNgayButtonLocator = By.id("order_product");
    public void ClickOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(DatHangNgayButtonLocator));
        driver.findElement(DatHangNgayButtonLocator).click();
    }
    public void CloseCartForm() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='cart_modal']//button[@type='button' and normalize-space()='×']")));
        closeButton.click();
    }

}
