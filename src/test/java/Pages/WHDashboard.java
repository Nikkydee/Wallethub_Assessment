package Pages;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHDashboard extends PageBase {
    public WHDashboard(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//div[@class='left-content']/a)[2]")
    WebElement reviewTab;

    public void clickReviewTab() {
        click(reviewTab);
    }
}
