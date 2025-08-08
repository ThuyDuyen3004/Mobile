package Test;

import Common.BaseTest;
import Common.TestListener;
import Models.Product;
import com.github.javafaker.Faker;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Constants;

@Listeners(TestListener.class)
public class TC_08 extends BaseTest {

    Faker faker = new Faker();


    String imagePath = Constants.IMAGE_PATH_PNG;
    String nameProduct = faker.lorem().characters(7);
    String price = String.valueOf(faker.number().numberBetween(10000, 99999));
    String quality = String.valueOf(faker.number().numberBetween(100, 999));
    String sale = String.valueOf(faker.number().numberBetween(10, 99));
    String manufacture = "Apple";
    String specification = faker.gameOfThrones().character();

    @Test(description = "TC_08 - Add Product Successfully")
    public void testAddProductSuccessfully() throws InterruptedException {
        // Step 1: Truy cập trang chính
        driver.get(Constants.URL);
        homePage.OpenLoginForm();

        // Step 2: Đăng nhập
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3: Vào trang Admin -> Product
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");

        // Step 4: Nhấn nút thêm sản phẩm
        viewProductPage.clickAddProductButton();

        // Step 5 ~ 11: Tạo sản phẩm và thêm vào
        product = new Product(nameProduct, price, quality, sale, manufacture, specification, imagePath);
        addProductsPage.addProduct(product);

        // Step 12: Kiểm tra thông báo thành công
        softAssert.assertEquals(
                addProductsPage.getAlertMessageSuccess(),
                "Cập nhật thành công",
                "Không tìm thấy thông báo thành công"
        );

        // Step 13: Kiểm tra sản phẩm mới có trong danh sách
        categories.openProductPage();
        Product productAfterAdd = viewProductPage.getProductByIndex(1);

        softAssert.assertEquals(
                productAfterAdd,
                product,
                "Sản phẩm mới được thêm vào list Product không thành công"
        );

        softAssert.assertAll();
    }
}
