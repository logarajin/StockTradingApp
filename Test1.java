package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ILP1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testILP1() throws Exception {
    driver.get("http://localhost:9085/register/main");
    driver.findElement(By.linkText("Register")).click();
    driver.findElement(By.id("userId")).click();
    driver.findElement(By.id("userId")).clear();
    driver.findElement(By.id("userId")).sendKeys("rajaj");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rajaj");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("loga_raja@rediffmail.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Email:'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("userId")).click();
    driver.findElement(By.id("userId")).clear();
    driver.findElement(By.id("userId")).sendKeys("rajaj");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='User Id:'])[1]/following::label[1]")).click();
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("rajaj");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::button[1]")).click();
    driver.findElement(By.id("ticker")).click();
    new Select(driver.findElement(By.id("ticker"))).selectByVisibleText("WIPRO");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Stock Trading'])[1]/following::option[2]")).click();
    driver.findElement(By.id("quantity")).click();
    driver.findElement(By.id("quantity")).clear();
    driver.findElement(By.id("quantity")).sendKeys("10");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Quantity:'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("Exit")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
