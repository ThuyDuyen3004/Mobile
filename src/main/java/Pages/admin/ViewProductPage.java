package Pages.admin;

import io.qameta.allure.Step;
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
    private By nameProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[2]");
    private By priceProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[3]");
    private By qualityProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[4]");
    private By saleProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[5]");
    private By munafacturesProductsLocator = By.xpath("//*[@id='view']/tbody/tr[1]/td[6]");




    public ViewProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    @Step("Get Name of the first product")
    public String getNameProduct() {
        WebElement nameProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameProductsLocator));
        String nameProduct = nameProductElement.getText().trim();
        return nameProduct;
    }
    @Step("Get Price of the first product")
    public String getPriceProduct() {
        WebElement priceProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceProductsLocator));
        String priceProduct = priceProductElement.getText().trim().replace("đ", "");
        return priceProduct;
    }

    @Step("Get Quality of the first product")
    public String getQualityProduct() {
        WebElement qualityProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(qualityProductsLocator));
        String qualityProduct = qualityProductElement.getText().split(" ")[0].trim();
        return qualityProduct;
    }

    @Step("Get Sale of the first product")
    public String getSaleProduct() {
        WebElement saleProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(saleProductsLocator));
        String saleProduct = saleProductElement.getText().replace("%", "").trim();
        return saleProduct;
    }
    @Step("Get Manufacture of the first product")
    public String getMunafacturesProduct() {
        WebElement munafacturesProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(munafacturesProductsLocator));
        String munafacturesProduct = munafacturesProductElement.getText().trim();
        return munafacturesProduct;
    }

    @Step("Get ID of the first product")
    public int getIdProduct() {
        WebElement idProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(idProductsLocator));
        String idProduct = idProductElement.getText().trim();
        return Integer.parseInt(idProduct);
    }

    @Step("Click Add Product Button")
    public void clickAddProductButton() {
        WebElement addProductButton = wait.until(ExpectedConditions.elementToBeClickable(addProductButtonLocator));
        addProductButton.click();
    }
}
