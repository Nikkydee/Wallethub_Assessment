package BaseClasses;

import Tests.WalletHubReview;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public Properties testData;
    private static final String testDataPath = "src/test/java/utils/TestData.properties";
    private static FileInputStream fis;
    public String browser;
    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;
    private static EdgeOptions edgeOptions;
    public static WebDriver driver;
    public static Logger log;
    Robot robot;

    public TestBase() {

        testData = new Properties();
        try {
            fis = new FileInputStream(testDataPath);
            testData.load(fis);
        }catch (IOException e) {
            e.printStackTrace();
        }
        browser = testData.getProperty("browser");
    }

    @BeforeClass
    public void setup() {
        log = LogManager.getLogger(WalletHubReview.class);
        if (browser.equalsIgnoreCase("chrome")){
            chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            chromeOptions.addArguments("--disable-notifications");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            firefoxOptions = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            firefoxOptions.addArguments("--disable-notifications");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("edge")){
            edgeOptions = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            edgeOptions.setCapability("profile.default_content_settings.popups",0);
//            edgeOptions.setCapability("useAutomationExtension",false);
            edgeOptions.setCapability("disable-notifications",true);
            driver = new EdgeDriver(edgeOptions);
        }
        log.info("Starting " + browser + " browser");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    public void clearNotification() {
        try {
            robot = new Robot();
            if (browser.equalsIgnoreCase("edge")) {
                System.out.println("The browser is edge");
                Dimension i = driver.manage().window().getSize();
                int x = (i.getWidth() / 4) + 20;
                int y = (i.getHeight() / 4) + 50;
                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sleep(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForRefreshedVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    @AfterClass
    public void tearDown(){
        if (driver != null){
            log.info("Browser is closing");
//            driver.close();
            driver.quit();
        }
    }

}
