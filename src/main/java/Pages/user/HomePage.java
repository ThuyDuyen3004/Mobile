package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    private By DangNhapButton = By.xpath("//div[@class='top-header']//a[@data-target='#login']");
    private By PhoneLocator=By.xpath("//nav[@class='navbar navbar-bg']//span[contains(@class, 'fa-mobile')]");
    private By Dropdown=By.xpath("//div[@class='btn-group pull-right']//button[@type='button']");
    private By ItemPrice = By.xpath("((//div[@class='col-md-8'])/following-sibling::div)/descendant::p[@class='text-danger']");

    public void OpenLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(DangNhapButton));
        loginBtn.click();
    }
    public List<WebElement> getAllAddToCartButtons() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'cart_class')]")));

        List<WebElement> allButtons = driver.findElements(By.xpath("//button[contains(@class,'cart_class')]"));

        List<WebElement> enabledButtons = new ArrayList<>();
        for (WebElement btn : allButtons) {
            if (btn.isEnabled()) {
                enabledButtons.add(btn);
            }
        }
        return enabledButtons;
    }
    //

//    public double GetPrice() {
//        String text = driver.findElement(ItemPrice).getText();
//        text = text.replace("Giá: ", "").replace("đ", "").replace(".", "").replace(",", "").trim();
//        return Double.parseDouble(text);
//    }

    public void clickPhoneCategory() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(PhoneLocator));
        button.click();
    }

    public void clickXemthem()
    {
        driver.findElement(Dropdown).click();
    }
    private By lowtohighPriceLocator= By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Giá từ thấp đến cao')]");
    private By HighToPricePriceLocator= By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Giá từ cao đến thấp')]");
    public void selectGiaThapDenCao() {
        WebElement giaThapDenCao = wait.until(ExpectedConditions.elementToBeClickable(lowtohighPriceLocator));
        giaThapDenCao.click();
    }

    public boolean isSortedByGiaThapDenCao() {
        List<WebElement> priceElements = driver.findElements(ItemPrice);
        List<Double> actualPrices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String text = element.getText()
                    .replace("Giá: ", "")
                    .replace("đ", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();
            actualPrices.add(Double.parseDouble(text));
        }

        for (int i = 0; i < actualPrices.size() - 1; i++) {
            if (actualPrices.get(i) > actualPrices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public void selectGiaCaoDenThap() {

        WebElement giaThapDenCao = wait.until(ExpectedConditions.elementToBeClickable(HighToPricePriceLocator));
        giaThapDenCao.click();
    }

    public boolean isSortedByGiaCaoDenThap() {
        List<WebElement> priceElements = driver.findElements(ItemPrice);
        List<Double> actualPrices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String text = element.getText()
                    .replace("Giá: ", "")
                    .replace("đ", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();
            actualPrices.add(Double.parseDouble(text));
        }

        for (int i = 0; i < actualPrices.size() - 1; i++) {
            if (actualPrices.get(i) < actualPrices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

//
    public void add3RandomProductsToCart() throws InterruptedException {
        List<WebElement> allButtons = getAllAddToCartButtons();

        for (int i = 0; i < 3; i++) {
            WebElement button = allButtons.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            CartPage cartPage = new CartPage(driver);
            cartPage.closeCartForm();
        }
    }

}
