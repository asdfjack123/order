package com.jack.order;

import com.jack.order.autil.Strs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class selenium {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver","D:\\chr\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        driver.get("http://www.google.com");
//        WebElement element = driver.findElement(By.name("q"));
        //使用implicitlyWait，抓取DOM時，會等DOM出現才抓，最多等10秒
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://www.google.com.tw/"); //開啟瀏覽器到 Google 首頁
//        driver.get("http://10.0.0.123/MegaImage/management/logFunctionManagePage");
        driver.get("http://10.0.0.123/MegaImage/home/SSOLogin?lightId=12345678");

        WebElement s = driver.findElement(By.tagName("h5"));

//        WebElement s = driver.findElement(By.id("function"));

        System.out.println("----------------------");
        System.out.println(s.getText());
        System.out.println("----------------------");

        //點擊使用者紀錄查詢(id是SystemAdmin.TransactionLog)
//        System.out.println("-----點擊使用者紀錄查詢開始-----");
//        driver.findElement(By.id("SystemAdmin.TransactionLog")).click();
//        System.out.println("-----點擊使用者紀錄查詢結束-----");

        driver.get("http://10.0.0.123/MegaImage/management/logFunctionManagePage");


        WebElement functionSelect = driver.findElement(By.id("function"));
        List<WebElement> options = driver.findElements(By.tagName("option"));
        for(WebElement option : options){
           if("Query.Image".equals(option.getAttribute("value"))){
               option.click();
               break;
           }
        }

//        Thread.sleep(3000);

        WebElement logBeginDate = driver.findElement(By.id("logBeginDate"));
        logBeginDate.clear();
        logBeginDate.sendKeys("2020/06/11");

//        Thread.sleep(3000);

        WebElement logEndDate = driver.findElement(By.id("logEndDate"));
        logEndDate.clear();
        logEndDate.sendKeys("2020/06/11");


//        Thread.sleep(3000);

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for(WebElement button : buttons){
            if("查詢".equals(button.getText())){
                button.click();
                break;
            }
        }

        Thread.sleep(3000);

        /* 寫入Txt檔案 */
        File writename = new File("D:\\text\\output.txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
        try {
            writename.createNewFile(); // 建立新檔案
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));


        WebElement theadtr =  driver.findElement(By.tagName("thead")).findElement(By.tagName("tr"));
        List<WebElement> ths = theadtr.findElements(By.tagName("th"));
        for (WebElement th : ths){
            String tmp = Strs.fixedSizeWithBlank(th.getText(),24);
            System.out.print(tmp);
            out.write(tmp);
        }

        System.out.println();
        out.write("\r\n");

        List<WebElement> trs = driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement tr : trs){
            List<WebElement> tds = tr.findElements(By.tagName("td"));
            for(WebElement td : tds){
                String tmp2 = Strs.fixedSizeWithBlank(td.getText(),24);
                System.out.print(tmp2);
                out.write(tmp2);
            }
            System.out.println();
            out.write("\r\n");
        }

        out.flush(); // 把快取區內容壓入檔案
        out.close(); // 最後記得關閉檔案


        //抓取DOM element，#lst-ib 為Google搜尋框
//        WebElement searchInput = driver.findElement(By.id("lst-ib"));

        //執行Javascript範例
        //將Google搜尋框打上字，"keyword"
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("arguments[0].value='keyword';", searchInput);

        //用WebElement物件直接操做DOM element範例
        //抓取DOM element，name=btnK 為Google搜尋按鈕
//        WebElement searchBtn = driver.findElement(By.name("btnK"));
//        searchBtn.click();

        //印出十頁的所有搜尋結果Title和Link url
//        for (int i = 0; i < 10; i++) {
//            //抓取DOM elements, (.r a) 為Google搜尋結果的link
//            List<WebElement> searchReultATagList = driver.findElements(By.cssSelector(".r a"));
//            for (WebElement searchReultATag : searchReultATagList) {
//                System.out.println(searchReultATag.getText() + " : ");
//                System.out.println(searchReultATag.getAttribute("href"));
//                System.out.println("=======================");
//            }
//            //抓取DOM element, #pnnext 為Google搜尋下一頁按鈕
//            WebElement nextPageBtn = driver.findElement(By.id("pnnext"));
//            nextPageBtn.click();
//        }

        //driver.quit(); //關閉瀏覽器
    }
}