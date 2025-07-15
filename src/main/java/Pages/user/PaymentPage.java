package Pages.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    protected WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By thankYouText = By.xpath("//div[@id='success_modal']//h4[1]");
    private By contactText = By.xpath("//div[@id='success_modal']//h4[2]");

    @Step("Get 'Thank You' message text")

    public String getThankYouText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouText));
        return element.getText().trim();
    }


    @Step("Get contact message text")
    public String getContactText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(contactText));
        return element.getText().trim();
    }
}
