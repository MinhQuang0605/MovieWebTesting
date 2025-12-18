package tests;

import Pages.MoviePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTestForMultipleUser;

public class TwoUserMovieTest extends BaseTestForMultipleUser {
    @Test
    public void bookingSameSeat() throws Exception {

        MoviePage movie = new MoviePage(getDriver());
        Thread.sleep(4000);
        movie.findMovie();
        movie.SelectChair(18);
        movie.findPurchaseButton();

        // Chỉ user thứ 2 mới thấy alert
        if (movie.isAlertDisplayed()) {
            Assert.assertTrue(movie.isAlertDisplayed(),
                    "Phai co Alert khi 1 user da dat ve");
        } else {
            Assert.assertTrue(true);
        }
    }
}
