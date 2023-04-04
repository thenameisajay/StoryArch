package main.java.storyArch.service;

import main.java.storyArch.model.Chapter;

import java.io.*;
import java.util.*;

/**
 * This is the service class for the chapter.
 * All the logic that relates to the chapter is done here.
 */
public class ChapterService implements Serializable {

    private Map<Integer, Chapter> chapters = new HashMap<>();

    private List<Integer> chapterIDS = new ArrayList<>();

    /**
     * Add a chapter to the system and generate a txt file for it with the version.
     *
     * @param storyboardID       - The storyboard ID
     * @param storyboardName     - The storyboard name
     * @param chapterName        - The chapter name
     * @param chapterDescription - The chapter description
     * @param createdDate        - The date when the chapter was created
     * @param modifiedDate       - The date when the chapter was last modified
     * @param creator            - The creator of the chapter
     * @param lastModifiedBy     - The last person to modify the chapter
     */
    public void addChapter(String storyboardID, String storyboardName, String chapterName, String chapterDescription, Date createdDate, Date modifiedDate, String creator, String lastModifiedBy) {
        // Check if the chapter Name is empty
        if (chapterName.isEmpty()) {
            throw new IllegalArgumentException("Chapter Name cannot be empty");
        }
        // Check if chapter already exists for the storyboard with the same version number
        for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
            if (entry.getValue().getChapterName().equals(chapterName.toLowerCase()) && entry.getValue().getStoryboardID().equals(storyboardID)) {
                throw new IllegalArgumentException("Chapter already exists for the storyboard ! Please enter a different chapter name.");
            }
        }

        // Generate a random number and check if it exists in the map, if it does, generate another random number and check again.
        Random rand = new Random();
        int chapterID = rand.nextInt((1000000 - 1) + 1) + 1;
        this.chapterIDS.add(chapterID);
        while (this.chapterIDS.contains(chapterID)) {
            chapterID = rand.nextInt((1000000 - 1) + 1) + 1;
        }

        float version = 1.0f;

        // Add the chapter to the map
        chapters.put(chapterID, new Chapter(storyboardID, storyboardName.toLowerCase(), chapterID, chapterName.toLowerCase(), chapterDescription, createdDate, modifiedDate, creator.toLowerCase(), lastModifiedBy.toLowerCase(), version));
        try {
            saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the chapter data to a file for loading purposes.
     *
     * @throws IOException - If the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/chaptersData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(chapters);
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
        FileInputStream fi = new FileInputStream("src/resources/chaptersData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
        chapters = (Map<Integer, Chapter>) oi.readObject();
        oi.close();
        fi.close();
        // Take the key value (ProjectID) and add it to the arraylist
        for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
            chapterIDS.add(entry.getKey());
        }
    }


    /**
     * Used to save the chapters to a file - as one of the requirements, is that the chapters can be read and written to by other txt applications.
     * It is saved in the folder resources/application/
     *
     * @throws FileNotFoundException - If the file is not found
     */
    public void saveToFile() throws FileNotFoundException {
        // Save  chapters hashmap to a file (.txt) for other txt applications to read and write
        try {
            PrintWriter pWriter = new PrintWriter(new File("src/resources/application/chapters.txt"));
            for (Map.Entry<Integer, Chapter> entry : chapters.entrySet()) {
                pWriter.println("Chapter Name: " + entry.getValue().getChapterName() + "\n " + "Chapter Description: " + entry.getValue().getChapterDescription() + "\n " + "Date Created: " + entry.getValue().getCreatedDate() + " \n" + "Date Modified: " + entry.getValue().getModifiedDate() + "\n " + "Creator: " + entry.getValue().getCreator() + "\n " + "Last Modified By: " + entry.getValue().getLastModifiedBy() + "\n " + "Version: " + entry.getValue().getVersion());
            }
            pWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to view the chapters for a specific storyboard.
     *
     * @return - The chapters sorted by created date
     */
    public Map<Integer, Chapter> viewChaptersByCreated() {
        if (chapters.isEmpty())
            throw new IllegalArgumentException("No chapters to display");
        // Sort the chapters by created date
        Map<Integer, Chapter> sortedChapters = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return chapters.get(o1).getCreatedDate().compareTo(chapters.get(o2).getCreatedDate());
            }
        });
        sortedChapters.putAll(chapters);
        return sortedChapters;
    }

    /**
     * This method is used to view the chapters for a specific storyboard.
     *
     * @return - The chapters sorted by modified date
     */
    public Map<Integer, Chapter> viewChaptersByModified() {
        if (chapters.isEmpty())
            throw new IllegalArgumentException("No chapters to display");
        // Sort the chapters by modified date
        Map<Integer, Chapter> sortedChapters = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return chapters.get(o1).getModifiedDate().compareTo(chapters.get(o2).getModifiedDate());
            }
        });
        sortedChapters.putAll(chapters);
        return sortedChapters;
    }


    /**
     * This method is used for deleting a chapter and can be only deleted if the user is the creator.
     *
     * @param chapterID - The chapter ID
     * @param creator   - The creator of the chapter
     */
    public void deleteChapter(String chapterID, String creator) {
        // Check if the chapter exists
        if (!chapters.containsKey(Integer.parseInt(chapterID))) {
            throw new IllegalArgumentException("Chapter does not exist");
        }
        // Check if the user is the creator of the chapter
        if (!chapters.get(Integer.parseInt(chapterID)).getCreator().equals(creator)) {
            throw new IllegalArgumentException("You are not the creator of this chapter");
        }
        // Delete the chapter
        chapters.remove(Integer.parseInt(chapterID));
    }

    /**
     * This method is used for editing a chapter , anybody attached to the project can edit the chapter.
     *
     * @param chapterID          - The chapter ID
     * @param chapterName        - The chapter name
     * @param chapterDescription - The chapter description
     * @param modifiedBy         - The user who modified the chapter
     */
    public void editChapter(Integer chapterID, String chapterName, String chapterDescription, String modifiedBy) {
        // Check if chapter ID is empty
        if (chapterID == null) {
            throw new IllegalArgumentException("Chapter ID cannot be empty");
        }
        // Check if chapter Name is empty
        // Create a version control for the chapter from 1.0 to 1.1 etc., if the chapter already exists else create a new version control
        float version = 1.0f;
        if (chapters.containsKey(chapterID) || chapters.get(chapterID).getChapterName().equals(chapterName.toLowerCase())) {
            version = chapters.get(chapterID).getVersion() + 0.1f;
        } else {
            version = 1.0f;
        }

        // Check if the chapter exists
        if (!chapters.containsKey(chapterID)) {
            throw new IllegalArgumentException("Chapter does not exist");
        }
        // Update the chapter as a whole new chapter
        Date modifiedDate = new Date();
        chapters.put(chapterID, new Chapter(chapters.get(chapterID).getStoryboardID(), chapters.get(chapterID).getStoryboardName(), chapterID, chapters.get(chapterID).getChapterName(), chapterDescription, chapters.get(chapterID).getCreatedDate(), modifiedDate, chapters.get(chapterID).getCreator(), modifiedBy.toLowerCase(), version));
        try {
            saveToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
