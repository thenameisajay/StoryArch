package tests.servicetests;

import main.java.storyArch.model.Message;
import main.java.storyArch.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageServiceTest {

    private final String userName = "JohnDoe";
    private final String system = "system";
    private final String message = "Hello, how are you?";
    private final Date presentDate = new Date();
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageService = new MessageService();
    }

    @Test
    void testSendMessage() {
        messageService.sendMessage(userName, system, message, presentDate);
        Map<Integer, Message> messages = messageService.viewMessage(userName);
        assertEquals(1, messages.size());

    }

    @Test
    void testViewMessage() {
        messageService.sendMessage(userName, system, message, presentDate);
        Map<Integer, Message> receivedMessages = messageService.viewMessage(userName);
        assertEquals(1, receivedMessages.size());

    }

    @Test
    void testDeleteMessage() {
        messageService.sendMessage(userName, system, message, presentDate);
        messageService.deleteMessage(userName);
        assertThrows(IllegalArgumentException.class, () -> {
            Map<Integer, Message> messages = messageService.viewMessage(userName);
        });
    }

    @Test
    void testSaveAndLoadData() throws IOException, ClassNotFoundException {
        messageService.sendMessage(userName, system, message, presentDate);
        messageService.saveData();
        messageService = new MessageService();
        messageService.loadData();
        Map<Integer, Message> messages = messageService.viewMessage(userName);
        assertEquals(1, messages.size());
    }
}
