package testCaseYummy.base;

import java.util.List;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import automation.factory.DriverManager;
import automation.utils.CookiesUtils;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;

import org.testng.annotations.AfterMethod;

public class BaseTest {
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
		}
	
	public WebDriver getDriver() {
		return this.driver.get();
	}
	
	@Parameters("browser")
	@BeforeMethod
//	public void startDriver(String browser) {
//		driver = new DriverManager().initializeDriver(browser);
//		
//	}

	public void startDriver(String browser) {
		browser = "CHROME";
		setDriver(new DriverManager().initializeDriver(browser));
		System.out.println("Current Thread: " + Thread.currentThread().getId());
	}

	@AfterMethod
	public void quitDriver() {
		getDriver().quit();
	}

	public void injectCookiesToBrowser(Cookies cookies) {
		List<Cookie> seleniumCookies = new CookiesUtils().convertRestAssuredCookiesToSelenium(cookies);
		for(Cookie cookie: seleniumCookies ) {
			getDriver().manage().addCookie(cookie);
		}
	
	}
	
}
