package com.eliasnogueira;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eliasnogueira.utils.SelectDriver;
import com.eliasnogueira.utils.Utils;

import io.appium.java_client.AppiumDriver;

public class IdealTest {

	/*
	 * Um passo a mais deste teste seria estar na estrutura de Page Objects
	 */
	@Test
	public void teste() throws MalformedURLException {	
		
		// selecao generica do driver (ios ou android) baseado no arquivo de configuracao
		AppiumDriver<WebElement> driver = SelectDriver.set(Utils.loadProperties("platform"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
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
