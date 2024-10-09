package com.automation;

import com.automation.pages.CartPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase17 extends BaseTest {

    @Test(description = "Test Case 17: Remove Products From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove Products From Cart")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click 'X' button corresponding to particular product
            8. Verify that product is removed from the cart""")
    public void removeProductFromCrt() {
            TestCase1.verifyThatHomePageIsVisibleSuccessfully();
            TestCase14.verifyThatCartPageIsDisplayed();
            verifyThatProductIsRemovedFromTheCart();
    }
    @Step("Verify that product is removed from the cart")
    public void verifyThatProductIsRemovedFromTheCart(){
        String shoppingCartText = new CartPage(getDriver())
                .xButtonClick()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(shoppingCartText, "Cart is empty! Click here to buy products.", "Verify that product is removed from the cart");
    }

}
