package main.java.storyArch.api;

import java.util.Scanner;

/**
 * This is the API for the publishers
 * This is used for the publisher feature.
 * Writers should be now able to submit plots to publishers (who are external to the system) via existing APIs.
 */


public class PublisherAPI {
    // Redirection to publisher's website

    private Scanner scanner;

    public void redirectToPublisherWebsite() {
        System.out.println("Redirecting to publisher's website");
        System.out.println("1. Penguin Random House");
        System.out.println("2. HarperCollins");
        System.out.println("3. Simon & Schuster");
        System.out.println("4. Hachette Book Group");
        System.out.println("5. Macmillan");
        System.out.println("6. Scholastic");
        System.out.println("7. Little, Brown and Company");
        System.out.println("8. Random House");
        System.out.println("9. Crown Publishing Group");
    }


    public void submitStoryboardViaAPI(String storyboardID) {
        System.out.println("Storyboard submitted to publisher via API");
        redirectToPublisherWebsite();
        System.out.println("Choose a publisher to submit your storyboard to");
        scanner = new Scanner(System.in);
        String publisher = scanner.nextLine();
        if (publisher.length() == 1) {
            switch (publisher.charAt(0)) {
                case '1':
                    System.out.println("Storyboard submitted to Penguin Random House");
                    break;
                case '2':
                    System.out.println("Storyboard submitted to HarperCollins");
                    break;
                case '3':
                    System.out.println("Storyboard submitted to Simon & Schuster");
                    break;
                case '4':
                    System.out.println("Storyboard submitted to Hachette Book Group");
                    break;
                case '5':
                    System.out.println("Storyboard submitted to Macmillan");
                    break;
                case '6':
                    System.out.println("Storyboard submitted to Scholastic");
                    break;
                case '7':
                    System.out.println("Storyboard submitted to Little, Brown and Company");
                    break;
                case '8':
                    System.out.println("Storyboard submitted to Random House");
                    break;
                case '9':
                    System.out.println("Storyboard submitted to Crown Publishing Group");
                    break;
                default:
                    System.out.println("Invalid publisher");
                    break;
            }
        }
    }
}
