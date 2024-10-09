package com.automation;

import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase19 extends BaseTest{
    @Test(description = "Test Case 19: View & Cart Brand Products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View & Cart Brand Products")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify that Brands are visible on left side bar
            5. Click on any brand name
            6. Verify that user is navigated to brand page and brand products are displayed
            7. On left side bar, click on any other brand link
            8. Verify that user is navigated to that brand page and can see products""")
    public void verifyViewAndCartBrandProducts(){
        new HomePage(getDriver()).productsButtonClick();
        verifyThatBrandAreVisibleOnLeftSideBar();
        verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed();
        VerifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts();
    }

    @Step("Verify that Brands are visible on left side bar")
    public void verifyThatBrandAreVisibleOnLeftSideBar(){
        boolean brandsIsVisible = new ProductsPage(getDriver())
                .getBrands()
                .isDisplayed();
        Assert.assertTrue(brandsIsVisible,"Verify that Brands are visible on left side bar");
    }

    @Step("Verify that user is navigated to brand page and brand products are displayed")
    public void verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed(){
        String brandText = new ProductsPage(getDriver())
                .poloBrandClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(brandText,"BRAND -  POLO PRODUCTS",
                "Verify that user is navigated to brand page and brand products are displayed");
    }

    @Step("Verify that user is navigated to that brand page and can see products")
    public void VerifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts(){
        String brandText = new ProductsPage(getDriver())
                .madameBrandClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(brandText,"BRAND - MADAME PRODUCTS",
                "Verify that user is navigated to that brand page and can see products");
    }
}
