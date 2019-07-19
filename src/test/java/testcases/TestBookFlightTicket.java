package testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.ctrip.pages.HomePage;
import com.ctrip.utilities.TestUtil;

import base.Page;

public class TestBookFlightTicket extends Page{

	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void testBookFlightTicket(Hashtable<String, String> data) throws InterruptedException {
		
		// 根据Excel中的设置，判断是否执行本用例
		if (!TestUtil.isTestRunnable("TestBookFlightTicket", excel)) {
			throw new SkipException("不执行本用例:" + "TestBookFlightTicket".toUpperCase());
		}
		
		// 根据Excel中的设置，判断是否执行本用例中的步骤
		if (!data.get("RUMMODE").equalsIgnoreCase("Y")) {
			throw new SkipException("不执行该步骤.");
		}
		
		String infoStart = "进入FlightTicket测试";
		System.out.println(infoStart);
		Reporter.log(infoStart);
		log.debug(infoStart);
		
		HomePage home = new HomePage(driver);
		home.gotoFlightTicket().bookFlightTicket(data.get("fromCity"), data.get("toCity"), data.get("fromDate"), data.get("returnDate"));
		TimeUnit.SECONDS.sleep(2);
		pageTitleContains(data.get("pagePartialTitle"));
//		pageTitleContains("北京到广州");
						
		String infoEnd = "FlightTicket内部测试完成。";
		System.out.println(infoEnd);
		Reporter.log(infoEnd);
		log.debug(infoEnd);
		
	}
}
