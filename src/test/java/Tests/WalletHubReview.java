package Tests;

import BaseClasses.TestBase;
import Pages.WHPageObjects.WHDashboard;
import Pages.WHPageObjects.WHLoginPage;
import Pages.WHPageObjects.WHReviewPage;;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WalletHubReview extends TestBase {
    Actions actions;
    WHLoginPage loginPage;
    WHDashboard dashboard;
    WHReviewPage reviewPage;
    public void initializer() {
        actions = new Actions(driver);
        loginPage = new WHLoginPage(driver);
        dashboard = new WHDashboard(driver);
        reviewPage = new WHReviewPage(driver);
    }

    @Test(priority = 1, description = "To tests that user can navigate to the review page")
    public void navigateToReviewPage() {
        initializer(); // Instantiate all page objects used in this class
        log.info("Launching Wallet Hub URL at "+ testData.getProperty("walletHubURL"));
        driver.get(testData.getProperty("walletHubURL"));
        waitForRefreshedVisibility(dashboard.getReviewTab()); // Wait for the review page to be refreshed
        dashboard.clickReviewTab();
    }

    @Test(priority = 2, description = "Can hover on the rating stars")
    public void hoverOnRatingStars() {
        log.info("Hovering on rating stars");
        for (int i = 0; i < dashboard.ratingStars().size(); i++) { // This block hovers on all the rating stars
            actions.moveToElement(dashboard.ratingStars().get(i));
        }
        int attempts = 0;
        while(attempts < 4) {
            try {
                actions.build().perform();
            }catch(StaleElementReferenceException e) {
                log.error("Stale element reference exception has occurred while hovering on rating stars but driver will attempt " + (2 - attempts) + " more times");
            }
            attempts++;
        }
        log.info("Clicking on the fourth start");
        dashboard.clickFourthStar(); // Clicks the fourth review star
    }
    @Test(priority = 3, description = "Select health insurance dropdown on review page")
    public void selectHealthInsurance() {
        reviewPage.clickDropDown();
        log.info("Selecting Health Insurance option");
        reviewPage.selectHealthInsurance();
    }
    @Test(priority = 4, description = "Entering and submit review")
    public void enterReview() {
        reviewPage.clickTextArea();
        log.info("Entering review");
        reviewPage.enterReview(testData.getProperty("review"));
        reviewPage.clickSubmitButton();
    }
    @Test(priority = 5, description = "Verify that user can see the posted review on the profile page")
    public void verifyUserPostReview() {
        loginPage.clickLoginTab();
        loginPage.enterEmail(testData.getProperty("WHEmail"));
        loginPage.enterPassword(testData.getProperty("WHPassword"));
        loginPage.clickLoginButton();
        waitForRefreshedVisibility(dashboard.getReviewContent());
        log.info("Asserting review on the profile page");
        Assert.assertEquals(dashboard.getReviewContent().getText(), testData.getProperty("review"));

    }
}
