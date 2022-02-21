package testCaseYummy.base;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


import testCaseYummy.utils.ConfigLoader;
import io.qameta.allure.Step;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait waitLong;
	protected WebDriverWait waitShort;
	protected WebDriverWait waitMedium; 

	
	public  BasePage(WebDriver driver) {
		this.driver = driver;
		waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
		waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
		waitMedium = new WebDriverWait(driver, Duration.ofSeconds(10));
	}


	public void load(String endPoint) {
		
		try {
			driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String loadUsername() {
		String name = null;
		try {
			name = ConfigLoader.getInstance().getUsername();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
		
	}
	
	public String loadUserphone() {
		String phone = null;
		try {
			phone = ConfigLoader.getInstance().getUserPhone();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phone;
		
	}
	
	public String loadEmail() {
		String email = null;
		try {
			email = ConfigLoader.getInstance().getUserEmail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
		
	}
	public String loadRoomDetail() {
		String room = null;
		try {
			room = ConfigLoader.getInstance().getUserRoomDetail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
		
	}
	
	public String loadPaymentMethod() {
		String payment = null;
		try {
			payment = ConfigLoader.getInstance().getPaymentMethod();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payment;
		
	}
	
	
	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		if(overlays.size()>0) {
			waitLong.until(ExpectedConditions.invisibilityOfAllElements(overlays)
					);
			System.out.println("OVERLAYS INVISIBLE");
		} else {
			System.out.println("OVERLAY NOT FOUND");
		}
	}
	
	public WebElement waitForElementToBeVisible(By element) {
		return (WebElement) waitLong.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
	
	public WebElement waitForElementToBeClickable(By element) {
		return (WebElement) waitLong.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementChange(By element, String target) {
		waitLong.until(ExpectedConditions.textToBePresentInElementLocated(element, target));
	}
	

}
