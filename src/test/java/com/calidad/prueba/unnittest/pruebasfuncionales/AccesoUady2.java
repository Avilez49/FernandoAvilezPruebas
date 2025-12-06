package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AccesoUady2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new"); 
    options.addArguments("--no-sandbox"); 
    options.addArguments("--disable-dev-shm-usage"); 
    options.addArguments("--disable-gpu"); 
    options.addArguments("--window-size=1920,1080"); 
    options.addArguments("--remote-allow-origins=*"); 
    driver = new ChromeDriver(options);
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testAccesoUady3() throws Exception {
    driver.get("https://es.uadyvirtual.uady.mx/login/index.php");
    pause(2000);
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("12345");
    pause(2000);
    WebElement passwordField = driver.findElement(By.id("password"));
    js.executeScript("arguments[0].scrollIntoView(true);", passwordField);
    passwordField.click();
    passwordField.clear();
    passwordField.sendKeys("123456");
    WebElement loginButton = driver.findElement(By.id("loginbtn"));
    js.executeScript("arguments[0].click();", loginButton);  
  }

  @AfterEach
  public void tearDown() throws Exception {
    if (driver != null) {
        driver.quit();
    }
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

    private void pause(long mils){
    try {
      Thread.sleep(mils);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}