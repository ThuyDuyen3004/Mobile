package Pages.admin;

import Models.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    private By titleViewProductPageLocator = By.xpath("//h3[@class='box-title']");
    private By titleTableViewProductPageLocator = By.xpath("//*[@id='view']/thead/tr/th");

    public ViewProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private int getColumnIndexByName(String columnName) {

        // Tìm tất cả các thẻ <th> (tiêu đề cột)
        List<WebElement> headers = driver.findElements(titleTableViewProductPageLocator);

        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers.get(i).getText().trim();

            if (headerText.equalsIgnoreCase(columnName.trim())) {

                return i + 1; // Trả về chỉ số cột (bắt đầu từ 1)
            }
        }

        // Nếu không tìm thấy tên cột thì throw exception
        throw new NoSuchElementException("Không tìm thấy cột với tên: " + columnName);
    }

    private String getDataTableViewProductPage(int index, String columnName) {
        String columnNameTrimmed = String.format("//*[@id='view']/tbody/tr[%d]/td[%d]", index, getColumnIndexByName(columnName));

        return columnNameTrimmed;
    }

    @Step("Get Name product")
    public String getNameProduct(int index) {
        WebElement nameProductElement = driver.findElement(By.xpath(getDataTableViewProductPage(index, "Name")));
        String nameProduct = nameProductElement.getText().trim();
        return nameProduct;

    }

    @Step("Get Title of View Product Page")
    public String getTitleViewProductPage() {
        WebElement titleViewProductPageElement = driver.findElement(titleViewProductPageLocator);
        String titleViewProductPage = titleViewProductPageElement.getText().trim();
        return titleViewProductPage;
    }

    @Step("Get Price product")
    public String getPriceProduct(int index) {
        WebElement priceProductElement = driver.findElement(By.xpath(getDataTableViewProductPage(index, "Price")));

        String priceProduct = priceProductElement.getText().trim().
                replace("đ", "").replace(",", "");

        return priceProduct;
    }

    @Step("Get Quality product")
    public String getQualityProduct(int index) {
        WebElement qualityProductElement = driver.findElement(By.xpath(getDataTableViewProductPage(index, "Quality")));
        String qualityProduct = qualityProductElement.getText().split(" ")[0].trim();
        return qualityProduct;
    }

    @Step("Get Sale product")
    public String getSaleProduct(int index) {
        WebElement saleProductElement = driver.findElement(By.xpath(getDataTableViewProductPage(index, "Sale")));
        String saleProduct = saleProductElement.getText().replace("%", "").trim();
        return saleProduct;
    }

    @Step("Get Manufacture product")
    public String getMunafacturesProduct(int index) {
        WebElement munafacturesProductElement = driver.findElement(By.xpath(getDataTableViewProductPage(index, "Munafactures")));
        String munafacturesProduct = munafacturesProductElement.getText().trim();
        return munafacturesProduct;
    }

    @Step("Get ID product")
    public int getIdProduct() {
        WebElement idProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(idProductsLocator));
        String idProduct = idProductElement.getText().trim();
        return Integer.parseInt(idProduct);
    }

    @Step("Click Add Product Button")
    public void clickAddProductButton() {
        WebElement addProductButton = driver.findElement(addProductButtonLocator);
        addProductButton.click();
    }

    @Step("Get Product by index in the table")
    public Product getProductByIndex(int index) {

        String name = getNameProduct(index);
        String price = getPriceProduct(index);
        String quality = getQualityProduct(index);
        String sale = getSaleProduct(index);
        String manufacture = getMunafacturesProduct(index);
        return new Product(name, price, quality, sale, manufacture);
    }
}
