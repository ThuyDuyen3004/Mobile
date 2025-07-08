package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    By passwordLocotor = By.id("password_od");
    By PaymentButtonLocotor = By.id("order_success");
    By FullNameLocator = By.id("name_od");
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void EnterPassWord(String p) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(passwordLocotor));
        driver.findElement(passwordLocotor).sendKeys(p);
    }

    public void ClickPaymentButton() {
        driver.findElement(PaymentButtonLocotor).click();
    }
    public void Payment(String p) {
        //ClickOrderButton();
        EnterPassWord(p);
        ClickPaymentButton();
        ClickPaymentButton();
    }
    public void EnterFullname(String d) {
        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(FullNameLocator));
        fullNameField.clear();
        fullNameField.sendKeys(d, Keys.TAB);
    }
    public String getFullName() {
        return driver.findElement(FullNameLocator).getAttribute("value");
    }


    public void EnterAddress(String s) {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_od")));
        addressField.clear();
        addressField.sendKeys(s, Keys.TAB);
    }

    public void EnterPassword(String p) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password_od")));
        passwordField.clear();
        passwordField.sendKeys(p, Keys.TAB);
    }
    public void EnterOrderInformation(String d, String s, String p) {
        EnterFullname(d);
        EnterAddress(s);
        EnterPassword(p);
    }
    public String getWarningMessage() {
        By locator = By.xpath("//i[@class='text-danger']");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception ignored) {
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String messageText;

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            messageText = messageElement.getText();
        } catch (Exception e) {
            messageText = "MESSAGE NOT FOUND";
        }

        return messageText;
    }


}
