package com.example.webmagic.spider.processor;

import com.example.webmagic.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

public class BaiduProcessor extends BaseProcessor {

    @Override
    public void process(Page page) {

        WebDriver driver = WebDriverUtil.getChromeDriver();
        driver.get(page.getUrl().toString());
        driver.findElement(By.id("kw")).sendKeys("李闱");
        driver.findElement(By.id("su")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //移动到窗口绝对位置坐标，如下移动到纵坐标500像素位置
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");

        String str = driver.findElement(By.xpath("//*[@id=\"1\"]/h3/a")).getText();
        page.putField("testStr", str);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
