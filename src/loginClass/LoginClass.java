package loginClass;
import UtilityClasses.ReadConfigFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginClass extends BaseClass{
	
	@FindBy(xpath="//div[@class='login_logo']")
	WebElement title;
	@FindBy(xpath="//input[@id='user-name']")
	WebElement username;
	@FindBy(xpath="//input[@id='password']")
	WebElement password;
	@FindBy(xpath="//input[@id='login-button']")
	WebElement loginBtn;
	
	public LoginClass() {
		window();
		PageFactory.initElements(driver, this);
	}
	
	public void loginTest() throws InterruptedException{
		ReadConfigFile config = new ReadConfigFile();
		driver.get("https://www.saucedemo.com/");
		System.out.println("Website: " + driver.getCurrentUrl());
		System.out.println("Website Name: " + title.getText());
		username.sendKeys(config.getUsername());
		password.sendKeys(config.getPassword());
		loginBtn.click();
		
		Thread.sleep(3000);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
}
