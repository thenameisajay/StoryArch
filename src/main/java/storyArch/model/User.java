package main.java.storyArch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User class - Data model for the user of the application
 * This class is used for storing the user information.
 */
public class User implements Serializable {
    private String fullName;
    private String email;
    private String password;

    private String userName;

    private SubscriptionType subscriptionType;

    private Date subscriptionStartDate;

    public User(String fullName, String email, String userName, String password, SubscriptionType subscriptionType, Date subscriptionStartDate) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.subscriptionType = subscriptionType;
        this.subscriptionStartDate = subscriptionStartDate;
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

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }


    // For Later Versions of the Application
//    private String TOTP_TOKEN;
//
//    private String AUTH_TOKEN;
//
//    private String marketBiography;
//
//    private byte[] profilePicture;


}
