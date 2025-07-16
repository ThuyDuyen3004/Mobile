package Test;

import Common.BaseTest;
import Models.Product;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

public class AddProductNegativeTests extends BaseTest {

    @Test(description = "Add product failed")
    public void addProductFailed() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // Step 1
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        // Step 2
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);

        // Step 3
        driver.get(Constants.URL_ADMIN);
        categories.openProductPage();
        viewProductPage.clickAddProductButton();


        // TC 09 - Add Product with Empty Fields
        addProductsPage.clickSaveButton();
        softAssert.assertEquals(addProductsPage.getTextByLabel("Name"),
                "Vui lòng nhập Họ và tên", "Missing name should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Price"),
                "Vui lòng nhập giá sản phẩm", "Missing price should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Quality"),
                "Vui lòng nhập số lượng sản phẩm", "Missing quality should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Sale"),
                "Vui lòng nhập giảm giá sản phẩm", "Missing sales should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Manufactures"),
                "Vui lòng chọn hãng sản xuất", "Missing Manufactures should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Image"),
                "Vui lòng chọn ảnh", "Missing image should show error");

        softAssert.assertEquals(addProductsPage.getTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Empty Fields");

        // Data for the next test
        String imagePath = Constants.IMAGE_PATH_PNG;
        String imagePathPDF = Constants.IMAGE_PATH_PDF;
        String nameProduct = faker.lorem().characters(7);

        String priceInvalid = "abc"; // Invalid price
        String price = String.valueOf(faker.number().numberBetween(10000, 99999));

        String quality = String.valueOf(faker.number().numberBetween(100, 999));
        String qualityNegative = String.valueOf((faker.number().numberBetween(-999, -100))); // Negative quality


        String sale = String.valueOf(faker.number().numberBetween(10, 99));
        String manufacture = "Apple";

        String specification = faker.gameOfThrones().character();
        String specificationEmpty = ""; // Empty specification

        //TC 10 - Add Product with Invalid Price
        product = new Product(nameProduct,
                priceInvalid, quality, sale, manufacture,
                specification, imagePath);
        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getTextByLabel("Price"),
                "Giá phải là số và bé hơn 1 tỉ", "Invalid price should show error");

        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Invalid Price");

        // TC 11 - Add Product with Negative Quantity
        product.setPrice(price);
        product.setQuality(qualityNegative);


        addProductsPage.addProduct(product);

        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Negative Quantity");

        // Close the alert message
        addProductsPage.closeAlertMessageSuccess();

        // TC 12 - Add Product with Invalid Image Type
        product.setImagePath(imagePathPDF);


        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getTextByLabel("Image"),
                "Ảnh ko đúng định dạng", "Invalid image type should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Invalid Image Type");

        // TC 13 - Add Product with Empty Specification
        product.setSpecification(specificationEmpty);


        addProductsPage.addProduct(product);
        softAssert.assertEquals(addProductsPage.getTextByLabel("Specification"),
                "Vui lòng nhập nội dung", "Missing specification should show error");


        softAssert.assertEquals(addProductsPage.getAlertMessageError(), "ko thành cong",
                "Missing required fields should show error with Empty Specification");

        softAssert.assertAll();
    }


}
