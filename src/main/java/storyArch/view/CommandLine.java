package main.java.storyArch.view;

import main.java.storyArch.controller.ArchController;
import main.java.storyArch.model.*;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// Command Line View for Story Arch application
public class CommandLine implements Serializable {

    ArchController archController;

    IllustrationServices illustrationServices;

    private Scanner scanner;

    // Saving cache data from the service layer.
    private Map<String, User> userInfo = new HashMap<>();
    private Map<String, Message> messageCache = new HashMap<>();

    private Map<Integer, Project> projects = new HashMap<>();

    private Map<Integer, Project> privateProject = new HashMap<>();

    private Map<Integer, Project> sharedProjects = new HashMap<>();

    private Map<Integer, StoryBoard> storyBoard = new HashMap<>();

    private Map<Integer, Chapter> chapters = new HashMap<>();
    private boolean loginStatus = false;

    private boolean connectionStatus = false;


    public CommandLine(ArchController archController) {
        this.archController = archController;
    }

    public void main() {
        System.out.println("Hello User!");
        checkConnection();
        loadData();
        start();
    }

    private void checkConnection() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            System.out.println("You are Online.");
            System.out.println("******************");
            connectionStatus = true;
        } catch (UnknownHostException e) {
            System.out.println("You are Offline, all data will be saved locally on your device until you are online.");
            System.out.println("Connection Status: false");
            connectionStatus = false;
        }
    }

    public void startMessage() {
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3.Save Data");
        System.out.println("4. Exit");
        System.out.println("******************");
        System.out.println(">> Enter your choice: ");
        System.out.println("******************");

    }

    public void start() {
        startLogo();
        System.out.println("******************");
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
                            saveData();
                        }
                        case '4' -> {
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

    private void saveData() {
        System.out.println("Save Data");
        try {
            archController.saveData();
            System.out.println("******************");
            if (connectionStatus) {
                System.out.println("Data Saved Successfully to the Server");
            } else {
                System.out.println("Data Saved Successfully to the Local Storage and will be synced when you are online");
            }
            System.out.println("******************");
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private void loadData() {
        System.out.println("Load Data");
        try {
            archController.loadData();
            System.out.println("******************");
            if (connectionStatus) {
                System.out.println("Data Loaded Successfully from the Server");
            } else {
                System.out.println("Data Loaded Successfully from the Local Storage");
            }
            System.out.println("******************");
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private void startLogo() {
        System.out.println("******************");
        String banner = """
                     _______.___________.  ______   .______     ____    ____         ___      .______        ______  __    __ \s
                    /       |           | /  __  \\  |   _  \\    \\   \\  /   /        /   \\     |   _  \\      /      ||  |  |  |\s
                   |   (----`---|  |----`|  |  |  | |  |_)  |    \\   \\/   / ______ /  ^  \\    |  |_)  |    |  ,----'|  |__|  |\s
                    \\   \\       |  |     |  |  |  | |      /      \\_    _/ |______/  /_\\  \\   |      /     |  |     |   __   |\s
                .----)   |      |  |     |  `--'  | |  |\\  \\----.   |  |         /  _____  \\  |  |\\  \\----.|  `----.|  |  |  |\s
                |_______/       |__|      \\______/  | _| `._____|   |__|        /__/     \\__\\ | _| `._____| \\______||__|  |__|\s
                                                                                                                         v 1.0 \s
                """;


        System.out.println(banner);

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
                    String hashedPassword = stringToSHA256(password);
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
                    String hashedPassword = stringToSHA256(password);
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
    private String stringToSHA256(String password) {
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
            String hashedPassword = stringToSHA256(password);
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
                3. Send a message to a user
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

                    projectMenu();
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
                        messageCache = archController.viewMessage(userName);
                        archController.deleteMessage(userName);
                        if (messageCache.isEmpty()) {
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
                    projects.clear();
                    messageCache.clear();
                    sharedProjects.clear();
                }
                default -> System.out.println("Please enter a valid option");
            }
        } else {
            System.out.println("Please enter a valid option");
            basicMenu();
        }
    }

    private void upgradeToPremium() {
        System.out.println("******************");
        System.out.println("The Premium subscription costs £ 24.99 per year.");
        System.out.println("Do you want to upgrade to premium? (Y/N)");
        System.out.println("******************");
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case 'Y' | 'y' -> {
                    System.out.println("Upgrading to Premium");
                    System.out.println("******************");
                    try {
                        String userName = "";
                        for (Map.Entry<String, User> entry : userInfo.entrySet()) {
                            userName = entry.getKey();
                        }
                        archController.paymentAPI();
                        archController.updateSubscriptionType(userName, SubscriptionType.Premium);
                        System.out.println("Your subscription has been updated to Premium");
                        System.out.println("******************");
                        premiumMenu();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        basicMenu();
                    }
                }
                case 'N' | 'n' -> {
                    System.out.println("Not upgrading to Premium");
                    System.out.println("******************");
                    basicMenu();
                }
                default -> System.out.println("Please enter a valid option");

            }
        } else {
            System.out.println("Please enter a valid option");
            upgradeToPremium();
        }

    }

    private void printInformation() {
        for (Map.Entry<String, Message> entry : messageCache.entrySet()) {
            System.out.println("Message Sender : " + entry.getValue().getFromUser());
            System.out.println("Message Date : " + entry.getValue().getTimeStamp());
            System.out.println("Message : " + entry.getValue().getMessage());
            System.out.println("******************");
        }
        System.out.println("You have " + messageCache.size() + " messages");
        System.out.println("******************");
        messageCache.clear();
    }

    private void premiumMenu() {
        System.out.println("******************");
        System.out.println("Welcome to the Premium Menu");
        System.out.println("******************");
        System.out.println("""
                1. Project Menu
                2. View My Account Details
                3. Send a message to a user
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

                    projectMenu();
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
                        messageCache = archController.viewMessage(userName);
                        archController.deleteMessage(userName);
                        if (messageCache.isEmpty()) {
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

    private void projectMenu() {

        System.out.println("******************");
        System.out.println("Welcome to the Project Menu");
        System.out.println("******************");
        System.out.println("""
                1. Create a Project
                2. Open a Project
                3. View All Projects
                4. Delete a Project
                5. Back to Main Menu""");
        System.out.println("******************");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    createProject();
                }
                case '2' -> {
                    openProject();
                }
                case '3' -> {
                    viewMyProjects();
                }
                case '4' -> {
                    deleteProject();
                }
                case '5' -> {
                    if (userInfo.entrySet().iterator().next().getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
                        premiumMenu();
                    } else {
                        basicMenu();
                    }
                }
                default -> {
                    System.out.println("Please enter a valid option");
                    System.out.println("******************");
                    projectMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            projectMenu();
        }
    }

    private void openProject() {
        System.out.println("******************");
        System.out.println("Open a Project");
        System.out.println("******************");
        System.out.println("Please enter the ID of the project you want to open: ");
        String projectID = scanner.nextLine().trim();
        String userName = userInfo.entrySet().iterator().next().getKey();
        try {
            privateProject = archController.openProject(projectID, userName);
            System.out.println("Project opened successfully");
            System.out.println("******************");
            insideProjectMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("******************");
            projectMenu();
        }
    }

    private void deleteProject() {
        System.out.println("******************");
        System.out.println("Delete a Project");
        System.out.println("******************");
        System.out.println("Please enter the ID of the project you want to delete: ");
        String projectID = scanner.nextLine().trim();
        String creator = userInfo.entrySet().iterator().next().getKey();
        try {
            archController.deleteProject(projectID, creator);
            System.out.println("Project deleted successfully");
            System.out.println("******************");
            projectMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("******************");
            projectMenu();
        }

    }

    private void createProject() {
        List<String> teamMembers = new ArrayList<>();
        System.out.println("******************");
        System.out.println("Create a Project");
        System.out.println("******************");
        System.out.println("Please enter the name of the project: ");
        String projectName = scanner.nextLine().trim();
        System.out.println("Please enter the description of the project: ");
        String projectDescription = scanner.nextLine().trim();
        String creator = userInfo.entrySet().iterator().next().getKey();
        Date date = new Date();
        System.out.println("Do you want to make use of External Illustration Services? (Y/N)");
        // Add enum choice for external illustration services
        String choice = scanner.nextLine().trim();
        choice = choice.toLowerCase();
        if (choice.length() == 1) {
            switch (choice.charAt(0)) {
                case ('y') -> {

                    illustrationServices = IllustrationServices.YES;
                    archController.illustrationServiceAPI();
                }
                case ('n') -> {
                    illustrationServices = IllustrationServices.NO;
                    System.out.println("External Illustration Services will not be used.");
                }
                default -> {
                    System.out.println("Please enter a valid option");
                    System.out.println("******************");
                    projectMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            projectMenu();
        }
        if (userInfo.entrySet().iterator().next().getValue().getSubscriptionType().equals(SubscriptionType.Premium)) {
            System.out.println("With Premium Subscription, you can add team members to your project");
            System.out.println("Do you want to add team members to your project? (Y/N)");
            String line = scanner.nextLine().trim();
            line = line.toLowerCase();
            if (line.length() == 1) {
                switch (line.charAt(0)) {
                    case ('y') -> {
                        System.out.println("Please enter the userName of the team members you want to add and separate them using a comma: ");

                        String teamMember = scanner.nextLine().trim();
                        String[] teamMemberArray = teamMember.split(",");
                        for (String s : teamMemberArray) {
                            teamMembers.add(s.toLowerCase());
                        }
                        System.out.println("Team members added successfully");
                        try {
                            archController.createProject(projectName, projectDescription, creator, date, illustrationServices, teamMembers);
                            System.out.println("Project created successfully");
                            System.out.println("******************");
                            // If List is of team members , dissect the list and send a message to the team members that they have been added to the project.
                            if (teamMembers != null) {
                                // Send a message to the team members that they have been added to the project.
                                for (String member : teamMembers) {
                                    archController.sendMessage(member.toLowerCase(), "System", "You have been added to the project by " + creator.toLowerCase() + " to: " + projectName, date);
                                }
                            }
                            projectMenu();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("******************");
                            projectMenu();
                        }
                    }
                    case ('n') -> {
                        try {
                            archController.createProject(projectName, projectDescription, creator, date, illustrationServices, teamMembers);
                            System.out.println("Project created successfully");
                            System.out.println("******************");
                            projectMenu();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("******************");
                            projectMenu();
                        }
                    }
                    default -> {
                        System.out.println("Please enter a valid option");
                        System.out.println("******************");
                        projectMenu();
                    }
                }
            } else {
                System.out.println("Please enter a valid option");
                System.out.println("******************");
                projectMenu();
            }

        } else {
            projects = archController.getProjectByCreator(creator);
            System.out.println("With Basic Subscription, Team collaboration is not available.");
            if (projects.size() < 10) {
                System.out.println("You can create a maximum of 10 projects.");
                if (projects.size() == 0)
                    System.out.println("You can create 10 projects.");
                else if (projects.size() >= 1)
                    System.out.println("You can create " + (10 - projects.size()) + " more project.");
                try {
                    // Empty list
                    archController.createProject(projectName, projectDescription, creator, date, illustrationServices, teamMembers);
                    System.out.println("Project created successfully");
                    System.out.println("******************");
                    projectMenu();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("******************");
                    projectMenu();
                }
            } else {
                System.out.println("You have reached the maximum number of projects you can create");
                System.out.println("Please upgrade to Premium Subscription to create more projects");
                System.out.println("Or delete a project to create a new one");
                System.out.println("******************");
                projectMenu();
            }
        }


    }


    private void viewMyProjects() {
        System.out.println("******************");
        System.out.println("Viewing All Projects");
        System.out.println("******************");
        String userName = userInfo.entrySet().iterator().next().getKey();
        projects = archController.getProjectByCreator(userName);
        sharedProjects = archController.getSharedProjects(userName);
        if (projects.size() == 0 && sharedProjects.size() == 0) {
            System.out.println("You have not created any projects yet");
            System.out.println("******************");
            projectMenu();
        } else {
            if (projects.size() != 0) {
                System.out.println("Your Own Projects : ");
                for (Map.Entry<Integer, Project> entry : projects.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println("Project ID: " + entry.getKey());
                    System.out.println("Project Name: " + entry.getValue().getProjectName());
                    System.out.println("Project Description: " + entry.getValue().getProjectDescription());
                    System.out.println("Project Creator: " + entry.getValue().getCreator());
                    System.out.println("Project Creation Date: " + entry.getValue().getDate());
                    System.out.println("Is External Illustrations enabled:  " + entry.getValue().getIllustrationServices());
                    System.out.println("Project Team Members: " + entry.getValue().getTeamMembers());
                    System.out.println("*****************************");
                }
            }
            if (sharedProjects.size() != 0) {
                System.out.println("Shared Projects by others : ");
                for (Map.Entry<Integer, Project> entry : sharedProjects.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println("Project ID: " + entry.getKey());
                    System.out.println("Project Name: " + entry.getValue().getProjectName());
                    System.out.println("Project Description: " + entry.getValue().getProjectDescription());
                    System.out.println("Project Creator: " + entry.getValue().getCreator());
                    System.out.println("Project Creation Date: " + entry.getValue().getDate());
                    System.out.println("Is External Illustrations enabled:  " + entry.getValue().getIllustrationServices());
                    System.out.println("Project Team Members: " + entry.getValue().getTeamMembers());
                    System.out.println("*****************************");
                }
            }
        }
        projects.clear();
        projectMenu();
    }

    private void insideProjectMenu() {
        String projectName = "";
        System.out.println("******************");
        for (Map.Entry<Integer, Project> entry : privateProject.entrySet()) {
            projectName = entry.getValue().getProjectName();
        }
        System.out.println(" " + projectName.toUpperCase() + "");
        System.out.println("******************");
        System.out.println("1. Add a Storyboard");
        System.out.println("2. Open a Storyboard");
        System.out.println("3. View Project Details");
        System.out.println("4. View Storyboards");
        System.out.println("5. Delete a Storyboard");
        System.out.println("6.Submit a Storyboard to Publisher");
        System.out.println("7. Back to Project Menu");
        System.out.println("******************");
        System.out.println("Please enter an option");
        String option = scanner.nextLine();
        if (option.length() == 1) {
            switch (option.charAt(0)) {
                case '1' -> {
                    addStoryboard();
                }
                case '2' -> {
                    openStoryboard();
                }
                case '3' -> {
                    viewProjectDetails();
                }
                case '4' -> {
                    viewStoryboards();
                }
                case '5' -> {
                    deleteStoryboard();
                }
                case '6' -> {
                    submitStoryboardViaAPI();
                }
                case '7' -> {
                    projectMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            insideProjectMenu();
        }
    }

    private void submitStoryboardViaAPI() {
        System.out.println("******************");
        System.out.println("Submitting a Storyboard to a Publisher");
        System.out.println("******************");
        System.out.println("Please enter the storyboard ID");
        String storyboardID = scanner.nextLine();
        try {
            archController.checkIfStoryboardExists(storyboardID);
            archController.submitStoryboardViaAPI(storyboardID);
            System.out.println("Storyboard submitted successfully");
            insideProjectMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideProjectMenu();
        }
    }

    private void openStoryboard() {
        System.out.println("******************");
        System.out.println("Opening a Storyboard");
        System.out.println("******************");
        System.out.println("Please enter the storyboard ID");
        String storyboardID = scanner.nextLine();
        try {
            archController.checkIfStoryboardExists(storyboardID);
            storyBoard = archController.getStoryboard(storyboardID);
            insideStoryboardMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideProjectMenu();
        }

    }

    private void deleteStoryboard() {
        System.out.println("******************");
        System.out.println("Deleting a Storyboard");
        System.out.println("******************");
        System.out.println("Please enter the storyboard ID");
        String storyboardID = scanner.nextLine();
        String userName = userInfo.entrySet().iterator().next().getKey();
        try {
            archController.deleteStoryboard(storyboardID, userName);
            System.out.println("Storyboard deleted successfully");
            insideProjectMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideProjectMenu();
        }
    }

    private void addStoryboard() {
        String projectID = "";
        String projectName = "";
        String creator = userInfo.entrySet().iterator().next().getKey();
        Date date = new Date();
        System.out.println("******************");
        System.out.println("Adding a Storyboard");
        System.out.println("******************");
        System.out.println("Please enter the storyboard name");
        String storyboardName = scanner.nextLine();
        System.out.println("Please enter the storyboard description");
        String storyboardDescription = scanner.nextLine();
        for (Map.Entry<Integer, Project> entry : privateProject.entrySet()) {
            projectID = String.valueOf(entry.getKey());
            projectName = entry.getValue().getProjectName();
        }
        try {
            archController.addStoryboard(projectID, projectName, storyboardName, storyboardDescription, date, creator);
            System.out.println("Storyboard added successfully");
            insideProjectMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideProjectMenu();
        }
    }


    private void viewStoryboards() {
        System.out.println("******************");
        System.out.println("Viewing Storyboards");
        System.out.println("******************");
        System.out.println("******************");
        System.out.println("1. View Storyboards");
        System.out.println("2. Back to Project Menu");
        System.out.println("******************");
        System.out.println("Please enter an option");
        String option = scanner.nextLine();
        if (option.length() == 1) {
            switch (option.charAt(0)) {

                case '1' -> {
                    viewStoryboardsAll();
                }
                case '2' -> {
                    insideProjectMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            viewStoryboards();
        }
    }

    private void viewStoryboardsAll() {
        System.out.println("******************");
        System.out.println("Viewing All Storyboards inside the Project");
        System.out.println("******************");
        try {
            storyBoard = archController.viewStoryboardsByAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            viewStoryboards();
        }
        if (storyBoard.isEmpty()) {
            System.out.println("No Storyboards found");
            viewStoryboards();
        }
        for (Map.Entry<Integer, StoryBoard> entry : storyBoard.entrySet()) {
            System.out.println("Storyboard ID: " + entry.getKey());
            System.out.println("Storyboard Name: " + entry.getValue().getStoryName());
            System.out.println("Storyboard Description: " + entry.getValue().getStoryDescription());
            System.out.println("Storyboard Creation Date: " + entry.getValue().getCreationDate());
            System.out.println("Storyboard Creator: " + entry.getValue().getCreator());
            System.out.println("*****************************");
        }
        insideProjectMenu();
    }

    private void viewProjectDetails() {
        System.out.println("******************");
        System.out.println("Viewing Project Details");
        System.out.println("******************");
        for (Map.Entry<Integer, Project> entry : privateProject.entrySet()) {
            System.out.println("Project ID: " + entry.getKey());
            System.out.println("Project Name: " + entry.getValue().getProjectName());
            System.out.println("Project Description: " + entry.getValue().getProjectDescription());
            System.out.println("Project Creator: " + entry.getValue().getCreator());
            System.out.println("Project Creation Date: " + entry.getValue().getDate());
            System.out.println("Is External Illustrations enabled:  " + entry.getValue().getIllustrationServices());
            System.out.println("Project Team Members: " + entry.getValue().getTeamMembers());
            System.out.println("*****************************");
        }
        System.out.println("******************");
        insideProjectMenu();
    }

    private void insideStoryboardMenu() {
        // TODO : ADD CHAPTER , EDIT CHAPTER , DELETE CHAPTER , BACK TO PROJECT MENU
        String storyboardName = "";
        System.out.println("******************");
        for (Map.Entry<Integer, StoryBoard> entry : storyBoard.entrySet()) {
            storyboardName = entry.getValue().getStoryName();
        }
        System.out.println(" " + storyboardName.toUpperCase() + " ");
        System.out.println("******************");
        System.out.println("1. Add a Chapter");
        System.out.println("2. Edit a Chapter");
        System.out.println("3. Delete a Chapter");
        System.out.println("4. View Chapters");
        System.out.println("5. Back to Project Menu");
        System.out.println("******************");
        System.out.println("Please enter an option");
        String option = scanner.nextLine();
        if (option.length() == 1) {
            switch (option.charAt(0)) {
                case '1' -> {

                    addChapter();
                }
                case '2' -> {
                    editChapter();
                }
                case '3' -> {
                    deleteChapter();
                }
                case '4' -> {
                    viewChapters();
                }
                case '5' -> {
                    insideProjectMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            insideStoryboardMenu();
        }
    }


    private void addChapter() {
        System.out.println("******************");
        System.out.println("Adding a Chapter");
        System.out.println("******************");
        System.out.println("Please enter the chapter name");
        String chapterName = scanner.nextLine();
        System.out.println("Please enter the chapter description");
        String chapterDescription = scanner.nextLine();
        String storyboardID = "";
        String storyboardName = "";
        for (Map.Entry<Integer, StoryBoard> entry : storyBoard.entrySet()) {
            storyboardID = String.valueOf(entry.getKey());
            storyboardName = entry.getValue().getStoryName();
        }
        String creator = userInfo.entrySet().iterator().next().getKey();
        String lastModifiedBy = userInfo.entrySet().iterator().next().getKey();
        Date createdDate = new Date();
        Date modifiedDate = new Date();
        try {
            archController.addChapter(storyboardID, storyboardName, chapterName, chapterDescription, createdDate, modifiedDate, creator, lastModifiedBy);
            System.out.println("Chapter added successfully");
            insideStoryboardMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideStoryboardMenu();
        }

    }


    private void editChapter() {
        System.out.println("******************");
        System.out.println("Editing a Chapter");
        System.out.println("******************");
        System.out.println("Please enter the chapter ID");
        String chapterID = scanner.nextLine();
        String chapterName = "";
        for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
            chapterName = entry.getValue().getChapterName();
        }
        System.out.println("Please enter the chapter description");
        String chapterDescription = scanner.nextLine();
        String modifiedBy = "";
        modifiedBy = userInfo.entrySet().iterator().next().getValue().getUserName();
        try {
            archController.editChapter(Integer.valueOf(chapterID), chapterName, chapterDescription, modifiedBy);
            System.out.println("Chapter edited successfully");
            insideStoryboardMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideStoryboardMenu();
        }
    }

    private void deleteChapter() {
        System.out.println("******************");
        System.out.println("Deleting a Chapter");
        System.out.println("******************");
        System.out.println("Please enter the chapter ID");
        String chapterID = scanner.nextLine();
        String creator = "";
        for (Map.Entry<Integer, StoryBoard> entry : storyBoard.entrySet()) {
            creator = entry.getValue().getCreator();
        }
        try {
            archController.deleteChapter(chapterID, creator);
            System.out.println("Chapter deleted successfully");
            insideStoryboardMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            insideStoryboardMenu();
        }
    }

    private void viewChapters() {
        System.out.println("******************");
        System.out.println("Viewing Chapters");
        System.out.println("******************");
        System.out.println("1.Do you want to view by Chapter Order (Created)");
        System.out.println("2.Do you want to view by Chapter Order (Modified)");
        System.out.println("3.Back to Storyboard Menu");
        System.out.println("******************");
        System.out.println("Please enter an option");
        String option = scanner.nextLine();
        if (option.length() == 1) {
            switch (option.charAt(0)) {
                case '1' -> {
                    viewChaptersByCreated();
                }
                case '2' -> {
                    viewChaptersByModified();
                }
                case '3' -> {
                    insideStoryboardMenu();
                }
            }
        } else {
            System.out.println("Please enter a valid option");
            System.out.println("******************");
            viewChapters();
        }
    }


    private void viewChaptersByCreated() {
        System.out.println("******************");
        System.out.println("Viewing Chapters by Created Order");
        System.out.println("******************");
        try {
            chapters = archController.viewChaptersByCreated();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            viewChapters();
        }
        if (chapters.isEmpty()) {
            System.out.println("No Chapters found");
            viewChapters();
        }
        for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
            System.out.println("Chapter ID: " + entry.getKey());
            System.out.println("Chapter Name: " + entry.getValue().getChapterName());
            System.out.println("Chapter Creation Date: " + entry.getValue().getCreatedDate());
            System.out.println("Chapter Creator: " + entry.getValue().getCreator());
            System.out.println("Version: " + entry.getValue().getVersion());
            System.out.println("*****************************");
        }
        insideStoryboardMenu();

    }

    private void viewChaptersByModified() {
        System.out.println("******************");
        System.out.println("Viewing Chapters by Modified Order");
        System.out.println("******************");
        try {
            chapters = archController.viewChaptersByModified();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            viewChapters();
        }
        if (chapters.isEmpty()) {
            System.out.println("No Chapters found");
            viewChapters();
        }
        for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
            System.out.println("Chapter ID: " + entry.getKey());
            System.out.println("Chapter Name: " + entry.getValue().getChapterName());
            System.out.println("Chapter Creation Date: " + entry.getValue().getCreatedDate());
            System.out.println("Modified Date: " + entry.getValue().getModifiedDate());
            System.out.println("Modified By: " + entry.getValue().getLastModifiedBy());
            System.out.println("Chapter Creator: " + entry.getValue().getCreator());
            System.out.println("Version: " + entry.getValue().getVersion());
            System.out.println("*****************************");
        }
        insideStoryboardMenu();

    }


}
