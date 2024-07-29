package com.training.Base;

import org.testng.annotations.BeforeClass;
import com.training.Utilities.Constants;
import com.training.Utilities.GitHubAPIDetails;


import io.restassured.RestAssured;

public class BaseTest {
	@BeforeClass
	public void setUp() {
		String uri=GitHubAPIDetails.readDataFromFile(Constants.GitHub_Credentails, "baseurl");
		RestAssured.baseURI=uri;
		
	//	TestDataDetails.readDataFromFile(Constants.TestData_Details, null);
	}

}
