package Pages.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By DangNhapButton = By.xpath("//div[@class='top-header']//a[@data-target='#login']");
    private By PhoneLocator = By.xpath("//nav[@class='navbar navbar-bg']//span[contains(@class, 'fa-mobile')]");
    private By Dropdown = By.xpath("//div[@class='btn-group pull-right']//button[@type='button']");
    private By ItemPrice = By.xpath("((//div[@class='col-md-8'])/following-sibling::div)/descendant::p[@class='text-danger']");
    private By lowtohighPriceLocator = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Giá từ thấp đến cao')]");
    private By HighToPricePriceLocator = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Giá từ cao đến thấp')]");
    @Step("Open the login form")
    public void OpenLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(DangNhapButton));
        loginBtn.click();
    }
    @Step("Get all enabled 'Add to Cart' buttons")
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

    @Step("Click a random 'Add to Cart' button")
    public void clickButtonAddToCartRandom() {
        List<WebElement> allButtons = getAllAddToCartButtons();
        System.out.println(allButtons.size());
        WebElement randomButton = allButtons.get(new Random().nextInt(allButtons.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
    }
    @Step("Click on 'Phone' category")
    public void clickPhoneCategory() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(PhoneLocator));
        button.click();
    }
    @Step("Click on the 'See More' dropdown")
    public void clickXemthem() {
        driver.findElement(Dropdown).click();
    }

    @Step("Select 'Price Low to High' sorting option")
    public void selectGiaThapDenCao() {
        WebElement giaThapDenCao = wait.until(ExpectedConditions.elementToBeClickable(lowtohighPriceLocator));
        giaThapDenCao.click();
    }
    @Step("Verify that items are sorted by price: low to high")

    public boolean isSortedByGiaThapDenCao() {
        List<WebElement> priceElements = driver.findElements(ItemPrice);
        List<Double> actualPrices = new ArrayList<>();

        for (int i = 0; i < priceElements.size(); i++) {
            String text = priceElements.get(i).getText()
                    .replace("Giá: ", "")
                    .replace("đ", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();
            double price = Double.parseDouble(text);
            actualPrices.add(price);
            if (i > 0 && actualPrices.get(i - 1) > price) {
                return false;
            }
        }
        return true;
    }

    @Step("Select 'Price High to Low' sorting option")
    public void selectGiaCaoDenThap() {

        WebElement giaThapDenCao = wait.until(ExpectedConditions.elementToBeClickable(HighToPricePriceLocator));
        giaThapDenCao.click();
    }
    @Step("Verify that items are sorted by price: high to low")
    public boolean isSortedByGiaCaoDenThap() {
        List<WebElement> priceElements = driver.findElements(ItemPrice);
        List<Double> actualPrices = new ArrayList<>();
        for (int i = 0; i < priceElements.size(); i++) {
            String text = priceElements.get(i).getText()
                    .replace("Giá: ", "")
                    .replace("đ", "")
                    .replace(".", "")
                    .replace(",", "")
                    .trim();
            double price = Double.parseDouble(text);
            actualPrices.add(price);

            if (i > 0 && actualPrices.get(i - 1) < price) {
                return false;
            }
        }
        return true;
    }

    //
    @Step("Add 3 random products to cart")
    public void add3RandomProductsToCart() throws InterruptedException {
        List<WebElement> allButtons = getAllAddToCartButtons();

        for (int i = 0; i < 3; i++) {
            WebElement button = allButtons.get(i);

            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();

            CartPage cartPage = new CartPage(driver);
            cartPage.closeCartForm();
        }
    }


}
