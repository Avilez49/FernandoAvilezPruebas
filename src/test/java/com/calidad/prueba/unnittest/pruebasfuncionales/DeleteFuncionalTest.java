package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteFuncionalTest {
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
  public void testDeleteUser() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    WebElement celdaUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//td[text()='Usuario111']")
    ));

    int cantidadInicial = driver.findElements(By.xpath("//table/tbody/tr")).size();

    driver.findElement(By.xpath("//tr[td[text()='Usuario111']]//button[contains(text(), 'Delete')]")).click();
    
    pause(1000); 
    driver.findElement(By.xpath("//button[text()='Yes']")).click();

    boolean filasDisminuyeron = wait.until(driverInstance -> {
        int cantidadActual = driverInstance.findElements(By.xpath("//table/tbody/tr")).size();
        return cantidadActual == (cantidadInicial - 1);
    });
    assertTrue(filasDisminuyeron, "FALLO: El conteo de filas no disminuyó.");

    List<WebElement> busquedaUsuario = driver.findElements(By.xpath("//td[text()='Usuario111']"));
    assertTrue(busquedaUsuario.isEmpty(), "FALLO: 'Usuario111' sigue apareciendo en la tabla después de borrarlo.");
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