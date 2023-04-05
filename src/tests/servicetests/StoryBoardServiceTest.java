package tests.servicetests;

import main.java.storyArch.model.StoryBoard;
import main.java.storyArch.service.StoryBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoryBoardServiceTest {

    private StoryBoardService storyBoardService;

    @BeforeEach
    void setUp() {
        storyBoardService = new StoryBoardService();
    }

    @Test
    void testAddStoryboard() {
        storyBoardService.addStoryboard("1", "Project One", "Story One", "This is a story", new Date(), "user1");
        Map<Integer, StoryBoard> storyBoards = storyBoardService.viewStoryboardsByDate();
        assertFalse(storyBoards.isEmpty());
        assertEquals("project one", storyBoards.get(storyBoards.keySet().iterator().next()).getProjectName());
        assertEquals("story one", storyBoards.get(storyBoards.keySet().iterator().next()).getStoryName());
        assertEquals("This is a story", storyBoards.get(storyBoards.keySet().iterator().next()).getStoryDescription());
        assertEquals("user1", storyBoards.get(storyBoards.keySet().iterator().next()).getCreator());
    }

    @Test
    void testAddStoryboardWithEmptyNameAndDescription() {
        assertThrows(IllegalArgumentException.class, () -> storyBoardService.addStoryboard("1", "Project One", "", "", new Date(), "user1"));
    }

    @Test
    void testAddStoryboardWithExistingName() {
        storyBoardService.addStoryboard("1", "Project One", "Story One", "This is a story", new Date(), "user1");
        assertThrows(IllegalArgumentException.class, () -> storyBoardService.addStoryboard("1", "Project One", "Story One", "This is a story", new Date(), "user1"));
    }

    @Test
    void testSaveAndLoadData() throws IOException, ClassNotFoundException {
        storyBoardService.addStoryboard("1", "Project One", "Story One", "This is a story", new Date(), "user1");
        storyBoardService.addStoryboard("2", "Project Two", "Story Two", "This is another story", new Date(), "user2");
        storyBoardService.saveData();
        storyBoardService.loadData();
        Map<Integer, StoryBoard> storyBoards = new HashMap<>();
        storyBoards = storyBoardService.viewStoryboardsByDate();
        assertFalse(storyBoards.isEmpty());
    }

    @Test
    void testViewStoryboardsByDate() {
        storyBoardService.addStoryboard("1", "Project One", "Story One", "This is a story", new Date(2021, 1, 1), "user1");
        storyBoardService.addStoryboard("2", "Project Two", "Story Two", "This is another story", new Date(2021, 1, 2), "user2");
        Map<Integer, StoryBoard> storyBoards = storyBoardService.viewStoryboardsByDate();
        assertFalse(storyBoards.isEmpty());
    }
}
