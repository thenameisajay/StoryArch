package main.java.storyArch.controller;

import main.java.storyArch.api.IllustrationServicesAPI;
import main.java.storyArch.api.PaymentAPI;
import main.java.storyArch.model.*;
import main.java.storyArch.service.MessageService;
import main.java.storyArch.service.ProjectService;
import main.java.storyArch.service.UserService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is the controller class for the StoryArch application.
 * It is used to control the flow of data between the view and the model.
 * It is also used to control the flow of data between the model and the API.
 * It is used to control the flow of data between the model and the service.
 * It is used to control the flow of data between the service and the model.
 */
public class ArchController implements Serializable {
    UserService userService = new UserService();

    MessageService messageService = new MessageService();

    PaymentAPI paymentAPI = new PaymentAPI();

    ProjectService projectService = new ProjectService();

    IllustrationServicesAPI illustrationServicesAPI = new IllustrationServicesAPI();

    public void register(String fullName, String email, String userName, String password, SubscriptionType subscriptionType, Date subscriptionDate) {
        userService.register(fullName, email, userName, password, subscriptionType, subscriptionDate);
    }

    public void saveData() throws IOException {
        userService.saveData();
        messageService.saveData();
        projectService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        userService.loadData();
        messageService.loadData();
        projectService.loadData();
    }

    public void paymentAPI() {
        paymentAPI.redirectToPaymentGateway();
    }

    public Map<String, User> login(String userName, String hashedPassword) {
        return userService.login(userName, hashedPassword);
    }

    public void deleteAccount(String userName) {
        userService.deleteAccount(userName);
    }

    public void updateSubscriptionType(String userName, SubscriptionType subscriptionType) {
        userService.updateSubscriptionType(userName, subscriptionType);
    }

    public void sendMessage(String userName, String system, String message, Date presentDate) {
        messageService.sendMessage(userName, system, message, presentDate);
    }

    public Map<String, Message> viewMessage(String userName) {
        return messageService.viewMessage(userName);
    }

    public void checkIfUserExists(String toUser) {
        userService.checkIfUserExists(toUser);
    }

    public void deleteMessage(String userName) {
        messageService.deleteMessage(userName);
    }

    public void updateSubscriptionDate(String userName, Date presentDate) {
        userService.updateSubscriptionDate(userName, presentDate);
    }

    public void illustrationServiceAPI() {
        illustrationServicesAPI.redirectToIllustrationService();
    }

    public void createProject(String projectName, String projectDescription, String creator, Date date, IllustrationServices illustrationServices, List<String> teamMembers) {
        projectService.createProject(projectName, projectDescription, creator, date, illustrationServices, teamMembers);
    }

    public Map<Integer, Project> getProjectByCreator(String creator) {
        return projectService.getProjectByCreator(creator);
    }

    public Map<Integer, Project> getSharedProjects(String userName) {
        return projectService.getSharedProjects(userName);
    }

    public void deleteProject(String projectID, String creator) {
        projectService.deleteProject(projectID, creator);
    }
}
