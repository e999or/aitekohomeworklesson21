package com.github.e999or.lesson21;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    void open() {
        webDriver.get("https://idemo.bspb.ru");
    }

    void clickLoginButton(String idButton){
        webDriver.findElement(By.id(idButton)).click();
    }

    boolean inspectCodePhone(){
        String phone = webDriver.findElement(By.xpath("//div[@id='otp-code-text']/b")).getAttribute("innerText");
        if(phone.equals("*1111")) {
            return true;
        }else{
            return false;
        }
    }

    void sendKeyskOtpCode(String idButton, String keys){
        webDriver.findElement(By.id(idButton)).sendKeys(keys);
    }

    void clikOtpCode(String idButton){
        webDriver.findElement(By.id(idButton)).click();
    }
}
