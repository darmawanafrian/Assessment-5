package com.automation;

import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginSignupPage;
import com.automation.pages.ProductsPage;
import com.automation.utils.JSONReader;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class TestCase20 extends BaseTest{

    @Test(description = "Test Case 20: Search Products and Verify Cart After Login")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Products and Verify Cart After Login")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify user is navigated to ALL PRODUCTS page successfully
            5. Enter product name in search input and click search button
            6. Verify 'SEARCHED PRODUCTS' is visible
            7. Verify all the products related to search are visible
            8. Add those products to cart
            9. Click 'Cart' button and verify that products are visible in cart
            10. Click 'Signup / Login' button and submit login details
            11. Again, go to Cart page
            12. Verify that those products are visible in cart after login as well""")
    public void searchProductsAndVerifyCartAfterLogin() throws IOException, ParseException {
            TestCase8.verifyUserIsNavigatedToAllProductsPageSuccessfully();
            verifySearchedProductsIsVisible();
            verifyAllTheProductsRelatedToSearchAreVisible();
            new ProductsPage(getDriver()).addAllProducts();
            verifyThatProductsAreVisibleInCart();
            loginWithCorrectEmailAndPssword();
            verifyThatProductsAreVisibleInCart();
    }
    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public static void verifySearchedProductsIsVisible() {
        String searchedProductsText = new ProductsPage(getDriver())
                .fillSearchProductInput("blue")
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(searchedProductsText, "SEARCHED PRODUCTS", "Verify 'SEARCHED PRODUCTS' is visible");
    }
    @Step("Verify all the products related to search are visible")
    public static List<String> verifyAllTheProductsRelatedToSearchAreVisible() {
        List<String> productsNames = new ProductsPage(getDriver()).getProductsSearchNames();

        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertTrue(productsNames.get(i).toLowerCase().contains("blue"));
            System.out.println(i + ". " + productsNames.get(i) + " - contain: " + "blue");
        }
        return productsNames;
    }
    @Step("Click 'Cart' button and verify that products are visible in cart")
    private void verifyThatProductsAreVisibleInCart() {
        new HomePage(getDriver()).cartButtonClick();
        List<String> prices = new CartPage(getDriver()).getPrices();
        List<String> quantity = new CartPage(getDriver()).getQuantity();
        List<String> totalPrices = new CartPage(getDriver()).getTotalPrices();

        for (int i = 0; i < 7; i++) {
            Assert.assertEquals(totalPrices.get(i), prices.get(i), "Verify their prices and total price");
            Assert.assertEquals(quantity.get(i), "1", "Verify their quantity");
            System.out.println(i + ". Prices = Total Prices | " + prices.get(i) + " = " + totalPrices.get(i));
            System.out.println(i + ". Quantity = 1 | " + quantity.get(i).equals("1"));
        }
    }
    @Step("Click 'Signup / Login' button and submit login details")
    public void loginWithCorrectEmailAndPssword() throws IOException, ParseException {
        TestCase2.verifyLoginToYourAccountIsVisible();
        new LoginSignupPage(getDriver())
                .fillCorrectLogin(JSONReader.existingUser("email"),
                        JSONReader.existingUser("password"));
    }
}
