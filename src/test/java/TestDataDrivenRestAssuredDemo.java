import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestDataDrivenRestAssuredDemo {
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
                //.then().statusCode(HttpStatus.SC_CREATED);
        .then().statusCode(HttpStatus.SC_CREATED).log().all();

    }

    @Test(dataProvider = "productdata")
    public void test_Post(String productName, Integer quantity, Integer price) {
        baseURI = "http://localhost:9090";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("quantity", quantity);
        jsonObject.put("price", price);
        System.out.println(jsonObject.toJSONString());
        //creating request specification
        RequestSpecification request = given();
        request.contentType(ContentType.JSON);
        request.body(jsonObject.toJSONString());
        //response
        Response response = request.post("productcatalog/products");
        String responseAsString = response.getBody().asString();
        System.out.println("Response " + responseAsString);
        //Assertions
        Assert.assertTrue(responseAsString.contains(productName));
        Assert.assertTrue(responseAsString.contains(quantity.toString()));
        Assert.assertTrue(responseAsString.contains(price.toString()));
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
    }

    @DataProvider(name="productdata")
        public Object[][]getProductData(){
        Object productInfo[][] = {{"tablet",Integer.valueOf(2),Integer.valueOf(200)},
                {"Desktop",Integer.valueOf(12),Integer.valueOf(120)},
                {"Iphone",Integer.valueOf(1),Integer.valueOf(3000)}
        };
        return productInfo;
    }

}