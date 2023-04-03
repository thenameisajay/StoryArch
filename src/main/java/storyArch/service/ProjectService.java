package main.java.storyArch.service;

import main.java.storyArch.model.IllustrationServices;
import main.java.storyArch.model.Project;

import java.io.*;
import java.util.*;

public class ProjectService {

    MessageService messageService = new MessageService();
    private Map<Integer, Project> projects = new HashMap<>();

    private List<Integer> projectIDS = new ArrayList<>();


    public Map<Integer,Project> getProjectByCreator(String creator) {
        Map<Integer, Project> projectsByCreator = new HashMap<>();
        for (Project project : projects.values()) {
            if (project.getCreator().equals(creator.toLowerCase())) {
                projectsByCreator.put(project.getProjectID(), project);
            }
        }
        return projectsByCreator;
    }

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
        projectIDS.add(projectID);
        while (projectIDS.contains(projectID)) {
            projectID = (int) (Math.random() * 10000000);
        }
        // Add the project to the database.
        projects.put(projectID, new Project(projectID, projectName, projectDescription, creator, date, illustrationServices, teamMembers));
    }
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/projectData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(projects);
        o.close();
        f.close();
    }

    /**
     * Load the user data from a file
     *
     * @throws IOException            - If the file is not found
     * @throws ClassNotFoundException - If the class is not found
     */
    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/resources/projectData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
       projects = (Map<Integer, Project>) oi.readObject();
        oi.close();
        fi.close();
        // Take the key value (ProjectID) and add it to the arraylist
        for (Map.Entry<Integer, Project> entry : projects.entrySet()) {
            projectIDS.add(entry.getKey());
        }
    }


}

