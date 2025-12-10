package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField= By.name("password");

    //    By usernameFIeld = By.xpath("//input[@placeholder='Username']");
//    By passwordField = By.xpath("//input[@placeholder='Password']");

    private By loginButton = By.xpath("//button[@type='submit']");

    private By errorMessage = By.xpath("//div[@role='alert']");


}
