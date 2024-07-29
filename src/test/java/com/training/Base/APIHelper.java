package com.training.Base;
import java.util.HashMap;

import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.RequestPOJO.CreateNewRepoRequest;
import com.training.RequestPOJO.GetRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class APIHelper {
	RequestSpecification resp;

	public Response getSingleRepository(String owner, String repo, String bearerToken) {
		resp= RestAssured.given();
		resp.header("Authorization", "Bearer"+bearerToken,"Content-Type", "application/json");
		resp.pathParam("owner", owner);
		resp.pathParam("repo", repo);
		Response response=null;
		try {
			response=resp.when().get("repos/{owner}/{repo}");
			response.then().statusCode(200);
			response.then().log().body();
			response.then().log().headers();
		}
		catch(Exception e) {
			Assert.fail("Get data is failing due to :: " + e.getMessage());
		}
		return response;
	}
	
	public Response getNotExistingRepo(String owner, String repo, String bearerToken) {
		resp= RestAssured.given();
		resp.header("Authorization", "Bearer"+bearerToken,"Content-Type", "application/json");
		resp.pathParam("owner", owner);
		resp.pathParam("repo", repo);
		Response response=null;
		try {
			response=resp.when().get("repos/{owner}/{repo}");
			response.then().statusCode(404);
			response.then().log().body();
		}
		catch(Exception e) {
			Assert.fail("Get data is failing due to :: " + e.getMessage());
		}
		return response;
	}
	
	public Response getAllRepository(String owner, String bearerToken) {
		resp= RestAssured.given();
		resp.header("Authorization", "Bearer"+bearerToken,"Content-Type", "application/json");
		resp.pathParam("owner", owner);
		Response response=null;
		try {
			response=resp.when().get("users/{owner}/repos");
			response.then().log().all();
		}
		catch(Exception e) {
			Assert.fail("Get data is failing due to :: " + e.getMessage());
		}
		return response;
	}
	
	public Response createNewRepository(String bearerToken, String name, String description, String homepage, String priv) {
		resp= RestAssured.given();
		resp.header("Authorization", "Bearer"+bearerToken,"Content-Type", "application/json");
		HashMap<String,String> data=new HashMap<String,String>();
		data.put("", name);
		data.put("description", description);
		data.put("homepage", homepage);
		data.put("private", priv);
		
		Response response=null;
		try {
			resp.body(new ObjectMapper().writeValueAsString(data));
			response=resp.when().post("user/repos");
			response.then().log().all();
		}
		catch(Exception e) {
			Assert.fail("Get data is failing due to :: " + e.getMessage());
		}
		return response;
	}	

}
