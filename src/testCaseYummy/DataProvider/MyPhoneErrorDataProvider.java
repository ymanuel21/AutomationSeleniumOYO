package testCaseYummy.DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import automation.utils.JacksonUtils;
import testCaseYummy.object.ErrorPhone;


public class MyPhoneErrorDataProvider {
	@DataProvider(name = "getPhoneErrorData", parallel = true)
	public ErrorPhone[] getPhoneErrorData() throws IOException {
		return JacksonUtils.deserializeJson("myPhoneError.json", ErrorPhone[].class);
	}
}
