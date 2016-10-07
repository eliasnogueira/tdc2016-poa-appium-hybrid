package com.eliasnogueira;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AndroidTest {

	@Test
	public void teste() throws MalformedURLException {	
		DesiredCapabilities capacidade = new DesiredCapabilities();
		capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "0023896100");
		capacidade.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		capacidade.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.javebratt.theonemaninventory");
		capacidade.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "MainActivity");
		
		AppiumDriver<WebElement> driver = 
				new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		// login
		driver.findElement(By.name("email")).sendKeys("elias.nogueira@gmail.com");
		driver.findElement(By.name("password")).sendKeys("elias");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		
		// validacao da categoria
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item.item-divider.center-text.ng-binding")));
		assertEquals("Categoria 1", driver.findElement(By.cssSelector(".item.item-divider.center-text.ng-binding")).getText());
		assertEquals("Teste de Categoria", driver.findElement(By.cssSelector(".item.item-text-wrap.ng-binding")).getText());
		driver.findElement(By.cssSelector(".item.item-text-wrap.ng-binding")).click();
		
		// validacao do produto
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
		assertEquals("Produto 1", driver.findElement(By.id("name")).getText());
		assertEquals("Descrição produto 1", driver.findElement(By.id("description")).getText());
		assertEquals("$200.00", driver.findElement(By.id("price")).getText());
		assertEquals("1", driver.findElement(By.id("unit")).getText());
	
		driver.quit();
	}

}
