package E2E.APItests;

import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
       String response =  given().log().all()
                .queryParam("access_token", access_token)
                .when().get("/getCourseDetails")
                .then().log().all().extract().response().asString();

       System.out.println(response);
    }
}
