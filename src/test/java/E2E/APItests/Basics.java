package E2E.APItests;

import E2E.utils.JsonInputs;
import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static String place_id = null;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }

    @Test
    public void postMethod() {

        String response = given().log().all().queryParam("Key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(JsonInputs.getInputJson()).
                when().post("/maps/api/place/add/json").
                then().extract().response().asPrettyString();

       JsonPath js = JsonPathReader.getJson(response);
        place_id = js.getString("place_id");
        System.out.println("Place ID" + place_id);
        Assert.assertNotNull(place_id);

    }

    @Test(description = "Fetching place id from response json")
    public void fetchPlaceId() {
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(JsonInputs.getInputJson()).
                when().post("/maps/api/place/add/json").
                then().log().all().assertThat()
                .body("scope", equalTo("APP"))
                .header("Server", equalTo("Apache/2.4.52 (Ubuntu)"));
    }

    @Test(description = "GET method", dependsOnMethods = {"postMethod"})
    public void getMethod() {
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then().log().all().extract().response().asString();
        JsonPath js = JsonPathReader.getJson(response);
        System.out.println(js.getString("location.latitude"));

    }

    @Test
    public void jsonParsing() {
        JsonPath js = new JsonPath(JsonInputs.getSample());

        //1. Print No of courses returned by API
        int value = js.getInt("courses.size()");
        System.out.println(value);
        System.out.println("====");

        // Print Purchase Amount
        System.out.println(js.getInt("dashboard.purchaseAmount"));
        System.out.println("====");

        // Print Title of the first course
        System.out.println(js.getString("courses[0].title"));
        System.out.println("====");

        //Print All course titles and their respective Prices
        for (int i = 0; i < value; i++) {
            System.out.println(js.getString("courses[" + i + "].title"));
            System.out.println(js.getInt("courses[" + i + "].price"));
        }
        System.out.println("====");

        // Print no of copies sold by RPA Course
        for (int i = 0; i < value; i++) {
            if (js.getString("courses[" + i + "].title").equalsIgnoreCase("rpa")) {
                System.out.println(js.getInt("courses[" + i + "].copies"));
            }
        }

        System.out.println("====");
        //Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0; i < value; i++) {
            int amount = (js.getInt("courses[" + i + "].price") * (js.getInt("courses[" + i + "].copies")));
            System.out.println(amount);
            sum = sum + amount;
        }

        int purchase = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(purchase, sum);


    }
}

