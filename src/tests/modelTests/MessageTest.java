package tests.modelTests;

import main.java.storyArch.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private Message message;

    @BeforeEach
    void setUp() {
        message = new Message(1L, "toUser", "fromUser", "Hello, how are you?", new Date());
    }

    @Test
    void testGetMessageID() {
        assertEquals(1L, message.getMessageID());
    }

    @Test
    void testSetMessageID() {
        message.setMessageID(2L);
        assertEquals(2L, message.getMessageID());
    }

    @Test
    void testGetToUser() {
        assertEquals("toUser", message.getToUser());
    }

    @Test
    void testSetToUser() {
        message.setToUser("newToUser");
        assertEquals("newToUser", message.getToUser());
    }

    @Test
    void testGetFromUser() {
        assertEquals("fromUser", message.getFromUser());
    }

    @Test
    void testSetFromUser() {
        message.setFromUser("newFromUser");
        assertEquals("newFromUser", message.getFromUser());
    }

    @Test
    void testGetMessage() {
        assertEquals("Hello, how are you?", message.getMessage());
    }

    @Test
    void testSetMessage() {
        message.setMessage("Goodbye");
        assertEquals("Goodbye", message.getMessage());
    }

    @Test
    void testGetTimeStamp() {
        assertNotNull(message.getTimeStamp());
    }

    @Test
    void testSetTimeStamp() {
        Date newTimeStamp = new Date();
        message.setTimeStamp(newTimeStamp);
        assertEquals(newTimeStamp, message.getTimeStamp());
    }
}
