package com.github.e999or.lesson21;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewButton {
    private final WebDriver webDriver;

    public ReviewButton(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id = "bank-overview")
    private WebElement bankOverview;

    @FindBy(xpath = "//small[@class='my-assets']")
    private WebElement myAssets;

    @FindBy(xpath = "//small[@class='my-assets']")
    private WebElement mySAssets;

    void clikIdButton(){
        bankOverview.click();
    }
    void clikxPathButton(){
        myAssets.click();
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
        String str = mySAssets.getAttribute("innerText");
        String sy = String.valueOf(new StringBuffer(str).delete(0,14));
        Matcher matcher = (Pattern.compile("^[0-9]{1,3} [0-9]{3} [0-9]{3}[.]{1}[0-9]{2} [\u20BD]{1}$")).matcher(sy);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

}
