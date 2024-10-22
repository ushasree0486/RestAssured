import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TestGet {
    @Test
    public void test_Get_01(){
        Response response = RestAssured.get("http://localhost:9090/productcatalog/products");
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println("Header == " +response.getHeader("Content-Type"));
        String expectedOutput="[{\"id\":1,\"name\":\"HP-Laptop\",\"quantity\":2,\"price\":300.75},{\"id\":6,\"name\":\"IPhone\",\"quantity\":1,\"price\":1350.0},{\"id\":3,\"name\":\"Mac Book\",\"quantity\":3,\"price\":300.75},{\"id\":4,\"name\":\"Dell Laptop\",\"quantity\":0,\"price\":300.75},{\"id\":5,\"name\":\"Laptop\",\"quantity\":3,\"price\":455.75},{\"id\":7,\"name\":\"tablet\",\"quantity\":1,\"price\":200.0},{\"id\":8,\"name\":\"Desktop\",\"quantity\":1,\"price\":350.0}]";
        Assert.assertEquals(expectedOutput,response.getBody().asString());
        int actualStatusCode= response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK,actualStatusCode);
    }
    @Test
    public void test_get_02(){
        given().get("http://localhost:9090/productcatalog/products").then().statusCode(HttpStatus.SC_OK);
    }
}
