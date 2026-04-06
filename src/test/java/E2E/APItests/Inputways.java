package E2E.APItests;

import E2E.utils.JsonInputs;
import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Inputways {

    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI = "http://216.10.245.166";
    }

    @DataProvider(name = "InputBooks")
    public Object[][] getMultiInputs()
    {
        Object[][] data = {{"Book of life","abcd","123","Sharath"},{"Nandhini's victories","efgh","456","Nandhini"}};
        return data;
    }

    @Test(dataProvider="InputBooks")
    public void verifyAddBookAPI(String bookName,String isbn, String aisle,String author) throws InterruptedException {
        String response = given().log().all()
                .body(JsonInputs.getAddBookInput(bookName,isbn,aisle,author))
                .when().post("Library/Addbook.php")
                .then().extract().response().asString();

        JsonPath js = JsonPathReader.getJson(response);
        String expected = isbn + aisle;
        Assert.assertEquals(js.get("ID").toString(),expected);
    }


    @Test(description = "Request directly from JSON file")
    public void verifyBodyJsonFile()
    {
        given().log().all()
                .body(new File(Paths.get(System.getProperty("user.dir")+"\\src\\test\\java\\E2E\\APITests\\sampleInput.json").toUri()))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().body("Msg",equalTo("successfully added"));
    }
}
