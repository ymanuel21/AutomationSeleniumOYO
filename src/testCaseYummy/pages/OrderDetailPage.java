package testCaseYummy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import io.qameta.allure.Step;
import testCaseYummy.base.BasePage;
import testCaseYummy.object.Products;

public class OrderDetailPage extends BasePage{
	
	public OrderDetailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@Step
	public Boolean pageLoaded() {
		return waitLong.until(ExpectedConditions.urlContains("/payment-see-detail"));
	}

}
