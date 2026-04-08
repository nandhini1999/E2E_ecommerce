package E2E.TestComponents.POJO;

public class getFullResponse {

    private  String instructor;
    private  String url;
    private  String services;
    private  String expertise;
    private  String linkedIn;
    private getCourseTypes courses;

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

    public getCourseTypes getCourses() {
        return courses;
    }

    public void setCourses(getCourseTypes courses) {
        this.courses = courses;
    }

}
