package tests;

import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.CsvReader;
import Utils.LoginData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.List;

public class LoginTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getUsername();
    private static final String VALID_PASSWORD= ConfigReader.getPassWord();
    private static final String INVALID_USERNAME = ConfigReader.getInvalidUsername();
    private static final String  INVALID_PASSWORD = ConfigReader.getInvalidPassWord();

    @Test
    public void testLoginSuccess() throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(VALID_USERNAME,VALID_PASSWORD);
        Thread.sleep(2000);
        String currentUrl= driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/login"));
    }

    @Test
    public void testLoginFail() throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(INVALID_USERNAME,INVALID_PASSWORD);
        Thread.sleep(2000);
        String currentUrl= driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/sign-in"));
    }

    //define Data Provider de doc du lieu
    @DataProvider(name="LoginData")
    public Object[][] getLoginData() throws Exception{
        //B1 lay path chua file csv
        String csvPath = System.getProperty("user.dir")+ "/src/test/resources/login.csv";
        //doc file csv va convert ve 1 list data de dung cho test case
        List<LoginData> data = CsvReader.readLoginData(csvPath);
        //convert ve dang chuan cua testNG
        //[data.size()] so luong hang, so luong data
        //[2] column chua du lieu username va password
        Object[][] listUser = new Object[data.size()][3];
        for(int i=0;i< data.size();i++){
            listUser[i][0] = data.get(i).getUsername();
            listUser[i][1] = data.get(i).getPassword();
            listUser[i][2] = data.get(i).isExpected();
        }
        return listUser;
    }

    @Test(dataProvider = "LoginData")
    public void testLoginwithCSVData(String username, String password, boolean expected) throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(username, password);
        extentTest.info("login with username: " +username+ " and password: "+password);
        Thread.sleep(3000);
        String currentUrl = loginPage.getCurrentUrl();
        if(currentUrl.contains("login")&& expected==false){
            Assert.assertTrue(currentUrl.contains("login"));
            boolean hasError = loginPage.isErrorDisplayed();
            Assert.assertTrue(hasError);
            extentTest.info("Login fail");
        }else{
            Assert.assertTrue(currentUrl.contains(ConfigReader.getHomeURL()));
            extentTest.info("Login success");
        }

    }
}
