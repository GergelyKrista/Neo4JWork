package dev.gergely.springbootneo4j.requests;

public class CreateuserRequest {
    private String username;
    private String name;
    private String password;
    private String roles;

    public CreateuserRequest(String username, String name, String password, String roles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
