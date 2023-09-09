package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FBCreatePostPage extends PageBase {

    public  FBCreatePostPage(WebDriver driver){
        super((driver));
    }

    @FindBy(xpath= "//div[(@role='textbox')]")
    WebElement messageField;

    @FindBy(xpath = "//span[ (text()='Post')]/../../../..")
    WebElement PostButton;

    public void enterMessagePost(String text){
        enterText(messageField, text);
    }

    public void clickPostButton(){
        click(PostButton);
    }

}
