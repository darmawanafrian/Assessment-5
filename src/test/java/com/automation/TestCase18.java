package com.automation;

import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestCase18 extends BaseTest {
    @Test(description = "Test Case 18: View Category Products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View Category Products")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that categories are visible on left side bar
            4. Click on 'Women' category
            5. Click on any category link under 'Women' category, for example: Dress
            6. Verify that category page is displayed and confirm text 'WOMEN - Dress PRODUCTS'
            7. On left side bar, click on any sub-category link of 'Men' category
            8. Verify that user is navigated to that category page""")
    public void viewCategoryProducts() {
            verifyThatCategoriesAreVisibleOnLeftSideBar();
            verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts();
            verifyThatUserIsNavigatedToThatCategoryPage();
    }

    public void verifyThatCategoriesAreVisibleOnLeftSideBar(){
        boolean categoryIsVisible = new HomePage(getDriver())
                .getCategories()
                .isDisplayed();
        Assert.assertTrue(categoryIsVisible,"Verify that categories are visible on left side bar");
    }

    @Step("Verify that category page is displayed and confirm text 'WOMEN - Dress PRODUCTS'")
    public void verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts(){
       boolean categoryText = new HomePage(getDriver())
               .womenCategoryClick()
               .dressCategoryClick()
               .getTitleTextCenter()
               .isDisplayed();
        Assert.assertTrue(categoryText,"Verify that category page is displayed and confirm text 'WOMEN - Dress PRODUCTS'");
    }
    @Step("Verify that user is navigated to that category page")
    public void verifyThatUserIsNavigatedToThatCategoryPage(){
        String categoryText = new ProductsPage(getDriver())
                .menCategoryClick()
                .tShirtsCategoryClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(categoryText,"MEN -  TSHIRTS PRODUCTS","Verify that user is navigated to that category page");
    }
}
