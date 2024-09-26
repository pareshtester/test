package TestCases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Reports.ITextent;





public class TagTesting  {
	 private static ExtentReports extent;
	    private static ExtentTest extenttest;
	WebDriver driver;
	String url ="https://www.fuchsiame.com/";
	//String testingType = "Google Tag Manager";
	String tagtext;
	//String GTMtext="GTM-KXJVNL5";
	//String Gtag="G-2Y8JNV0SZV";
	
	@BeforeSuite
    public void setUp() {
		deletefiles d = new deletefiles();
		d.delete();
		System.out.println("deleted");
		//Date date = new Date();
		 //String date1= date.toString().replace(" ", "-").replace(":", "-");
		CreateFile f = new CreateFile();
		File file = f.newF();
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report on Google tag presence");
		spark.config().setReportName("SEO Google tag report");
		spark.config().thumbnailForBase64();
        extent = new ExtentReports();
        extent.attachReporter(spark);
       
        System.out.println("??????????????created successfully"+file+".html");
    }
	
	 
    @Test(dataProvider="data", retryAnalyzer = ReRunAutomationScript2.class )
	public void tagtesting(String website,String tagtype, String GTMtext)

	{
    	  
    	extenttest = extent.createTest("Google tag implementation of"+website);
    	System.out.println("Website is:"+website);
    	System.out.println("tagtype is:"+tagtype);
    	System.out.println("Code is:"+GTMtext);
    	
		System.setProperty("webdriver.gecko.driver", "D:\\Automation\\IdeaSTUDIO\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("view-source:"+website);
		driver.manage().window().maximize();
		
		
		if(tagtype.equalsIgnoreCase("Google Tag Manager"))
		{

			//tagtext =driver.findElement(By.xpath("/html/body/pre/span[615]")).getText();
			
			tagtext = driver.findElement(By.xpath("//*[contains(text()," +GTMtext  + ")]")).getText();
			System.out.print("testing");
		//	tagtext = driver.findElement(By.xpath("/html/body/table/tbody/tr[532]/td[2]")).getText();
			System.out.println("this is text"+tagtext);
			
			assertEquals(true, tagtext.contains(GTMtext));
		}else if (tagtype.equalsIgnoreCase("google tag"))
		{
			System.out.print("testing1");
			tagtext = driver.findElement(By.xpath("//*[contains(text()," + GTMtext  + ")]")).getText();
			//System.out.println("this is GTAG text"+tagtext);
			assertEquals(true, tagtext.contains(GTMtext));
		}
		
		//extenttest.log(, "Testing the implementation of google tags");
		//extenttest.log(Status., null)
		
		//driver.close();
//		Actions action = new Actions(driver);
//		action.keyDown(Keys.CONTROL).sendKeys("u").build().perform();
		//img[@src='/img/logo/new_logo.jpg']
		//html/body/pre/span[615]
		driver.quit();
	}
    
    
    @AfterMethod
    public void reportvalidation(ITestResult result)
    {
    	if(result.getStatus()==ITestResult.FAILURE)
    	{
    		extenttest.log(Status.FAIL, result.getThrowable());
    	}
    	else if(result.getStatus()==ITestResult.SKIP)
    	{
    		extenttest.log(Status.SKIP, result.getThrowable());
    		
    	}else
    	{
    		extenttest.log(Status.PASS, "Test passed");
    	}
    	extent.flush();
    	//driver.quit();
    }
    
    
    @DataProvider(name="data")
	   public String[][] Authentication() throws Exception{
		  // CaptureExcelData ed = new CaptureExcelData();
		   ExcelData d = new ExcelData();
		   
			
			String[][] data_login = null;
			
			
			data_login = d.readData();
			//data_login=ed.readfile(System.getProperty("user.dir") +"/Excel", "ZentoDataProvider.xls", "Login");
			return data_login;
			
		 
				}
    
    @AfterTest
    public void close()
    {
    	driver.close();
    }
    
}


