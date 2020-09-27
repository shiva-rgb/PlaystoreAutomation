package com.testingplaystore.testCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
	
	public static void main(String[] args) throws ParseException {  
		
	      float score;
	      int j;
	      
	    // Setting System Property for Chrome Driver
	    WebDriverManager.chromedriver().setup();
	      
	    // Instantiate a ChromeDriver class.     
		WebDriver driver=new ChromeDriver(); 
	      
	    for(j = 1; j<=3;j++)
      {  
	     
      // Launch Website  
      driver.navigate().to("https://play.google.com/store/apps/top"); 
   
      //locating the 1st 3 app on top charts  
      String xpaths = "//*[@id=\"fcxH9b\"]/div[4]/c-wiz/div/c-wiz/div/div/c-wiz/c-wiz[1]/c-wiz/div/div[2]/div["+j+"]";
      driver.findElement(By.xpath(xpaths)).click();
      
      // Providing a wait statement to load the elements
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   
      //Locating the app name
      String appName = driver.findElement(By.className("AHFaub")).getText();
      System.out.println("Name of Application: "+appName);
   
      //Locating the app reviews counts
      String reviewTotal = driver.findElement(By.xpath("//*[@id=\"fcxH9b\"]/div[4]/c-wiz[2]/div/div[2]/" + "div/div/main/div/div[1]/c-wiz/div[1]/span/span[2]")).getText().trim();
   
      String updated = (reviewTotal).replaceAll(",","");
      
      //Converting the fetched counts to int.
      int reviewCount=Integer.parseInt(updated); 
      System.out.println("Number of Reviews of the application: "+reviewCount);
   
      // Getting the app last updated date
      String updatedDate = driver.findElement(By.className("htlgb")).getText();
              
      //Converting the date format
      SimpleDateFormat formatter1=new SimpleDateFormat("MMM dd, yyyy");
      Date date1=formatter1.parse(updatedDate);
      System.out.println("Last Updated date: "+date1);
      
      //Getting system local date
      SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy");  
      Date date = new Date();  
      System.out.println("Current date: "+formatter.format(date));
   
      //Getting date difference between the last updated date and current date
      long diff = date.getTime() - date1.getTime();
      int lastUpdate = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between apps last updated date and current date: " + lastUpdate);
		
		//Calculating the score = 
		score = reviewCount/(lastUpdate);
		System.out.println("Application score: "+score);
		System.out.println("-----------------------------------");
		
      }
	    //Closing browser
	  	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  	driver.quit();
	}
}
