package UtilityClasses;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
public class ReadConfigFile {
	Properties property;
	
	public ReadConfigFile() {
		File src = new File("./Configuration/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			property = new Properties();
			property.load(fis);
		}catch(Exception e) {
			System.out.println("Exception is: " + e.getMessage());
		}
	}
	
	public String getUsername() {
		return property.getProperty("username").trim();
	}
	
	public String getPassword() {
		return property.getProperty("password").trim();
	}
	
	public String getPath() {
		return property.getProperty("path").trim();
	}
}
