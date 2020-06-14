package com.jack.order;

import com.jack.order.autil.Strs;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class selenium2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver","D:\\chr\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:8080/test");
        WebElement b = driver.findElement(By.id("alert"));
        b.click();
        Thread.sleep(3000);
        try {
            Alert alert = driver.switchTo().alert();
            String message = alert.getText();
            System.out.println(message);
            alert.accept();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}