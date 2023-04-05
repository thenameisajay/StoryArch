package tests.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.storyArch.model.IllustrationServices;
import main.java.storyArch.model.Project;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void testGettersAndSetters() {
        // Test getter and setter methods for all fields
        int projectID = 1;
        String projectName = "Project 1";
        String projectDescription = "This is project 1";
        String creator = "John Doe";
        Date date = new Date();
       IllustrationServices illustrationServices ;
        List<String> teamMembers = new ArrayList<String>();
        teamMembers.add("Jane Doe");
        teamMembers.add("Bob Smith");

        Project project = new Project(projectID, projectName, projectDescription, creator, date, IllustrationServices.YES, teamMembers);

        assertEquals(projectID, project.getProjectID());
        assertEquals(projectName, project.getProjectName());
        assertEquals(projectDescription, project.getProjectDescription());
        assertEquals(creator, project.getCreator());
        assertEquals(date, project.getDate());
        assertEquals(IllustrationServices.YES, project.getIllustrationServices());
        assertEquals(teamMembers, project.getTeamMembers());

        int newProjectID = 2;
        String newProjectName = "Project 2";
        String newProjectDescription = "This is project 2";
        String newCreator = "Jane Smith";
        Date newDate = new Date();
        List<String> newTeamMembers = new ArrayList<String>();
        newTeamMembers.add("Bob Johnson");
        newTeamMembers.add("Alice Lee");

        project.setProjectID(newProjectID);
        project.setProjectName(newProjectName);
        project.setProjectDescription(newProjectDescription);
        project.setCreator(newCreator);
        project.setDate(newDate);
        project.setIllustrationServices(IllustrationServices.YES);
        project.setTeamMembers(newTeamMembers);

        assertEquals(newProjectID, project.getProjectID());
        assertEquals(newProjectName, project.getProjectName());
        assertEquals(newProjectDescription, project.getProjectDescription());
        assertEquals(newCreator, project.getCreator());
        assertEquals(newDate, project.getDate());
        assertEquals(IllustrationServices.YES, project.getIllustrationServices());
        assertEquals(newTeamMembers, project.getTeamMembers());
    }

    @Test
    void testConstructor() {
        // Test constructor and getter methods for all fields
        int projectID = 1;
        String projectName = "Project 1";
        String projectDescription = "This is project 1";
        String creator = "John Doe";
        Date date = new Date();

        List<String> teamMembers = new ArrayList<String>();
        teamMembers.add("Jane Doe");
        teamMembers.add("Bob Smith");

        Project project = new Project(projectID, projectName, projectDescription, creator, date, IllustrationServices.NO, teamMembers);

        assertEquals(projectID, project.getProjectID());
        assertEquals(projectName, project.getProjectName());
        assertEquals(projectDescription, project.getProjectDescription());
        assertEquals(creator, project.getCreator());
        assertEquals(date, project.getDate());
        assertEquals(IllustrationServices.NO, project.getIllustrationServices());
        assertEquals(teamMembers, project.getTeamMembers());
    }

}
