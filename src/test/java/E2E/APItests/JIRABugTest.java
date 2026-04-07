package E2E.APItests;

import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JIRABugTest {
    String id = null;

    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI = "https://nandhinidevi957.atlassian.net";
    }

 @Test
    public void createBug()
 {
      String token = "bmFuZGhpbmlkZXZpOTU3QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3ZDAzU2NmejNUTTdLWldFcFVYRjVnY1ZUejFzc1pqQVFlSXprTnR3VEtGOHRUbDlxb2VROGFfc1dNNW1VdzhLbVUtQmFGWVNiazVfYkk4cktuTUk5Q0YtQXBscjlQYm11dGpBTUNCZXNzMU9lcmVCOEVKZTJNMTZ3ZG9jUHotd3BmRWZKNnZLU2toZ1VXejhfSldtSDRhOGxxb3U0bzFjOEFxMnJobzl2MU09QkE2MzdCMDQ=";

    String response = given().log().all()
             .header("Content-Type","application/json")
             .header("Authorization","Basic "+token)
             .body("{\n" +
                     "    \"fields\": {\n" +
                     "       \"project\":\n" +
                     "       {\n" +
                     "          \"key\": \"SNK\"\n" +
                     "       },\n" +
                     "       \"summary\": \"REST ye merry gentlemen.\",\n" +
                     "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                     "       \"issuetype\": {\n" +
                     "          \"name\": \"Bug\"\n" +
                     "       }\n" +
                     "   }\n" +
                     "}\n")

             .when().post("rest/api/2/issue")
             .then().log().all().extract().response().asString();

    JsonPath js = JsonPathReader.getJson(response);
    id = js.getString("id");

    System.out.println(id);
 }

 @Test(dependsOnMethods = {"createBug"})
    public void addAttachment()
 {
     String token = "bmFuZGhpbmlkZXZpOTU3QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3ZDAzU2NmejNUTTdLWldFcFVYRjVnY1ZUejFzc1pqQVFlSXprTnR3VEtGOHRUbDlxb2VROGFfc1dNNW1VdzhLbVUtQmFGWVNiazVfYkk4cktuTUk5Q0YtQXBscjlQYm11dGpBTUNCZXNzMU9lcmVCOEVKZTJNMTZ3ZG9jUHotd3BmRWZKNnZLU2toZ1VXejhfSldtSDRhOGxxb3U0bzFjOEFxMnJobzl2MU09QkE2MzdCMDQ=";

     given()
             .pathParams("id",id)
             .header("X-Atlassian-Token","no-check")
             .header("Authorization","Basic "+token)
             .multiPart("file",new File(System.getProperty("user.home")+"\\OneDrive\\Pictures\\Screenshots\\test.png"))
             .when().post("/rest/api/3/issue/{id}/attachments")
             .then().log().all().assertThat().statusCode(200);
 }
}
