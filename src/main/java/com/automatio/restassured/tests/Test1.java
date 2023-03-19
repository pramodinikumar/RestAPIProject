package com.automatio.restassured.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.restassured.requestPayload.RegisterStudentPojo;
import com.automation.restassured.utilities.ReadPropertiesData;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class Test1 extends BaseTest {
	
	@BeforeClass
	public void beforeClass() {
		System.out.print("BeforeClass");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.print("BeforTest");
	}
	
	//(priority=2)
	@Test
	public void getStudentsGTest() {
		request.basePath("students");
		response = request.get();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	//@Test(priority=1, dependsOnMethods = "getStudentsGTest")
	@Test
	public void postStudentRegistration() {
		request.basePath("student");
		request.contentType(ContentType.JSON);
		//PodamFactory podam = new PodamFactoryImpl();
		//RegisterStudentPojo obj = podam.manufacturePojo(RegisterStudentPojo.class);
		RegisterStudentPojo obj = new RegisterStudentPojo();
		obj.setEmail(ReadPropertiesData.readData("emailid"));
		obj.setName(ReadPropertiesData.readData("name"));
		obj.setPhone(ReadPropertiesData.readData("phonenum"));
		obj.setUserName(ReadPropertiesData.readData("username"));
		obj.setPassword(ReadPropertiesData.readData("password"));
		request.body(obj);
		Response response = request.post();
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
