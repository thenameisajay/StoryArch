package main.java.storyArch.view;

import main.java.storyArch.controller.ArchController;
import main.java.storyArch.model.SubscriptionType;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;

// Command Line View for Story Arch application
public class CommandLine implements Serializable {

    ArchController archController;

    private Scanner scanner;

    public CommandLine(ArchController archController) {
        this.archController = archController;
    }


    // Saving cache of resources.data from the service layer.


    public void main() {
        System.out.println("Hello User!");
        start();
    }

    public void startMessage() {
        System.out.println("*** Story Arch ***");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("4.Save Data");
        System.out.println("5.Load Data");
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
                            System.out.println("Login");
                            // TODO :  Add login method
                            login();
                        }

                        case '2' -> {
                            System.out.println("Register");
                            register();

                        }
                        case '3' -> {
                            System.out.println("Exit");
                            exit();
                        }
                        case '4' -> {
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
                        case '5' -> {
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
                        default -> System.out.println("Please enter a valid option");
                    }

                } else {
                    System.out.println("Please enter a valid option");
                    start();
                }

            } while (line.charAt(0) != '4' || line.length() != 1);
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
        System.out.println("***Welcome to Story-Arch Login page***");
        System.out.println("The No.1 choice for Aspiring Writers.");
        System.out.println("Please enter your username: ");

    }

    public void exit() {
        System.out.println("*************************");
        System.out.println("Thank you for using Story Arch!");
        System.out.println("*************************");
        System.exit(0);
    }
}
