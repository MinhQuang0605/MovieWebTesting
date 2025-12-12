package Pages;

import Utils.ConfigReader;
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

    //    By usernameFIeld = By.xpath("//input[@placeholder='Username']");
//    By passwordField = By.xpath("//input[@placeholder='Password']");

    private By loginButton = By.xpath("//button[@type='submit']");

    private By errorMessage = By.xpath("//div[@role='alert']");

    private By loginPath = By.xpath("//a[@href='/sign-in']");
    private String loginUrl= ConfigReader.getLoginURL();

    public LoginPage(WebDriver driver){
        this.driver =driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void openLoginPage(){
        driver.get(loginUrl);
    }

    public void enterUserName(String username){
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassWord(String password){
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement button = driver.findElement(loginButton);
        button.click();
    }
    public void GotoLoginPage(){
        WebElement login = driver.findElement(loginPath);
        login.click();
    }
    public void Login(String username,String password){
        openLoginPage();
        //  GotoLoginPage();
        enterUserName(username);
        enterPassWord(password);
        clickLoginButton();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
