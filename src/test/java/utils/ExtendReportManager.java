package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class ExtendReportManager {
    protected WebDriver driver;
    private static ExtentReports extend;

    private static ExtentTest test;
    public static ExtentReports getInstance(){
        return extend;
    }

}
