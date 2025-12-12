package utils;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest extentTest;

//    @BeforeSuite
//    public void setUpSuite(){
//        ExtendReportManager.getInstance();
//    }

    @AfterSuite
    public void tearDownSuite(){
        //ExtendReportManager.flushreport();
    }
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options= new ChromeOptions();

        options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void TearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
