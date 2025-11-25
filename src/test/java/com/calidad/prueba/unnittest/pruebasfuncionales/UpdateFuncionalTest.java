package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;
import java.util.Collections;
import org.openqa.selenium.NoSuchElementException; 
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UpdateFuncionalTest {

  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() throws Exception {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    options.addArguments("--start-maximized");
    options.addArguments("--disable-search-engine-choice-screen");
    options.addArguments("--disable-extensions");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-popup-blocking");
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    options.setExperimentalOption("useAutomationExtension", false);
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testUpdateKatalon() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    pause(2000); 
    if (!isElementPresent(By.xpath("//td[contains(text(), 'Usuario111')]"))) {
        System.out.println("--- EL USUARIO NO EXISTE: Creándolo automáticamente... ---");
        driver.findElement(By.xpath("//button[text()='Add New']")).click(); 
        pause(1000);
        driver.findElement(By.name("name")).sendKeys("Usuario111");
        driver.findElement(By.name("email")).sendKeys("usuario111@gmail.com");
        driver.findElement(By.name("age")).sendKeys("39");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
        driver.findElement(By.xpath("//button[text()='Add']")).click();
        driver.findElement(By.xpath("//i")).click(); 
        pause(2000); 
    }
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Usuario111'])[1]/following::button[1]")).click();
    pause(1000);
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Usuario222");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("usuario222@gmail.com");
    driver.findElement(By.xpath("//button[text()='Save']")).click();
    pause(1000);
    driver.findElement(By.xpath("//i")).click();
    pause(2000);
    try {
        boolean existeNuevoUsuario = isElementPresent(By.xpath("//td[contains(text(), 'Usuario222')]"));
        assertTrue(existeNuevoUsuario, "ERROR: El usuario no se actualizó a 'Usuario222'.");
        System.out.println("ÉXITO: Test finalizado correctamente.");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  // ESTE METODO ES EL QUE FALLABA POR EL IMPORT INCORRECTO
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) { 
      // Ahora sí capturará org.openqa.selenium.NoSuchElementException
      return false;
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