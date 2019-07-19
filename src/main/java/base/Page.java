package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

import com.ctrip.utilities.ExcelReader;
import com.ctrip.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties(); 
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") +
										"\\src\\test\\resources\\com\\ctrip\\excel\\testdata.xlsx");
	public static Logger log = Logger.getLogger("ctrip");
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	public Page() {
		
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\properties\\log4j.properties");
		
		File fileDir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\properties");
		File configFile = new File(fileDir, "config.properties");
		File oRFile = new File(fileDir, "OR.properties");
		if (driver==null) {
			try {
				fis = new FileInputStream(configFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(oRFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// jenkins参数化构建浏览器环境变量
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			}else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			}else if (config.getProperty("browser").equals("edge")) {
				System.setProperty("webdriver.edge.driver", 
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\executables\\MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.get(config.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")), TimeUnit.SECONDS);
			String info = "获取config和OR文档,打开浏览器: " + config.getProperty("browser") + ", 最大化窗口, 最长等待时间(秒):" + config.getProperty("implicitlyWait");
			System.out.println(info);
			Reporter.log(info);
			log.debug(info);
		
		}
	}
	// 清空页面元素
	public void clear(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
		}
		String info = "清空页面元素:" + locator;
		System.out.println(info);
		Reporter.log(info);
		log.debug(info);
		test.log(LogStatus.INFO, info);
	}
	// 点击页面元素
	public void click(String locator) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		String info = "点击页面元素:" + locator;
		System.out.println(info);
		Reporter.log(info);
		log.debug(info);
		test.log(LogStatus.INFO, info);
		
	}
	// 向目标元素发送信息
	public void sendKeys(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
		String info = "发送信息:" + value + ", 到页面元素:" + locator;
		System.out.println(info);
		Reporter.log(info);
		log.debug(info);
		test.log(LogStatus.INFO, info);
		
	}
	// 定义判断页面标题中是否含有特定元素的方法
	public static void pageTitleContains(String expected) {
		String actual = driver.getTitle();
		try {
			boolean result = actual.contains(expected);
			String info = "页面标题是否含有元素->" + expected + ": " + result;
			System.out.println(info);
			Reporter.log(info);
			log.debug(info);
			Assert.assertTrue(result);
		} catch (AssertionError e) {
			e.printStackTrace();
		}
	}
	// 跳转页面时切换到目标页面。
	public static void switchHandles(int num) {
		int handleNum = driver.getWindowHandles().size();
		if (handleNum>1) { // 确认有两个及以上页面时
			if (num<handleNum) {  // 下标从0开始，避免越界
				String[] handles = new String[handleNum];
				driver.getWindowHandles().toArray(handles);
				driver.switchTo().window(handles[num]);
			}
		}
	}
	// 执行结束后操作
	@AfterSuite
	public void tearDown() {
		if (driver!=null) {
			driver.quit();
		}
	}
}























