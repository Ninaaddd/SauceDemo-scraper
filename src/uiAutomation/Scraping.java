package uiAutomation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClasses.ReadConfigFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class Scraping {
	WebDriver driver;
	
	List<String> names = new ArrayList<>();
	List<String> descriptions = new ArrayList<>();
	List<String> prices = new ArrayList<>();
	
	@FindBy(xpath="//span[@class='title']")
	WebElement pageHeader;
	@FindAll({
		@FindBy(xpath="//div[@class='inventory_item_name ']")
	})
	List<WebElement> products;
	@FindAll({
		@FindBy(xpath="//div[@class='inventory_item_name ']/parent::a")
	})
	List<WebElement> productLinks;
	@FindBy(xpath="//div[@class='inventory_details_name large_size']")
	WebElement productName;
	@FindBy(xpath="//div[@class='inventory_details_desc large_size']")
	WebElement productDescription;
	@FindBy(xpath="//div[@class='inventory_details_price']")
	WebElement productPrice;
	
	public Scraping(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getProductInfo() throws IOException {
		try {
			for(int i=0; i<products.size();i++) {
				WebElement product = products.get(i);
				WebElement link = productLinks.get(i);
			
				String name = product.getText();
				System.out.println("Product "+ (i + 1) + ":" + name);
			
				link.click();
			
				names.add(productName.getText());
				System.out.println("Product name: " + productName.getText());
				descriptions.add(productDescription.getText());
				System.out.println("Product description: " + productDescription.getText());
				prices.add(productPrice.getText());
				System.out.println("Product price: " + productPrice.getText());
				System.out.println("");
				
				driver.navigate().back();
				
				products = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
			    productLinks = driver.findElements(By.xpath("//div[@class='inventory_item_name ']/parent::a"));
			    
			}
		}catch(Exception e) {
			System.out.println("Exception is: " + e.getMessage());
		}
		
		readWriteScrapData(names,descriptions,prices);
	}
	
	public void readWriteScrapData(List<String> names, List<String> descriptions, List<String> prices) throws IOException {
		ReadConfigFile config = new ReadConfigFile();
		FileInputStream fis = new FileInputStream(config.getPath());
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		
		Row headerRow = sheet.getRow(0);
		
		int itemNoCol = -1, productNameCol = -1, productDescCol = -1, productPriceCol = -1;
		for(Cell cell : headerRow) {
			switch(cell.getStringCellValue().trim()) {
			case "Item no.":
				itemNoCol = cell.getColumnIndex();
				break;
			case "Product Name":
				productNameCol = cell.getColumnIndex();
				break;
			case "Product Description":
				productDescCol = cell.getColumnIndex();
				break;
			case "Product Price":
				productPriceCol = cell.getColumnIndex();
				break;
			}
		}
		
		if (itemNoCol == -1 || productNameCol == -1 || productDescCol == -1 || productPriceCol == -1) {
	        workbook.close();
	        throw new RuntimeException("One or more required columns were not found.");
	    }
		
		for (int i = 0; i < names.size(); i++) {
	        Row row = sheet.createRow(i + 1);

	        row.createCell(itemNoCol).setCellValue(i + 1);
	        row.createCell(productNameCol).setCellValue(names.get(i));
	        row.createCell(productDescCol).setCellValue(descriptions.get(i));
	        row.createCell(productPriceCol).setCellValue(prices.get(i));
	    }

	    // Write to file
	    FileOutputStream fos = new FileOutputStream(config.getPath());
	    workbook.write(fos);
	    fos.close();
	    workbook.close();

	    System.out.println("✅ Excel updated with scraped data.");
	}
		
}

