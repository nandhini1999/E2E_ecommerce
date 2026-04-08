package E2E.APItests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ReqResSpec {

    public static void main(String[] args)
    {

        Map<String,String> jsonData = new HashMap<String,String>();

        jsonData.put("name","HashMap input");
        jsonData.put("isbn","hello");
        jsonData.put("aisle","9899");
        jsonData.put("author","John");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://216.10.245.166")
                .setContentType(ContentType.JSON)
                .setUrlEncodingEnabled(false).build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200)
                .expectBody("Msg",equalTo("successfully added")).build();

        String Id = given().spec(requestSpecification)
                .body(jsonData)
                .when().post("Library/Addbook.php")
                .then().spec(responseSpecification).extract().response().path("ID");


        given().log().all()
                .body("{\n" +
                        "\"ID\" : \""+Id+"\"\n" +
                        "} \n")
                .when().delete("/Library/DeleteBook.php")
                .then().spec(responseSpecification);

    }
}
