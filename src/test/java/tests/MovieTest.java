package tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MoviePage;
import Utils.ConfigReader;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.security.PublicKey;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MovieTest extends BaseTest {
    private static final String USERNAME = ConfigReader.getUsername();
    private static final String PASSWORD= ConfigReader.getPassWord();
    private static final String INVALID_USERNAME = ConfigReader.getInvalidUsername();
    private static final String  INVALID_PASSWORD = ConfigReader.getInvalidPassWord();
    private static final String USERNAME2 = ConfigReader.getUsername2();
    private static final String PASSWORD2= ConfigReader.getPassWord2();
    @Test
    public void BookingMovie() throws Exception{
        int seat=29;
        LoginPage loginPage = new LoginPage(driver);
        extentTest.info("Dang nhap voi user hop le");
        loginPage.Login(USERNAME,PASSWORD);
        MoviePage movie = new MoviePage(driver);
       // Thread.sleep(2000);
       // movie.OpenHomePage();
        movie.findMovie();
        extentTest.info("Tim phim John Wick chieu vao luc 01/01/2019 -12:01");
        movie.SelectChair(seat);
        extentTest.info("Chon ghe so: "+seat);
        movie.findPurchaseButton();
        extentTest.info("Click vao button mua ve");
        Assert.assertFalse(movie.isAlertDisplayed());
    }

    @Test
    public void FindMultipleChair() throws Exception{
        MoviePage movie = new MoviePage(driver);
        List<Integer> seats = Arrays.asList(1, 5, 10, 45);
        // Thread.sleep(2000);
        movie.OpenHomePage();
        movie.findMovie();
        extentTest.info("Click into movie");
        //movie.findPurchaseButton();
        movie.findMultipleChairs(seats);
        extentTest.info("found Chairs");
        Assert.assertTrue(true);
    }

    @Test
    public void FindingChair() throws Exception{
        MoviePage movie = new MoviePage(driver);
        // Thread.sleep(2000);
        movie.OpenHomePage();
        movie.findMovie();
       // movie.findPurchaseButton();
        int seatNum =100;
        boolean isSeatFound = movie.SelectChair(seatNum);
        if (isSeatFound) {
            extentTest.info("Seat number " + seatNum + " was found and selected");
        } else {
            extentTest.info("Seat number " + seatNum + " was NOT found");
        }
        Assert.assertTrue(isSeatFound, "Seat number " + seatNum + " not found");
    }

    @Test
    public void FindingVIPChair() throws Exception{
        MoviePage movie = new MoviePage(driver);
        // Thread.sleep(2000);
        movie.OpenHomePage();
        movie.findMovie();
        extentTest.info("Click into movie");
        // movie.findPurchaseButton();
        int seatNum= 100;
        boolean isSeatFound = movie.findVIPEmptyChair(seatNum);
        if (isSeatFound) {
            extentTest.info("VIP Seat number " + seatNum + " was found and selected");
        } else {
            extentTest.info("VIP Seat number " + seatNum + " was NOT found");
        }
        Assert.assertTrue(isSeatFound, "VIP Seat number " + seatNum + " not found");

    }

    @Test
    public void BookingMovieWithOutLogin() throws Exception{
        MoviePage movie = new MoviePage(driver);
        // Thread.sleep(2000);
         movie.OpenHomePage();
        movie.findMovie();
        extentTest.info("Click into movie");
        int seatNum =100;
        boolean isSeatFound = movie.findVIPEmptyChair(seatNum);
        if (isSeatFound) {
            extentTest.info("VIP Seat number " + seatNum + " was found and selected");
        } else {
            extentTest.info("VIP Seat number " + seatNum + " was NOT found");
        }
        Assert.assertTrue(isSeatFound, "VIP Seat number " + seatNum + " not found");
        movie.findPurchaseButton();
        Thread.sleep(2000);
        Assert.assertTrue(movie.isAlertDisplayed());

    }

    @Test
    public void BookingSameSeatWithTwoUsers() throws Exception {
        int seat =23;
        // ===== USER 1 =====
        LoginPage loginPage1 = new LoginPage(driver);
        loginPage1.Login(USERNAME, PASSWORD);
        extentTest.info("Log in with user 1");
        MoviePage moviePage1 = new MoviePage(driver);
        Thread.sleep(3000);
        moviePage1.findMovie();
        extentTest.info("Click into movie with user 1");
        Thread.sleep(3000);
        moviePage1.SelectChair(seat);
        extentTest.info("Find seat number"+ seat+ " with user 1");

        // ===== USER 2 =====
        // Tao driver cho user 2
        WebDriverManager.chromedriver().setup();
        ChromeOptions options2 = new ChromeOptions();
        options2.addArguments("--start-maximized");
        driver2 = new ChromeDriver(options2);
        driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage2 = new LoginPage(driver2);
        loginPage2.Login(USERNAME2, PASSWORD2);
        extentTest.info("Log in with user 2");

        MoviePage moviePage2 = new MoviePage(driver2);
        moviePage2.findMovie();
        extentTest.info("Click into movie with user 2");
        moviePage2.SelectChair(seat);
        extentTest.info("Find seat number"+ seat+ " with user 2");

        moviePage1.findPurchaseButton();
        extentTest.info("Click Purchase button with user 1");
        Thread.sleep(2000);
        // ===== VERIFY =====
        Assert.assertTrue(moviePage2.isAlertDisplayed(),
                "No error display");
        moviePage2.findPurchaseButton();
        extentTest.info("Click Purchase button with user 2");
        // Cleanup
        Thread.sleep(2000);
        TearDownManual(driver2);
    }

}
