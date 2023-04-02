package main.java.storyArch.view;

import main.java.storyArch.controller.ArchController;
import main.java.storyArch.model.Message;
import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// Command Line View for Story Arch application
public class CommandLine implements Serializable {

    ArchController archController;

    private Scanner scanner;
    // Saving cache of data from the service layer.
    private Map<String, User> userInfo = new HashMap<>();
    private Map<String, Message> messages = new HashMap<>();
    private boolean loginStatus = false;


    public CommandLine(ArchController archController) {
        this.archController = archController;
    }

    public void main() {
        System.out.println("Hello User!");
        start();
    }

    public void startMessage() {
        System.out.println("*** Story Arch ***");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3.Save Data");
        System.out.println("4.Load Data");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
        System.out.println("******************");

    }

    public void start() {
        startLogo();
        startMessage();
        String line;
        scanner = new Scanner(System.in);
        try {
            do {
                line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> {
                            System.out.println("Login Page");
                            login();
                        }

                        case '2' -> {
                            System.out.println("Register Page");
                            register();

                        }
                        case '3' -> {
                            System.out.println("Save Data");
                            try {
                                archController.saveData();
                                System.out.println("Data Saved Successfully");
                                System.out.println("******************");
                                start();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                start();
                            }
                        }
                        case '4' -> {
                            System.out.println("Load Data");
                            try {
                                archController.loadData();
                                System.out.println("Data Loaded Successfully");
                                System.out.println("******************");
                                start();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                start();
                            }
                        }
                        case '5' -> {
                            System.out.println("Exit");
                            exit();
                        }
                        default -> System.out.println("Please enter a valid option");
                    }

                } else {
                    System.out.println("Please enter a valid option");
                    start();
                }

            } while (line.charAt(0) != '6' || line.length() != 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private void startLogo() {
        //TODO: Add  - SORT OF TEXT OF STORY ARCH

    }

    private void register() {
        System.out.println("***Welcome to the registration page***");
        System.out.println("Please enter your full-name: ");
        String fullName = scanner.nextLine().trim();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        System.out.println("Do you want the basic plan or the premium plan? (B/P)");
        String plan = scanner.nextLine().trim().toLowerCase();
        switch (plan.charAt(0)) {
            case 'b' -> {
                System.out.println("You have selected the basic plan");
                SubscriptionType subscriptionType = SubscriptionType.Free;
                // Get the date of subscription
                Date subscriptionDate = new Date();
                try {
                    String hashedPassword = stringTosh256(password);
                    archController.register(fullName, email, userName, hashedPassword, subscriptionType, subscriptionDate);
                    System.out.println("Registration Successful");
                    System.out.println("You have been registered as a free user");
                    System.out.println("""
                            Basic features are :
                            1. Support for Individual Stories
                            2. Create a maximum of 10 Projects
                            3. Upgrade to the Paid Version at your convenience\s
                            """
                    );
                    System.out.println("******************");
                    start();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    start();
                }

            }
            case 'p' -> {
                System.out.println("You have selected the premium plan");
                SubscriptionType subscriptionType = SubscriptionType.Premium;
                Date subscriptionDate = new Date();
                try {
                    String hashedPassword = stringTosh256(password);
                    archController.register(fullName, email, userName, hashedPassword, subscriptionType, subscriptionDate);
                    archController.paymentAPI();
                    System.out.println("Registration Successful");
                    System.out.println("You have been registered as a premium user");
                    System.out.println("******************");
                    start();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    start();
                }
            }
            default -> {
                System.out.println("Please enter a valid option");
                register();
            }
        }


    }

    /**
     * Hashes the password using SHA-256
     *
     * @param password - Password to be hashed
     * @return - Hashed password
     */
    private String stringTosh256(String password) {
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    private void login() {
        System.out.println("******************");
        System.out.println("***Welcome to Story-Arch Login page***");
        System.out.println("The No.1 choice for Aspiring Writers.");
        System.out.println("******************");
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        try {
            String hashedPassword = stringTosh256(password);
            userInfo = archController.login(userName, hashedPassword);
            System.out.println("Login Successful");
            System.out.println("******************");
            loginStatus = true;
            for (Map.Entry<String, User> entry : userInfo.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(userName)) {
                    if (entry.getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
                        if (initialiseSubscription()) {
                            premiumMenu();
                        } else {
                            basicMenu();
                        }
                    } else {
                        basicMenu();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            start();
        }

    }

    /**
     * To check if the subscription of a premium user is still valid or not.
     * If the subscription is expired, the user is downgraded to a basic user.
     * If the subscription is still about to expire, the user is notified.
     *
     * @return - Returns true if the subscription is valid or false if the subscription is expired
     */
    private boolean initialiseSubscription() {

        Date date = null;
        String userName = "";
        boolean status = true;
        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
            if (entry.getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
                date = entry.getValue().getSubscriptionStartDate();
                userName = entry.getKey();
            }
        }
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            Date dateAfterOneYear = cal.getTime();
            if (dateAfterOneYear.before(new Date())) {
                System.out.println("Your subscription has expired");
                System.out.println("Please renew your subscription");
                System.out.println("******************");
                try {
                    archController.updateSubscriptionType(userName, SubscriptionType.Free);
                    System.out.println("Your subscription has been updated to Free");
                    System.out.println("******************");
                    status = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        // Check if subscription (lasts about a year) is about to expire in less than month and give a warning
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.MONTH, -1);
            Date dateAfterOneYear = cal.getTime();
            if (dateAfterOneYear.before(new Date())) {
                // Get Date of present
                Date presentDate = new Date();
                archController.sendMessage(userName, "System", "Your subscription is about to expire. Please renew your subscription", presentDate);
            }
        }

        return status;
    }

    private void basicMenu() {
        System.out.println("******************");
        System.out.println("Welcome to the Basic Menu");
        System.out.println("******************");
        System.out.println("""
                1. Project Menu
                2. View My Account Details
                3. Send a message to user
                4. View My Messages
                5. Upgrade to Premium
                6. Delete My Account
                7. Logout""");
        System.out.println("******************");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    System.out.println("Project Menu");
                    //TODO : Create a project menu as per the requirements
                    //  projectMenu();
                }
                case '2' -> {
                    System.out.println("View My Account Details");
                    viewAccountDetails();
                }
                case '3' -> {
                    System.out.println("Send a message to user");
                    sendMessage();
                }
                case '4' -> {
                    System.out.println("View My Messages");
                    try {
                        String userName = "";
                        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
                            userName = entry.getKey();
                        }
                        messages = archController.viewMessage(userName);
                        archController.deleteMessage(userName);
                        if (messages.isEmpty()) {
                            System.out.println("You have no messages");
                            System.out.println("******************");
                            basicMenu();
                        } else {
                            printInformation();
                            basicMenu();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        basicMenu();
                    }
                }
                case '5' -> {
                    System.out.println("Upgrade to Premium");
                    upgradeToPremium();
                }
                case '6' -> {
                    System.out.println("Delete My Account");
                    deleteAccount();
                }
                case '7' -> {
                    System.out.println("Logging out !");
                    System.out.println("******************");
                    logout();
                    loginStatus = false;
                    userInfo.clear();
                }
                default -> System.out.println("Please enter a valid option");
            }
        } else {
            System.out.println("Please enter a valid option");
            basicMenu();
        }
    }

    private void upgradeToPremium() {
        //TODO : Upgrade to premium
        System.out.println("******************");

    }

    private void printInformation() {
        for (Map.Entry<String, Message> entry : messages.entrySet()) {
            System.out.println("Message Sender : " + entry.getValue().getFromUser());
            System.out.println("Message Date : " + entry.getValue().getTimeStamp());
            System.out.println("Message : " + entry.getValue().getMessage());
            System.out.println("******************");
        }
        System.out.println("You have " + messages.size() + " messages");
        System.out.println("******************");
        messages.clear();
    }

    private void premiumMenu() {
        System.out.println("******************");
        System.out.println("Welcome to the Premium Menu");
        System.out.println("******************");
        System.out.println("""
                1. Project Menu
                2. View My Account Details
                3. Send a message to user
                4. View My Messages
                5. Renew Subscription
                6. Delete My Account
                7. Logout""");
        System.out.println("******************");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    System.out.println("Project Menu");
                    //TODO : Create a project menu as per the requirements
                    //  projectMenu();
                }
                case '2' -> {
                    System.out.println("View My Account Details");
                    viewAccountDetails();
                }
                case '3' -> {
                    System.out.println("Send a message to user");
                    sendMessage();
                }
                case '4' -> {
                    System.out.println("View My Messages");
                    try {
                        String userName = "";
                        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
                            userName = entry.getKey();
                        }
                        messages = archController.viewMessage(userName);
                        archController.deleteMessage(userName);
                        if (messages.isEmpty()) {
                            System.out.println("You have no messages");
                            System.out.println("******************");
                            premiumMenu();
                        } else {
                            printInformation();
                            premiumMenu();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        premiumMenu();
                    }
                }
                case '5' -> {
                    System.out.println("Renew Subscription");
                    renewSubscription();
                }
                case '6' -> {
                    System.out.println("Delete My Account");
                    deleteAccount();
                }
                case '7' -> {
                    System.out.println("Logging out !");
                    System.out.println("******************");
                    logout();
                    loginStatus = false;
                    userInfo.clear();
                }
                default -> System.out.println("Please enter a valid option");
            }
        } else {
            System.out.println("Please enter a valid option");
            premiumMenu();
        }
    }

    private void renewSubscription() {
        // Premium users can renew their subscription
        System.out.println("******************");
        System.out.println("Renew Subscription");
        System.out.println("******************");
        // From the user info map get the subscriptionDate of the user
        System.out.println("Your subscription will be renewed for one year from the date of renewal");
        System.out.println("******************");
        System.out.println("Do you want to renew your subscription? (Y/N)");
        String line = scanner.nextLine().trim();
        switch (line.charAt(0)) {
            case 'y' | 'Y' -> {
                Date date = null;
                String userName = "";
                for (Map.Entry<String, User> entry : userInfo.entrySet()) {
                    if (entry.getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
                        date = entry.getValue().getSubscriptionStartDate();
                        userName = entry.getKey();
                    }
                }
                // Get Date of present
                Date presentDate = new Date();
                try {
                    archController.updateSubscriptionType(userName, SubscriptionType.Premium);
                    archController.updateSubscriptionDate(userName, presentDate);
                    archController.paymentAPI();
                    System.out.println("******************");
                    premiumMenu();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            case 'n' | 'N' -> {
                System.out.println("You have chosen not to renew your subscription");
                System.out.println("******************");
                premiumMenu();
            }
            default -> {
                System.out.println("Please enter a valid option");
                System.out.println("******************");
                premiumMenu();
            }
        }


    }

    private void sendMessage() {
        System.out.println("******************");
        System.out.println("Send a message to user");
        System.out.println("******************");
        System.out.println("Please enter the username of the user you want to send a message to: ");
        String toUser = scanner.nextLine().trim();
        System.out.println("Please enter the message you want to send: ");
        String message = scanner.nextLine().trim();
        Date date = new Date();
        String fromUser = "";
        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
            fromUser = entry.getKey();
        }
        try {
            archController.checkIfUserExists(toUser);
            archController.sendMessage(toUser, fromUser, message, date);
            System.out.println("Message sent successfully");
            System.out.println("******************");
            if (userInfo.get(fromUser).getSubscriptionType().equals(SubscriptionType.Premium)) {
                premiumMenu();
            } else {
                basicMenu();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (userInfo.get(fromUser).getSubscriptionType().equals(SubscriptionType.Premium)) {
                premiumMenu();
            } else {
                basicMenu();
            }
        }
    }

    /**
     * To view the account details of the user
     * and shows the number of days left for the subscription to expire
     */
    private void viewAccountDetails() {
        Date date = null;
        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
            date = entry.getValue().getSubscriptionStartDate();
            System.out.println("******************");
            System.out.println("User Name: " + entry.getKey());
            System.out.println("Full Name: " + entry.getValue().getFullName());
            System.out.println("Email: " + entry.getValue().getEmail());
            System.out.println("Subscription Type: " + entry.getValue().getSubscriptionType());
            System.out.println("Subscription Date: " + date);
            System.out.println("******************");
        }
        // Check how many days until subscription (lasts for a year) expires  and print the message
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            Date dateAfterOneYear = cal.getTime();
            if (dateAfterOneYear.before(new Date())) {
                // This (if-method is left blank) as it will never take place as there is a check in the initialiseSubscription method
            } else {
                System.out.println("Your subscription will expire in " + (dateAfterOneYear.getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24) + " days");
                System.out.println("******************");
            }
        }
        if (userInfo.entrySet().iterator().next().getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
            premiumMenu();
        } else {
            basicMenu();
        }
    }

    private void deleteAccount() {
        try {
            String userName = userInfo.entrySet().iterator().next().getKey();
            archController.deleteAccount(userName);
            System.out.println("Account deleted successfully");
            System.out.println("Thank you for using Story Arch!");
            System.out.println("******************");
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (userInfo.entrySet().iterator().next().getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
                premiumMenu();
            } else {
                basicMenu();
            }
        }
    }

    private void logout() {
        System.out.println("******************");
        System.out.println("You have been logged out");
        System.out.println("******************");
        loginStatus = false;
        userInfo.clear();
        start();
    }


    public void exit() {
        System.out.println("*************************");
        System.out.println("Thank you for using Story Arch!");
        System.out.println("*************************");
        System.exit(0);
    }
}
