package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectphase;
import com.hartron.investharyana.repository.ProjectphaseRepository;
import com.hartron.investharyana.service.ProjectphaseService;
import com.hartron.investharyana.service.dto.ProjectphaseDTO;
import com.hartron.investharyana.service.mapper.ProjectphaseMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectphaseResource REST controller.
 *
 * @see ProjectphaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectphaseResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_FCIPHASE = "AAAAAAAAAA";
    private static final String UPDATED_FCIPHASE = "BBBBBBBBBB";

    private static final String DEFAULT_FCI_PRODUCT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_FCI_PRODUCT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_FCI = "AAAAAAAAAA";
    private static final String UPDATED_FCI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FIC_IMPLEMENTATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FIC_IMPLEMENTATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ProjectphaseRepository projectphaseRepository;

    @Autowired
    private ProjectphaseMapper projectphaseMapper;

    @Autowired
    private ProjectphaseService projectphaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectphaseMockMvc;

    private Projectphase projectphase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectphaseResource projectphaseResource = new ProjectphaseResource(projectphaseService);
        this.restProjectphaseMockMvc = MockMvcBuilders.standaloneSetup(projectphaseResource)
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
    public static Projectphase createEntity() {
        Projectphase projectphase = new Projectphase()
                .projectid(DEFAULT_PROJECTID)
                .fciphase(DEFAULT_FCIPHASE)
                .fci_product_category(DEFAULT_FCI_PRODUCT_CATEGORY)
                .fci(DEFAULT_FCI)
                .fic_implementation_date(DEFAULT_FIC_IMPLEMENTATION_DATE);
        return projectphase;
    }

    @Before
    public void initTest() {
        projectphaseRepository.deleteAll();
        projectphase = createEntity();
    }

    @Test
    public void createProjectphase() throws Exception {
        int databaseSizeBeforeCreate = projectphaseRepository.findAll().size();

        // Create the Projectphase
        ProjectphaseDTO projectphaseDTO = projectphaseMapper.projectphaseToProjectphaseDTO(projectphase);

        restProjectphaseMockMvc.perform(post("/api/projectphases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectphaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectphase in the database
        List<Projectphase> projectphaseList = projectphaseRepository.findAll();
        assertThat(projectphaseList).hasSize(databaseSizeBeforeCreate + 1);
        Projectphase testProjectphase = projectphaseList.get(projectphaseList.size() - 1);
        assertThat(testProjectphase.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectphase.getFciphase()).isEqualTo(DEFAULT_FCIPHASE);
        assertThat(testProjectphase.getFci_product_category()).isEqualTo(DEFAULT_FCI_PRODUCT_CATEGORY);
        assertThat(testProjectphase.getFci()).isEqualTo(DEFAULT_FCI);
        assertThat(testProjectphase.getFic_implementation_date()).isEqualTo(DEFAULT_FIC_IMPLEMENTATION_DATE);
    }

    @Test
    public void createProjectphaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectphaseRepository.findAll().size();

        // Create the Projectphase with an existing ID
        Projectphase existingProjectphase = new Projectphase();
        existingProjectphase.setId(UUID.randomUUID());
        ProjectphaseDTO existingProjectphaseDTO = projectphaseMapper.projectphaseToProjectphaseDTO(existingProjectphase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectphaseMockMvc.perform(post("/api/projectphases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectphaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectphase> projectphaseList = projectphaseRepository.findAll();
        assertThat(projectphaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectphases() throws Exception {
        // Initialize the database
        projectphaseRepository.save(projectphase);

        // Get all the projectphaseList
        restProjectphaseMockMvc.perform(get("/api/projectphases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectphase.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].fciphase").value(hasItem(DEFAULT_FCIPHASE.toString())))
            .andExpect(jsonPath("$.[*].fci_product_category").value(hasItem(DEFAULT_FCI_PRODUCT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].fci").value(hasItem(DEFAULT_FCI.toString())))
            .andExpect(jsonPath("$.[*].fic_implementation_date").value(hasItem(sameInstant(DEFAULT_FIC_IMPLEMENTATION_DATE))));
    }

    @Test
    public void getProjectphase() throws Exception {
        // Initialize the database
        projectphaseRepository.save(projectphase);

        // Get the projectphase
        restProjectphaseMockMvc.perform(get("/api/projectphases/{id}", projectphase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectphase.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.fciphase").value(DEFAULT_FCIPHASE.toString()))
            .andExpect(jsonPath("$.fci_product_category").value(DEFAULT_FCI_PRODUCT_CATEGORY.toString()))
            .andExpect(jsonPath("$.fci").value(DEFAULT_FCI.toString()))
            .andExpect(jsonPath("$.fic_implementation_date").value(sameInstant(DEFAULT_FIC_IMPLEMENTATION_DATE)));
    }

    @Test
    public void getNonExistingProjectphase() throws Exception {
        // Get the projectphase
        restProjectphaseMockMvc.perform(get("/api/projectphases/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectphase() throws Exception {
        // Initialize the database
        projectphaseRepository.save(projectphase);
        int databaseSizeBeforeUpdate = projectphaseRepository.findAll().size();

        // Update the projectphase
        Projectphase updatedProjectphase = projectphaseRepository.findOne(projectphase.getId());
        updatedProjectphase
                .projectid(UPDATED_PROJECTID)
                .fciphase(UPDATED_FCIPHASE)
                .fci_product_category(UPDATED_FCI_PRODUCT_CATEGORY)
                .fci(UPDATED_FCI)
                .fic_implementation_date(UPDATED_FIC_IMPLEMENTATION_DATE);
        ProjectphaseDTO projectphaseDTO = projectphaseMapper.projectphaseToProjectphaseDTO(updatedProjectphase);

        restProjectphaseMockMvc.perform(put("/api/projectphases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectphaseDTO)))
            .andExpect(status().isOk());

        // Validate the Projectphase in the database
        List<Projectphase> projectphaseList = projectphaseRepository.findAll();
        assertThat(projectphaseList).hasSize(databaseSizeBeforeUpdate);
        Projectphase testProjectphase = projectphaseList.get(projectphaseList.size() - 1);
        assertThat(testProjectphase.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectphase.getFciphase()).isEqualTo(UPDATED_FCIPHASE);
        assertThat(testProjectphase.getFci_product_category()).isEqualTo(UPDATED_FCI_PRODUCT_CATEGORY);
        assertThat(testProjectphase.getFci()).isEqualTo(UPDATED_FCI);
        assertThat(testProjectphase.getFic_implementation_date()).isEqualTo(UPDATED_FIC_IMPLEMENTATION_DATE);
    }

    @Test
    public void updateNonExistingProjectphase() throws Exception {
        int databaseSizeBeforeUpdate = projectphaseRepository.findAll().size();

        // Create the Projectphase
        ProjectphaseDTO projectphaseDTO = projectphaseMapper.projectphaseToProjectphaseDTO(projectphase);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectphaseMockMvc.perform(put("/api/projectphases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectphaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectphase in the database
        List<Projectphase> projectphaseList = projectphaseRepository.findAll();
        assertThat(projectphaseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectphase() throws Exception {
        // Initialize the database
        projectphaseRepository.save(projectphase);
        int databaseSizeBeforeDelete = projectphaseRepository.findAll().size();

        // Get the projectphase
        restProjectphaseMockMvc.perform(delete("/api/projectphases/{id}", projectphase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectphase> projectphaseList = projectphaseRepository.findAll();
        assertThat(projectphaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectphase.class);
    }
}
