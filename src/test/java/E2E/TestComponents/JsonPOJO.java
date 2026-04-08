package E2E.TestComponents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openqa.selenium.json.Json;

import java.util.List;
import java.util.Map;

public class DataReader {

    private String email;
    private String password;
    private String invalidEmail;
    private String invalidPassword;
    private String productName;
    private List<Map<String,String>> MultipleLoginList;
    private List<Map<String,String>> CategoriesList;
    private List<Map<String,String>> InvalidChFormList;
    private String firstName;
    private String lastName;
    private String pincode;

    @JsonProperty("CheckOutForm")
    public void setCheckOutForm(Map<String,String> data)
    {
        this.firstName = data.get("firstName");
        this.lastName = data.get("lastName");
        this.pincode = data.get("pincode");
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPincode()
    {
        return pincode;
    }


    //getter setter for multiple set of data
    @JsonProperty("Categories")
    public void setCategories(List<Map<String,String>> CategoriesList)
    {
        this.CategoriesList = CategoriesList;
    }

    @JsonProperty("InvalidFormData")
    public void setInvalidChForm(List<Map<String,String>> data)
    {
        this.InvalidChFormList = data;
    }

    public List<Map<String,String>> getInvalidChForm()
    {
        return InvalidChFormList;
    }

    public List<Map<String,String>> getCategoriesList()
    {
        return CategoriesList;
    }

    @JsonProperty("InvalidLogin")
        public void setInvalidData(Map<String,String> data)
    {
        this.invalidEmail = data.get("email");
        this.invalidPassword = data.get("password");
    }
    public String getInvalidEmail()
    {
        return invalidEmail;
    }

    public String getInvalidPassword()
    {
        return invalidPassword;
    }


//getter setter for multiple set of data
    @JsonProperty("Multiple_Login")
    public void setMultiple_Login(List<Map<String,String>> MultipleLoginList)
    {
        this.MultipleLoginList = MultipleLoginList;
    }

    public List<Map<String,String>> getMultipleLogin()
    {
        return MultipleLoginList;
    }

    //Single Data set
    @JsonProperty("Success_login")
    public void unPackSuccess_login(Map<String,String> data) // this will be called by jackson to set values from JSON
    {
        this.email = data.get("email");
        this.password = data.get("password");
        this.productName = data.get("productName");
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getProductName()
    {
        return productName;
    }


}
