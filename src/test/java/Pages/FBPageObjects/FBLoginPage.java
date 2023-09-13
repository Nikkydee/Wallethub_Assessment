package Pages.FBPageObjects;

import BaseClasses.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FBLoginPage extends PageBase {

    public FBLoginPage(WebDriver driver) {
        super(driver);
    }

//    public FBLoginPage(WebDriver driver){
//        By usernameField= By.id("email");
//        By passwordField = By.id("pass");
//        By loginButton = By.xpath ("//button[@name='login']");
//
//    }
    @FindBy(id = "email")
    WebElement usernameField;
    @FindBy(id = "pass")
    WebElement passwordField;
    @FindBy(xpath = "//button[@name='login']")
    WebElement loginButton;
    @FindBy(xpath = "//div[@role='banner']/*[5]/*[1]/*[4]")
    public List <WebElement> menu;

    public void enterEmail(String email){
        enterText(usernameField, email);
    }
    public void enterPassword(String password){
        enterText(passwordField, password);
    }
    public void clickLoginButton(){
        click(loginButton);
    }

}
