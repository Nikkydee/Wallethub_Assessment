package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHLoginPage extends PageBase {
    public WHLoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@name='em']")
    WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//span[text()='Login']/..")
    WebElement loginButton;

    public void enterEmail(String email) {
        enterText(emailField, email);
    }
    public void enterPassword(String password) {
        enterText(passwordField, password);
    }
    public void clickLoginButton() {
        click(loginButton);
    }
}
