package main.java.storyArch;

import main.java.storyArch.controller.ArchController;
import main.java.storyArch.view.CommandLine;

/**
 * This class is the main class of the application.
 */
public class MainApp {
    public static void main(String[] args) {

        // Creating an instance of the Controller Class
        ArchController archController = new ArchController();

        // Creating an instance of the View Class
        CommandLine commandLine = new CommandLine(archController);
        // Calling the main method of the View Class
        commandLine.main();
    }
}