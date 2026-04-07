package E2E.APItests;

import E2E.resources.ExcelReader;
import E2E.utils.JsonInputs;
import E2E.utils.JsonPathReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Test(description = "Request hashMap to JSON")
    public void verifyHashMap()
    {
        Map<String,String> jsonData = new HashMap<String,String>();

        jsonData.put("name","Selenium TestNG");
        jsonData.put("isbn","abuhk");
        jsonData.put("aisle","6789");
        jsonData.put("author","Me");

        given().log().all()
                .body(jsonData)
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().body("Msg",equalTo("successfully added"));
    }

    @Test
    public void verifyExcelInput() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        String excelFile = "exceldemo1";
     ArrayList<String> a = excelReader.getInputRow(excelFile,"Rest Assured","Book Addition");

     String jsonInput = JsonInputs.getAddBookInput(a.get(0),a.get(1),a.get(2),a.get(3));
     System.out.println(jsonInput);

        String response = given().log().all()
             .body(jsonInput)
             .when().post("Library/Addbook.php")
             .then().log().all().extract().response().asString();

        JsonPath js = JsonPathReader.getJson(response);
        String id = js.getString("ID");

        given().log().all()
                .body("{\n" +
                        "\"ID\" : \""+id+"\"\n" +
                        "} \n")
                .when().delete("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);
    }
}
