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
								           
	private String amountPath= "//div[@class='css-901oao r-1l7q1w7 r-fppytw r-1b43r93 r-uiaua r-rjixqe r-p1pxzi r-11wrixw r-61z16t r-1mnahxq r-g18oep r-gy4na3 r-9aemit r-fdjqy7 r-5ywpl4 r-13wfysu r-1a2p6p6 r-ll0aj r-3twk1y']";
	private String amountInWeb;
	
	
	private String pricePath="//div[@class='css-901oao r-70qq1b r-1smb3hh r-n6v787 r-uiaua r-14yzgew r-p1pxzi r-11wrixw r-61z16t r-1mnahxq r-g18oep r-gy4na3 r-9aemit r-fdjqy7 r-154t0cg r-13wfysu r-1a2p6p6 r-ll0aj r-3twk1y']";
	private String priceInWeb;
	
	private String firstPath = "//div[normalize-space()='";
	private String lastPath = "']";
	private String name;
	
	private String totalPriceString;
	private double totalPrice;
	private double pricePerItemInWeb;
	private double itemPrice;
	
	
	@Step
	public Boolean pageLoaded() {
		return waitLong.until(ExpectedConditions.urlContains("/checkout"));
	}
	
	@Step
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
	

	@Step
	public String getProductAmount(int loadedAmount) {
		
		amountInWeb = "(" + amountPath + ")[" + Integer.toString(loadedAmount) + "]" ;
		By productAmount = By.xpath(amountInWeb);
		return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productAmount)).getText();

	}
	
	@Step
	public void assertMultipleItemAmount(Products[] products, CartPage cartPage) {
		for (int i = 0; i < products.length; i++) {
			AssertJUnit.assertEquals(cartPage.getProductAmount(i+1),(products[i].getFoodAmount()+ "x"));
		}
	}
	

	@Step
	public String getProductPrice(int loadedAmount) {
		
		priceInWeb = "(" + pricePath + ")[" + Integer.toString(loadedAmount) + "]" ;
		By productPrice = By.xpath(priceInWeb);
		return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText();

	}
	
	@Step
	public void assertMultipleItemPrice(Products[] products, CartPage cartPage) {
		for (int i = 0; i < products.length; i++) {
		
			totalPriceString = cartPage.getProductPrice(i+1);
			totalPriceString = totalPriceString.replace("Rp", "");
			totalPrice = Double.parseDouble(totalPriceString);
			
			if(products[i].getFoodAmount()==1) {
				pricePerItemInWeb = totalPrice;
			}else {
				pricePerItemInWeb = totalPrice / (products[i].getFoodAmount());
			}
			
			if(pricePerItemInWeb > 10) {
				pricePerItemInWeb = pricePerItemInWeb * 1000;
				// mengubah dari double menjadi rupiah
			}
			
			itemPrice = products[i].getFoodPrice();
			AssertJUnit.assertEquals(pricePerItemInWeb,itemPrice);
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
	
	@Step
	public CartPage insertEmailAndRoomDetail(String email, String roomDetail, String choice) {
		
		driver.findElement(emailFld).sendKeys(email);
		if(choice.equalsIgnoreCase("Delivery")) {
			driver.findElement(roomDetailFld).sendKeys(roomDetail);
		}
		driver.findElement(nextBtn).click();
		return this;
	}
}
