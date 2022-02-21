package testCaseYummy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import io.qameta.allure.Step;
import testCaseYummy.base.BasePage;
import testCaseYummy.object.Products;

public class CartPage extends BasePage{
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private By emailFld = By.xpath("//input[@placeholder='Enter your email address']");
	private By roomDetailFld = By.xpath("//input[@placeholder='Enter Room Details']");
	private By nextBtn = By.xpath("//button[@type='submit']");
			
	private By deliveryBtn = By.xpath("//div[contains(text(),'Delivery')]");
	private By pickupBtn = By.xpath("//div[contains(text(),'Pickup')]");
	
	private String firstPath = "//div[normalize-space()='";
	private String lastPath = "']";
	private String name;
	
	@Step
	public Boolean pageLoaded() {
		return waitLong.until(ExpectedConditions.urlContains("/checkout"));
	}
	
	
	public String getProductName(String loadedName) {
		
		name = firstPath + loadedName + lastPath;	
		By productNames = By.xpath(name);
		return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productNames)).getText();

	}
	
	@Step
	public void assertMultipleItem(Products[] products, CartPage cartPage) {
		for (int i = 0; i < products.length; i++) {
			AssertJUnit.assertEquals(cartPage.getProductName(products[i].getXpathFoodName()),products[i].getFoodName());
			
		}
	}
	
	public CartPage selectDeliveryChoice (String choice) {
		if(choice.equalsIgnoreCase("Delivery")) {
			driver.findElement(deliveryBtn).click();
		} else if(choice.equalsIgnoreCase("Pickup")) {
			driver.findElement(pickupBtn).click();
		}
		return this;
	}
	public CartPage insertEmailAndRoomDetail(String email, String roomDetail, String choice) {
		
		driver.findElement(emailFld).sendKeys(email);
		if(choice.equalsIgnoreCase("Delivery")) {
			driver.findElement(roomDetailFld).sendKeys(roomDetail);
		}
		driver.findElement(nextBtn).click();
		return this;
	}
}
