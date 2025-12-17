package utils;

import Utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendReportManager {
    protected WebDriver driver;
    private static ExtentReports extend;

    private static ExtentTest test;
    public static ExtentReports getInstance(){
    if(extend==null){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

        String ReportPath = System.getProperty("user.dir")+"/" +
                ConfigReader.getReportPath() + "/Report_"+ timeStamp + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ReportPath);

        //Cau hinh report: title, report name, theme, ngay tao report,...
        sparkReporter.config().setDocumentTitle("Movie Test report");
        sparkReporter.config().setReportName("Selenium test execution");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd");

        extend = new ExtentReports();
        extend.attachReporter(sparkReporter);
        }
        return extend;

    }

    //ham tao test case moi
    public static ExtentTest createTest(String testname, String description){
        test = getInstance().createTest(testname, description);
        return test;
    }

    public static ExtentTest getTest(){
        return test;
    }

    //ham flush de ghi infor test case ra file
    public static void flushReport(){
        if(extend !=null){
            extend.flush();
        }

    }

}
