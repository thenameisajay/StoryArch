package main.java.storyArch.model;

/**
 * User class - Data model for the user of the application
 */
public class User {
    private String fullName;
    private String email;
    private String password;

    private String userName;

    // For Later Versions of the Application
//    private String TOTP_TOKEN;
//
//    private String AUTH_TOKEN;
//
//    private String marketBiography;
//
//    private byte[] profilePicture;


    public User(String fullName, String email, String password, String userName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
