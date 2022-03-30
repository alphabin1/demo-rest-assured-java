import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
public class requestRes {

    @Test
    public void listUsers(){
        Response res = given().
        baseUri("https://reqres.in/api/users?page=2").
        contentType(ContentType.JSON)
        .when()
        .get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void singleUsers(){
        Response res = given().
        baseUri("https://reqres.in/api/").
        contentType(ContentType.JSON).
        when()
        .get("users/2");
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void userNotFound(){
        Response res = given().
        baseUri("https://reqres.in/api/").
        contentType("application/Json").
        when()
        .get("users/23");
        Assert.assertEquals(res.statusCode(),404);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void createUser(){
        HashMap<String, String> map = new HashMap<String , String>();
        map.put("name" , "morpheus");
        map.put("job" , "leader");
        String user = "{\"name\" : \"morpheus\" , \"job\" : \"leader\"}";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .post("users");
        Assert.assertEquals(res.statusCode(),201);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void updateUser(){
        HashMap<String, String> map = new HashMap<String , String>();
        map.put("name" , "morpheus");
        map.put("job" , "leader");
        String user = "{\"name\" : \"morpheus\" , \"job\" : \"zion resident\"}";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .put("users/2");
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.getBody().asString());
    }

    @Test
    public void deleteUsers(){
        Response res = given().
        baseUri("https://reqres.in/api/users/2").
        contentType(ContentType.JSON).
        when()
        .delete();
        Assert.assertEquals(res.statusCode(),204);
    }

    @Test
    public void registerUser(){

        String user = "{\"email\" : \"eve.holt@reqres.in\" , \"password\" : \"pistol\"}";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .post("register");
        Assert.assertEquals(res.statusCode(),200 , "Registration failed");
        System.out.println(res.getBody().asString());
    }

    @Test
    public void registerNotSucceseful(){
        String user = "{\"email\" : \"eve.holt@reqres.in\"}";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .post("register");
        Assert.assertEquals(res.statusCode(),400 , "Registration failed because password missing!");
        System.out.println(res.getBody().asString());
    }

    @Test
    public void loginSucceseful(){
        String user = "{\"email\" : \"eve.holt@reqres.in\" , \"password\" : \"cityslicka\"}";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .post("login");
        Assert.assertEquals(res.statusCode(),200 , "Login failed");
        System.out.println(res.getBody().asString());
    }

    @Test
    public void loginUnSucceseful(){
        String user = "{\"email\" : \"eve.holt@reqres.in\" }";
        Response res = given()
        .baseUri("https://reqres.in/api/")
        .contentType("application/json")
        .body(user)
        .when()
        .post("login");
        Assert.assertEquals(res.statusCode(),400 , "Login failed because Password missing!");
        System.out.println(res.getBody().asString());
    }

    @Test
    public void delayed(){
        String user = "{\"email\" : \"eve.holt@reqres.in\" }";
        Response res = given()
        .baseUri("https://reqres.in/api/users?delay=3")
        .contentType("application/json")
        .body(user)
        .when()
        .get();
        Assert.assertEquals(res.statusCode(),200);
        System.out.println(res.getBody().asString());
    }
}