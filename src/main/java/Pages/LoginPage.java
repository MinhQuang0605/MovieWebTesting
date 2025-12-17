package Pages;

import Utils.ConfigReader;
import Utils.ScreenShotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private By usernameField = By.xpath("//input[@id='taiKhoan']");
    private By passwordField= By.id("matKhau");
    //private By usernameField = By.name("taiKhoan");
   // private By usernameField = By.id("taiKhoan");
    private ScreenShotHelper screenShotHelper;

    //    By usernameFIeld = By.xpath("//input[@placeholder='Username']");
//    By passwordField = By.xpath("//input[@placeholder='Password']");

    private By loginButton = By.xpath("//button[@type='submit']");

    private By errorMessage = By.xpath("//div[@role='alert']");
//p[contains(@class,'MuiFormHelperText-root') and contains(@class,'Mui-error')]
//p[contains(@id,'matKhau') and contains(@class,'Mui-error')]
//div[@role='alert']
    private By loginPath = By.xpath("//a[@href='/sign-in']");
    private String loginUrl= ConfigReader.getLoginURL();

    public LoginPage(WebDriver driver){
        this.driver =driver;
        this.js = (JavascriptExecutor) driver;
        this.screenShotHelper= new ScreenShotHelper(driver,"LoginPage");
    }

    public void openLoginPage(){
        driver.get(loginUrl);
    }

    public void enterUserName(String username) throws Exception {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        screenShotHelper.CaptureHighLight(usernameElement, "Enter UserName");
    }

    public void enterPassWord(String password) throws Exception {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        screenShotHelper.CaptureHighLight(passwordElement, "Enter Password");
    }

    public void clickLoginButton() throws Exception{
        WebElement button = driver.findElement(loginButton);
        screenShotHelper.CaptureHighLight(button, "Click Login Button");
        button.click();
    }
    //Mo trang login bang trang chu
    public void GotoLoginPage(){
        WebElement login = driver.findElement(loginPath);
        login.click();
    }

    public boolean  isErrorDisplayed() throws Exception{
        WebElement error = driver.findElement(errorMessage);
        return error.isDisplayed();
    }
    public void Login(String username,String password) throws Exception {
        openLoginPage();
        //  GotoLoginPage();
        enterUserName(username);
        enterPassWord(password);
        clickLoginButton();
        Thread.sleep(2000);
        screenShotHelper.ExtraCapture("After Login");
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
