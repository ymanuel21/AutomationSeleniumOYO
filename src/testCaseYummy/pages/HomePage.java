package testCaseYummy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import automation.Objects.Product;
import automation.pages.CartPage;
import testCaseYummy.base.BasePage;
import testCaseYummy.object.Products;
import io.qameta.allure.Step;

public class HomePage extends BasePage{
	
	private final By viewCartLink = By.xpath("//div[contains(text(),'View Cart')]");
	private final By title 		  = By.xpath("//div[contains(text(),'OYO')]");
	private final By plusItem 	  = By.xpath("//div[@class='ant-drawer ant-drawer-right ant-drawer-open']//div[3]//div[1]//*[name()='svg']//*[name()='path' and contains(@clip-rule,'evenodd')]");
	
	private final By usernameFld  = By.xpath("//input[@placeholder='Eg: Budi']");
	private final By userPhoneFld = By.xpath("//input[@placeholder='081234567890']");
	private final By checkoutBtn  = By.xpath("//button[@type='submit']");
	
	private final By numberToCart = By.xpath("//div[@class='css-901oao r-aw03qq r-1smb3hh r-ubezar r-uiaua r-hbpseb r-p1pxzi r-11wrixw r-61z16t r-1mnahxq r-g18oep r-gy4na3 r-9aemit r-fdjqy7 r-cnds34 r-13wfysu r-1a2p6p6 r-ll0aj r-3twk1y']");
	private final By addToCartBtn = By.xpath("//div[contains(text(),'Add To Cart')]");
	
	private final By note 		  = By.xpath("//textarea[@placeholder='Write additional notes here']");
	
	private String firstPath = "//*[contains(text(),'";
	private String lastPath  = "')]";
	private String name;
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@Step
	public HomePage load(){
		load("/");
		String titleText = driver.findElement(title).getText();
		AssertJUnit.assertEquals(titleText, "OYO");
		return this;
	}
	@Step
	public void clickProduct(String product) {
		
		By productName = By.xpath("//h3[normalize-space()='"+ product +"']");
		driver.findElement(productName).click();
	}

	@Step
	public void sendNotes(String notes) {
		driver.findElement(note).sendKeys(notes);
	}
	
	@Step
	public void clickAddToCartBtn(String product) {
		
		driver.findElement(numberToCart).getText();
		driver.findElement(addToCartBtn).click();
	}
	
	
	@Step
	public HomePage addMultipleItem(Products[] products, HomePage homePage) {
		for (int i = 0; i < products.length; i++) {
			homePage.clickProduct(products[i].getXpathFoodName());
			if(products[i].getFoodAmount()>1) {
			for (int x = 1; products[i].getFoodAmount() > x; x++) {
				driver.findElement(plusItem).click();
			}
			}
			homePage.sendNotes(products[i].getNotes());
			homePage.clickAddToCartBtn(products[i].getXpathFoodName());
			} return this;
		}
	
	@Step
	public HomePage clickViewCartLink() {
		driver.findElement(viewCartLink).click();
		return this;
	} 
	
	@Step
	public HomePage inputUsernameAndPhone(String Name, String Phone) {	
		driver.findElement(usernameFld).sendKeys(Name);
		driver.findElement(userPhoneFld).sendKeys(Phone);
		driver.findElement(checkoutBtn).click();
		return this;
	}
	
	public String getProductName(String loadedName) {
		
		name = firstPath + loadedName + lastPath;	
		By productNames = By.xpath(name);
		return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productNames)).getText();
//		return driver.findElement(productName).getText();	
	}
	
	@Step
	public void assertMultipleItem(Products[] products, CartPage cartPage) {
		for (int i = 0; i < products.length; i++) {
			AssertJUnit.assertEquals(cartPage.getProductName(products[i].getXpathFoodName()),products[i].getFoodName());
			
		}
	}
	
	}
	
	

