package org.test;

public class Amazon extends BaseClass {
	public static void main(String[] args) {
		launchBrowser("Chrome");
		launchUrl("https://www.amazon.in/");
		FirstPage a=new FirstPage();
		enter(a.getSearchbox(),"iphone mobiles");
	}

}
