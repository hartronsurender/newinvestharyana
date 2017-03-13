package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectprocess_flowsteps;
import com.hartron.investharyana.repository.Projectprocess_flowstepsRepository;
import com.hartron.investharyana.service.Projectprocess_flowstepsService;
import com.hartron.investharyana.service.dto.Projectprocess_flowstepsDTO;
import com.hartron.investharyana.service.mapper.Projectprocess_flowstepsMapper;
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
import org.springframework.util.Base64Utils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Projectprocess_flowstepsResource REST controller.
 *
 * @see Projectprocess_flowstepsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Projectprocess_flowstepsResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final ByteBuffer DEFAULT_PROCESS_FLOW_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_PROCESS_FLOW_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_STEPS = "AAAAAAAAAA";
    private static final String UPDATED_STEPS = "BBBBBBBBBB";

    @Autowired
    private Projectprocess_flowstepsRepository projectprocess_flowstepsRepository;

    @Autowired
    private Projectprocess_flowstepsMapper projectprocess_flowstepsMapper;

    @Autowired
    private Projectprocess_flowstepsService projectprocess_flowstepsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectprocess_flowstepsMockMvc;

    private Projectprocess_flowsteps projectprocess_flowsteps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Projectprocess_flowstepsResource projectprocess_flowstepsResource = new Projectprocess_flowstepsResource(projectprocess_flowstepsService);
        this.restProjectprocess_flowstepsMockMvc = MockMvcBuilders.standaloneSetup(projectprocess_flowstepsResource)
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
    public static Projectprocess_flowsteps createEntity() {
        Projectprocess_flowsteps projectprocess_flowsteps = new Projectprocess_flowsteps()
                .projectid(DEFAULT_PROJECTID)
                .process_flow_document(DEFAULT_PROCESS_FLOW_DOCUMENT)
                .process_flow_documentContentType(DEFAULT_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE)
                .steps(DEFAULT_STEPS);
        return projectprocess_flowsteps;
    }

    @Before
    public void initTest() {
        projectprocess_flowstepsRepository.deleteAll();
        projectprocess_flowsteps = createEntity();
    }

    @Test
    public void createProjectprocess_flowsteps() throws Exception {
        int databaseSizeBeforeCreate = projectprocess_flowstepsRepository.findAll().size();

        // Create the Projectprocess_flowsteps
        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(projectprocess_flowsteps);

        restProjectprocess_flowstepsMockMvc.perform(post("/api/projectprocess-flowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocess_flowstepsDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectprocess_flowsteps in the database
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = projectprocess_flowstepsRepository.findAll();
        assertThat(projectprocess_flowstepsList).hasSize(databaseSizeBeforeCreate + 1);
        Projectprocess_flowsteps testProjectprocess_flowsteps = projectprocess_flowstepsList.get(projectprocess_flowstepsList.size() - 1);
        assertThat(testProjectprocess_flowsteps.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectprocess_flowsteps.getProcess_flow_document()).isEqualTo(DEFAULT_PROCESS_FLOW_DOCUMENT);
        assertThat(testProjectprocess_flowsteps.getProcess_flow_documentContentType()).isEqualTo(DEFAULT_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectprocess_flowsteps.getSteps()).isEqualTo(DEFAULT_STEPS);
    }

    @Test
    public void createProjectprocess_flowstepsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectprocess_flowstepsRepository.findAll().size();

        // Create the Projectprocess_flowsteps with an existing ID
        Projectprocess_flowsteps existingProjectprocess_flowsteps = new Projectprocess_flowsteps();
        existingProjectprocess_flowsteps.setId(UUID.randomUUID());
        Projectprocess_flowstepsDTO existingProjectprocess_flowstepsDTO = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(existingProjectprocess_flowsteps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectprocess_flowstepsMockMvc.perform(post("/api/projectprocess-flowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectprocess_flowstepsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = projectprocess_flowstepsRepository.findAll();
        assertThat(projectprocess_flowstepsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectprocess_flowsteps() throws Exception {
        // Initialize the database
        projectprocess_flowstepsRepository.save(projectprocess_flowsteps);

        // Get all the projectprocess_flowstepsList
        restProjectprocess_flowstepsMockMvc.perform(get("/api/projectprocess-flowsteps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectprocess_flowsteps.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].process_flow_documentContentType").value(hasItem(DEFAULT_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].process_flow_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROCESS_FLOW_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].steps").value(hasItem(DEFAULT_STEPS.toString())));
    }

    @Test
    public void getProjectprocess_flowsteps() throws Exception {
        // Initialize the database
        projectprocess_flowstepsRepository.save(projectprocess_flowsteps);

        // Get the projectprocess_flowsteps
        restProjectprocess_flowstepsMockMvc.perform(get("/api/projectprocess-flowsteps/{id}", projectprocess_flowsteps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectprocess_flowsteps.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.process_flow_documentContentType").value(DEFAULT_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.process_flow_document").value(Base64Utils.encodeToString(DEFAULT_PROCESS_FLOW_DOCUMENT.array())))
            .andExpect(jsonPath("$.steps").value(DEFAULT_STEPS.toString()));
    }

    @Test
    public void getNonExistingProjectprocess_flowsteps() throws Exception {
        // Get the projectprocess_flowsteps
        restProjectprocess_flowstepsMockMvc.perform(get("/api/projectprocess-flowsteps/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectprocess_flowsteps() throws Exception {
        // Initialize the database
        projectprocess_flowstepsRepository.save(projectprocess_flowsteps);
        int databaseSizeBeforeUpdate = projectprocess_flowstepsRepository.findAll().size();

        // Update the projectprocess_flowsteps
        Projectprocess_flowsteps updatedProjectprocess_flowsteps = projectprocess_flowstepsRepository.findOne(projectprocess_flowsteps.getId());
        updatedProjectprocess_flowsteps
                .projectid(UPDATED_PROJECTID)
                .process_flow_document(UPDATED_PROCESS_FLOW_DOCUMENT)
                .process_flow_documentContentType(UPDATED_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE)
                .steps(UPDATED_STEPS);
        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(updatedProjectprocess_flowsteps);

        restProjectprocess_flowstepsMockMvc.perform(put("/api/projectprocess-flowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocess_flowstepsDTO)))
            .andExpect(status().isOk());

        // Validate the Projectprocess_flowsteps in the database
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = projectprocess_flowstepsRepository.findAll();
        assertThat(projectprocess_flowstepsList).hasSize(databaseSizeBeforeUpdate);
        Projectprocess_flowsteps testProjectprocess_flowsteps = projectprocess_flowstepsList.get(projectprocess_flowstepsList.size() - 1);
        assertThat(testProjectprocess_flowsteps.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectprocess_flowsteps.getProcess_flow_document()).isEqualTo(UPDATED_PROCESS_FLOW_DOCUMENT);
        assertThat(testProjectprocess_flowsteps.getProcess_flow_documentContentType()).isEqualTo(UPDATED_PROCESS_FLOW_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectprocess_flowsteps.getSteps()).isEqualTo(UPDATED_STEPS);
    }

    @Test
    public void updateNonExistingProjectprocess_flowsteps() throws Exception {
        int databaseSizeBeforeUpdate = projectprocess_flowstepsRepository.findAll().size();

        // Create the Projectprocess_flowsteps
        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(projectprocess_flowsteps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectprocess_flowstepsMockMvc.perform(put("/api/projectprocess-flowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocess_flowstepsDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectprocess_flowsteps in the database
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = projectprocess_flowstepsRepository.findAll();
        assertThat(projectprocess_flowstepsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectprocess_flowsteps() throws Exception {
        // Initialize the database
        projectprocess_flowstepsRepository.save(projectprocess_flowsteps);
        int databaseSizeBeforeDelete = projectprocess_flowstepsRepository.findAll().size();

        // Get the projectprocess_flowsteps
        restProjectprocess_flowstepsMockMvc.perform(delete("/api/projectprocess-flowsteps/{id}", projectprocess_flowsteps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectprocess_flowsteps> projectprocess_flowstepsList = projectprocess_flowstepsRepository.findAll();
        assertThat(projectprocess_flowstepsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectprocess_flowsteps.class);
    }
}
