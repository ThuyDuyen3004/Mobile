
package Pages.user;

import Models.CartItem;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    private static final List<String> COLUMN_NAMES = List.of(
            "Hình ảnh", "Tên sản phẩm", "Giảm giá", "Số lượng", "Đơn giá", "Thành tiền"
    );


    private final By iconClose = By.xpath("//div[@id='cart_modal']//button[@type='button' and normalize-space()='×']");
    By OrderbuttonLocator=By.id("order_product");
    By TotalPrice = By.xpath("(//label[contains(@class, 'toal_money')])/span");
    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Step("Close the cart popup")
    public void closeCartForm() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(iconClose));
        closeButton.click();
    }

    @Step("Get product name at row {index}")
    public String getProductNameByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        return row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Tên sản phẩm")).getText().trim();
    }
    @Step("Get discount at row {index}")
    public String getDiscountByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        return row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Giảm giá")).getText().trim();
    }
    @Step("Get unit price at row {index}")
    public double getUnitPriceByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        String text = row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Đơn giá")).getText().trim();
        return parseCurrencyToDouble(text);
    }

    @Step("Get quantity at row {index}")
    public int getQuantityByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        WebElement td = row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Số lượng"));
        WebElement select = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(td, By.tagName("select")));

        Select dropdown = new Select(select);
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String quantity = selectedOption.getText().trim();
        return Integer.parseInt(quantity);
    }
    @Step("Get subtotal at row {index}")

    public double getSubtotalByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        String text = row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Thành tiền")).getText().trim();
        return parseCurrencyToDouble(text);
    }
    @Step("Get discount as double at row {index}")
    public double getDiscountAsDoubleByIndex(int index) {
        String text = getDiscountByIndex(index).trim();
        return parsePercentToDouble(text);
    }
    @Step("Verify subtotal at row {index}")
    public boolean isSubtotalCorrect(int index) {
        int quantity = getQuantityByIndex(index);
        double unitPrice = getUnitPriceByIndex(index);
        double discount = getDiscountAsDoubleByIndex(index);
        double expected = ((100.0 - discount) / 100.0) * quantity * unitPrice;
        double actual = getSubtotalByIndex(index);

        // In ra chi tiết
//        System.out.println("Dòng " + index + ": Số lượng = " + quantity + ", Đơn giá = " + unitPrice +
//                ", Giảm giá = " + discount + "%");
//        System.out.printf("Thành tiền mong đợi: ((100 - %.1f)/100) * %d * %.0f = %.2f\n",
//                discount, quantity, unitPrice, expected);
//        System.out.printf("Thành tiền thực tế: %.2f\n\n", actual);

        return expected == actual;
    }

    //    private By getRowByIndex(int index) {
//        return By.xpath(String.format("//table/tbody/tr[%d]", index));
//    }
    private WebElement getRowElementByIndex(int index) {
        By rowLocator = By.xpath(String.format("//table/tbody/tr[%d]", index));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
    }


    private double parseCurrencyToDouble(String text) {
        String cleaned = text.replace("đ", "")
                .replace(".", "")
                .replace(",", "")
                .trim();
        return Double.parseDouble(cleaned);
    }

    private double parsePercentToDouble(String text) {
        String cleaned = text.replace("%", "").trim();
        return Double.parseDouble(cleaned);
    }

    //

    @Step("Get total price displayed on the page")
    public double getTotalPrice() {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalPrice));
        String totalText = totalElement.getText().replace("đ", "").replace(".", "").replace(",", "").trim();
        return Double.parseDouble(totalText);
    }
    @Step("Calculate total of all subtotals")
    public double calculateTotalSubtotal() {
        List<WebElement> rows = webDriver.findElements(By.xpath("//table/tbody/tr"));
        double total = 0.0;
        for (int i = 1; i <= rows.size() - 1; i++) {
            total += getSubtotalByIndex(i);
        }
        return total;
    }
    @Step("Verify total price matches the sum of all subtotals")
    public boolean isTotalPriceCorrect() {
        double expectedTotal = calculateTotalSubtotal();
        double actualTotal = getTotalPrice();
        return expectedTotal == actualTotal;
    }

    @Step("Click vào nút Đặt hàng")
    public void ClickOrderButton() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(OrderbuttonLocator));
        orderButton.click();
    }
    //
    @Step("Get product name, price, and discount from Cartpage")
    public ArrayList<CartItem> getAllCartItems() {
        WaitUtils.sleep(2);

        ArrayList<CartItem> cartItemList = new ArrayList<>();

        List<WebElement> cartItems = webDriver.findElements(By.xpath("//tbody/tr"));

        for (int i = 1; i < cartItems.size(); i++) {
            String name = webDriver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText();
            String priceText = webDriver.findElement(By.xpath("//tbody/tr[" + i + "]/td[5]")).getText().replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);
            String promotion = webDriver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]")).getText();

            cartItemList.add(new CartItem(name, price, promotion));

            System.out.println("Cart Item " + i + ": " + name + ", Price: " + price + ", Promotion: " + promotion);
        }

        return cartItemList;
    }

}
