//package Pages.user;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class CartPage {
//    private WebDriver driver;
//    protected WebDriverWait wait;
//    public CartPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//    private By DatHangNgayButtonLocator = By.id("order_product");
//    private By iconClose=By.xpath("//div[@id='cart_modal']//button[@type='button' and normalize-space()='×']");
//    public void clickOrderButton() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(DatHangNgayButtonLocator));
//        driver.findElement(DatHangNgayButtonLocator).click();
//    }
//    public void closeCartForm() {
//        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(iconClose));
//        closeButton.click();
//    }
//
//    public By getDiscountByRow(int s) {
//        return By.xpath("//table//tbody//tr[" + s + "]/td[3]");
//    }
//
//    public By getUnitPriceByRow(int s) {
//        return By.xpath("//table//tbody//tr[" + s + "]/td[5]");
//    }
//
//    public By getSubtotalByRow(int s) {
//        return By.xpath("//table//tbody//tr[" + s + "]/td[6]");
//    }
//
//    public int getQuantityAsIntByRow(int s) {
//        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//table//tbody//tr[" + s + "]/td[4]//select")));
//        Select select = new Select(selectElement);
//        String selectedText = select.getFirstSelectedOption().getText().trim();
//        return Integer.parseInt(selectedText);
//    }
//
//    // Lấy đơn giá
//    public double getUnitPriceByRowAsDouble(int s) {
//        String text = driver.findElement(getUnitPriceByRow(s)).getText().trim();
//        return parseCurrencyToDouble(text);
//    }
//
//    // Lấy thành tiền
//    public double getSubtotalByRowAsDouble(int s) {
//        String text = driver.findElement(getSubtotalByRow(s)).getText().trim();
//        return parseCurrencyToDouble(text);
//    }
//
//    // Lấy giảm giá
//    public double getDiscountAsDouble(int s) {
//        String text = driver.findElement(getDiscountByRow(s)).getText().trim();
//        if (text.isEmpty()) {
//            return 0.0;
//        }
//        return parsePercentToDouble(text);
//    }
//
//    // Tính expected subtotal
//    public double calculateExpectedSubtotal(int s) {
//        int quantity = getQuantityAsIntByRow(s);
//        double unitPrice = getUnitPriceByRowAsDouble(s);
//        double discount = getDiscountAsDouble(s);
//
//        return ((100 - discount) / 100.0) * quantity * unitPrice;
//    }
//
//    // Kiểm tra expected subtotal với subtotal thực tế
//    public boolean isSubtotalCorrect(int s) {
//        int quantity = getQuantityAsIntByRow(s);
//        double unitPrice = getUnitPriceByRowAsDouble(s);
//        double discount = getDiscountAsDouble(s);
//        double expected = ((100 - discount) / 100.0) * quantity * unitPrice;
//        double actual = getSubtotalByRowAsDouble(s);
//
//        // In ra chi tiết
////        System.out.println(" Dòng " + s + ": Số lượng = " + quantity + ", Đơn giá = " + unitPrice +
////                ", Giảm giá = " + discount + "%");
////        System.out.printf("Thành tiền mong đợi: ((100 - %.1f)/100) * %d * %.0f = %.2f\n",
////                discount, quantity, unitPrice, expected);
////        System.out.printf("Thành tiền thực tế: %.2f\n\n", actual);
////
//       return expected == actual;
//    }
//
//    private double parseCurrencyToDouble(String text) {
//        return Double.parseDouble(text.replace("đ", "").replace(".", "").replace(",", "").trim());
//    }
//
//
//    private double parsePercentToDouble(String text) {
//        return Double.parseDouble(text.replace("%", "").trim());
//    }
//
//
//}
//
//
package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    private static final List<String> COLUMN_NAMES = List.of(
            "Hình ảnh", "Tên sản phẩm", "Giảm giá", "Số lượng", "Đơn giá", "Thành tiền"
    );

    private final By DatHangNgayButtonLocator = By.id("order_product");
    private final By iconClose = By.xpath("//div[@id='cart_modal']//button[@type='button' and normalize-space()='×']");

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void clickOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DatHangNgayButtonLocator));
        button.click();
    }

    public void closeCartForm() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(iconClose));
        closeButton.click();
    }

    public String getProductNameByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        return row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Tên sản phẩm")).getText().trim();
    }

    public String getDiscountByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        return row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Giảm giá")).getText().trim();
    }

    public double getUnitPriceByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        String text = row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Đơn giá")).getText().trim();
        return parseCurrencyToDouble(text);
    }

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

    public double getSubtotalByIndex(int index) {
        WebElement row = getRowElementByIndex(index);
        String text = row.findElements(By.tagName("td"))
                .get(COLUMN_NAMES.indexOf("Thành tiền")).getText().trim();
        return parseCurrencyToDouble(text);
    }


    public double getDiscountAsDoubleByIndex(int index) {
        String text = getDiscountByIndex(index).trim();
        return parsePercentToDouble(text);
    }


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

        return expected==actual;
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
    By TotalPrice =By.xpath("(//label[contains(@class, 'toal_money')])/span");
    public double getTotalPrice() {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalPrice));
        String totalText = totalElement.getText().replace("đ", "").replace(".", "").replace(",", "").trim();
        return Double.parseDouble(totalText);
    }
    public double calculateTotalSubtotal() {
        List<WebElement> rows = webDriver.findElements(By.xpath("//table/tbody/tr"));
        double total = 0.0;
        for (int i = 1; i <= rows.size()-1; i++) {
            total += getSubtotalByIndex(i);
        }
        return total;
    }
    public boolean isTotalPriceCorrect() {
        double expectedTotal = calculateTotalSubtotal();
        double actualTotal = getTotalPrice();
        return expectedTotal==actualTotal;
    }

}
