package main.java.storyArch.model;

import java.util.Date;
import java.util.List;

/**
 * This is the main class for the project. It contains all the information about a project.
 */
public class Project {
    private int projectID;
    private String projectName;
    private String projectDescription;
    private String creator;
    private Date date;
    private IllustrationServices illustrationServices;
    private List<String> teamMembers;

    public Project(int projectID, String projectName, String projectDescription, String creator, Date date, IllustrationServices illustrationServices, List<String> teamMembers) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.creator = creator;
        this.date = date;
        this.illustrationServices = illustrationServices;
        this.teamMembers = teamMembers;
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IllustrationServices getIllustrationServices() {
        return illustrationServices;
    }

    public void setIllustrationServices(IllustrationServices illustrationServices) {
        this.illustrationServices = illustrationServices;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
