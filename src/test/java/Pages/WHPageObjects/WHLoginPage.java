package Pages.WHPageObjects;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHLoginPage extends PageBase {
    public WHLoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[text()='Login']/..")
    WebElement loginTab;
    @FindBy(id = "em-ipt")
    WebElement emailField;
    @FindBy(id = "pw1-ipt")
    WebElement passwordField;
    @FindBy(xpath = "//span[text()='Login']/../..")
    WebElement loginButton;

    public void clickLoginTab() {
        click(loginTab);
    }
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
