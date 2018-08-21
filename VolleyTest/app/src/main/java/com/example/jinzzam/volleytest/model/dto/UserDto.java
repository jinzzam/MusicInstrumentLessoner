package com.example.jinzzam.volleytest.model.dto;

public class UserDto {
    private static UserDto instance;
    private String userEmail;
    private String userPassword;
    private String userName;

    private UserDto() {

    }

    public UserDto(String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public static UserDto getInstance() {
        if (instance == null) {
            instance = new UserDto();
        }
        return instance;
    }
}
