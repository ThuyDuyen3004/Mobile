package Pages.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    private By usernameTextBoxLocator = By.id("e_p_lg");
    private By passwordTextBoxLocator = By.id("pass_lg");
    private By loginButtonLocator = By.id("submit_lg");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameTextBoxLocator));
        usernameField.sendKeys(username, Keys.TAB);
    }
    @Step("Enter password")
    public void enterPassword(String password) {
        driver.findElement(passwordTextBoxLocator).sendKeys(password, Keys.TAB);
    }
    @Step("Click on login button")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
    @Step("Log in")
    public void login(String username, String password) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        Thread.sleep(2000);
    }

}
