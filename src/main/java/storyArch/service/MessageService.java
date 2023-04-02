package main.java.storyArch.service;

import main.java.storyArch.model.Message;

import java.io.*;
import java.util.*;

public class MessageService implements Serializable {
    private Map<Integer, Message> messages = new HashMap<>();
    ArrayList<Integer> messageIDS = new ArrayList<>();


    /**
     * Save the user data to a file
     *
     * @throws IOException - If the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/resources/messageData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(messages);
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
        FileInputStream fi = new FileInputStream("src/resources/messageData.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        // Read objects
        messages = (Map<Integer, Message>) oi.readObject();
        oi.close();
        fi.close();
        // Take the key value (MessageID) and add it to the arraylist
        for (Map.Entry<Integer, Message> entry : messages.entrySet()) {
            messageIDS.add(entry.getKey());
        }
    }

    public void sendMessage(String userName, String system, String message, Date presentDate) {
        // First Check : For empty fields
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        // Add the Message to the Map using a random generated number as the key , store the message in the map with the key.
        // Generate a random number and check if it exists in the map, if it does, generate another random number and check again.
        Random rand = new Random();
        int randomNum = rand.nextInt((1000000 - 1) + 1) + 1;
        messageIDS.add(randomNum);
        while (messageIDS.contains(randomNum)) {
            randomNum = rand.nextInt((1000000 - 1) + 1) + 1;
        }
        messages.put(randomNum, new Message(userName.toLowerCase(), system.toLowerCase(), message, presentDate));
    }


    public Map<String,Message> viewMessage(String userName){
      Map<String,Message> returnMessage = new HashMap<>();
      if (messages.size() == 0) {
            throw new IllegalArgumentException("No messages to display");
        }
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        for (Map.Entry<Integer, Message> entry : messages.entrySet()) {
            if (entry.getValue().getToUser().equals(userName.toLowerCase())) {
                returnMessage.put(entry.getKey().toString(), entry.getValue());
            }
        }
        return returnMessage;
    }

    public void deleteMessage(String userName) {
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        for (Map.Entry<Integer, Message> entry : messages.entrySet()) {
            if (entry.getValue().getToUser().equals(userName.toLowerCase())) {
                messages.remove(entry.getKey());
            }
        }
    }
}
