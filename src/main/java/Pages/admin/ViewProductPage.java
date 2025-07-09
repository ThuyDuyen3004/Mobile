package Pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By addProductButtonLocator = By.xpath("//*[@class='box-header']/a");
    private By idProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[1]");
    public ViewProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    public int getIdProduct() {
        WebElement idProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(idProductsLocator));
        String idProduct = idProductElement.getText().trim();
        return Integer.parseInt(idProduct);
    }

    public void clickAddProductButton() {
        WebElement addProductButton = wait.until(ExpectedConditions.elementToBeClickable(addProductButtonLocator));
        addProductButton.click();
    }
}
