package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHHomePage extends PageBase {
    public WHHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='login-join']/span")
    WebElement loginButton;

    public void clickLoginButton() {
        int attempts = 0;
        while (attempts < 4) {
            try {
                click(loginButton);
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        }
    }
    public WebElement loginButton() {
        return loginButton;
    }
}
