package com.github.e999or.lesson21;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SimpleTest {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleTest.class);
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private LoginPage loginPage;
    private ReviewButton reviewButton;


    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        reviewButton = new ReviewButton(webDriver);
        webDriverWait = new WebDriverWait(webDriver, 60);
        LOG.info("Before test method");
    }

    @AfterMethod
    public void afterTestMethod(){
        webDriver.quit();
        LOG.info("After test method");
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        loginPage.open();
        loginPage.clickLoginButton("login-button");
        String phone = webDriver.findElement(By.xpath("//div[@id='otp-code-text']/b")).getAttribute("innerText");
        Assert.assertTrue(loginPage.inspectCodePhone());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp-code")));

        loginPage.sendKeyskOtpCode("otp-code","0000");
        loginPage.clikOtpCode("login-otp-button");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bank-overview")));
        Assert.assertEquals(webDriver.getTitle(), "Старт - Интернет банк - Банк Санкт-Петербург");

        reviewButton.clikIdButton("bank-overview");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("can-spend")));
        Assert.assertTrue(reviewButton.inspectTitle());

        reviewButton.clikxPathButton("//small[@class='my-assets']");
        Assert.assertTrue(reviewButton.inspectTipe());
    }
}