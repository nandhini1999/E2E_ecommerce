package E2E.APItests;

import E2E.utils.ReqResSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqResSpecTest {

    @Test
    public void verifyReqResSpec() {
        Map<String, String> jsonData = new HashMap<String, String>();

        jsonData.put("name", "HashMap input");
        jsonData.put("isbn", "hello");
        jsonData.put("aisle", "9799");
        jsonData.put("author", "John");

        RequestSpecification requestSpecification = ReqResSpecification.reqSpecification("http://216.10.245.166");
        ResponseSpecification responseSpecification = ReqResSpecification.resSpecification("Msg", "successfully added");

        String Id = given().spec(requestSpecification)
                .body(jsonData)
                .when().post("Library/Addbook.php")
                .then().spec(responseSpecification).extract().response().path("ID");


        given().log().all().spec(requestSpecification)
                .body("{\n" +
                        "\"ID\" : \"" + Id + "\"\n" +
                        "} \n")
                .when().delete("/Library/DeleteBook.php")
                .then().statusCode(200);

    }
}
