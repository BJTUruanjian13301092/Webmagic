package com.example.webmagic.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {

    private static WebDriver chromeDriver;

    static{
        System.setProperty("webdriver.chrome.driver", "D:\\workingSoftwares\\Chrome\\chromedriver_win32\\chromedriver.exe");
        //设置无头浏览器(不加载页面)
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        setChromeDriver(new ChromeDriver(options));
    }

    private static void setChromeDriver(WebDriver driver){
        chromeDriver = driver;
    }

    public static WebDriver getChromeDriver(){
        return chromeDriver;
    }

    public static void closeChromeDriver(){
        chromeDriver.close();
    }
}
