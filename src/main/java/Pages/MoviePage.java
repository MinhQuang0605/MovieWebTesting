package Pages;

import Utils.ConfigReader;
import Utils.ScreenShotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MoviePage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    private ScreenShotHelper screenShotHelper;

    private By movie1 = By.xpath("//a[   .//p[normalize-space()='01-01-2019'] " +
            "  and   .//h3[normalize-space()='12:10'] ]");
    private By PurchaseButton =  By.xpath("//span[text()='ĐẶT VÉ']/parent::button");
    private By EmptyChair = By.xpath("//span[text()='100']/parent::button");
    //button[not(@disabled)]//span[text()='01']/parent::button
    private By TakenChair = By.xpath("//button[@disabled]//span[text()='X']/parent::button");
    //button[@disabled]//span[text()='X']/parent::button
        private By chairs  = By.xpath("//button[not(@disabled)]//span[@class='MuiButton-label']");
    private By chairs2 = By.xpath("//button[not(@disabled)]");
    private String HomeURL = ConfigReader.getHomeURL();
    private By FailAlert = By.xpath("//div[contains(@class,'swal2-popup') and contains(@class,'swal2-icon-error')]");

    public MoviePage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.screenShotHelper = new ScreenShotHelper(driver, "MoviePage");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public  WebElement highlightPink(WebElement element) throws Exception {
        js.executeScript(
                "arguments[0].style.border='3px solid red';"+
                        "arguments[0].style.backgroundColor='pink';"+
                        "arguments[0].style.boxShadow='0 0 10px red';", element
        );
        Thread.sleep(1000);
        return element;
    }

    public void OpenHomePage(){
        driver.get(HomeURL);
        //wait.until(ExpectedConditions.presenceOfElementLocated(movie1));
    }

    public void findMovie() throws Exception {
        WebElement movieDate = driver.findElement(movie1);
      js.executeScript("arguments[0].scrollIntoView({block: 'center'});", movieDate);
       screenShotHelper.CaptureHighLight(movieDate, "Finding movie");
       movieDate.click();
        Thread.sleep(2000);
    }

    public void findPurchaseButton() throws Exception{
        wait.until(ExpectedConditions.presenceOfElementLocated(PurchaseButton));
        WebElement purchase = driver.findElement(PurchaseButton);
        screenShotHelper.CaptureHighLight(purchase,"Finding purchase button");
        Thread.sleep(3000);
        purchase.click();
        Thread.sleep(2000);
        screenShotHelper.ExtraCapture("After Purchase Result");
    }

    public void findEmptyChair() throws Exception{
        wait.until(ExpectedConditions.presenceOfElementLocated(EmptyChair));
        WebElement EmptySeat = driver.findElement(EmptyChair);
        String bgColor = EmptySeat.getCssValue("background-color");
        System.out.println(bgColor);
        highlightPink(EmptySeat);
        screenShotHelper.ExtraCapture("Finding empty chair");
        Thread.sleep(2000);
        EmptySeat.click();
    }

    public boolean findVIPEmptyChair(int seat) throws Exception{
        List<WebElement> chairs = driver.findElements(chairs2);

        for (WebElement chair : chairs) {
            String text = chair.getText().trim();

            if (text.matches("\\d+") && Integer.parseInt(text) == seat) {
                String bgColor = chair.getCssValue("background-color");
                System.out.println(bgColor);

                if (bgColor.contains("255, 165, 0")) {
                    System.out.println("Ghế VIP");

                    chair.click();
                    screenShotHelper.CaptureHighLight(chair,"Success finding empty VIP chair");
                    return true;
                }

            }
        }
        screenShotHelper.ExtraCapture("Failure finding empty VIP chair");
        return false;

    }


    public void findMultipleChairs(List<Integer> seats) throws Exception {
        for (WebElement chair : driver.findElements(chairs)) {
            String text = chair.getText().trim();

            if (text.matches("\\d+") && seats.contains(Integer.parseInt(text))) {
                highlightPink(chair);
           //     chair.click();
            }

        }
        screenShotHelper.ExtraCapture("All selected seats");
    }

    public boolean SelectChair(int seat) throws Exception{
        List<WebElement> chairs = driver.findElements(this.chairs);

        for (WebElement chair : chairs) {
            String text = chair.getText().trim();

            if (text.matches("\\d+") && Integer.parseInt(text) == seat) {
                highlightPink(chair);
                chair.click();
                screenShotHelper.ExtraCapture("Selected seat " + seat);
                return true;
            }
        }
        return false;
    }

    public boolean isAlertDisplayed() {
        return !driver.findElements(FailAlert).isEmpty()
                && driver.findElements(FailAlert).get(0).isDisplayed();
    }



}
