package main.java.storyArch.model;

import java.util.Date;

public class Story extends Chapter {

    public Story(String fullName, String email, String password, String TOTP_TOKEN, String AUTH_TOKEN, String marketBiography, byte[] profilePicture, int invoiceID, String currency, float total, Date sentDate, Date dueDate, Date paidDate, int ID, String description, Date publishedDate, Date completedDate, int chapterId, String chapterName, String description1, Date dateCreated, Date dateModified, boolean uploadChanges, int ID1, String title, String description2, Date createdDate, Date modifiedDate, boolean updatedChanges) {
        super(fullName, email, password, TOTP_TOKEN, AUTH_TOKEN, marketBiography, profilePicture, invoiceID, currency, total, sentDate, dueDate, paidDate, ID, description, publishedDate, completedDate, chapterId, chapterName, description1, dateCreated, dateModified, uploadChanges);
        this.ID = ID1;
        this.title = title;
        this.description = description2;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.updatedChanges = updatedChanges;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isUpdatedChanges() {
        return updatedChanges;
    }

    public void setUpdatedChanges(boolean updatedChanges) {
        this.updatedChanges = updatedChanges;
    }

    private int ID;

    private String title;

    private String description;

    private Date createdDate;

    private Date modifiedDate;

    private boolean updatedChanges;


}
