package Pages.WHPageObjects;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WHReviewPage extends PageBase {
    public WHReviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "wrev-drp")
    WebElement dropDown;
    @FindBy(xpath = "//li[text()='Health Insurance']")
    WebElement healthInsuranceOption;
    @FindBy(xpath = "//textarea[@placeholder='Write your review...']")
    WebElement textField;
    @FindBy(xpath = "//div[@class='sub-nav-ct']/*[1]")
    WebElement submitButton;

    public void clickDropDown() {
        click(dropDown);
    }
    public void selectHealthInsurance() {
        click(healthInsuranceOption);
    }
    public void clickTextArea() {
        click(textField);
    }
    public void enterReview(String review) {
        enterText(textField, review);
    }
    public void clickSubmitButton() {
        click(submitButton);
    }
}
