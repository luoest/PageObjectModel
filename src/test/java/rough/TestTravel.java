package rough;

import java.util.concurrent.TimeUnit;

import com.ctrip.pages.TopMenuPage;

import base.Page;

public class TestTravel extends Page{

	// 测试基本功能
	public static void main(String[] args) throws InterruptedException {
		TopMenuPage menu = new TopMenuPage(driver);
		menu.gotoTravel().themeTraveling();
		TimeUnit.SECONDS.sleep(1);
		switchHandles(1);
		TimeUnit.SECONDS.sleep(1);
		pageTitleContains("主题旅游");
		
		driver.quit();
	}
}
