package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSinEmailFuncionalTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  
  @BeforeEach
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    options.addArguments("--start-maximized");
    options.addArguments("--disable-search-engine-choice-screen");
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    options.setExperimentalOption("useAutomationExtension", false);
    driver = new ChromeDriver(options);
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testIngresoSinEmail() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    pause(2000); 
    driver.findElement(By.name("name")).sendKeys("UsuarioSinEmail");
    WebElement emailField = driver.findElement(By.name("email"));
    driver.findElement(By.name("age")).sendKeys("25");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
    pause(500);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[2]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
    pause(1000); 

    String mensajeValidacion = (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    boolean hayError = mensajeValidacion.length() > 0;
    assertTrue(hayError, "FALLO: El sistema permitió enviar el formulario sin email.");
    driver.findElement(By.xpath("//i")).click();
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  // ... Métodos auxiliares ...
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