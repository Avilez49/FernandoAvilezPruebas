package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateFuncionalTest {
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
  public void testUpdateUser() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    WebElement btnEdit = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//tr[td[text()='Usuario111']]//button[contains(text(), 'Edit')]")
    ));
    btnEdit.click();
    
    pause(1000);

    WebElement emailField = driver.findElement(By.name("email"));
    emailField.click();
    emailField.clear();
    String nuevoEmail = "actualizado111@gmail.com";
    emailField.sendKeys(nuevoEmail);
    
    driver.findElement(By.xpath("//button[contains(text(), 'Save')]")).click();
    
    boolean mensajeExito = wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.xpath("//form//p"), 
            "Successfully updated!"
    ));
    
    assertTrue(mensajeExito, "No apareció el mensaje 'Successfully updated!'");
    
    try {
        driver.findElement(By.xpath("//i")).click();
    } catch (Exception e) {
    }
    
    pause(1000);

    WebElement celdaEmail = driver.findElement(By.xpath("//tr[td[text()='Usuario111']]/td[2]"));
    assertEquals(nuevoEmail, celdaEmail.getText(), "El email en la tabla no se actualizó correctamente.");
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