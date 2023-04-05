package tests.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John Doe", "johndoe@example.com", "johndoe", "password", SubscriptionType.Free, new Date());
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", user.getFullName());
    }

    @Test
    public void testSetFullName() {
        user.setFullName("Jane Doe");
        assertEquals("Jane Doe", user.getFullName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("janedoe@example.com");
        assertEquals("janedoe@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testGetUserName() {
        assertEquals("johndoe", user.getUserName());
    }

    @Test
    public void testSetUserName() {
        user.setUserName("janedoe");
        assertEquals("janedoe", user.getUserName());
    }

    @Test
    public void testGetSubscriptionType() {
        assertEquals(SubscriptionType.Free, user.getSubscriptionType());
    }

    @Test
    public void testSetSubscriptionType() {
        user.setSubscriptionType(SubscriptionType.Premium);
        assertEquals(SubscriptionType.Premium, user.getSubscriptionType());
    }

    @Test
    public void testGetSubscriptionStartDate() {
        assertNotNull(user.getSubscriptionStartDate());
    }

    @Test
    public void testSetSubscriptionStartDate() {
        Date newDate = new Date();
        user.setSubscriptionStartDate(newDate);
        assertEquals(newDate, user.getSubscriptionStartDate());
    }
}
