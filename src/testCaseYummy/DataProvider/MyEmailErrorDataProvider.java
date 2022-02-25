package testCaseYummy.DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import automation.utils.JacksonUtils;
import testCaseYummy.object.ErrorEmail;

public class MyEmailErrorDataProvider {
	@DataProvider(name = "getEmailErrorData", parallel = true)
	public ErrorEmail[] getEmailErrorData() throws IOException {
		return JacksonUtils.deserializeJson("myEmailError.json", ErrorEmail[].class);
	}
}

