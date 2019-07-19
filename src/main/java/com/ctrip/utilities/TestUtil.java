package com.ctrip.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.Page;

public class TestUtil extends Page{
	public static String screenshotName;

	// 解析Excel
	@DataProvider(name = "dp")
	public Object[][] getData(Method m){
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			table = new Hashtable<String, String>();
			for(int colNum=0; colNum<cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}
	// 截图至指定目录
	public static void getScreenshot() throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		
	}
	
	// 在excel的RUMMODE下设置是否执行，“Y”为执行
	public static boolean isTestRunnable(String testCase, ExcelReader excel) {
		String sheetName = "testsuite";
		int rows = excel.getRowCount(sheetName);
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			String testName = excel.getCellData(sheetName, "TCID", rowNum);
			if (testName.equalsIgnoreCase(testCase)) {
				String mode = excel.getCellData(sheetName, "RUMMODE", rowNum);
				if (mode.equalsIgnoreCase("Y"))
					return true;
				return false;
			}
		}
		return false;
	}
}






















