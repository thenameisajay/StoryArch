package tests.modelTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.storyArch.model.StoryBoard;

public class StoryBoardTest {
    private StoryBoard storyBoard;

    @BeforeEach
    public void setUp() {
        storyBoard = new StoryBoard(1, "Project", 2, "Story", "Description", new Date(), "Creator");
    }

    @Test
    public void testGetProjectID() {
        assertEquals(1, storyBoard.getProjectID());
    }

    @Test
    public void testSetProjectID() {
        storyBoard.setProjectID(3);
        assertEquals(3, storyBoard.getProjectID());
    }

    @Test
    public void testGetProjectName() {
        assertEquals("Project", storyBoard.getProjectName());
    }

    @Test
    public void testSetProjectName() {
        storyBoard.setProjectName("New Project");
        assertEquals("New Project", storyBoard.getProjectName());
    }

    @Test
    public void testGetStoryID() {
        assertEquals(2, storyBoard.getStoryID());
    }

    @Test
    public void testSetStoryID() {
        storyBoard.setStoryID(4);
        assertEquals(4, storyBoard.getStoryID());
    }

    @Test
    public void testGetStoryName() {
        assertEquals("Story", storyBoard.getStoryName());
    }

    @Test
    public void testSetStoryName() {
        storyBoard.setStoryName("New Story");
        assertEquals("New Story", storyBoard.getStoryName());
    }

    @Test
    public void testGetStoryDescription() {
        assertEquals("Description", storyBoard.getStoryDescription());
    }

    @Test
    public void testSetStoryDescription() {
        storyBoard.setStoryDescription("New Description");
        assertEquals("New Description", storyBoard.getStoryDescription());
    }

    @Test
    public void testGetCreationDate() {
        assertNotNull(storyBoard.getCreationDate());
    }

    @Test
    public void testSetCreationDate() {
        Date newDate = new Date();
        storyBoard.setCreationDate(newDate);
        assertEquals(newDate, storyBoard.getCreationDate());
    }

    @Test
    public void testGetCreator() {
        assertEquals("Creator", storyBoard.getCreator());
    }

    @Test
    public void testSetCreator() {
        storyBoard.setCreator("New Creator");
        assertEquals("New Creator", storyBoard.getCreator());
    }

    @Test
    public void testConstructor() {
        assertNotNull(storyBoard);
    }

    @Test
    public void testNullConstructor() {
        StoryBoard nullBoard = new StoryBoard(0, null, 0, null, null, null, null);
        assertNotNull(nullBoard);
        assertNull(nullBoard.getProjectName());
        assertNull(nullBoard.getStoryName());
        assertNull(nullBoard.getStoryDescription());
        assertNull(nullBoard.getCreationDate());
        assertNull(nullBoard.getCreator());
    }
}
