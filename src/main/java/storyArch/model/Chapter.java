package main.java.storyArch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This is used for storing the chapters of the storyboards.
 * It is used in the storyboard creation.
 */
public class Chapter implements Serializable {

    private String storyboardID;
    private String storyboardName;
    private int chapterID;

    private String chapterName;

    private String chapterDescription;

    private Date createdDate;

    private Date modifiedDate;

    private String creator;

    private String lastModifiedBy;

    private float version;


    public Chapter(String storyboardID, String storyboardName, int chapterID, String chapterName, String chapterDescription, Date createdDate, Date modifiedDate, String creator, String lastModifiedBy, float version) {
        this.storyboardID = storyboardID;
        this.storyboardName = storyboardName;
        this.chapterID = chapterID;
        this.chapterName = chapterName;
        this.chapterDescription = chapterDescription;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.creator = creator;
        this.lastModifiedBy = lastModifiedBy;
        this.version = version;
    }

    public String getStoryboardID() {
        return storyboardID;
    }

    public void setStoryboardID(String storyboardID) {
        this.storyboardID = storyboardID;
    }

    public String getStoryboardName() {
        return storyboardName;
    }

    public void setStoryboardName(String storyboardName) {
        this.storyboardName = storyboardName;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }
}
