package com.programs.pageObjects;

import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginPage {
	
	WebDriver driver;
	
	public String URL = "https://testpages.herokuapp.com/styled/tag/dynamic-table.html";
	public By TABLE_DATA = By.xpath("//summary[text()='Table Data']");
	public By INPUT_BOX = By.xpath("//textarea[@id='jsondata']");
	public By REFRESH_TABLE = By.xpath("//button[text()='Refresh Table']");
	public By TABLE_COUNT = By.xpath("//table[@id='dynamictable']//tr//td");
	

	public void launchChrome() {
		System.setProperty("weddriver.chromedriver.com", "./Drivers//chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
	}
	
	public void insertJSONDataandVerify() throws Exception {
		driver.get(URL);
		driver.findElement(TABLE_DATA).click();
		driver.findElement(INPUT_BOX).clear();
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(".\\src\\test\\java\\com\\programs\\testcases\\TestData.json");
		//Read JSON file 
		Object obj = jsonParser.parse(reader);	
		String data = obj.toString();
		System.out.println("Json Data="+data);	
		driver.findElement(INPUT_BOX).sendKeys(data);
		Thread.sleep(1000);
		driver.findElement(REFRESH_TABLE).click();	
		
		int count = driver.findElements(TABLE_COUNT).size();
		System.out.println("count="+count);
		for (int i=1;i<=count;i++)
		{
		 String actualData = driver.findElement(By.xpath("(//table[@id='dynamictable']//tr//td)["+i+"]")).getText();
		 System.out.println("ActualData="+actualData);
		
		 Assert.assertTrue(data.contains(actualData));
		}
	}
	
	public void closeChrome() {
		driver.close();
	}
}
