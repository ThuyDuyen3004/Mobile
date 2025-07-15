
package Pages.user;

import Models.CartItem;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    By passwordLocator = By.id("password_od");
    By fullNameLocator = By.id("name_od");
    By addressLocator = By.id("address_od");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By PaymentButtonLocator= By.id("order_success");


    @Step("Enter full name: {fullName}")
    public void EnterFullname(String d) {
        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameLocator));
        fullNameField.clear();
        fullNameField.sendKeys(d, Keys.TAB);
    }
    @Step("Enter address: {address}")
    public void EnterAddress(String s) {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLocator));
        addressField.clear();
        addressField.sendKeys(s, Keys.TAB);
    }
    @Step("Enter password")
    public void EnterPassword(String p) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        passwordField.clear();
        passwordField.sendKeys(p, Keys.TAB);
    }

    @Step("Get full name from form")
    public String getFullName() {
        return driver.findElement(fullNameLocator).getAttribute("value");
    }

    @Step("Get warning message after form submission")
    public String getWarningMessage() {
        By locator = By.xpath("//i[@class='text-danger']");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception ignored) {
        }

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return messageElement.getText();
        } catch (Exception e) {
            return "MESSAGE NOT FOUND";
        }
    }
    @Step("Click on 'Payment' button")
    public void clickPayMentButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(PaymentButtonLocator));
        button.click();
    }

//    private final String nameXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::label)[%d]";
//    private final String priceXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::span[1])[%d]";
//    private final String discountXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::span[2])[%d]";
//

    @Step("Get product information (name, price, discount) of all products in order page")

    public ArrayList<CartItem> getAllOrderItems() {
        WaitUtils.sleep(2);

        ArrayList<CartItem> cartItemList = new ArrayList<>();

        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@id='list_order']/div[contains(@class,'row information-list')]"));

        for (int i = 1; i <= cartItems.size(); i++) {
            String name = driver.findElement(By.xpath("(//div[@id='list_order']/div[contains(@class,'row information-list')])[" + i + "]//label")).getText();
            String priceText = driver.findElement(By.xpath("(//div[@id='list_order']/div[contains(@class,'row information-list')])[" + i + "]//span[1]")).getText().replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);
            String promotion = driver.findElement(By.xpath("(//div[@id='list_order']/div[contains(@class,'row information-list')])[" + i + "]//span[2]")).getText();

            cartItemList.add(new CartItem(name, price, promotion));

          //  System.out.println("Cart Item " + i + ": " + name + ", Price: " + price + ", Promotion: " + promotion);
        }

        return cartItemList;
    }

}