import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPutPatchDelete {
    @Test
    public void test_put_1() {
        baseURI = "http://localhost:9090/";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Minitab");
        map.put("quantity", Integer.valueOf(18));
        map.put("price", Integer.valueOf(2070));
        System.out.println("map " + map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("JsonObject " + jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                // .when().post("http://localhost:9090/productcatalog/products")
                .when().put("productcatalog/product/update/12")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void test_patch_1() {
        baseURI = "http://localhost:9090/";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Microtab");
        // map.put("quantity", Integer.valueOf(18));
        // map.put("price", Integer.valueOf(2070));
        System.out.println("map " + map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("JsonObject " + jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                // .when().post("http://localhost:9090/productcatalog/products")
                .when().patch("productcatalog/product/update/18")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

    @Test
    public void test_Delete_1() {
        baseURI = "http://localhost:9191/";
        given()
                .when().delete("productcatalog/delete/45" )
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

}
