package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBild data = new TestDataBild();
    JsonPath js;
    static String placeID;
    
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// post request with body getting the requestSpecification from utils file
		res = given().spec(requestSpecification()).body(data.addPlaceDataLoad(name, language, address));
	}

	@When("User call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String method) {

		// read API resource from Enum class
		APIResources resourceAPI = APIResources.valueOf(resource);
		String resourceType = resourceAPI.getResource();

		// Common response and getting the URI from Utils file
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (method.equalsIgnoreCase("post")) {
			response = res.when().post(resourceType).then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("get")) {
			response = res.when().get(resourceType).then().spec(resspec).extract().response();
		}
	}

	@Then("The API succeeded with status code {int}")
	public void the_api_succeeded_with_status_code(int int1) {
		assertEquals(response.getStatusCode(), int1);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		//use get json path from utils class
		assertEquals(getJsonPath(response,keyValue), expectedValue);
		
	}
	
	@Then("verify place_ID created maps to {string} using {string} with {string} http request")
	public void verify_place_id_created_maps_to_using_with_http_request(String excpectedName, String resource, String method) throws IOException {
		placeID=getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_call_with_post_http_request(resource,method);
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName, excpectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload_with() throws IOException {
	   res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
	}
}
