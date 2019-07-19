package com.ctrip.pages;

import org.openqa.selenium.WebDriver;

import com.ctrip.actionpages.FlightTicket;

import base.Page;

public class HomePage extends Page{

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	// 主页主窗口中的“机票”选项
	public FlightTicket gotoFlightTicket() {
		click("home_CSS"); // 确认到首页
		click("flightTicket_CSS");
		return new FlightTicket();
	}
}
