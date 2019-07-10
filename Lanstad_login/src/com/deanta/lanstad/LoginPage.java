package com.deanta.lanstad;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class LoginPage {
	public static void main(String[] args)  {

		WebDriver d=null;
		String path=null;
		path=System.getProperty("user.dir");
		path=path+"/Source/chromedriver.exe";
		System.out.println(path);
		System.setProperty("webdriver.chrome.driver",path);
		d=new ChromeDriver();
		//d.manage().window().maximize();
		d.navigate().to("http://www.lanstad.com");
		int totalnorow=getnoofrow();
		for(int i=1;i<totalnorow;i++)
		{
			String useremail=LoginPage.getData(i,0);
			d.findElement(By.id("LoginForm_username")).sendKeys(useremail);
			String pwd=LoginPage.getData(i,1);
			d.findElement(By.id("LoginForm_password")).sendKeys(pwd);
			d.findElement(By.name("yt0")).click();
			String Actual=d.getCurrentUrl();
			String Excepted="http://lanstad.com/epublishing/?r=configuration/projects";
			if(Excepted.equalsIgnoreCase(Actual))
			{
				System.out.println("Test Case pass");
			}
			else
			{
				System.out.println("Fail");
				break;
			}
			d.findElement(By.className("logoutlink")).click();
			d.navigate().refresh();
		}
	}
	public static String getData(int r,int c)
	{
		String str=null;
		try{
			FileInputStream fis=new FileInputStream("E:\\selenium\\milestone\\Loginpage.xlsx");
			Workbook wb=WorkbookFactory.create(fis);
			str=wb.getSheet("sheet1").getRow(r).getCell(c).getStringCellValue();
		}
		catch(Exception e)
		{
			System.out.println("Error in getdata method/n");
		}
		return str;
	}

	public static int getnoofrow() 
	{
		try{
			FileInputStream fis=new FileInputStream("E:\\selenium\\milestone\\Loginpage.xlsx");
			Workbook wb=WorkbookFactory.create(fis);
			int n=wb.getSheet("sheet1").getLastRowNum();
		}
		catch(Exception e)
		{
			System.out.println("Row not found");
		}
		int n=0;
		return n+1;
	}
}
