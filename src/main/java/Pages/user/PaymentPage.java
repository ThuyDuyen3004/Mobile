package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String getThankYouText() {
        WebElement element = driver.findElement(thankYouText);
        return element.getAttribute("textContent").trim();
    }

    public String getContactText() {
        WebElement element = driver.findElement(contactText);
        return element.getAttribute("textContent").trim();
    }


}
