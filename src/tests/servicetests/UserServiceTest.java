package tests.servicetests;

import main.java.storyArch.model.SubscriptionType;
import main.java.storyArch.model.User;
import main.java.storyArch.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    Map<String, User> user = new HashMap<>();
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();

    }

    @AfterEach
    public void tearDown() throws IOException {
        userService.saveData();
        Path path = Paths.get("src/resources/userData.ser");
        Files.deleteIfExists(path);
    }

    @Test
    public void testRegister() {
        userService.register("Alice Johnson", "alice@test.com", "alice", "password123", SubscriptionType.Premium, new Date());
        Map<String, User> users = userService.viewUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    public void testRegisterThrowsExceptionWhenAllFieldsAreNotProvided() {
        assertThrows(IllegalArgumentException.class, () -> userService.register("", "", "", "", SubscriptionType.Free, new Date()));
    }

    @Test
    public void testRegisterThrowsExceptionWhenFullNameContainsNumbers() {
        assertThrows(IllegalArgumentException.class, () -> userService.register("Alice Johnson 123", "alice@test.com", "alice", "password123", SubscriptionType.Free, new Date()));
    }

    @Test
    public void testRegisterThrowsExceptionWhenEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> userService.register("Alice Johnson", "alice.com", "alice", "password123", SubscriptionType.Free, new Date()));
    }

    @Test
    public void testRegisterThrowsExceptionWhenUsernameContainsSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> userService.register("Alice Johnson", "alice@test.com", "alice!", "password123", SubscriptionType.Free, new Date()));
    }

    @Test
    public void testRegisterThrowsExceptionWhenUsernameIsAlreadyTaken() {
        userService.register("Alice Johnson", "alice@test.com", "alice", "password123", SubscriptionType.Free, new Date());
        assertThrows(IllegalArgumentException.class, () -> userService.register("Bob Johnson", "bob@test.com", "alice", "password123", SubscriptionType.Free, new Date()));
    }

    @Test
    public void testSaveData() throws IOException {
        userService.register("Alice Johnson", "alice@test.com", "alice", "password123", SubscriptionType.Free, new Date());
        userService.saveData();
        assertTrue(Files.exists(Paths.get("src/resources/userData.ser")));
    }

    @Test
    public void testLoadData() throws IOException, ClassNotFoundException {
        userService.register("Alice Johnson", "alice@test.com", "alice", "password123", SubscriptionType.Free, new Date());
        userService.saveData();
        userService.wipeAll();
        userService.loadData();

        user = userService.viewUsers();

        assertEquals(1, user.size());
    }

    @Test
    public void testLogin() {
        userService.register("Alice Johnson", "alice@test.com", "alice", "password123", SubscriptionType.Free, new Date());
        Map<String, User> loggedInUser = userService.login("alice", "password123");

        assertEquals(1, loggedInUser.size());
        assertTrue(loggedInUser.containsKey("alice"));
    }

    @Test
    public void testLoginThrowsExceptionWhenUsernameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> userService.login("", "password123"));
    }

    @Test
    public void testLoginThrowsExceptionWhenPasswordIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> userService.login("alice", ""));
    }

    @Test
    void testCheckIfUserExists() {
        // Add a user
        userService.register("John Doe", "john.doe@example.com", "johndoe", "password", SubscriptionType.Free, new Date());
        // Check if user exists
        assertDoesNotThrow(() -> userService.checkIfUserExists("johndoe"));
        assertThrows(IllegalArgumentException.class, () -> userService.checkIfUserExists("janedoe"));
    }

    @Test
    void testSaveAndLoadData() throws IOException, ClassNotFoundException {
        // Add a user
        userService.register("John Doe", "john.doe@example.com", "johndoe", "password", SubscriptionType.Free, new Date());
        // Save user data to file
        assertDoesNotThrow(() -> userService.saveData());
        // Clear user data
        userService.wipeAll();
        // Load user data from file
        assertDoesNotThrow(() -> userService.loadData());
        // Check if user exists
        assertDoesNotThrow(() -> userService.checkIfUserExists("johndoe"));
    }

    @Test
    void testDeleteAccount() {
        // Add a user
        userService.register("John Doe", "john.doe@example.com", "johndoe", "password", SubscriptionType.Free, new Date());
        // Delete user account
        assertDoesNotThrow(() -> userService.deleteAccount("johndoe"));
        // Check if user exists
        assertThrows(IllegalArgumentException.class, () -> userService.checkIfUserExists("johndoe"));
    }

}
