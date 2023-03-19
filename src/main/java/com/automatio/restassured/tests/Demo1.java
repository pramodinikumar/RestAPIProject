package com.automatio.restassured.tests;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.restassured.requestPayload.RegisterStudentPojo;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



public class Demo1 {

	@Test
	public void test() {
		
		RequestSpecification request = RestAssured.with();
		request.baseUri("http://localhost:8080/api");
		request.basePath("/student");//endpoints or rest services
		request.contentType(ContentType.JSON);
		request.accept(ContentType.JSON);
		/*request.body("{\r\n"
				+ "    \"name\": \"test7377\",\r\n"
				+ "    \"email\": \"test12323kumar123@gmail.com\",\r\n"
				+ "    \"phone\": \"4546576543\",\r\n"
				+ "    \"userName\": \"gtr2es9869\",\r\n"
				+ "    \"password\": \"125556789\"\r\n"
				+ "}");*/
		//File file = new File(System.getProperty("user.dir")+"/payloads/creation.json");
		
		//request.body(file);
		
//		RegisterStudentPojo obj = new RegisterStudentPojo();
//		obj.setName("test745767");
//		obj.setEmail("test@Test.com");
//		obj.setPhone("9876543210");
//		obj.setUserName("test0007");
//		obj.setPassword("test007");
		PodamFactory podam = new PodamFactoryImpl();
		
		RegisterStudentPojo obj = podam.manufacturePojo(RegisterStudentPojo.class);
		request.body(obj);
		Response response = request.post();
		System.out.println(response.getStatusCode());
		Assert.assertTrue(response.getStatusCode()==201,"Student registration has been failed");
		System.out.println(response.getStatusLine());

	}
	
	@Test
	public void fileUploadTEst() {
		RequestSpecification request = RestAssured.with();
		request.baseUri("http://localhost:8080/api");
		request.basePath("/upload/45");//endpoints or rest services
		//request.contentType(ContentType.JSON);
		File file = new File("C:\\Users\\DELL\\Desktop\\CHETHAN SIGN.jpg");
		request.multiPart(file);
		Response res = request.post();
		System.out.println(res.getStatusCode());
	}
	
	@Test
	public void basicAuthTest() {
		
		RequestSpecification request = RestAssured.with();
		request.baseUri("https://the-internet.herokuapp.com/basic_auth");
		//request.auth().basic("admin", "admin");
		request.header("Authorization", "Basic YWRtaW46YWRtaW4=");
	
		Response res = request.get();
		//System.out.println(res.getStatusCode());
		//System.out.println(res.prettyPrint());
		//System.out.println(res.headers());
		RestAssured.given().config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Set-Cookie"))).log().headers();
		res.then().log().headers();
	}
	
	@Test
	public void APIKeyTest() {
		
		RequestSpecification request = RestAssured.with();
		request.baseUri("https://exchange-rates.abstractapi.com/v1/live/");
		request.queryParam("api_key","dcf7028d3b7a4687a77d7186aa9f2b58" );
		request.queryParam("base", "USD");
		request.queryParam("target", "EUR");
		//request.header("Authorization", "dcf7028d3b7a4687a77d7186aa9f2b58");
	
		Response res = request.get();
		System.out.println(res.getStatusCode());
		System.out.println(res.asPrettyString());
		
	}
	

	@Test
	public void userSignUp() {
		
		RequestSpecification request = RestAssured.with();
		request.baseUri("http://localhost:8080");
		request.basePath("/users/signup");
		request.contentType(ContentType.JSON);
		request.body("{\r\n"
				+ "  \"username\": \"test\",\r\n"
				+ "  \"email\": \"string@test.com\",\r\n"
				+ "  \"password\": \"12gdghafsh565\",\r\n"
				+ "  \"appUserRoles\": [\r\n"
				+ "    \"ROLE_CLIENT\"\r\n"
				+ "  ]\r\n"
				+ "}");
	
		Response res = request.post();
		System.out.println(res.getStatusCode());
		System.out.println(res.asPrettyString());
		
	}
	
	@Test
	public void BearerOROuth2Test() {
		
		RequestSpecification request1 = RestAssured.with();
		request1.baseUri("http://localhost:8080");
		request1.basePath("/users/signin");
		request1.formParam("username", "admin");
		request1.formParam("password", "admin");
		Response res1 = request1.post();
		String token = res1.getBody().asString();
		
		
		RequestSpecification request = RestAssured.with();
		request.baseUri("http://localhost:8080");
		request.basePath("/users/test");
		
		request.header("Authorization", "Beaer "+token);
	
		Response res = request.get();
		System.out.println(res.getStatusCode());
		System.out.println(res.asPrettyString());
		
	}

}
