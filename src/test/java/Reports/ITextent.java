package Reports;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public abstract class ITextent {

	 public static ExtentReports extent;
	    public static ExtentTest extenttest;
	    
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
	    }
}
