//package Pages.user;
//
//import net.datafaker.Faker;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class OrderPage {
//    private WebDriver driver;
//    protected WebDriverWait wait;
//    By passwordLocotor = By.id("password_od");
//    By PaymentButtonLocotor = By.id("order_success");
//    By FullNameLocator = By.id("name_od");
//    public OrderPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        this.faker = new Faker();
//    }
//    public void EnterPassWord(String p) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(passwordLocotor));
//        driver.findElement(passwordLocotor).sendKeys(p);
//    }
//
//    public void ClickPaymentButton() {
//        driver.findElement(PaymentButtonLocotor).click();
//    }
//    public void Payment(String p) {
//        //ClickOrderButton();
//        EnterPassWord(p);
//        ClickPaymentButton();
//        ClickPaymentButton();
//    }
//    public void EnterFullname(String d) {
//        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(FullNameLocator));
//        fullNameField.clear();
//        fullNameField.sendKeys(d, Keys.TAB);
//    }
//    public String getFullName() {
//        return driver.findElement(FullNameLocator).getAttribute("value");
//    }
//
//
//    public void EnterAddress(String s) {
//        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address_od")));
//        addressField.clear();
//        addressField.sendKeys(s, Keys.TAB);
//    }
//
//    public void EnterPassword(String p) {
//        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password_od")));
//        passwordField.clear();
//        passwordField.sendKeys(p, Keys.TAB);
//    }
//    public void EnterOrderInformation(String d, String s, String p) {
//        EnterFullname(d);
//        EnterAddress(s);
//        EnterPassword(p);
//    }
//    public String getWarningMessage() {
//        By locator = By.xpath("//i[@class='text-danger']");
//
//        try {
//            new WebDriverWait(driver, Duration.ofSeconds(3))
//                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        } catch (Exception ignored) {
//        }
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        String messageText;
//
//        try {
//            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            messageText = messageElement.getText();
//        } catch (Exception e) {
//            messageText = "MESSAGE NOT FOUND";
//        }
//
//        return messageText;
//    }
//
//    Faker faker = new Faker();;
//    // Ví dụ hàm sinh dữ liệu tự động
//    public String getFakeFullName() {
//        return faker.name().fullName();
//    }
//
//    public String getFakeAddress() {
//        return faker.address().streetAddress();
//    }
//
//    public String getFakePassword() {
//        return faker.internet().password(10, 15, true, true, true);
//    }
//
//
//}

package Pages.user;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    By passwordLocator = By.id("password_od");
    By paymentButtonLocator = By.id("order_success");
    By fullNameLocator = By.id("name_od");
    By addressLocator = By.id("address_od");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void EnterFullname(String d) {
        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameLocator));
        fullNameField.clear();
        fullNameField.sendKeys(d, Keys.TAB);
    }

    public void EnterAddress(String s) {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLocator));
        addressField.clear();
        addressField.sendKeys(s, Keys.TAB);
    }

    public void EnterPassword(String p) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        passwordField.clear();
        passwordField.sendKeys(p, Keys.TAB);
    }

    public void ClickPaymentButton() {
        driver.findElement(paymentButtonLocator).click();
    }

    public void EnterOrderInformation(String name, String address, String password) {
        EnterFullname(name);
        EnterAddress(address);
        EnterPassword(password);
    }

    public String getFullName() {
        return driver.findElement(fullNameLocator).getAttribute("value");
    }


    public String getWarningMessage() {
        By locator = By.xpath("//i[@class='text-danger']");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception ignored) {}

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return messageElement.getText();
        } catch (Exception e) {
            return "MESSAGE NOT FOUND";
        }
    }
//
    private final String nameXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::label)[%d]";
    private final String priceXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::span[1])[%d]";
    private final String discountXpath = "(//div[@id='list_order']//div[contains(@class,'information-list')]/descendant::span[2])[%d]";

    public String getProductNameAfterByIndex(int index) {
        By locator = By.xpath(String.format(nameXpath, index));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText().trim();
    }

    public double getProductPriceAfterByIndex(int index) {
        By locator = By.xpath(String.format(priceXpath, index));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String raw = element.getText().replace("đ", "").replace(".", "").replace(",", "").trim();
        return Double.parseDouble(raw);
    }

    public double getProductDiscountAfterByIndex(int index) {
        By locator = By.xpath(String.format(discountXpath, index));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String raw = element.getText().replace("%", "").trim();
        return Double.parseDouble(raw);
    }
    //
    public boolean isProductInfoMatchingWithCart(int index, CartPage cartPage) {
        // Dữ liệu từ cart
        String nameCart = cartPage.getProductNameByIndex(index);
        double priceCart = cartPage.getUnitPriceByIndex(index);
        double discountCart = cartPage.getDiscountAsDoubleByIndex(index);

        // Dữ liệu từ order page
        String nameOrder = getProductNameAfterByIndex(index);
        double priceOrder = getProductPriceAfterByIndex(index);
        double discountOrder = getProductDiscountAfterByIndex(index);

//        System.out.printf("Product %d:\n", index);
//        System.out.printf( nameCart, priceCart, discountCart);
//        System.out.printf(nameOrder, priceOrder, discountOrder);

        return nameCart.equalsIgnoreCase(nameOrder) &&
                (priceCart==priceOrder) && (discountCart==discountOrder);
    }
    //
    By comebackLocator = By.xpath("//button[@id='cancel_order']");
    public void clickComebackbutton()
    {
        driver.findElement(comebackLocator).click();
    }
}

