package main.java.storyArch.service;

import main.java.storyArch.model.IllustrationServices;
import main.java.storyArch.model.Project;

import java.util.*;

public class ProjectService {

    MessageService messageService = new MessageService();
    private Map<Integer, Project> projects = new HashMap<>();

    private List<Integer> projectIDs = new ArrayList<>();

    public void createProject(String projectName, String projectDescription, String creator, Date date, IllustrationServices illustrationServices, List<String> teamMembers) {
        // If List is of team members , dissect the list and send a message to the team members that they have been added to the project.
        if (teamMembers != null) {
            // Send a message to the team members that they have been added to the project.
            for (String member : teamMembers) {
                messageService.sendMessage(member.toLowerCase(), "System", "You have been added to the project by " + creator.toLowerCase() + "to: " + projectName, date);
            }

        }
        // Create a random Project ID using numeric values of 7 digits
        int projectID = (int) (Math.random() * 10000000);
        projectIDs.add(projectID);
        while (projectIDs.contains(projectID)) {
            projectID = (int) (Math.random() * 10000000);
        }
        // Add the project to the database.
        projects.put(projectID, new Project(projectID, projectName, projectDescription, creator, date, illustrationServices, teamMembers));
    }
}
