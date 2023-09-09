package BaseClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
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

    @AfterClass
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
