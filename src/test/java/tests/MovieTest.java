package tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MoviePage;
import Utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

public class MovieTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getUsername();
    private static final String VALID_PASSWORD= ConfigReader.getPassWord();
    private static final String INVALID_USERNAME = ConfigReader.getInvalidUsername();
    private static final String  INVALID_PASSWORD = ConfigReader.getInvalidPassWord();

    @Test
    public void BookingMovie() throws Exception{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(VALID_USERNAME,VALID_PASSWORD);
        MoviePage movie = new MoviePage(driver);
       // Thread.sleep(2000);
       // movie.OpenHomePage();
        movie.findMovie();
        movie.SelectChair(18);
        movie.findPurchaseButton();
        Assert.assertTrue(true);
    }

    @Test
    public void FindMultipleChair() throws Exception{
        MoviePage movie = new MoviePage(driver);
        List<Integer> seats = Arrays.asList(1, 5, 10, 45);
        // Thread.sleep(2000);
        movie.OpenHomePage();
        movie.findMovie();
        movie.findPurchaseButton();
        movie.findMultipleChairs(seats);
        movie.findVIPEmptyChair();
        Assert.assertTrue(true);
    }

    @Test
    public void FindingChair() throws Exception{
        MoviePage movie = new MoviePage(driver);
        // Thread.sleep(2000);
        movie.OpenHomePage();
        movie.findMovie();
        movie.findPurchaseButton();
        movie.SelectChair(18);
        Assert.assertTrue(true);
    }

    @Test
    public void BookingMovieWOLogin() throws Exception{
        MoviePage movie = new MoviePage(driver);
        // Thread.sleep(2000);
         movie.OpenHomePage();
        movie.findMovie();
        movie.SelectChair(18);
        movie.findPurchaseButton();
        Thread.sleep(2000);
        Assert.assertTrue(movie.isLoginAlertDisplayed());

    }

}
