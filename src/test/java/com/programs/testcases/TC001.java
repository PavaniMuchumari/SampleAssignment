package com.programs.testcases;

import org.testng.annotations.Test;
import com.programs.pageObjects.LoginPage;


public class TC001 {
	
	@Test
	public void test() throws Exception {
		
		LoginPage loginpage = new LoginPage();
		loginpage.launchChrome();
		loginpage.insertJSONDataandVerify();
		loginpage.closeChrome();
		
	}

}
