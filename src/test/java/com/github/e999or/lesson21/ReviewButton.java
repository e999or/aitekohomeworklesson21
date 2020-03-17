package com.github.e999or.lesson21;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewButton {
    private final WebDriver webDriver;

    public ReviewButton(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    void clikIdButton(String idButton){
        webDriver.findElement(By.id(idButton)).click();
    }
    void clikxPathButton(String xPathButton){
        webDriver.findElement(By.xpath(xPathButton)).click();
    }

    boolean inspectTitle(){
        String text =webDriver.findElement(By.id("can-spend")).getAttribute("innerText");
        if((text).contains( "Финансовая свобода")) {
            return true;
        }else{
            return false;
        }
    }

    boolean inspectTipe(){
        String str = (webDriver.findElement(By.xpath("//small[@class='my-assets']")).getAttribute("innerText"));
        String sy = String.valueOf(new StringBuffer(str).delete(0,14));
        Matcher matcher = (Pattern.compile("^[0-9]{1,3} [0-9]{3} [0-9]{3}[.]{1}[0-9]{2} [\u20BD]{1}$")).matcher(sy);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

}
