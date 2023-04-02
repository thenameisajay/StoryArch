package main.java.storyArch.controller;

import main.java.storyArch.api.PaymentAPI;
import main.java.storyArch.model.Message;
import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;
import main.java.storyArch.service.MessageService;
import main.java.storyArch.service.UserService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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

    public void register(String fullName, String email, String userName, String password, SubscriptionType subscriptionType, Date subscriptionDate) {
        userService.register(fullName, email, userName, password, subscriptionType, subscriptionDate);
    }

    public void saveData() throws IOException {
        userService.saveData();
        messageService.saveData();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        userService.loadData();
        messageService.loadData();
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
}
