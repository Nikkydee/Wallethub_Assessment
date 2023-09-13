package Pages.WHPageObjects;

import BaseClasses.PageBase;
import Tests.WalletHubReview;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WHDashboard extends PageBase {
    public WHDashboard(WebDriver driver) {
        super(driver);
    }
    public static Logger log = LogManager.getLogger(WalletHubReview .class);

    @FindBy(xpath = "(//div[@class='left-content']/a)[2]")
    WebElement reviewTab;
    @FindBy(xpath = "(//review-star[@class='rvs-svg']/div)/*")
    List<WebElement> ratingStars;
    @FindBy(xpath = "//section[@class='rvtab-content']/article[1]/*[7]")
    WebElement reviewContent;

    public WebElement getReviewTab() {
        return reviewTab;
    }

    public void clickReviewTab() {
        int attempts = 0;
        while (attempts < 4) {
            try {
                click(reviewTab);
                break;
            } catch (StaleElementReferenceException e) {
                log.error("Stale element reference exception has occurred while clicking review tab but driver will attempt " + (2 - attempts) + " more times");
            }
            attempts++;
        }
    }

    public List<WebElement> ratingStars() {
        return ratingStars;
    }
    public void clickFourthStar() {
        click(ratingStars.get(3));
    }
    public WebElement getReviewContent() {
        return reviewContent;
    }
}
