package com.testcases;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.RequestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HTTPRequestTest {

	private final String BASE_URL = "http://localhost:5000";

	private ExtentReports extent;
	private ExtentSparkReporter spark;
	private ExtentTest test;
	private Response response;

	@BeforeTest
	public void beforeTest() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("index.html");
		extent.attachReporter(spark);

		RestAssured.baseURI = BASE_URL;
	}

	@BeforeMethod
	public void setup(Method method) {
		test = extent.createTest(method.getName());
	}

	@Test(priority = 1)
	public void verifyPostRequest() {

		Map<String, String> body = RequestUtil.generateBody();

		response = given().contentType(ContentType.JSON).body(body).post("/users");

		int statusCode = response.getStatusCode();
		JsonPath json = response.getBody().jsonPath();

		test.info("Status code returned from API = " + statusCode);
		test.info(MarkupHelper.createCodeBlock(response.getBody().asPrettyString(), CodeLanguage.JSON));

		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(body.get("username"), json.getString("username"));
		Assert.assertEquals(body.get("email"), json.getString("email"));
		Assert.assertEquals(body.get("password"), json.getString("password")+"qq");
	}

	@Test(priority = 2)
	public void verifyGetByIDRequest() {
		// To-Do
	}

	@AfterMethod
	public void tear(Method method, ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(String.format("Test case [ %s ] PASSED", method.getName()));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(String.format("Test case [ %s ] FAILED", method.getName()));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip(String.format("Test case [ %s ] SKIPPED", method.getName()));
		}
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
	}

}
