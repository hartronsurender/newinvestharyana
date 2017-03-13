package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectrawmaterial;
import com.hartron.investharyana.repository.ProjectrawmaterialRepository;
import com.hartron.investharyana.service.ProjectrawmaterialService;
import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;
import com.hartron.investharyana.service.mapper.ProjectrawmaterialMapper;
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
 * Test class for the ProjectrawmaterialResource REST controller.
 *
 * @see ProjectrawmaterialResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectrawmaterialResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_RAW_MATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAW_MATERIAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_RAW_QUANTITY = 1;
    private static final Integer UPDATED_RAW_QUANTITY = 2;

    private static final UUID DEFAULT_RAW_UNIT = UUID.randomUUID();
    private static final UUID UPDATED_RAW_UNIT = UUID.randomUUID();

    @Autowired
    private ProjectrawmaterialRepository projectrawmaterialRepository;

    @Autowired
    private ProjectrawmaterialMapper projectrawmaterialMapper;

    @Autowired
    private ProjectrawmaterialService projectrawmaterialService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectrawmaterialMockMvc;

    private Projectrawmaterial projectrawmaterial;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectrawmaterialResource projectrawmaterialResource = new ProjectrawmaterialResource(projectrawmaterialService);
        this.restProjectrawmaterialMockMvc = MockMvcBuilders.standaloneSetup(projectrawmaterialResource)
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
    public static Projectrawmaterial createEntity() {
        Projectrawmaterial projectrawmaterial = new Projectrawmaterial()
                .projectid(DEFAULT_PROJECTID)
                .raw_material(DEFAULT_RAW_MATERIAL)
                .raw_quantity(DEFAULT_RAW_QUANTITY)
                .raw_unit(DEFAULT_RAW_UNIT);
        return projectrawmaterial;
    }

    @Before
    public void initTest() {
        projectrawmaterialRepository.deleteAll();
        projectrawmaterial = createEntity();
    }

    @Test
    public void createProjectrawmaterial() throws Exception {
        int databaseSizeBeforeCreate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);

        restProjectrawmaterialMockMvc.perform(post("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeCreate + 1);
        Projectrawmaterial testProjectrawmaterial = projectrawmaterialList.get(projectrawmaterialList.size() - 1);
        assertThat(testProjectrawmaterial.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectrawmaterial.getRaw_material()).isEqualTo(DEFAULT_RAW_MATERIAL);
        assertThat(testProjectrawmaterial.getRaw_quantity()).isEqualTo(DEFAULT_RAW_QUANTITY);
        assertThat(testProjectrawmaterial.getRaw_unit()).isEqualTo(DEFAULT_RAW_UNIT);
    }

    @Test
    public void createProjectrawmaterialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial with an existing ID
        Projectrawmaterial existingProjectrawmaterial = new Projectrawmaterial();
        existingProjectrawmaterial.setId(UUID.randomUUID());
        ProjectrawmaterialDTO existingProjectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(existingProjectrawmaterial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectrawmaterialMockMvc.perform(post("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectrawmaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectrawmaterials() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);

        // Get all the projectrawmaterialList
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectrawmaterial.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].raw_material").value(hasItem(DEFAULT_RAW_MATERIAL.toString())))
            .andExpect(jsonPath("$.[*].raw_quantity").value(hasItem(DEFAULT_RAW_QUANTITY)))
            .andExpect(jsonPath("$.[*].raw_unit").value(hasItem(DEFAULT_RAW_UNIT.toString())));
    }

    @Test
    public void getProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);

        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials/{id}", projectrawmaterial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectrawmaterial.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.raw_material").value(DEFAULT_RAW_MATERIAL.toString()))
            .andExpect(jsonPath("$.raw_quantity").value(DEFAULT_RAW_QUANTITY))
            .andExpect(jsonPath("$.raw_unit").value(DEFAULT_RAW_UNIT.toString()));
    }

    @Test
    public void getNonExistingProjectrawmaterial() throws Exception {
        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);
        int databaseSizeBeforeUpdate = projectrawmaterialRepository.findAll().size();

        // Update the projectrawmaterial
        Projectrawmaterial updatedProjectrawmaterial = projectrawmaterialRepository.findOne(projectrawmaterial.getId());
        updatedProjectrawmaterial
                .projectid(UPDATED_PROJECTID)
                .raw_material(UPDATED_RAW_MATERIAL)
                .raw_quantity(UPDATED_RAW_QUANTITY)
                .raw_unit(UPDATED_RAW_UNIT);
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(updatedProjectrawmaterial);

        restProjectrawmaterialMockMvc.perform(put("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isOk());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeUpdate);
        Projectrawmaterial testProjectrawmaterial = projectrawmaterialList.get(projectrawmaterialList.size() - 1);
        assertThat(testProjectrawmaterial.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectrawmaterial.getRaw_material()).isEqualTo(UPDATED_RAW_MATERIAL);
        assertThat(testProjectrawmaterial.getRaw_quantity()).isEqualTo(UPDATED_RAW_QUANTITY);
        assertThat(testProjectrawmaterial.getRaw_unit()).isEqualTo(UPDATED_RAW_UNIT);
    }

    @Test
    public void updateNonExistingProjectrawmaterial() throws Exception {
        int databaseSizeBeforeUpdate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectrawmaterialMockMvc.perform(put("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);
        int databaseSizeBeforeDelete = projectrawmaterialRepository.findAll().size();

        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(delete("/api/projectrawmaterials/{id}", projectrawmaterial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectrawmaterial.class);
    }
}
