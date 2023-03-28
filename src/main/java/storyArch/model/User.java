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

    public User(String fullName, String email, String password, String TOTP_TOKEN, String AUTH_TOKEN, String marketBiography, byte[] profilePicture) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.TOTP_TOKEN = TOTP_TOKEN;
        this.AUTH_TOKEN = AUTH_TOKEN;
        this.marketBiography = marketBiography;
        this.profilePicture = profilePicture;
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

    public String getTOTP_TOKEN() {
        return TOTP_TOKEN;
    }

    public void setTOTP_TOKEN(String TOTP_TOKEN) {
        this.TOTP_TOKEN = TOTP_TOKEN;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getMarketBiography() {
        return marketBiography;
    }

    public void setMarketBiography(String marketBiography) {
        this.marketBiography = marketBiography;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
