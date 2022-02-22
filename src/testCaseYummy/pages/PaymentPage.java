package testCaseYummy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import io.qameta.allure.Step;
import testCaseYummy.base.BasePage;
import testCaseYummy.object.Products;

public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private By paymentOVO = By.xpath("//div[contains(text(),'OVO')]");
	private By paymentGoPay = By.xpath("//div[contains(text(),'GoPay')]");
	private By paymentShopeePay = By.xpath("//div[contains(text(),'ShopeePay')]");
	
	private By chooseMethod = By.xpath("//div[contains(text(),'Choose Method')]");
	private By paymentOVOPhone = By.xpath("//input[@placeholder='Enter your phone number']");

//	private By continueToPay = By.xpath("//div[contains(text(),'Continue To Pay')]");
//  diatas bug. untuk GoPay harus 'Continue "To" Pay' dan OVO Harus 'Continue "to" Pay'
	
	private By continueToPay = By.xpath("//div[@class='css-901oao r-jwli3a r-fppytw r-1b43r93 r-vw2c0b r-rjixqe r-p1pxzi r-11wrixw r-61z16t r-1mnahxq r-g18oep r-gy4na3 r-9aemit r-q4m81j r-paz4er r-13wfysu r-1a2p6p6 r-ll0aj r-1ozqkpa']");
	
	private By orderDetailLink = By.xpath("//div[contains(text(),'Order Detail')]");
	private By TotalPaymentInChooseMethod = By.xpath("//div[@class='css-901oao r-aw03qq r-fppytw r-ubezar r-vw2c0b r-hbpseb r-p1pxzi r-11wrixw r-61z16t r-1mnahxq r-g18oep r-gy4na3 r-9aemit r-1ff274t r-cnds34 r-13wfysu r-1a2p6p6 r-ll0aj r-3twk1y']");
	
	@Step
	public Boolean pageLoaded() {
		return waitLong.until(ExpectedConditions.urlContains("/payment"));
	}

	@Step
	public Boolean paymentPageLoaded() {
		return waitLong.until(ExpectedConditions.urlContains("/payment-detail"));
	}
	
	@Step
	public PaymentPage paymentChoice(String PaymentChoice, String Phone) {
		if(PaymentChoice.equalsIgnoreCase("OVO")) {
			driver.findElement(paymentOVO).click();
			driver.findElement(chooseMethod).click();
			driver.findElement(paymentOVOPhone).sendKeys(Phone);
		} if(PaymentChoice.equalsIgnoreCase("GoPay")){
			driver.findElement(paymentGoPay).click();
			driver.findElement(chooseMethod).click();
		} return this;
//		else {
//			driver.findElement(paymentShopeePay).click();
//			driver.findElement(chooseMethod).click();
//		}
//		
	}
	
	@Step
	public PaymentPage continuePayment() {
			driver.findElement(continueToPay).click();
			return this;
	}
	
	@Step
	public PaymentPage clickOrderDetail() {
			driver.findElement(orderDetailLink).click();
			return this;
	}
	
	public PaymentPage phoneInputed() {
		waitLong.until(ExpectedConditions.elementToBeClickable(continueToPay));
		return this;
	}
	
	public double totalPaymentForUser(Products[] products, CartPage cartPage) {
		double totalPayment = 0;
		int totalOrder;
		for (int i = 0; i < products.length; i++) {
			totalOrder = products[i].getFoodAmount() * products[i].getFoodPrice();
			totalPayment = totalPayment + Double.valueOf(totalOrder);
		}return totalPayment;
	} 
	
	@Step
	public PaymentPage assertTotalPrice(Products[] products, CartPage cartPage) {
		String methodPrice = driver.findElement(TotalPaymentInChooseMethod).getText();
		methodPrice = methodPrice.replace(".","");
		methodPrice = methodPrice + ".0";
		AssertJUnit.assertEquals(methodPrice,("Rp"+totalPaymentForUser(products,cartPage)));
		return this;
	} 

}
