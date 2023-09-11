package Assignment2;

import BaseClasses.TestBase;
import Pages.WHDashboard;
import Pages.WHLoginPage;
import Pages.WHHomePage;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class WalletHubReview extends TestBase {

    Actions actions;
    WHHomePage homePage;
    WHLoginPage loginPage;
    WHDashboard dashboard;
    @Test
    public void verifyUserCanLaunchURL() {
//         actions = new Actions(driver);
         homePage = new WHHomePage(driver);
         loginPage = new WHLoginPage(driver);
         dashboard = new WHDashboard(driver);

         driver.get(testData.getProperty("walletHubURL"));
         waitForRefreshedVisibility(homePage.loginButton());
         homePage.clickLoginButton();
         loginPage.enterEmail(testData.getProperty("WHEmail"));
         loginPage.enterPassword(testData.getProperty("WHPassword"));
         loginPage.clickLoginButton();
         dashboard.clickReviewTab();
         sleep(10);
    }
}
