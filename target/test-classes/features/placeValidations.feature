Feature: Validate Place APIs

Scenario: Verify that AddPlaceAPI adds the place correctly
   Given Add place payload with "<name>" "<language>" "<address>"
   When  User call "addPlaceAPI" with "post" http request
   Then  The API succeeded with status code 200
   And   "status" in response body is "OK"
   And   "scope" in response body is "APP"
   And   verify place_ID created maps to "<name>" using "getPlaceAPI" with "get" http request
Examples:
	|name      |language |address      |
	|testName  |English  |Test Address |
	|testName2 |English2 |Test Address2|