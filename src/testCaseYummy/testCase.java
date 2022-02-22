package testCaseYummy;

import java.io.IOException;

import org.bouncycastle.util.Properties;
import org.testng.annotations.Test;

import automation.DataProvider.MyStoreCategoriesDropDownDataProvider;
import testCaseYummy.object.Payment;
import testCaseYummy.object.Products;
import testCaseYummy.utils.JacksonUtils;
import testCaseYummy.pages.CartPage;
import testCaseYummy.pages.HomePage;
import testCaseYummy.pages.OrderDetailPage;
import testCaseYummy.pages.PaymentPage;
import testCaseYummy.DataProvider.MyPaymentDataProvider;
import testCaseYummy.base.BaseTest;

public class testCase extends BaseTest {
	@Test(dataProvider = "getPaymentData", dataProviderClass = MyPaymentDataProvider.class)
	public void testCaseAddToCartAllitemParralel(Payment paymentData) throws IOException, InterruptedException {
		Products[] products = JacksonUtils.deserializeJson("foodProduct.json", Products[].class);
	    HomePage homePage = new HomePage(getDriver()).load();
	    homePage.addMultipleItem(products, homePage);
	    
	    homePage.clickViewCartLink();
	    String nama = homePage.loadUsername();
	    String phone = homePage.loadUserphone();
	    homePage.inputUsernameAndPhone(nama, phone);
	    
	    CartPage cartPage = new CartPage(getDriver());
	    cartPage.pageLoaded();
	    cartPage.assertMultipleItem(products, cartPage);
	    cartPage.assertMultipleItemAmount(products, cartPage);
	    cartPage.assertMultipleItemPrice(products, cartPage);
	    
	    String deliveryChoice = paymentData.getDeliveryChoice();
	    String email = homePage.loadEmail();
	    String room = homePage.loadRoomDetail();
	    cartPage.selectDeliveryChoice(deliveryChoice)
	    		.insertEmailAndRoomDetail(email, room, deliveryChoice);
	    
	    PaymentPage paymentPage = new PaymentPage(getDriver());
	    paymentPage.pageLoaded();
//	    String choice = homePage.loadPaymentMethod();
	    String choice = paymentData.getPayment();
	    paymentPage.assertTotalPrice(products, cartPage);
	    paymentPage.paymentChoice(choice,phone);
	    paymentPage.paymentPageLoaded();
	    paymentPage.phoneInputed()
	    		   .continuePayment()
	    	       .clickOrderDetail();
	   
	    OrderDetailPage orderDetailPage = new OrderDetailPage(getDriver());
	    orderDetailPage.pageLoaded();
	    
	    cartPage.assertMultipleItem(products, cartPage);

	}
}
