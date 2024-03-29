package dev.gergely.springbootneo4j.requests;

public class CourseEnrolmentRequest {
    private String identifier;

    public CourseEnrolmentRequest() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
