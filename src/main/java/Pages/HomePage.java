package Pages;

import Utils.ConfigReader;
import Utils.ScreenShotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.layertree.model.ScrollRect;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private JavascriptExecutor js;

    private By movieItems = By.xpath("//div[contains(@class,'CarouselItem')][2]");
    private By movieLists = By.xpath("//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-4 MuiGrid-grid-md-3')]");
   private By CarouselButton = By.xpath("(//div[contains(@class,'jss89')])[2]//button");
   //Css dong, de sai can cap nhat lai moi khi trang update noi dung moi
   private By AllButtons = By.xpath("//button[contains(@class,'MuiIconButton-root')]");
    //"//div[contains(@class,'jss288') and contains(@class,'jss280')]" +
        //            "//div[contains(@class,'CarouselItem')]"
    //((//div[contains(@class,'jss289')])[2]//button)
    private ScreenShotHelper screenShotHelper;
    private String HomeURL = ConfigReader.getHomeURL();
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.screenShotHelper = new ScreenShotHelper(driver, "HomePage");
    }

    public  WebElement highlight(WebElement element) throws Exception {
        js.executeScript(
                "arguments[0].style.border='3px solid red';"+
                        "arguments[0].style.backgroundColor='yellow';"+
                        "arguments[0].style.boxShadow='0 0 10px red';", element
        );
        Thread.sleep(1000);
        return element;
    }
    public  void Remmovehighlight(WebElement element){
        js.executeScript(
                "arguments[0].removeAttribute('style')", element
        );
    }

    public void OpenHomePage(){
        driver.get(HomeURL);
    }

    public boolean ReturnAllMovies() throws Exception{
        OpenHomePage();
        List<WebElement> buttons = driver.findElements(CarouselButton);
        int buttonNumber = (int) buttons.size();
        int totalMovie = 0;
        for(WebElement button :buttons){
            button.click();
            Thread.sleep(2000);
            List<WebElement> movies = driver.findElements(movieLists);
            totalMovie+= (int) movies.size();
            for (WebElement movie : movies) {
                highlight(movie);
                Remmovehighlight(movie);
                Thread.sleep(500);
            }
        }
        System.out.println("So phim dang hien thi: " + totalMovie);
        System.out.println("So nut dang hien thi"+ buttonNumber);
        if(totalMovie>0){
            return true;
        } else {
            return false;
        }

    }

    public boolean FindCarouselButton() throws Exception{
        OpenHomePage();
        List<WebElement> buttons = driver.findElements(CarouselButton);
        System.out.println("Số nút đang hiển thị: " + buttons.size());
        for(WebElement button: buttons){
            highlight(button);
            button.click();
            Thread.sleep(3000);
        }
        if(buttons.size()>0){
            return true;
        }else
            return false;
    }

    public boolean FindAllButtons() throws Exception{
        OpenHomePage();
        Thread.sleep(2000);
        List<WebElement> buttons = driver.findElements(AllButtons);
        System.out.println("Số nút đang hiển thị: " + buttons.size());
        for(WebElement button: buttons){
            highlight(button);
            //button.click();
            Thread.sleep(3000);
        }
        if(buttons.size()>0){
            return true;
        }else
            return false;
    }

}
