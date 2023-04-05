package tests.modelTests;

import main.java.storyArch.model.Chapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ChapterTest {

    private Chapter chapter;

    @BeforeEach
    void setUp() {
        chapter = new Chapter("001", "Storyboard 1", 1, "Chapter 1", "Chapter 1 description", new Date(), new Date(), "creator", "modifier", 1.0f);
    }

    @Test
    void testGetStoryboardID() {
        Assertions.assertEquals("001", chapter.getStoryboardID());
    }

    @Test
    void testSetStoryboardID() {
        chapter.setStoryboardID("002");
        Assertions.assertEquals("002", chapter.getStoryboardID());
    }

    @Test
    void testGetStoryboardName() {
        Assertions.assertEquals("Storyboard 1", chapter.getStoryboardName());
    }

    @Test
    void testSetStoryboardName() {
        chapter.setStoryboardName("Storyboard 2");
        Assertions.assertEquals("Storyboard 2", chapter.getStoryboardName());
    }

    @Test
    void testGetChapterID() {
        Assertions.assertEquals(1, chapter.getChapterID());
    }

    @Test
    void testSetChapterID() {
        chapter.setChapterID(2);
        Assertions.assertEquals(2, chapter.getChapterID());
    }

    @Test
    void testGetChapterName() {
        Assertions.assertEquals("Chapter 1", chapter.getChapterName());
    }

    @Test
    void testSetChapterName() {
        chapter.setChapterName("Chapter 2");
        Assertions.assertEquals("Chapter 2", chapter.getChapterName());
    }

    @Test
    void testGetChapterDescription() {
        Assertions.assertEquals("Chapter 1 description", chapter.getChapterDescription());
    }

    @Test
    void testSetChapterDescription() {
        chapter.setChapterDescription("Chapter 2 description");
        Assertions.assertEquals("Chapter 2 description", chapter.getChapterDescription());
    }

    @Test
    void testGetCreatedDate() {
        Assertions.assertNotNull(chapter.getCreatedDate());
    }

    @Test
    void testSetCreatedDate() {
        Date newDate = new Date();
        chapter.setCreatedDate(newDate);
        Assertions.assertEquals(newDate, chapter.getCreatedDate());
    }

    @Test
    void testGetModifiedDate() {
        Assertions.assertNotNull(chapter.getModifiedDate());
    }

    @Test
    void testSetModifiedDate() {
        Date newDate = new Date();
        chapter.setModifiedDate(newDate);
        Assertions.assertEquals(newDate, chapter.getModifiedDate());
    }

    @Test
    void testGetCreator() {
        Assertions.assertEquals("creator", chapter.getCreator());
    }

    @Test
    void testSetCreator() {
        chapter.setCreator("new creator");
        Assertions.assertEquals("new creator", chapter.getCreator());
    }

    @Test
    void testGetLastModifiedBy() {
        Assertions.assertEquals("modifier", chapter.getLastModifiedBy());
    }

    @Test
    void testSetLastModifiedBy() {
        chapter.setLastModifiedBy("new modifier");
        Assertions.assertEquals("new modifier", chapter.getLastModifiedBy());
    }

    @Test
    void testGetVersion() {
        Assertions.assertEquals(1.0f, chapter.getVersion());
    }

    @Test
    void testSetVersion() {
        chapter.setVersion(2.0f);
        Assertions.assertEquals(2.0f, chapter.getVersion());
    }
}
