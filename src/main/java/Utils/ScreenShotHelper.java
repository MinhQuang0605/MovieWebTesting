package Utils;

import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotHelper {
    private  WebDriver driver;
    private  int stepsCount=0;
    private  String screenshotDIR;
    private String PageName;
    private  JavascriptExecutor js;

    public ScreenShotHelper(WebDriver driver, String PageName){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        //setup take screenshot
        //reset step counts và tao thu muc screenshot moi cho tung test case
        stepsCount=0;
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        //Tao duong dan luu screenshot
        screenshotDIR = System.getProperty("user.dir") + "/test-output/screenshots/" +PageName +timeStamp+"/";
        //tao folder dua tren screenshot Directory
        new File(screenshotDIR).mkdirs();
    }
    public  WebElement hightlightElement(WebElement element) throws Exception {
        js.executeScript(
                "arguments[0].style.border='3px solid red';"+
                        "arguments[0].style.backgroundColor='yellow';"+
                        "arguments[0].style.boxShadow='0 0 10px red';", element
        );
        Thread.sleep(1000);
        return element;
    }

    public  void Remmovehightlight(WebElement element){
        js.executeScript(
                "arguments[0].removeAttribute('style')", element
        );
    }

    //capture screenshot
    public  void CaptureHighLight(WebElement element, String stepName) throws Exception{
        //b1 highlight element
        hightlightElement(element);
        //chup man hinh
        File fileName = new File(screenshotDIR + "Step_" + ++stepsCount +" "+ stepName + ".png");
        ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE).renameTo(fileName);
        Remmovehightlight(element);
    }

    public void ExtraCapture(String StepName){
        File picture = new File(screenshotDIR + "Step_"+ ++stepsCount+ " "+ StepName+ ".png");
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(picture);
    }

}
