package TestCases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReRunAutomationScript2 implements IRetryAnalyzer {

	private int retrycount = 0;
	private static final int count = 7;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retrycount<count)
		{
			retrycount++;
			return true;
		}
		return false;
	}

}
