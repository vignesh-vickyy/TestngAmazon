package org.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstPage extends BaseClass {
	public FirstPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="twotabsearchtextbox")
	public static WebElement searchbox;
	@FindBy(id="nav-search-submit-button")
	public static WebElement searchbutton;
	public static WebElement getSearchbox() {
		return searchbox;
	}
	public static WebElement getSearchbutton() {
		return searchbutton;
	}
}
