package com.springboot.microservices.tradingapp;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


@Category(IntegrationTest.class)
public class TradingAppApplicationTests {
	
	static WebDriver driver;

	@BeforeClass
	public static void setup() {


	    System.setProperty("webdriver.gecko.driver", "D:\\Printout_24\\geckodriver-v0.23.0-win32\\geckodriver.exe");
	     driver = new FirefoxDriver();
	 //    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     
	}

	@Test
	public void login(){
	    driver.get("http://localhost:6080/StockTradingApp/register/main");
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
	    driver.findElement(By.id("quantity")).sendKeys("2");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Quantity:'])[1]/following::button[1]")).click();
	  // driver.findElement(By.linkText("Exit")).click();
	 //   System.out.println("driver.getTitle() ==>"+driver.getTitle());
	      assertTrue(driver.getTitle().contains("Stock Trading Application"));
	  //  assertTrue(driver.getTitle(),"Percentage Calculator");
	      try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void loginFail(){
		 driver.get("http://localhost:6080/StockTradingApp/register/main");
	      driver.findElement(By.linkText("Login")).click();
	      driver.findElement(By.id("userId")).click();
	      driver.findElement(By.id("userId")).clear();
	      driver.findElement(By.id("userId")).sendKeys("rajaj");
	      driver.findElement(By.id("password")).click();
	      driver.findElement(By.id("password")).clear();
	      driver.findElement(By.id("password")).sendKeys("r323");
	      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::button[1]")).click();
	  //  assertTrue(driver.getTitle(),"Percentage Calculator");
	      try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  @AfterClass
	  public static void cleanUp() throws Exception {
	    driver.quit();
	   
	  }

}
