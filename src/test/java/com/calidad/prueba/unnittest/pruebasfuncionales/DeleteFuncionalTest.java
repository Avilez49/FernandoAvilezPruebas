package com.calidad.prueba.unnittest.pruebasfuncionales;

import java.time.Duration;
import java.util.Collections;
import org.openqa.selenium.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeleteFuncionalTest {

  private WebDriver driver;
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
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    options.setExperimentalOption("useAutomationExtension", false);
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testDeleteKatalon() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    pause(2000);
    boolean existeUsuario = isElementPresent(By.xpath("//td[contains(text(), 'Usuario222')]"));
    if (existeUsuario) {
        System.out.println("Usuario encontrado. Eliminando...");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Usuario222'])[1]/following::button[2]")).click();
        pause(1000); 
        driver.findElement(By.xpath("//button[text()='Yes']")).click();
        pause(1000); 
        try {
            if (isElementPresent(By.xpath("//i"))) {
                driver.findElement(By.xpath("//i")).click();
            }
        } catch (Exception e) {
        }
        pause(2000); 
        boolean sigueAhi = isElementPresent(By.xpath("//td[contains(text(), 'Usuario222')]"));
        assertFalse(sigueAhi, "Error: Se intentó borrar pero el usuario sigue visible.");
        System.out.println("EXITO: Usuario eliminado correctamente.");
    } else {
        System.out.println("AVISO: El 'Usuario222' ya no existe en la tabla. No es necesario borrarlo.");
        System.out.println("Test Finalizado Exitosamente.");
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
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