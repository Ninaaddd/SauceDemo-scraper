import java.io.IOException;

import org.openqa.selenium.WebDriver;

import loginClass.*;
import uiAutomation.*;

public class Main1 {
	public static void main(String[] args) throws InterruptedException, IOException {
		LoginClass login = new LoginClass();
		login.loginTest();
		
		WebDriver driver = login.getDriver();
		
		Scraping scrap = new Scraping(driver);
		scrap.getProductInfo();
	}
}
