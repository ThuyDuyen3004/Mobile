package Pages.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By DangNhapButton = By.xpath("//div[@class='top-header']//a[@data-target='#login']");
    By LastNameAtAvatar = By.xpath("//div[contains(@class, 'login-top')]//img/following-sibling::span");
    By QuatityInCart = By.xpath("//div[contains(@class,'login-top')]//span[contains(@class,'text-danger')]");
    By CartButton = By.xpath("//div[contains(@class, 'login-top')]//button[contains(@class, 'cart_modal')]");
    By LoggoutButtonLocator = By.xpath("//div[@class='btn-group open']//a[@class='logout']");

    @Step("Click biểu tượng giỏ hàng")
    public void clickCartIcon() {
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(CartButton));
        cartIcon.click();
    }


    @Step("Lấy số lượng sản phẩm trong giỏ")
    public Integer QuatityInCart() {
        return Integer.parseInt(driver.findElement(QuatityInCart).getText());
    }


    public void clickUserName() {
        WebElement Lastusername = wait.until(ExpectedConditions.visibilityOfElementLocated(LastNameAtAvatar));
        Lastusername.click();
    }


    public void logoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LoggoutButtonLocator)).click();
    }
    @Step("Thực hiện logout")
    public void logout() {
        clickUserName();
        logoutButton();
    }
}
