import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestJsonSchema {
    @Test
    public void testSchemaForCollege(){
        baseURI = "http://localhost:3000";
        //create folder resources under test folder
        get("college").then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json"));

    }
}
