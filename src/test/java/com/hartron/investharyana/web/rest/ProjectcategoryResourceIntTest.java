package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectcategory;
import com.hartron.investharyana.repository.ProjectcategoryRepository;
import com.hartron.investharyana.service.ProjectcategoryService;
import com.hartron.investharyana.service.dto.ProjectcategoryDTO;
import com.hartron.investharyana.service.mapper.ProjectcategoryMapper;
import com.hartron.investharyana.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectcategoryResource REST controller.
 *
 * @see ProjectcategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectcategoryResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PROJECTCATEGORYNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTCATEGORYNAME = "BBBBBBBBBB";

    @Autowired
    private ProjectcategoryRepository projectcategoryRepository;

    @Autowired
    private ProjectcategoryMapper projectcategoryMapper;

    @Autowired
    private ProjectcategoryService projectcategoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectcategoryMockMvc;

    private Projectcategory projectcategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectcategoryResource projectcategoryResource = new ProjectcategoryResource(projectcategoryService);
        this.restProjectcategoryMockMvc = MockMvcBuilders.standaloneSetup(projectcategoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectcategory createEntity() {
        Projectcategory projectcategory = new Projectcategory()
                .projectcategoryname(DEFAULT_PROJECTCATEGORYNAME);
        return projectcategory;
    }

    @Before
    public void initTest() {
        projectcategoryRepository.deleteAll();
        projectcategory = createEntity();
    }

    @Test
    public void createProjectcategory() throws Exception {
        int databaseSizeBeforeCreate = projectcategoryRepository.findAll().size();

        // Create the Projectcategory
        ProjectcategoryDTO projectcategoryDTO = projectcategoryMapper.projectcategoryToProjectcategoryDTO(projectcategory);

        restProjectcategoryMockMvc.perform(post("/api/projectcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectcategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectcategory in the database
        List<Projectcategory> projectcategoryList = projectcategoryRepository.findAll();
        assertThat(projectcategoryList).hasSize(databaseSizeBeforeCreate + 1);
        Projectcategory testProjectcategory = projectcategoryList.get(projectcategoryList.size() - 1);
        assertThat(testProjectcategory.getProjectcategoryname()).isEqualTo(DEFAULT_PROJECTCATEGORYNAME);
    }

    @Test
    public void createProjectcategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectcategoryRepository.findAll().size();

        // Create the Projectcategory with an existing ID
        Projectcategory existingProjectcategory = new Projectcategory();
        existingProjectcategory.setId(UUID.randomUUID());
        ProjectcategoryDTO existingProjectcategoryDTO = projectcategoryMapper.projectcategoryToProjectcategoryDTO(existingProjectcategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectcategoryMockMvc.perform(post("/api/projectcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectcategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectcategory> projectcategoryList = projectcategoryRepository.findAll();
        assertThat(projectcategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectcategories() throws Exception {
        // Initialize the database
        projectcategoryRepository.save(projectcategory);

        // Get all the projectcategoryList
        restProjectcategoryMockMvc.perform(get("/api/projectcategories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectcategory.getId().toString())))
            .andExpect(jsonPath("$.[*].projectcategoryname").value(hasItem(DEFAULT_PROJECTCATEGORYNAME.toString())));
    }

    @Test
    public void getProjectcategory() throws Exception {
        // Initialize the database
        projectcategoryRepository.save(projectcategory);

        // Get the projectcategory
        restProjectcategoryMockMvc.perform(get("/api/projectcategories/{id}", projectcategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectcategory.getId().toString()))
            .andExpect(jsonPath("$.projectcategoryname").value(DEFAULT_PROJECTCATEGORYNAME.toString()));
    }

    @Test
    public void getNonExistingProjectcategory() throws Exception {
        // Get the projectcategory
        restProjectcategoryMockMvc.perform(get("/api/projectcategories/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectcategory() throws Exception {
        // Initialize the database
        projectcategoryRepository.save(projectcategory);
        int databaseSizeBeforeUpdate = projectcategoryRepository.findAll().size();

        // Update the projectcategory
        Projectcategory updatedProjectcategory = projectcategoryRepository.findOne(projectcategory.getId());
        updatedProjectcategory
                .projectcategoryname(UPDATED_PROJECTCATEGORYNAME);
        ProjectcategoryDTO projectcategoryDTO = projectcategoryMapper.projectcategoryToProjectcategoryDTO(updatedProjectcategory);

        restProjectcategoryMockMvc.perform(put("/api/projectcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectcategoryDTO)))
            .andExpect(status().isOk());

        // Validate the Projectcategory in the database
        List<Projectcategory> projectcategoryList = projectcategoryRepository.findAll();
        assertThat(projectcategoryList).hasSize(databaseSizeBeforeUpdate);
        Projectcategory testProjectcategory = projectcategoryList.get(projectcategoryList.size() - 1);
        assertThat(testProjectcategory.getProjectcategoryname()).isEqualTo(UPDATED_PROJECTCATEGORYNAME);
    }

    @Test
    public void updateNonExistingProjectcategory() throws Exception {
        int databaseSizeBeforeUpdate = projectcategoryRepository.findAll().size();

        // Create the Projectcategory
        ProjectcategoryDTO projectcategoryDTO = projectcategoryMapper.projectcategoryToProjectcategoryDTO(projectcategory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectcategoryMockMvc.perform(put("/api/projectcategories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectcategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectcategory in the database
        List<Projectcategory> projectcategoryList = projectcategoryRepository.findAll();
        assertThat(projectcategoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectcategory() throws Exception {
        // Initialize the database
        projectcategoryRepository.save(projectcategory);
        int databaseSizeBeforeDelete = projectcategoryRepository.findAll().size();

        // Get the projectcategory
        restProjectcategoryMockMvc.perform(delete("/api/projectcategories/{id}", projectcategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectcategory> projectcategoryList = projectcategoryRepository.findAll();
        assertThat(projectcategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectcategory.class);
    }
}
