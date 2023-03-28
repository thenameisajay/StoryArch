package main.java.storyArch.model;

/**
 *  User class - Data model for the user of the application
 */
public class User {
    private String fullName;
    private String email;
    private String password;

    private String TOTP_TOKEN;

    private String AUTH_TOKEN;

    private String marketBiography;

    private byte[] profilePicture;
}
