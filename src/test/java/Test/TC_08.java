package Test;

import Common.BaseTest;
import Models.Product;
import org.testng.annotations.Test;
import utils.Constants;

public class TC_08 extends BaseTest {

    @Test(description = "TC_08 - Add Product Successfully")
    public void testAddProductSuccessfully() throws InterruptedException {
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.goToCategoryByName("Products");


        // Step 4
        viewProductPage.clickAddProductButton();
        // Step 5 ~ Step 11
        String imagePath = Constants.IMAGE_PATH_PNG;
        String nameProduct = faker.lorem().characters(7);
        String price = String.valueOf(faker.number().numberBetween(10000, 99999));
        String quality = String.valueOf(faker.number().numberBetween(100, 999));
        String sale = String.valueOf(faker.number().numberBetween(10, 99));
        String manufacture = "Apple";
        String specification = faker.gameOfThrones().character();

        product = new Product(nameProduct,
                price, quality, sale, manufacture,
                specification, imagePath);

        addProductsPage.addProduct(product);

        // Step 12
        softAssert.assertEquals(addProductsPage.getAlertMessageSuccess(),
                "Cập nhật thành công", "Không tìm thấy thông báo thành công"
        );


        categories.openProductPage();
        Product productAfterAdd = viewProductPage.getProductByIndex(0);

        softAssert.assertEquals(productAfterAdd, product,
                "Sản phẩm mới được thêm vào list Product không thành công");

        softAssert.assertAll();
    }
}
