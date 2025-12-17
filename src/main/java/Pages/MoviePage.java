package Pages;

import Utils.ConfigReader;
import Utils.ScreenShotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By EmptyChair = By.xpath("//span[text()='45']/parent::button");
    //button[not(@disabled)]//span[text()='01']/parent::button
    private By TakenChair = By.xpath("//button[@disabled]//span[text()='X']/parent::button");
    //button[@disabled]//span[text()='X']/parent::button
    private By chairs  = By.xpath("//button[not(@disabled)]//span[@class='MuiButton-label']");
    private String HomeURL = ConfigReader.getHomeURL();
    private By loginAlert = By.xpath("//div[contains(@class,'swal2-popup')]");

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

    public void findVIPEmptyChair() throws Exception{
        wait.until(ExpectedConditions.presenceOfElementLocated(EmptyChair));
        WebElement EmptySeat = driver.findElement(EmptyChair);
        String bgColor = EmptySeat.getCssValue("background-color");
        System.out.println(bgColor);
        if (bgColor.contains("255, 165, 0, 1")) {
            System.out.println("Ghe VIP");
            highlightPink(EmptySeat);
            screenShotHelper.ExtraCapture("Success finding empty VIP chair");
        }
       screenShotHelper.CaptureHighLight(EmptySeat,"Fail finding VIP Chair");
        Thread.sleep(2000);
        EmptySeat.click();
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

    public void SelectChair(int seat) throws Exception{
        for (WebElement chair : driver.findElements(chairs)){
            String text = chair.getText().trim();
            if (text.matches("\\d+") && seat==(Integer.parseInt(text))) {
                highlightPink(chair);
                    chair.click();
            }
        }
        screenShotHelper.ExtraCapture("Selected seats: "+seat);
    }

    public boolean  isLoginAlertDisplayed() throws Exception{
        WebElement error = driver.findElement(loginAlert);
        return error.isDisplayed();
    }



}
