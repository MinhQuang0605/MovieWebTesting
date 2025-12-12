package tests;

import Pages.LoginPage;
import Utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getUsername();
    private static final String VALID_PASSWORD= ConfigReader.getPassWord();
    private static final String INVALID_USERNAME = ConfigReader.getInvalidUsername();
    private static final String  INVALID_PASSWORD = ConfigReader.getInvalidPassWord();

    @Test
    public void testLoginSuccess() throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(VALID_USERNAME,VALID_PASSWORD);
        Thread.sleep(4000);
        String currentUrl= driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/login"));
    }

    @Test
    public void testLoginFail() throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(INVALID_USERNAME,INVALID_PASSWORD);
        Thread.sleep(4000);
        String currentUrl= driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/sign-in"));
    }
}
