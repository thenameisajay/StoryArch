package main.java.storyArch.model;

import java.util.Date;

/**
 * This is used for storing the messages between users and any system messages.
 * It is used for the chat feature.
 */
public class Message {
    private String toUser;

    private String fromUser;

    private String message;

    private Date date;

    public Message(String toUser, String fromUser, String message, Date date) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.message = message;
        this.date = date;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
