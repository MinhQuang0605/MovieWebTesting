package utils;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTestForMultipleUser {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters({"username", "password"})
    public void setup(String username, String password, ITestResult result) throws Exception {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.set(webDriver);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.Login(username, password);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}
