package Pages.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By nameInputLocator = By.name("name");
    private By priceInputLocator = By.name("price");
    private By qualityInputLocator = By.name("quality");
    private By salesInputLocator = By.name("sale");
    private By manufacturesLocator = By.name("manufactures");
    private By saveButtonLocator = By.xpath("//button[@type='submit' and contains(text(), 'SAVE')]");
    private By cancelButtonLocator = By.xpath("//button[@type='button' and contains(text(), 'Cancel')]");
    private By uploadImageLocator = By.xpath("//input[@type='file']");

    private By alertMessageSuccessLocator = By.xpath("//p[contains(@class, 'alert-success')]");
    private By alertMessageErrorLocator = By.xpath("//div[@class='box-body']");

    public AddProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    @Step("Get alert message error")
    public String getAlertMessageError() {
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageErrorLocator));
        return alertMessage.getText().trim().split("\n")[0];
    }

    @Step("Get content error by label text")
    public String getSecondDangerTextByLabel(String labelText) {
        String xpath = String.format("//label[contains(text(), '%s')]/span[@class='text-danger'][2]", labelText);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element.getText().trim();
    }
    @Step("Get alert message success")
    public String getAlertMessageSuccess() {
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageSuccessLocator));
        return alertMessage.getText().replace("×", "").trim();
    }
    @Step("Click save button")
    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(saveButtonLocator);
        saveButton.click();
    }

    @Step("Click cancel button")
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(cancelButtonLocator);
        cancelButton.click();
    }

    @Step("Select manufacture by visible text")
    public void selectManufactureByText(String visibleText) {
        WebElement selectElement = driver.findElement(manufacturesLocator);
        Select select = new Select(selectElement);
        select.selectByVisibleText(visibleText);
    }
    @Step("Enter product name")
    public void enterName(String name) {
        WebElement nameInput = driver.findElement(nameInputLocator);
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    @Step("Enter product Price")
    public void enterPrice(String price) {
        WebElement priceInput = driver.findElement(priceInputLocator);
        priceInput.clear();
        priceInput.sendKeys(price);
    }
    @Step("Enter product quality")
    public void enterQuality(String quality) {
        WebElement qualityInput = driver.findElement(qualityInputLocator);
        qualityInput.clear();
        qualityInput.sendKeys(quality);
    }
    @Step("Enter product sales")
    public void enterSales(String sales) {
        WebElement salesInput = driver.findElement(salesInputLocator);
        salesInput.clear();
        salesInput.sendKeys(sales);
    }
    @Step("Enter product Specification")
    public void fillSpecification(String htmlContent) throws InterruptedException {
        // 1. Switch vào iframe CKEditor
        WebElement iframe = driver.findElement(By.xpath(
                "//iframe[contains(@class, 'cke_wysiwyg_frame')]"));

        driver.switchTo().frame(iframe);
        Thread.sleep(2000); // Chờ iframe tải xong

        // 2. Tìm phần tử body trong iframe và nhập nội dung
        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        body.clear();
        body.sendKeys(htmlContent);

        // 3. Quay lại nội dung chính
        driver.switchTo().defaultContent();
    }
    @Step("Upload product image")
    public void uploadImage(String imagePath) {

        // 1. Tìm phần tử input để tải lên hình ảnh
        WebElement uploadInput = driver.findElement(uploadImageLocator);
        // 2. Nhập đường dẫn hình ảnh vào input

        uploadInput.sendKeys(imagePath);
    }

//    public void addProduct(String name, String price, String quality, String sales, String manufacture, String specification) throws InterruptedException {
//        enterName(name);
//        enterPrice(price);
//        enterQuality(quality);
//        enterSales(sales);
//        selectManufactureByText(manufacture);
//        String imagePath = System.getProperty("user.dir") + "/src/main/java/resources/Screenshot_2025-04-21_212042.png";
//        uploadImage(imagePath); // Cập nhật đường dẫn hình ảnh nếu cần
//        fillSpecification(specification);
//        clickSaveButton();
//        Thread.sleep(3000);
//
//    }
    public void addProduct(Product product) throws InterruptedException {
        enterName(product.getName());
        enterPrice(product.getPrice());
        enterQuality(product.getQuality());
        enterSales(product.getSales());
        selectManufactureByText(product.getManufacture());
        uploadImage(product.getImagePath());
        fillSpecification(product.getSpecification());
        Thread.sleep(3000);
        clickSaveButton();

    }

}
