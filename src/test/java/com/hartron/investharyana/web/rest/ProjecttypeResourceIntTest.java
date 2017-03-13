package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projecttype;
import com.hartron.investharyana.repository.ProjecttypeRepository;
import com.hartron.investharyana.service.ProjecttypeService;
import com.hartron.investharyana.service.dto.ProjecttypeDTO;
import com.hartron.investharyana.service.mapper.ProjecttypeMapper;
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
 * Test class for the ProjecttypeResource REST controller.
 *
 * @see ProjecttypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjecttypeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PROJECTTYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTTYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private ProjecttypeRepository projecttypeRepository;

    @Autowired
    private ProjecttypeMapper projecttypeMapper;

    @Autowired
    private ProjecttypeService projecttypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjecttypeMockMvc;

    private Projecttype projecttype;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjecttypeResource projecttypeResource = new ProjecttypeResource(projecttypeService);
        this.restProjecttypeMockMvc = MockMvcBuilders.standaloneSetup(projecttypeResource)
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
    public static Projecttype createEntity() {
        Projecttype projecttype = new Projecttype()
                .projecttype_name(DEFAULT_PROJECTTYPE_NAME);
        return projecttype;
    }

    @Before
    public void initTest() {
        projecttypeRepository.deleteAll();
        projecttype = createEntity();
    }

    @Test
    public void createProjecttype() throws Exception {
        int databaseSizeBeforeCreate = projecttypeRepository.findAll().size();

        // Create the Projecttype
        ProjecttypeDTO projecttypeDTO = projecttypeMapper.projecttypeToProjecttypeDTO(projecttype);

        restProjecttypeMockMvc.perform(post("/api/projecttypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projecttypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Projecttype in the database
        List<Projecttype> projecttypeList = projecttypeRepository.findAll();
        assertThat(projecttypeList).hasSize(databaseSizeBeforeCreate + 1);
        Projecttype testProjecttype = projecttypeList.get(projecttypeList.size() - 1);
        assertThat(testProjecttype.getProjecttype_name()).isEqualTo(DEFAULT_PROJECTTYPE_NAME);
    }

    @Test
    public void createProjecttypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projecttypeRepository.findAll().size();

        // Create the Projecttype with an existing ID
        Projecttype existingProjecttype = new Projecttype();
        existingProjecttype.setId(UUID.randomUUID());
        ProjecttypeDTO existingProjecttypeDTO = projecttypeMapper.projecttypeToProjecttypeDTO(existingProjecttype);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjecttypeMockMvc.perform(post("/api/projecttypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjecttypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projecttype> projecttypeList = projecttypeRepository.findAll();
        assertThat(projecttypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjecttypes() throws Exception {
        // Initialize the database
        projecttypeRepository.save(projecttype);

        // Get all the projecttypeList
        restProjecttypeMockMvc.perform(get("/api/projecttypes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projecttype.getId().toString())))
            .andExpect(jsonPath("$.[*].projecttype_name").value(hasItem(DEFAULT_PROJECTTYPE_NAME.toString())));
    }

    @Test
    public void getProjecttype() throws Exception {
        // Initialize the database
        projecttypeRepository.save(projecttype);

        // Get the projecttype
        restProjecttypeMockMvc.perform(get("/api/projecttypes/{id}", projecttype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projecttype.getId().toString()))
            .andExpect(jsonPath("$.projecttype_name").value(DEFAULT_PROJECTTYPE_NAME.toString()));
    }

    @Test
    public void getNonExistingProjecttype() throws Exception {
        // Get the projecttype
        restProjecttypeMockMvc.perform(get("/api/projecttypes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjecttype() throws Exception {
        // Initialize the database
        projecttypeRepository.save(projecttype);
        int databaseSizeBeforeUpdate = projecttypeRepository.findAll().size();

        // Update the projecttype
        Projecttype updatedProjecttype = projecttypeRepository.findOne(projecttype.getId());
        updatedProjecttype
                .projecttype_name(UPDATED_PROJECTTYPE_NAME);
        ProjecttypeDTO projecttypeDTO = projecttypeMapper.projecttypeToProjecttypeDTO(updatedProjecttype);

        restProjecttypeMockMvc.perform(put("/api/projecttypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projecttypeDTO)))
            .andExpect(status().isOk());

        // Validate the Projecttype in the database
        List<Projecttype> projecttypeList = projecttypeRepository.findAll();
        assertThat(projecttypeList).hasSize(databaseSizeBeforeUpdate);
        Projecttype testProjecttype = projecttypeList.get(projecttypeList.size() - 1);
        assertThat(testProjecttype.getProjecttype_name()).isEqualTo(UPDATED_PROJECTTYPE_NAME);
    }

    @Test
    public void updateNonExistingProjecttype() throws Exception {
        int databaseSizeBeforeUpdate = projecttypeRepository.findAll().size();

        // Create the Projecttype
        ProjecttypeDTO projecttypeDTO = projecttypeMapper.projecttypeToProjecttypeDTO(projecttype);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjecttypeMockMvc.perform(put("/api/projecttypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projecttypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Projecttype in the database
        List<Projecttype> projecttypeList = projecttypeRepository.findAll();
        assertThat(projecttypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjecttype() throws Exception {
        // Initialize the database
        projecttypeRepository.save(projecttype);
        int databaseSizeBeforeDelete = projecttypeRepository.findAll().size();

        // Get the projecttype
        restProjecttypeMockMvc.perform(delete("/api/projecttypes/{id}", projecttype.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projecttype> projecttypeList = projecttypeRepository.findAll();
        assertThat(projecttypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projecttype.class);
    }
}
