package E2E.utils;

import io.restassured.path.json.JsonPath;

public class JsonPathReader {

    public static JsonPath getJson(String response)
    {
        JsonPath js = new JsonPath(response);
        return js;
    }
}
