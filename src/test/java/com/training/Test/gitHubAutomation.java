package com.training.Test;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.Base.APIHelper;
import com.training.Base.BaseTest;
import com.training.RequestPOJO.CreateNewRepoRequest;
import com.training.ResponsePOJO.GetResponse;
import com.training.Utilities.Constants;
import com.training.Utilities.ExtentReportUtility;
import com.training.Utilities.GitHubAPIDetails;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class gitHubAutomation extends BaseTest{
	APIHelper apiHelper;
	String token=GitHubAPIDetails.readDataFromFile(Constants.GitHub_Credentails, "bearerToken");
	public static ExtentReportUtility extentReport=ExtentReportUtility.getInstance();
	String OWNER=GitHubAPIDetails.readDataFromFile(Constants.GitHub_Credentails, "owner");
	@BeforeClass
	public void beforeClass() {
	apiHelper=new APIHelper();
	}


	@Test
	public void getSingleRepo() {
		String expectedFullName="gowthamgowdagithub/getStartedWithJavaGowtham";
		Response SingleRepository=apiHelper.getSingleRepository(OWNER, "getstartedWithJavaGowtham",token);
		Assert.assertEquals(SingleRepository.getStatusCode(), HttpStatus.SC_OK, "error occured");
		System.out.println("Successfully single repo is printed with status code of 200");
		Assert.assertEquals(SingleRepository.contentType(), "application/json; charset=utf-8", "not equal");
		System.out.println("Content Type is same as we expected.");
		
		GetResponse getResponse=SingleRepository.as(GetResponse.class);
		String name=getResponse.full_name;
		System.out.println(name);
		Assert.assertEquals(getResponse.full_name, expectedFullName, "it is not equal");
		System.out.println("The fullName is same as we expected");
	}
	
	@Test
	public void getNonExistingRepo() {
		Response getRepo=apiHelper.getNotExistingRepo(OWNER, "getStartedWith", token);
	//	GetResponse getResponse=getRepo.as(GetResponse.class);
//		Assert.assertEquals(getResponse.status, 404, "it is not working properly");
//		Assert.assertEquals(getResponse.message, "Not Found", "the error message is not as we expected.");
		Assert.assertEquals(getRepo.getStatusCode(), HttpStatus.SC_NOT_FOUND, "error occured");
		System.out.println("Successfully single repo is printed with status code of 404");
	    String errorMessage=getRepo.body().jsonPath().get("message");
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Not Found", "not matching");
		System.out.println("Successfully received message as \"Not Found\"");
	}
	
	@Test
	public void getAllRepo() {
		Response getAllRepos=apiHelper.getAllRepository(OWNER, token);
		Assert.assertEquals(getAllRepos.getStatusCode(), HttpStatus.SC_OK, "error occured");
		System.out.println("Successfully single repo is printed with status code of 200");
		Assert.assertEquals(getAllRepos.contentType(), "application/json; charset=utf-8", "not equal");
		System.out.println("Content Type is same as we expected.");
		 List<GetResponse> allItems = getAllRepos.jsonPath().getList("",GetResponse.class);
	//	List<GetResponse>allItems=getAllRepos.as(new TypeRef<List<GetResponse>>() {});
		int totalRepo=allItems.size();
		System.out.println(totalRepo);
	}
	
	
	@Test
	public void createNewRepo() {
		Response creteNew=apiHelper.createNewRepository(token, "Hello-Java", "This is your firstrepo!", "https://github.com", "false");
		Assert.assertEquals(creteNew.getStatusCode(), HttpStatus.SC_CREATED, "error occured");
		System.out.println("Successfully single repo is printed with status code of 200");
	}
	
	
}
