package com.ctrip.actionpages;

import java.util.concurrent.TimeUnit;

import org.testng.Reporter;

import base.Page;

public class FlightTicket extends Page{

	// 查询ctrip机票的具体流程
	public void bookFlightTicket(String startCity, String toCicy, String fromDate, String returnDate) throws InterruptedException {
		
		click("double_CSS");
		TimeUnit.SECONDS.sleep(1);
		clear("startCity_CSS");
		sendKeys("startCity_CSS", startCity);
		clear("toCity_CSS");
		sendKeys("toCity_CSS", toCicy);
		TimeUnit.SECONDS.sleep(1);
		clear("fromDate_CSS");
		sendKeys("fromDate_CSS", fromDate);
		clear("returnDate_CSS");
		sendKeys("returnDate_CSS", returnDate);
		TimeUnit.SECONDS.sleep(1);
		click("hasChild_CSS");
		click("hasBaby_CSS");
		click("search_CSS");
	}
}
