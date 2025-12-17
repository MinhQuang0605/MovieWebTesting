package tests;

import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

public class HomeTest extends BaseTest {
    @Test
    public void findAllMovie() throws Exception {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.ReturnAllMovies());
    }
    @Test
    public void findAllButton() throws Exception{
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.FindAllButtons());
    }

    @Test
    public void findAllCarouselButton() throws Exception{
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.FindCarouselButton());
    }
}
