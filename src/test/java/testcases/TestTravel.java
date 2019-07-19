package testcases;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.ctrip.pages.TopMenuPage;
import com.ctrip.utilities.TestUtil;

import base.Page;

public class TestTravel extends Page{

	@Test
	public void testTravel() throws InterruptedException {
		
		// 根据Excel中的设置，判断是否执行本用例
		if (!TestUtil.isTestRunnable("TestTravel", excel)) {
			throw new SkipException("不执行本用例：" + "TestTravel".toUpperCase());
		}
		
		String infoStart = "进入TestTravel测试";
		System.out.println(infoStart);
		Reporter.log(infoStart);
		log.debug(infoStart);
		
		TopMenuPage menu = new TopMenuPage(driver);
		menu.gotoTravel().themeTraveling();
		TimeUnit.SECONDS.sleep(1);
		switchHandles(1);
		TimeUnit.SECONDS.sleep(1);
		pageTitleContains("主题旅游");
		
		String infoEnd = "TestTravel内部测试完成。";
		System.out.println(infoEnd);
		Reporter.log(infoEnd);
		log.debug(infoEnd);
		Assert.assertFalse(false);
	}
}
