package E2E.TestComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APIDataPojo {

  private  String instructor;
    private  String url;
    private  String services;
    private  String expertise;
    private  String linkedIn;
    private  Map<String,Object> courses;

    private  List<Map<String,Object>> webAutomation;
    private  List<Map<String,Object>> api;
    private  List<Map<String,Object>> mobile;

    private  String courseTitle;
    private  int price;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public Map<String, Object> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Object> courses) {
        this.courses = courses;
    }

    public List<Map<String, Object>> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<Map<String, Object>> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Map<String, Object>> getApi() {
        return api;
    }

    public void setApi(List<Map<String, Object>> api) {
        this.api = api;
    }

    public List<Map<String, Object>> getMobile() {
        return mobile;
    }

    public void setMobile(List<Map<String, Object>> mobile) {
        this.mobile = mobile;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
