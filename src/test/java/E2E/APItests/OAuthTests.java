package E2E.APItests;

import E2E.TestComponents.POJO.getCourseDetails;
import E2E.TestComponents.POJO.getCourseTypes;
import E2E.TestComponents.POJO.getFullResponse;
import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class OAuthTests {

    String access_token = null;

    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi";
    }

    @Test
    public void getAccessToken()
    {
        String response = given().log().all()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("oauth2/resourceOwner/token")
                .then().log().all().extract().response().asString();

        JsonPath js = JsonPathReader.getJson(response);
         access_token = js.getString("access_token");
    }

    @Test(dependsOnMethods = {"getAccessToken"})
    public void getCourseDetails()
    {
      getFullResponse response =  given().log().all()
                .queryParam("access_token", access_token)
                .when().get("/getCourseDetails")
                .then().log().all().extract().response().as(getFullResponse.class);

      //Response JSON to Java object
         for( getCourseDetails course : response.getCourses().getWebAutomation())
         {
             System.out.println(course.getCourseTitle());
             System.out.println(course.getPrice());
         }

    }
}
