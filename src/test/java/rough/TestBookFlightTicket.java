package rough;

import com.ctrip.pages.HomePage;

import base.Page;

public class TestBookFlightTicket extends Page{

	// 测试基本功能
	public static void main(String[] args) throws InterruptedException {
		HomePage home = new HomePage(driver);
		home.gotoFlightTicket().bookFlightTicket("北京", "上海", "2019-07-29", "2019-07-30");
		pageTitleContains("北京到上海");
		pageTitleContains("北京到广州");
		driver.quit();
		
	}
}
