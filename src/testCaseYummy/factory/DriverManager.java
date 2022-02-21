package testCaseYummy.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	public WebDriver initializeDriver(String browser) {
		WebDriver driver;
		String localBrowser;
		localBrowser = System.getProperty("browser");
		localBrowser = browser;
		
		switch(DriverType.valueOf(localBrowser)) {
		case CHROME ->{
			WebDriverManager.chromedriver().setup(); 
			driver = new ChromeDriver();
		}
		default -> throw new IllegalStateException("Invalid Browser:" + localBrowser);
		}
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
}
