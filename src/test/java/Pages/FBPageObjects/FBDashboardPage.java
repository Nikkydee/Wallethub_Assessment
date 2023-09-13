package Pages.FBPageObjects;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FBDashboardPage extends PageBase {

    public FBDashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@class='x9e5oc1'] ")
    WebElement menuIcon;

    @FindBy(xpath = "//span[ (text()='Post')]/../../../../../../.")
    WebElement postMenu;
    public void clickMenuIcon(){
      click(menuIcon);
    }

    public void clickPostMenu(){
        click(postMenu);
    }
}
