package main.java.storyArch.service;

import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for storing the user information.
 * It is used for the registration and login feature.
 * It also stores the user data to a file.
 * It also loads the user data from a file.
 * It also checks for the validity of the user data.
 * It also checks for the validity of the user login.
 */
public class UserService implements Serializable {

    private Map<String, User> user = new HashMap<>();

    /**
     * Register a new user to the system
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
        user.put(userName, new User(fullName.toLowerCase(), email.toLowerCase(), userName.toLowerCase(), password, subscriptionType, subscriptionDate));
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
        if (user.size() == 0) {
            throw new IllegalArgumentException("No users registered");
        }
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
        if (user.containsKey(userName.toLowerCase())) {
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

    /**
     * Delete a user from the system
     *
     * @param userName - Username of the user
     */
    public void deleteAccount(String userName) {
        // First Check: If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users registered");
        }
        // Second Check
        if (user.containsKey(userName.toLowerCase())) {
            user.remove(userName);
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }

    }

    /**
     * Update the subscription type of user
     *
     * @param userName         - Username of the user
     * @param subscriptionType - Subscription type of the user
     */
    public void updateSubscriptionType(String userName, SubscriptionType subscriptionType) {
        // First Check: If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users registered");
        }
        // Second Check
        if (user.containsKey(userName)) {
            user.get(userName).setSubscriptionType(subscriptionType);
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }

    /**
     * Check if the user exists in the system
     *
     * @param toUser - Username of the user
     */
    public void checkIfUserExists(String toUser) {
        if (!user.containsKey(toUser.toLowerCase())) {
            throw new IllegalArgumentException("User does not exist");
        }
    }

    /**
     * Update the subscription date of user
     *
     * @param userName    - Username of the user
     * @param presentDate - Current date
     */
    public void updateSubscriptionDate(String userName, Date presentDate) {
        // First Check: If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users registered");
        }
        // Second Check
        if (user.containsKey(userName.toLowerCase())) {
            user.get(userName).setSubscriptionStartDate(presentDate);
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }

    /**
     * For testing purposes.
     *
     * @return - Returns the user data
     */
    public Map<String, User> viewUsers() {
        return user;
    }

    /**
     * For testing purposes.
     * Wipes all the user data.
     */
    public void wipeAll() {
        user.clear();
    }
}
