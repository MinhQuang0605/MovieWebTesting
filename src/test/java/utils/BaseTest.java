package utils;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
  //  protected WebDriver driver2;
    protected ExtentTest extentTest;
    protected ScreenshotCleaner screenshotCleaner;
//    @BeforeSuite
//    public void setUpSuite(){
//        ExtendReportManager.getInstance();
//    }
    @BeforeSuite
    public void setUpSuite(){

        ExtendReportManager.getInstance();
}
    @AfterSuite
    public void tearDownSuite(){
        ScreenshotCleaner.deleteEmptyScreenShotFolder();
        ExtendReportManager.flushReport();
    }
    @BeforeMethod
    public void setup(ITestResult result) {
        //Tao test case trong report
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        if(testDescription == null || testDescription.isEmpty()){
            testDescription = "test case " + testName;
        }
        extentTest = ExtendReportManager.createTest(testName, testDescription);

//        B1: cấu hình ChromeDriver
        extentTest.info("Cau hinh chrome driver");
        WebDriverManager.chromedriver().setup();

//        B2: cấu hình các tùy chọn
        extentTest.info("tang kich co man hinh toi da");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

//        B3: khởi tạo ChromeDriver
        extentTest.info("Khoi tao chrome driver");
        driver = new ChromeDriver(options);


//        B4: Doi khoang 10s
        extentTest.info("doi khoan 5s");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterMethod
    public void TearDown(){
        if(driver != null){
            driver.quit();
        }

    }
}
