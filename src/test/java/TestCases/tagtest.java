package TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class tagtest {

	WebDriver driver;
	 private static ExtentReports extent;
	    private static ExtentTest extenttest;
	String tagtextarray[];
	Date date = new Date();
	String date1;
	@BeforeSuite
    public void setUp() {
		deletefiles d = new deletefiles();
		d.delete();
		//d.delete();
		System.out.println("deleted");
		
		 String date1= date.toString().replace(" ", "-").replace(":", "-");
		CreateFile f = new CreateFile();
		File file = f.newF();
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        System.out.println("??????????????created successfully"+file+".html");
        
       
    }
	
	
    @Test(dataProvider="data", retryAnalyzer = ReRunAutomationScript2.class )
	public void tagtesting(String website,String tagtype, String GTMtext) throws InterruptedException
	{
    	 try {
    	extenttest = extent.createTest("Google tag implementation of " +website);
    	//extenttest.fail("My ss", MediaEntityBuilder.createScreenCaptureFromPath("D:\\Automation\\GoogleTags\\Screenshot\\ss.png").build());
    	System.out.println("Website is:"+website);
    	System.out.println("tagtype is:"+tagtype);
    	System.out.println("Code is:"+GTMtext);
    	
		//System.setProperty("webdriver.edge.driver", "D:\\Automation\\edgedriver_win64\\msedgedriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\Automation\\IdeaSTUDIO\\geckodriver.exe");
		driver = new FirefoxDriver();
		// driver = new EdgeDriver();
		driver.get("https://tagassistant.google.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/await-bootstrap-ng2/ng-transclude/debug-home/div[2]/div[2]/button")).click();
		driver.findElement(By.id("domain-start-url")).sendKeys(website);   
		Thread.sleep(10000);
		driver.findElement(By.id("domain-start-button")).click();
		Thread.sleep(20000);
        Set<String> windowhandles =  driver.getWindowHandles();
        System.out.println(windowhandles);
        Iterator<String> iterator =windowhandles.iterator();
        String parentwindow=iterator.next();
        String childwindow  =iterator.next();
        System.out.println("child"+childwindow);
        driver.switchTo().window(childwindow);
        System.out.println("current"+driver.getWindowHandle());
        Thread.sleep(10000);

		//String text =driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div/div/div[2]/div/div/div/ul/li[1]/a")).getText();
		//System.out.println(text);
        Thread.sleep(10000);
   driver.switchTo().frame(driver.findElement(By.className("__TAG_ASSISTANT_BADGE")));
		driver.findElement(By.xpath("//button[contains(text(),' Finish ')]")).click();
        Thread.sleep(10000);
        driver.switchTo().window(parentwindow);

	String x =	driver.findElement(By.xpath("/html/body/div/div[2]/debugger/div[2]/div[1]/span[2]")).getText();
	System.out.println("text is"+x);
     driver.switchTo().window(parentwindow);
       // driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//button[contains(text(),' Continue ')]")).click();
		List<WebElement> we = driver.findElements(By.cssSelector(".container-picker__chip"));
	System.out.println("number is"+we.size());
	String tagtextarray[] = new String[we.size()];
	for(int i=1; i<=we.size(); i++)
	{
		String elementtagtext=driver.findElement(By.cssSelector("div.container-picker__chip:nth-child("+i+")")).getText();
		System.out.println("tag text is"+elementtagtext);
		
		tagtextarray[i-1] = elementtagtext;
		
	}
	System.out.println(tagtextarray.length);
//	for (int i =0; i < tagtextarray.length; i++)
//	{
//		System.out.println("i value is"+i);
//		
//		System.out.println(tagtextarray[i]);
//		if(tagtextarray[i].equalsIgnoreCase("G-5FG1J90486"))
//		{
//			System.out.println("pass");
//			assertTrue(true);
//			
//		}
//		
//		
//	}
	String newtext="";
	int i =0;
	while( i < tagtextarray.length){
		if(tagtextarray[i].equalsIgnoreCase(GTMtext))
		{
				System.out.println("pass");
				//assertTrue(true);
				
				newtext= tagtextarray[i];
				System.out.println(newtext);
				break;
			}  else {
				i++;
			}
		
	}
	assertEquals(newtext, GTMtext);
//		String selectedtag = driver.findElement(By.className("container-picker__chip container-picker__chip--selected")).getText();
//		System.out.println(selectedtag);
		
  driver.quit();
  
    	 } catch(Exception e)
    	 {
    		 ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
           //  String screenshotPath = screenshotUtil.captureScreenshot("D:\\Automation\\GoogleTags\\testWithFailureScreenshot.png");
    		 String screenshotPath =screenshotUtil.captureScreenshot(date1);
             extenttest.fail("Test Failed. Screenshot attached.",
                     MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
             driver.quit();
    	 }
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
