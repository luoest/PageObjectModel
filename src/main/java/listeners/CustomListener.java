package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.ctrip.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

import base.Page;

public class CustomListener extends Page implements ITestListener{

	public void onTestStart(ITestResult result) {
		test = rep.startTest("开始测试:" + result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS, "通过:" + result.getName().toUpperCase());
		
		rep.flush();
		rep.endTest(test);
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.getScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, "失败:" + result.getName().toUpperCase());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">执行异常截图</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		Reporter.log("<br>");
		
		rep.flush();
		rep.endTest(test);
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "忽略:" + result.getName().toUpperCase());
		rep.flush();
		rep.endTest(test);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
