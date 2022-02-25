package testCaseYummy;

import java.io.IOException;

import org.bouncycastle.util.Properties;
import org.testng.annotations.Test;

import automation.DataProvider.MyStoreCategoriesDropDownDataProvider;
import io.qameta.allure.Description;
import testCaseYummy.object.ErrorEmail;
import testCaseYummy.object.ErrorPhone;
import testCaseYummy.object.Payment;
import testCaseYummy.object.Products;
import testCaseYummy.utils.JacksonUtils;
import testCaseYummy.pages.CartPage;
import testCaseYummy.pages.HomePage;
import testCaseYummy.pages.OrderDetailPage;
import testCaseYummy.pages.PaymentPage;
import testCaseYummy.DataProvider.MyEmailErrorDataProvider;
import testCaseYummy.DataProvider.MyPaymentDataProvider;
import testCaseYummy.DataProvider.MyPhoneErrorDataProvider;
import testCaseYummy.base.BaseTest;

public class testCase extends BaseTest {
	@Test(dataProvider = "getPaymentData", dataProviderClass = MyPaymentDataProvider.class)
	public void testCaseAddToCartAllitemParallel(Payment paymentData) throws IOException, InterruptedException {
		Products[] products = JacksonUtils.deserializeJson("foodProduct.json", Products[].class);
	    HomePage homePage = new HomePage(getDriver()).load();
	    
	    String nama 			= homePage.loadUsername();
	    String phone			= homePage.loadUserphone();
	    String deliveryChoice 	= paymentData.getDeliveryChoice();
	    String email 			= homePage.loadEmail();
	    String room 			= homePage.loadRoomDetail();
//	    String choice 			= homePage.loadPaymentMethod();
	    String choice 			= paymentData.getPayment();
	    
	    homePage.addMultipleItem(products, homePage)
	    		.clickViewCartLink()
	    		.inputUsernameAndPhone(nama, phone)
	    		.clickProcessToCheckoutBtn();
	    
	    CartPage cartPage = new CartPage(getDriver());
	    cartPage.pageLoaded();
	    
	    cartPage.assertMultipleItem(products, cartPage)
	    		.assertMultipleItemAmount(products, cartPage)
	    		.assertMultipleItemPrice(products, cartPage);
	    

	    cartPage.selectDeliveryChoice(deliveryChoice)
	    		.insertEmailAndRoomDetail(email, room, deliveryChoice)
	    		.clickNextBtn();
	    
	    PaymentPage paymentPage = new PaymentPage(getDriver());
	    paymentPage.pageLoaded();
	    paymentPage.assertTotalPrice(products, cartPage)
	    		   .paymentChoice(choice,phone);
	    paymentPage.paymentPageLoaded();
	    paymentPage.phoneInputed()
	    		   .continuePayment()
	    	       .clickOrderDetail();
	   
	    OrderDetailPage orderDetailPage = new OrderDetailPage(getDriver());
	    orderDetailPage.pageLoaded();
	    
	    cartPage.assertMultipleItem(products, cartPage);

	}
	
//	@Description("Error Pada Nomor WA")
//	@Test(dataProvider = "getPhoneErrorData", dataProviderClass = MyPhoneErrorDataProvider.class)
//	public void testCaseErrorPhoneValidation(ErrorPhone errorPhone) throws IOException, InterruptedException {
//		Products[] products = JacksonUtils.deserializeJson("foodProductOneItem.json", Products[].class);
//	    HomePage homePage = new HomePage(getDriver()).load();
//	    
//	    String nama 			= homePage.loadUsername();
//	    String phone			= errorPhone.getErrorPhone();
//	    
//	    homePage.addMultipleItem(products, homePage)
//		.clickViewCartLink()
//		.inputUsernameAndPhone(nama, phone)
//		.clickUsernameAndPhoneFld()
//		.assertIfPhoneNotFilledOrError(phone);
//	    
//	}
	
//	@Description("Error Pada Nama")
//	@Test
//	public void testCaseErrorNameValidation() throws IOException, InterruptedException {
//		Products[] products = JacksonUtils.deserializeJson("foodProductOneItem.json", Products[].class);
//	    HomePage homePage = new HomePage(getDriver()).load();
//	    
//	    String nama 			= "";
//	    String phone			= homePage.loadUserphone();
//	    
//	    homePage.addMultipleItem(products, homePage)
//		.clickViewCartLink()
//		.inputUsernameAndPhone(nama, phone)
//		.clickUsernameAndPhoneFld()
//		.assertIfNameNotFilled();
//	    
//	}
	
	
//	@Description("Error Pada Email")
//	@Test(dataProvider = "getEmailErrorData", dataProviderClass = MyEmailErrorDataProvider.class)
//	public void testCaseErrorEmailValidation(ErrorEmail errorEmail) throws IOException, InterruptedException {
//		Products[] products = JacksonUtils.deserializeJson("foodProductOneItem.json", Products[].class);
//	    HomePage homePage = new HomePage(getDriver()).load();
//	    
//	    String nama 			= homePage.loadUsername();
//	    String phone			= homePage.loadUserphone();
//
//	    String deliveryChoice 	= "Delivery";
//	    String email 			= errorEmail.getErrorEmail();
//	    String room 			= homePage.loadRoomDetail();
//	    
//	    homePage.addMultipleItem(products, homePage)
//		.clickViewCartLink()
//		.inputUsernameAndPhone(nama, phone)
//		.clickUsernameAndPhoneFld()
//		.clickProcessToCheckoutBtn();
//	    
//	    CartPage cartPage = new CartPage(getDriver());
//	    cartPage.pageLoaded();
//	    cartPage.selectDeliveryChoice(deliveryChoice)
//		.insertEmailAndRoomDetail(email, room, deliveryChoice)
//		.assertEmailErrorOrEmpty(email);
//	}
	
}
