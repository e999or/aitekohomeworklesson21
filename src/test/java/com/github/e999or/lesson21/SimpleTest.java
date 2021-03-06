package com.github.e999or.lesson21;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
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

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
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
        LoginPage factoryPage = PageFactory.initElements(webDriver, LoginPage.class);
        ReviewButton factoryPageReviewMenu = PageFactory.initElements(webDriver, ReviewButton.class);
        factoryPage.open()
                .clickLoginButton();

        Assert.assertTrue(factoryPage.inspectCodePhone());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp-code")));

        factoryPage.sendKeyskOtpCode("0000")
                    .clikOtpCode();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bank-overview")));
        Assert.assertEquals(webDriver.getTitle(), "Старт - Интернет банк - Банк Санкт-Петербург");

        factoryPageReviewMenu.clikIdButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("can-spend")));
        Assert.assertTrue(factoryPageReviewMenu.inspectTitle());

        factoryPageReviewMenu.clikxPathButton();
        Assert.assertTrue(factoryPageReviewMenu.inspectTipe());
    }
}