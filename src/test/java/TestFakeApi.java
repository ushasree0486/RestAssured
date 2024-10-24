import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestFakeApi {
    @Test
    public void test_get_01() {
        baseURI = "http://localhost:3000";
        given().get("users").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void test_Post_01() {
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Tina");
        jsonObject.put("lastname", "saxena");
        jsonObject.put("subjectId", 1);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).post("users")
                .then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void test_Put_01() {
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "JyotiK");
        jsonObject.put("subjectId", 1);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).put("users/2")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void test_Patch_01() {
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Sarika");
        jsonObject.put("id", 1);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).patch("users/3")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void test_Delete_01() {
        baseURI = "http://localhost:3000";
        given().contentType(ContentType.JSON).delete("users/2")
                .then().statusCode(HttpStatus.SC_OK);

    }
}