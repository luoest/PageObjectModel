package com.ctrip.pages;

import org.openqa.selenium.WebDriver;

import com.ctrip.actionpages.Travelling;

import base.Page;

public class TopMenuPage extends Page{

	WebDriver driver;
	public TopMenuPage(WebDriver driver) {
		this.driver = driver;
	}
	// topmenu中的“旅游”选项
	public Travelling gotoTravel() {
		click("home_CSS"); // 确认到首页
		click("travel_CSS"); // 点击主页TopMenu的“旅游”项目
		return new Travelling();
	}
}
