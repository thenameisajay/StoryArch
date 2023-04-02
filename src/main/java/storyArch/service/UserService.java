package main.java.storyArch.service;

import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserService implements Serializable {

    private Map<String, User> user = new HashMap<>();

    /**
     * Register a new user to the system
     *
     * @param fullName         - Full Name of the user
     * @param email            - Email of the user
     * @param userName         - Username of the user
     * @param password         - Password of the user
     * @param subscriptionType
     * @param subscriptionDate
     */
    public void register(String fullName, String email, String userName, String password, SubscriptionType subscriptionType, Date subscriptionDate) {
        // First Check for empty fields
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || userName.isEmpty()) {
            throw new IllegalArgumentException("All fields are required");
        }
        // Second check : For validation of full name with spaces, email and username
        if (!fullName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Full name can only contain alphabets and spaces");
        }
        if (!email.matches("^(.+)@(.+)$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!userName.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username can only contain alphabets and numbers");
        }
        // Check if the username is already taken
        if (user.containsKey(userName)) {
            throw new IllegalArgumentException("Username already taken");
        }
        // Add the user to the system
        user.put(userName, new User(fullName, email, userName, password, subscriptionType, subscriptionDate));
    }

    /**
     * Save the user data to a file
     *
     * @throws IOException - If the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/userData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(user);
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
        FileInputStream fi = new FileInputStream("src/resources/userData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
        user = (Map<String, User>) oi.readObject();
        oi.close();
        fi.close();
    }

    /**
     * Login to the system
     *
     * @param userName       - Username of the user
     * @param hashedPassword - Password of the user
     */
    public Map<String, User> login(String userName, String hashedPassword) {
        Map<String, User> returnUser = new HashMap<>();
        // First Check : For empty fields
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (hashedPassword == null || hashedPassword.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users registered");
        }
        // Third Check : If username exists in the system.
        if (user.containsKey(userName)) {
            // Fourth Check : If password is correct
            if (user.get(userName).getPassword().equals(hashedPassword)) {
                returnUser.put(userName, user.get(userName));
                return returnUser;
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }

    }

    public void deleteAccount(String userName) {
        // First Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users registered");
        }
        // Second Check
        if (user.containsKey(userName)) {
            user.remove(userName);
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }
}
