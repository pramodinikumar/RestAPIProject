package stepDefinitions;

import java.io.File;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDef {
	Response response;
	File file;
	RequestSpecification request;
	
	@Given("User should get {int} as response code")
	public void user_shouldgetResponse(int responseCode) {
	    Assert.assertEquals(response.getStatusCode(),responseCode, "User is not getting 200 as response code");
	}
	
	@Given("User hits {string} API {string}")
	public void user_hits_api(String httpMethod, String endPoint) {
	   request = RestAssured.with();
	   request.baseUri(endPoint);

	   if(httpMethod.equalsIgnoreCase("GET")) {
		   response = request.get();
	   }
	   else if(httpMethod.equalsIgnoreCase("POST")) {
		   request.contentType(ContentType.JSON);
		   request.body(file);
		   response = request.post();
	   }
	}
	
	@Given("User passes the payload {string}")
	public void user_passes_payload(String string) {
	   file  = new File(string);
	}

}
