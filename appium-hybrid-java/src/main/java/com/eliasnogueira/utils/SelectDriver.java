package com.eliasnogueira.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class SelectDriver {

	
	public static AppiumDriver<WebElement> set(String platform) {
		DesiredCapabilities dc = new DesiredCapabilities(); 
		dc.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		
		AppiumDriver<WebElement> driver = null;
		
		switch (platform.toUpperCase()) {
		case "ANDROID" :
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.javebratt.theonemaninventory");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "MainActivity");
			
			try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			break;

		case "IOS":
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5s");
			dc.setCapability(MobileCapabilityType.APP, "/Users/eliasnogueira/Documents/workspace/appium-hybrid-tdcpoa-2016/app/inventory.app");
			
			try {
				driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			break;
			
		default:
			break;
		}
		
		return driver;
	}
	
	
}
