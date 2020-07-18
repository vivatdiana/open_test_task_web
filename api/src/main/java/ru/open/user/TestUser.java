package ru.open.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestUser {
    String email;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    String avatar;

    public TestUser() {}

    public TestUser(String email, String firstName, String lastName, String avatar) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
