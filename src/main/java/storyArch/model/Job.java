package main.java.storyArch.model;

import java.util.Date;

/**
 * This Class holds the information for a Job
 * such as the ID, description, published date, and completed date.
 * It extends the Invoice class extending the User class.
 */

public class Job extends Invoice {
    private int ID;

    private String description;

    private Date publishedDate;

    private Date completedDate;

    public Job(String fullName, String email, String password, String TOTP_TOKEN, String AUTH_TOKEN, String marketBiography, byte[] profilePicture, int invoiceID, String currency, float total, Date sentDate, Date dueDate, Date paidDate, int ID, String description, Date publishedDate, Date completedDate) {
        super(fullName, email, password, TOTP_TOKEN, AUTH_TOKEN, marketBiography, profilePicture, invoiceID, currency, total, sentDate, dueDate, paidDate);
        this.ID = ID;
        this.description = description;
        this.publishedDate = publishedDate;
        this.completedDate = completedDate;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
}
