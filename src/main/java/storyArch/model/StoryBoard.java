package main.java.storyArch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the main class for Storyboard. It contains all the information about a storyboard.
 */
public class StoryBoard implements Serializable {
    private int projectID;

    private String projectName;

    private int StoryID;

    private String StoryName;

    private String StoryDescription;

    private Date creationDate;


    private String creator;


    public StoryBoard(int projectID, String projectName, int storyID, String storyName, String storyDescription, Date creationDate, String creator) {
        this.projectID = projectID;
        this.projectName = projectName;
        StoryID = storyID;
        StoryName = storyName;
        StoryDescription = storyDescription;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStoryID() {
        return StoryID;
    }

    public void setStoryID(int storyID) {
        StoryID = storyID;
    }

    public String getStoryName() {
        return StoryName;
    }

    public void setStoryName(String storyName) {
        StoryName = storyName;
    }

    public String getStoryDescription() {
        return StoryDescription;
    }

    public void setStoryDescription(String storyDescription) {
        StoryDescription = storyDescription;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

