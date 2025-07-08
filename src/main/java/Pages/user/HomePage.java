package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    private By DangNhapButton = By.xpath("//div[@class='top-header']//a[@data-target='#login']");
    public void OpenLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(DangNhapButton));
        loginBtn.click();
    }
    public List<WebElement> getAllAddToCartButtons() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'cart_class')]")));

        List<WebElement> allButtons = driver.findElements(By.xpath("//button[contains(@class,'cart_class')]"));

        List<WebElement> enabledButtons = new ArrayList<>();
        for (WebElement btn : allButtons) {
            if (btn.isEnabled()) {
                enabledButtons.add(btn);
            } else {
                System.out.println("Bỏ qua nút Add to Cart bị disable.");
            }
        }
        return enabledButtons;
    }


}
