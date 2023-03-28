package main.java.storyArch.model;

import java.util.Date;

/**
 * This Class holds the information for a Job
 * such as the ID, description, published date, and completed date.
 * It extends the Invoice class extending the User class.
 */

public class Job extends Invoice {
    private final int ID;

    private final String description;

    private final Date publishedDate;

    private final Date completedDate;

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

    public String getDescription() {
        return description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }
}
