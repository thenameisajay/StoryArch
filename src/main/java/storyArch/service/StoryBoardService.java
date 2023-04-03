package main.java.storyArch.service;

import main.java.storyArch.model.StoryBoard;

import java.io.*;
import java.util.*;

public class StoryBoardService {

    private Map<Integer, StoryBoard> storyBoards = new HashMap<>();

    private List<Integer> storyBoardIDs = new ArrayList<>();

    public void addStoryboard(String projectID, String projectName, String storyboardName, String storyboardDescription, Date date, String creator) {
        // check if storyboard and its description are not null
        if (storyboardName == null && storyboardDescription == null || storyboardName.isEmpty() && storyboardDescription.isEmpty()) {
            throw new IllegalArgumentException("Storyboard name and description cannot be Empty");
        }
        //Check if the storyboard name already exists in the database with the same project ID
        for (StoryBoard storyBoard : storyBoards.values()) {
            String projectIDString = Integer.toString(storyBoard.getProjectID());
            if (storyBoard.getStoryName().equals(storyboardName.toLowerCase()) && projectIDString.equals(projectID)) {
                throw new IllegalArgumentException("Storyboard name already exists ! Create a new storyboard name.");
            }
        }
        // Create a random StoryBoard ID using numeric values of 7 digits
        int storyBoardID = (int) (Math.random() * 10000000);
        storyBoardIDs.add(storyBoardID);
        while (storyBoardIDs.contains(storyBoardID)) {
            storyBoardID = (int) (Math.random() * 10000000);
        }
        // Add the storyboard to the database.
        storyBoards.put(storyBoardID, new StoryBoard(Integer.parseInt(projectID), projectName.toLowerCase(), storyBoardID, storyboardName.toLowerCase(), storyboardDescription, date, creator.toLowerCase()));

    }

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/storyBoardData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(storyBoards);
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
        FileInputStream fi = new FileInputStream("src/resources/storyBoardData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
        storyBoards = (Map<Integer, StoryBoard>) oi.readObject();
        oi.close();
        fi.close();
        // Take the key value (ProjectID) and add it to the arraylist
        for (Map.Entry<Integer, StoryBoard> entry : storyBoards.entrySet()) {
            storyBoardIDs.add(entry.getKey());
        }
    }


    public Map<Integer, StoryBoard> viewStoryboardsByDate() {
        Map<Integer, StoryBoard> storyBoardsByDate = new HashMap<>();
        for (StoryBoard storyBoard : storyBoards.values()) {
            // Check for the date created and sort the storyboards by date
            storyBoardsByDate.put(storyBoard.getStoryID(), storyBoard);
        }
        return storyBoardsByDate;
    }

    public void deleteStoryboard(String storyboardID, String userName) {
        // First check for null values
        if (storyboardID == null || storyboardID.isEmpty())
            throw new IllegalArgumentException("Storyboard ID cannot be empty");
        // Check if the storyboard exists in the database
        if (!storyBoards.containsKey(Integer.parseInt(storyboardID)))
            throw new IllegalArgumentException("Storyboard does not exist");
        else {
            // Check if the user is the creator of the storyboard
            if (storyBoards.get(Integer.parseInt(storyboardID)).getCreator().equals(userName.toLowerCase())) {
                storyBoards.remove(Integer.parseInt(storyboardID));
            } else {
                throw new IllegalArgumentException("You are not the creator of this storyboard");
            }
        }
    }

    public void checkIfStoryboardExists(String storyboardID) {
        if (storyboardID == null || storyboardID.isEmpty())
            throw new IllegalArgumentException("Storyboard ID cannot be empty");
        // Check if the storyboard exists in the database
        if (!storyBoards.containsKey(Integer.parseInt(storyboardID)))
            throw new IllegalArgumentException("Storyboard does not exist");
    }
}
