package E2E.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ReqResSpecification {

    public static RequestSpecification reqSpecification(String urlPath)
    {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(urlPath)
                .setContentType(ContentType.JSON)
                .setUrlEncodingEnabled(false).build();
        return requestSpecification;
    }

    public static ResponseSpecification resSpecification(String bodyKey,String bodyValue)
    {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200)
                .expectBody(bodyKey,equalTo(bodyValue)).build();

        return responseSpecification;
    }
}
