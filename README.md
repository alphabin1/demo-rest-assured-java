# api-rest-assured-sample

Used tools and frameworks
---------------------------------------
1. Rest-Assured
2. Maven repository 
3. TestNG

## How to create Rest API test framework using Rest Assured Java library

	• Maven Dependency to add,
	  <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
    <dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.0.0</version>
    <scope>test</scope>
    </dependency>
		
	• RestAssured.uri = "" // to specify the basic URL of the API

	• Basic building blocks of Rest Assured is - given(), when(), then()
	
	• Values that can be provided in the given block,
		    Response res = given().
                baseUri("https://reqres.in/api/users?page=2").
                contentType(ContentType.JSON).
                
	• To print the response body as string,
	            System.out.println(res.getBody().asString());

	• To specify particular header value when sending the request
		   Header header = new Header("key","value");
		   RequestSpecification requestSpecification = given().header(header);
				
	• To validate response content type,
		   response.then().contentType(ContentType.JSON);
	
	• To get log when response is being fetched,
	           response.then().log().all();
	
	• To validate a particular item from the response body and status code,
               Assert.assertEquals(res.statusCode(),200);
			
