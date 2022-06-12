
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRastApi {

    @BeforeClass
    public static void Setup()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void baseUriReturnsSuccess(){

        Response response = given()
                .when()
                .get()
                .then()
                .extract()
                .response();


        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // Scenario get All
    @Test
    public void it_should_return_valid_data() {
        Response response = given()
                .when()
                .get("posts")
                .then()
                .extract()
                .response();

        System.out.println(response.jsonPath().get("[0].title").toString());

        JsonPath jsonPath = response.jsonPath();
        String title = jsonPath.getString("[0].title");
        Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }

    // Scenario get by parameter
    @Test
    public void when_passing_id_then_it_should_only_return_data_for_that_id() {
        Response response = given()
                .when()
                .get("posts/1")
                .then()
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        String title = jsonPath.getString("title");
        Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }

    // Scenario Post to create
    @Test
    public void it_should_create_new_post() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"title\": \"abcd\",\n" +
                        "    \"body\": \"abce efg\"\n" +
                        "}")
                .post("posts/")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 201);

        JsonPath jsonPath = response.jsonPath();
        String title = jsonPath.getString("title");
        String body = jsonPath.getString("body");
        Assert.assertEquals(title, "abcd");
        Assert.assertEquals(body, "abce efg");
    }

    // Scenario Put to create
    @Test
    public void it_should_create_new_put() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"title\": \"abcd\",\n" +
                        "    \"body\": \"abce efg\"\n" +
                        "}")
                .put("posts/10")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String title = jsonPath.getString("title");
        String body = jsonPath.getString("body");
        String id = jsonPath.getString("id");

        Assert.assertEquals(id, "10");
        Assert.assertEquals(title, "abcd");
        Assert.assertEquals(body, "abce efg");
    }
}
