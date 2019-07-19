package com.ctrip.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	// 设置、配置ExtentReports
	private static ExtentReports extent;
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extent.html", 
					true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ctrip\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}
}
