package Assignment1;

import BaseClasses.TestBase;
import Pages.FBCreatePostPage;
import Pages.FBDashboardPage;
import Pages.FBLoginPage;

import org.testng.annotations.Test;

public class Facebook extends TestBase {

    FBLoginPage loginPage;
    FBDashboardPage dashboardPage;
    FBCreatePostPage createPostPage;

    @Test(priority = 1, description = "Verify user can log in successfully with valid credentials")
    public void userLogin() {

        loginPage = new FBLoginPage(driver);
        driver.get(testData.getProperty("facebookURL"));
        loginPage.enterEmail(testData.getProperty("facebookUsername"));
        loginPage.enterPassword(testData.getProperty("facebookPassword"));
        loginPage.clickLoginButton();
        clearNotification();
    }
    @Test(priority = 2, description = "Verify user can click post menu")
    public void userClickPostMenu() {
        dashboardPage =new FBDashboardPage(driver);
        dashboardPage.clickMenuIcon();
        dashboardPage.clickPostMenu();
    }

    @Test(priority = 3, description = "Verify user can post a status message")
    public void userPostMessage(){
        createPostPage= new FBCreatePostPage(driver);
        createPostPage.enterMessagePost(testData.getProperty("message"));
        createPostPage.clickPostButton();
    }
}
