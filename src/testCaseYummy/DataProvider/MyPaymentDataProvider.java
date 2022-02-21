package testCaseYummy.DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import automation.utils.JacksonUtils;
import testCaseYummy.object.Payment;

public class MyPaymentDataProvider {
	@DataProvider(name = "getPaymentData", parallel = true)
	public Payment[] getPaymentData() throws IOException {
		return JacksonUtils.deserializeJson("myPayment.json", Payment[].class);
	}
}

