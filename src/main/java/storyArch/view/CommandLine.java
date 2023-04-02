package main.java.storyArch.view;

import main.java.storyArch.controller.ArchController;

import java.util.Scanner;

// Command Line View for Story Arch application
public class CommandLine {

    ArchController archController;

    private Scanner scanner;

    public CommandLine(ArchController archController) {
        this.archController = archController;
    }


    // Saving cache of data from the service layer.


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
        System.out.println("Enter your choice: ");
        System.out.println("******************");

    }

    public void start() {
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

    private void register() {
        System.out.println("Please enter your username: ");

    }

    private void login() {
        System.out.println("Please enter your username: ");


    }

    public void exit() {
        System.out.println("*************************");
        System.out.println("Thank you for using Story Arch!");
        System.out.println("*************************");
        System.exit(0);
    }
}
