import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestRequest {

    @Test
    public void test_get1() {
        baseURI = "https://reqres.in";
        given().param("page", 2).get("api/users").then()
                .body("data.id[1]", equalTo(8))
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke"))
                .statusCode(200)
                .log().all();

        /* given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id[1]", equalTo(8))
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke"))
                .statusCode(200)
                .log().all();*/
    }

    @Test
    public void test_get2() {
        given().header("till-type", "refund")/*.contentType(ContentType.JSON)*/
                /* .accept(ContentType.JSON)*/
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id[1]", equalTo(8))
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke"))
                .statusCode(200)
                .log().all();

    }

    @Test
    public void test_post_1() {
        baseURI = "http://localhost:9090/";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tablet");
        map.put("quantity", Integer.valueOf(1));
        map.put("price", Integer.valueOf(300));
        System.out.println("map " + map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("JsonObject " + jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                // .when().post("http://localhost:9090/productcatalog/products")
                .when().post("productcatalog/products")

                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void test_post() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "tablet");
        jsonObject.put("quantity", 1);
        jsonObject.put("price", 200);
        System.out.println("JsonObject " + jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().post("http://localhost:9090/productcatalog/products")
                //.when().post("productcatalog/products")
                .then().statusCode(HttpStatus.SC_CREATED);
                //.then().statusCode(HttpStatus.SC_OK).log().all();


    }


}