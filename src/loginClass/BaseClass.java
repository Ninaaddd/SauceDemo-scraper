package loginClass;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
	public static WebDriver driver;
	int commonWaitForAllElements = 60;
	
	public void window() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--force-device-scale-factor=0.8");
		options.setExperimentalOption("prefs", Map.of(
		        "credentials_enable_service", false,
		        "profile.password_manager_enabled", false
		    ));
		options.addArguments("--incognito");
		options.addArguments("--no-default-browser-check");
	    options.addArguments("--disable-infobars");
	    options.addArguments("--disable-notifications");
	    options.addArguments("--disable-popup-blocking");
		
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(commonWaitForAllElements));
		driver.manage().window().maximize();
		
		
		
	}
}
