package com.github.e999or.lesson21;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='otp-code-text']/b")
    private WebElement otpCodeText;

    @FindBy(id = "otp-code")
    private WebElement otpCode;

    @FindBy(id = "login-otp-button")
    private WebElement loginOtpButton;

    LoginPage open() {
        webDriver.get("https://idemo.bspb.ru");
        return this;
    }

    LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    boolean inspectCodePhone(){
        String phone = otpCodeText.getAttribute("innerText");
        if(phone.equals("*1111")) {
            return true;
        }else{
            return false;
        }
    }

    LoginPage sendKeyskOtpCode(String keys){
        otpCode.sendKeys(keys);
        return this;
    }

    LoginPage clikOtpCode(){
        loginOtpButton.click();
        return this;
    }
}
