package main.java.storyArch.model;

import java.util.Date;

public class Chapter extends Job {
    private int chapterId;

    private String chapterName;

    private String description;

    private Date dateCreated;

    private Date dateModified;

    private boolean uploadChanges;


    public Chapter(String fullName, String email, String password, String TOTP_TOKEN, String AUTH_TOKEN, String marketBiography, byte[] profilePicture, int invoiceID, String currency, float total, Date sentDate, Date dueDate, Date paidDate, int ID, String description, Date publishedDate, Date completedDate, int chapterId, String chapterName, String description1, Date dateCreated, Date dateModified, boolean uploadChanges) {
        super(fullName, email, password, TOTP_TOKEN, AUTH_TOKEN, marketBiography, profilePicture, invoiceID, currency, total, sentDate, dueDate, paidDate, ID, description, publishedDate, completedDate);
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.description = description1;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.uploadChanges = uploadChanges;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isUploadChanges() {
        return uploadChanges;
    }

    public void setUploadChanges(boolean uploadChanges) {
        this.uploadChanges = uploadChanges;
    }
}
