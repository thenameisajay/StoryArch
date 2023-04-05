package tests.servicetests;

import main.java.storyArch.model.IllustrationServices;
import main.java.storyArch.model.Project;
import main.java.storyArch.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectServiceTest {

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectService = new ProjectService();
    }

    @Test
    void testGetProjectByCreator() {
        projectService.createProject("Project 1", "Description", "creator1", new Date(), IllustrationServices.YES, null);
        projectService.createProject("Project 2", "Description", "creator2", new Date(), IllustrationServices.YES, null);
        projectService.createProject("Project 3", "Description", "creator1", new Date(), IllustrationServices.NO, null);

        Map<Integer, Project> projectsByCreator = projectService.getProjectByCreator("creator1");
        assertEquals(2, projectsByCreator.size());

    }

    @Test
    void testCreateProject() {
        projectService.createProject("Project 1", "Description", "creator1", new Date(), IllustrationServices.YES, null);
        projectService.createProject("Project 2", "Description", "creator2", new Date(), IllustrationServices.YES, null);

        assertThrows(IllegalArgumentException.class, () ->
                projectService.createProject("Project 1", "Description", "creator3", new Date(), IllustrationServices.YES, null));

        assertThrows(IllegalArgumentException.class, () ->
                projectService.createProject("Project 3", "Description", "creator2", new Date(), IllustrationServices.YES, List.of("creator2")));
    }

    @Test
    void testSaveAndLoadData() throws Exception {
        projectService.createProject("Project 1", "Description", "creator1", new Date(), IllustrationServices.YES, null);
        projectService.createProject("Project 2", "Description", "creator2", new Date(), IllustrationServices.YES, null);

        projectService.saveData();
        projectService.loadData();

        Map<Integer, Project> projects = projectService.getProjectByCreator("creator1");
        assertEquals(1, projects.size());

    }

    @Test
    void testGetSharedProjects() {
        projectService.createProject("Project 1", "Description", "creator1", new Date(), IllustrationServices.YES, Arrays.asList("creator2", "user1"));
        projectService.createProject("Project 2", "Description", "creator2", new Date(), IllustrationServices.NO, Arrays.asList("creator1", "user1"));
        projectService.createProject("Project 3", "Description", "creator1", new Date(), IllustrationServices.YES, null);

        Map<Integer, Project> sharedProjects = projectService.getSharedProjects("user1");
        assertEquals(2, sharedProjects.size());

    }
}

