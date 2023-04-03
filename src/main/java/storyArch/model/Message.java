package main.java.storyArch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This is used for storing the messages between users and any system messages.
 * It is used for the chat feature.
 */
public class Message implements Serializable {


    private long MessageID;
    private String toUser;

    private String fromUser;

    private String message;

    private Date timeStamp;

    public Message(long messageID, String toUser, String fromUser, String message, Date timeStamp) {
        MessageID = messageID;
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public long getMessageID() {
        return MessageID;
    }

    public void setMessageID(long messageID) {
        MessageID = messageID;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
