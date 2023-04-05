package tests.servicetests;

import main.java.storyArch.model.Chapter;
import main.java.storyArch.service.ChapterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

public class ChapterServiceTest {

    private ChapterService chapterService;

    @BeforeEach
    public void setUp() {
        chapterService = new ChapterService();
    }

    @Test
    public void testAddChapterWithEmptyChapterName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            chapterService.addChapter("storyboard1", "Story 1", "", "Chapter Description", new Date(), new Date(), "Creator", "Modifier");
        });
    }

    @Test
    public void testAddChapterWithExistingChapterName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Chapter Description", new Date(), new Date(), "Creator", "Modifier");
            chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Chapter Description", new Date(), new Date(), "Creator", "Modifier");
        });
    }

    @Test
    public void testAddChapter() {
        chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Chapter Description", new Date(), new Date(), "Creator", "Modifier");
        Map<Integer, Chapter> chapters = chapterService.viewChaptersByCreated();
        Assertions.assertEquals(1, chapters.size());
        Chapter chapter = chapters.values().iterator().next();
        Assertions.assertEquals("chapter1", chapter.getChapterName());
        Assertions.assertEquals("storyboard1", chapter.getStoryboardID());
    }

    @Test
    public void testSaveAndLoadData() throws Exception {
        chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Chapter Description", new Date(), new Date(), "Creator", "Modifier");
        chapterService.saveData();

        chapterService = new ChapterService();
        chapterService.loadData();

        Map<Integer, Chapter> chapters = chapterService.viewChaptersByCreated();
        Assertions.assertEquals(1, chapters.size());
        Chapter chapter = chapters.values().iterator().next();
        Assertions.assertEquals("chapter1", chapter.getChapterName());
        Assertions.assertEquals("storyboard1", chapter.getStoryboardID());
    }

    @Test
    public void testViewChaptersByCreated() {
        chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Chapter Description", new Date(2022, 1, 1), new Date(), "Creator", "Modifier");
        chapterService.addChapter("storyboard1", "Story 1", "Chapter2", "Chapter Description", new Date(2022, 1, 2), new Date(), "Creator", "Modifier");
        chapterService.addChapter("storyboard1", "Story 1", "Chapter3", "Chapter Description", new Date(2022, 1, 3), new Date(), "Creator", "Modifier");
        Map<Integer, Chapter> chapters = chapterService.viewChaptersByCreated();
        Assertions.assertEquals(3, chapters.size());
        Integer[] chapterIDs = chapters.keySet().toArray(new Integer[0]);
        Assertions.assertEquals("chapter1", chapters.get(chapterIDs[0]).getChapterName());
        Assertions.assertEquals("chapter2", chapters.get(chapterIDs[1]).getChapterName());
        Assertions.assertEquals("chapter3", chapters.get(chapterIDs[2]).getChapterName());
    }

    @Test
    public void testSaveToFile() throws Exception {
        chapterService.addChapter("storyboard1", "Story 1", "Chapter1", "Hello World", new Date(2022, 1, 1), new Date(), "Creator", "Modifier");
    }
}