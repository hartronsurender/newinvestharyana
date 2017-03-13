package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Environment_impact_detail;
import com.hartron.investharyana.repository.Environment_impact_detailRepository;
import com.hartron.investharyana.service.Environment_impact_detailService;
import com.hartron.investharyana.service.dto.Environment_impact_detailDTO;
import com.hartron.investharyana.service.mapper.Environment_impact_detailMapper;
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
 * Test class for the Environment_impact_detailResource REST controller.
 *
 * @see Environment_impact_detailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Environment_impact_detailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_WATER_SUPPLY_SOURCE = UUID.randomUUID();
    private static final UUID UPDATED_WATER_SUPPLY_SOURCE = UUID.randomUUID();

    private static final Integer DEFAULT_WATER_USAGE_PROCESS = 1;
    private static final Integer UPDATED_WATER_USAGE_PROCESS = 2;

    private static final Integer DEFAULT_WATER_USAGE_COOLING = 1;
    private static final Integer UPDATED_WATER_USAGE_COOLING = 2;

    private static final Integer DEFAULT_WATER_USAGE_DOMESTIC = 1;
    private static final Integer UPDATED_WATER_USAGE_DOMESTIC = 2;

    private static final String DEFAULT_WATER_USAGE_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_WATER_USAGE_OTHER = "BBBBBBBBBB";

    private static final Integer DEFAULT_WATER_WASTE_PROCESS = 1;
    private static final Integer UPDATED_WATER_WASTE_PROCESS = 2;

    private static final Integer DEFAULT_WATER_WASTE_COOLING = 1;
    private static final Integer UPDATED_WATER_WASTE_COOLING = 2;

    private static final Integer DEFAULT_WATER_WASTE_DOMESTING = 1;
    private static final Integer UPDATED_WATER_WASTE_DOMESTING = 2;

    private static final String DEFAULT_WATER_WASTE_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_WATER_WASTE_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_WASTE_WATER_TREATMENT = "AAAAAAAAAA";
    private static final String UPDATED_WASTE_WATER_TREATMENT = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_WASTE_WATER_TREATMENT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final UUID DEFAULT_MODE_OF_DISPOSAL = UUID.randomUUID();
    private static final UUID UPDATED_MODE_OF_DISPOSAL = UUID.randomUUID();

    private static final UUID DEFAULT_EMISSIONID = UUID.randomUUID();
    private static final UUID UPDATED_EMISSIONID = UUID.randomUUID();

    private static final UUID DEFAULT_WATER_WASTE_ID = UUID.randomUUID();
    private static final UUID UPDATED_WATER_WASTE_ID = UUID.randomUUID();

    @Autowired
    private Environment_impact_detailRepository environment_impact_detailRepository;

    @Autowired
    private Environment_impact_detailMapper environment_impact_detailMapper;

    @Autowired
    private Environment_impact_detailService environment_impact_detailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEnvironment_impact_detailMockMvc;

    private Environment_impact_detail environment_impact_detail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Environment_impact_detailResource environment_impact_detailResource = new Environment_impact_detailResource(environment_impact_detailService);
        this.restEnvironment_impact_detailMockMvc = MockMvcBuilders.standaloneSetup(environment_impact_detailResource)
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
    public static Environment_impact_detail createEntity() {
        Environment_impact_detail environment_impact_detail = new Environment_impact_detail()
                .projectid(DEFAULT_PROJECTID)
                .water_supply_source(DEFAULT_WATER_SUPPLY_SOURCE)
                .water_usage_process(DEFAULT_WATER_USAGE_PROCESS)
                .water_usage_cooling(DEFAULT_WATER_USAGE_COOLING)
                .water_usage_domestic(DEFAULT_WATER_USAGE_DOMESTIC)
                .water_usage_other(DEFAULT_WATER_USAGE_OTHER)
                .water_waste_process(DEFAULT_WATER_WASTE_PROCESS)
                .water_waste_cooling(DEFAULT_WATER_WASTE_COOLING)
                .water_waste_domesting(DEFAULT_WATER_WASTE_DOMESTING)
                .water_waste_other(DEFAULT_WATER_WASTE_OTHER)
                .waste_water_treatment(DEFAULT_WASTE_WATER_TREATMENT)
                .waste_water_treatment_document(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT)
                .waste_water_treatment_documentContentType(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE)
                .mode_of_disposal(DEFAULT_MODE_OF_DISPOSAL)
                .emissionid(DEFAULT_EMISSIONID)
                .water_waste_id(DEFAULT_WATER_WASTE_ID);
        return environment_impact_detail;
    }

    @Before
    public void initTest() {
        environment_impact_detailRepository.deleteAll();
        environment_impact_detail = createEntity();
    }

    @Test
    public void createEnvironment_impact_detail() throws Exception {
        int databaseSizeBeforeCreate = environment_impact_detailRepository.findAll().size();

        // Create the Environment_impact_detail
        Environment_impact_detailDTO environment_impact_detailDTO = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(environment_impact_detail);

        restEnvironment_impact_detailMockMvc.perform(post("/api/environment-impact-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impact_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Environment_impact_detail in the database
        List<Environment_impact_detail> environment_impact_detailList = environment_impact_detailRepository.findAll();
        assertThat(environment_impact_detailList).hasSize(databaseSizeBeforeCreate + 1);
        Environment_impact_detail testEnvironment_impact_detail = environment_impact_detailList.get(environment_impact_detailList.size() - 1);
        assertThat(testEnvironment_impact_detail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testEnvironment_impact_detail.getWater_supply_source()).isEqualTo(DEFAULT_WATER_SUPPLY_SOURCE);
        assertThat(testEnvironment_impact_detail.getWater_usage_process()).isEqualTo(DEFAULT_WATER_USAGE_PROCESS);
        assertThat(testEnvironment_impact_detail.getWater_usage_cooling()).isEqualTo(DEFAULT_WATER_USAGE_COOLING);
        assertThat(testEnvironment_impact_detail.getWater_usage_domestic()).isEqualTo(DEFAULT_WATER_USAGE_DOMESTIC);
        assertThat(testEnvironment_impact_detail.getWater_usage_other()).isEqualTo(DEFAULT_WATER_USAGE_OTHER);
        assertThat(testEnvironment_impact_detail.getWater_waste_process()).isEqualTo(DEFAULT_WATER_WASTE_PROCESS);
        assertThat(testEnvironment_impact_detail.getWater_waste_cooling()).isEqualTo(DEFAULT_WATER_WASTE_COOLING);
        assertThat(testEnvironment_impact_detail.getWater_waste_domesting()).isEqualTo(DEFAULT_WATER_WASTE_DOMESTING);
        assertThat(testEnvironment_impact_detail.getWater_waste_other()).isEqualTo(DEFAULT_WATER_WASTE_OTHER);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment()).isEqualTo(DEFAULT_WASTE_WATER_TREATMENT);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment_document()).isEqualTo(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment_documentContentType()).isEqualTo(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE);
        assertThat(testEnvironment_impact_detail.getMode_of_disposal()).isEqualTo(DEFAULT_MODE_OF_DISPOSAL);
        assertThat(testEnvironment_impact_detail.getEmissionid()).isEqualTo(DEFAULT_EMISSIONID);
        assertThat(testEnvironment_impact_detail.getWater_waste_id()).isEqualTo(DEFAULT_WATER_WASTE_ID);
    }

    @Test
    public void createEnvironment_impact_detailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = environment_impact_detailRepository.findAll().size();

        // Create the Environment_impact_detail with an existing ID
        Environment_impact_detail existingEnvironment_impact_detail = new Environment_impact_detail();
        existingEnvironment_impact_detail.setId(UUID.randomUUID());
        Environment_impact_detailDTO existingEnvironment_impact_detailDTO = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(existingEnvironment_impact_detail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnvironment_impact_detailMockMvc.perform(post("/api/environment-impact-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEnvironment_impact_detailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Environment_impact_detail> environment_impact_detailList = environment_impact_detailRepository.findAll();
        assertThat(environment_impact_detailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEnvironment_impact_details() throws Exception {
        // Initialize the database
        environment_impact_detailRepository.save(environment_impact_detail);

        // Get all the environment_impact_detailList
        restEnvironment_impact_detailMockMvc.perform(get("/api/environment-impact-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(environment_impact_detail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].water_supply_source").value(hasItem(DEFAULT_WATER_SUPPLY_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].water_usage_process").value(hasItem(DEFAULT_WATER_USAGE_PROCESS)))
            .andExpect(jsonPath("$.[*].water_usage_cooling").value(hasItem(DEFAULT_WATER_USAGE_COOLING)))
            .andExpect(jsonPath("$.[*].water_usage_domestic").value(hasItem(DEFAULT_WATER_USAGE_DOMESTIC)))
            .andExpect(jsonPath("$.[*].water_usage_other").value(hasItem(DEFAULT_WATER_USAGE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].water_waste_process").value(hasItem(DEFAULT_WATER_WASTE_PROCESS)))
            .andExpect(jsonPath("$.[*].water_waste_cooling").value(hasItem(DEFAULT_WATER_WASTE_COOLING)))
            .andExpect(jsonPath("$.[*].water_waste_domesting").value(hasItem(DEFAULT_WATER_WASTE_DOMESTING)))
            .andExpect(jsonPath("$.[*].water_waste_other").value(hasItem(DEFAULT_WATER_WASTE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].waste_water_treatment").value(hasItem(DEFAULT_WASTE_WATER_TREATMENT.toString())))
            .andExpect(jsonPath("$.[*].waste_water_treatment_documentContentType").value(hasItem(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].waste_water_treatment_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].mode_of_disposal").value(hasItem(DEFAULT_MODE_OF_DISPOSAL.toString())))
            .andExpect(jsonPath("$.[*].emissionid").value(hasItem(DEFAULT_EMISSIONID.toString())))
            .andExpect(jsonPath("$.[*].water_waste_id").value(hasItem(DEFAULT_WATER_WASTE_ID.toString())));
    }

    @Test
    public void getEnvironment_impact_detail() throws Exception {
        // Initialize the database
        environment_impact_detailRepository.save(environment_impact_detail);

        // Get the environment_impact_detail
        restEnvironment_impact_detailMockMvc.perform(get("/api/environment-impact-details/{id}", environment_impact_detail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(environment_impact_detail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.water_supply_source").value(DEFAULT_WATER_SUPPLY_SOURCE.toString()))
            .andExpect(jsonPath("$.water_usage_process").value(DEFAULT_WATER_USAGE_PROCESS))
            .andExpect(jsonPath("$.water_usage_cooling").value(DEFAULT_WATER_USAGE_COOLING))
            .andExpect(jsonPath("$.water_usage_domestic").value(DEFAULT_WATER_USAGE_DOMESTIC))
            .andExpect(jsonPath("$.water_usage_other").value(DEFAULT_WATER_USAGE_OTHER.toString()))
            .andExpect(jsonPath("$.water_waste_process").value(DEFAULT_WATER_WASTE_PROCESS))
            .andExpect(jsonPath("$.water_waste_cooling").value(DEFAULT_WATER_WASTE_COOLING))
            .andExpect(jsonPath("$.water_waste_domesting").value(DEFAULT_WATER_WASTE_DOMESTING))
            .andExpect(jsonPath("$.water_waste_other").value(DEFAULT_WATER_WASTE_OTHER.toString()))
            .andExpect(jsonPath("$.waste_water_treatment").value(DEFAULT_WASTE_WATER_TREATMENT.toString()))
            .andExpect(jsonPath("$.waste_water_treatment_documentContentType").value(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.waste_water_treatment_document").value(Base64Utils.encodeToString(DEFAULT_WASTE_WATER_TREATMENT_DOCUMENT.array())))
            .andExpect(jsonPath("$.mode_of_disposal").value(DEFAULT_MODE_OF_DISPOSAL.toString()))
            .andExpect(jsonPath("$.emissionid").value(DEFAULT_EMISSIONID.toString()))
            .andExpect(jsonPath("$.water_waste_id").value(DEFAULT_WATER_WASTE_ID.toString()));
    }

    @Test
    public void getNonExistingEnvironment_impact_detail() throws Exception {
        // Get the environment_impact_detail
        restEnvironment_impact_detailMockMvc.perform(get("/api/environment-impact-details/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEnvironment_impact_detail() throws Exception {
        // Initialize the database
        environment_impact_detailRepository.save(environment_impact_detail);
        int databaseSizeBeforeUpdate = environment_impact_detailRepository.findAll().size();

        // Update the environment_impact_detail
        Environment_impact_detail updatedEnvironment_impact_detail = environment_impact_detailRepository.findOne(environment_impact_detail.getId());
        updatedEnvironment_impact_detail
                .projectid(UPDATED_PROJECTID)
                .water_supply_source(UPDATED_WATER_SUPPLY_SOURCE)
                .water_usage_process(UPDATED_WATER_USAGE_PROCESS)
                .water_usage_cooling(UPDATED_WATER_USAGE_COOLING)
                .water_usage_domestic(UPDATED_WATER_USAGE_DOMESTIC)
                .water_usage_other(UPDATED_WATER_USAGE_OTHER)
                .water_waste_process(UPDATED_WATER_WASTE_PROCESS)
                .water_waste_cooling(UPDATED_WATER_WASTE_COOLING)
                .water_waste_domesting(UPDATED_WATER_WASTE_DOMESTING)
                .water_waste_other(UPDATED_WATER_WASTE_OTHER)
                .waste_water_treatment(UPDATED_WASTE_WATER_TREATMENT)
                .waste_water_treatment_document(UPDATED_WASTE_WATER_TREATMENT_DOCUMENT)
                .waste_water_treatment_documentContentType(UPDATED_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE)
                .mode_of_disposal(UPDATED_MODE_OF_DISPOSAL)
                .emissionid(UPDATED_EMISSIONID)
                .water_waste_id(UPDATED_WATER_WASTE_ID);
        Environment_impact_detailDTO environment_impact_detailDTO = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(updatedEnvironment_impact_detail);

        restEnvironment_impact_detailMockMvc.perform(put("/api/environment-impact-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impact_detailDTO)))
            .andExpect(status().isOk());

        // Validate the Environment_impact_detail in the database
        List<Environment_impact_detail> environment_impact_detailList = environment_impact_detailRepository.findAll();
        assertThat(environment_impact_detailList).hasSize(databaseSizeBeforeUpdate);
        Environment_impact_detail testEnvironment_impact_detail = environment_impact_detailList.get(environment_impact_detailList.size() - 1);
        assertThat(testEnvironment_impact_detail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testEnvironment_impact_detail.getWater_supply_source()).isEqualTo(UPDATED_WATER_SUPPLY_SOURCE);
        assertThat(testEnvironment_impact_detail.getWater_usage_process()).isEqualTo(UPDATED_WATER_USAGE_PROCESS);
        assertThat(testEnvironment_impact_detail.getWater_usage_cooling()).isEqualTo(UPDATED_WATER_USAGE_COOLING);
        assertThat(testEnvironment_impact_detail.getWater_usage_domestic()).isEqualTo(UPDATED_WATER_USAGE_DOMESTIC);
        assertThat(testEnvironment_impact_detail.getWater_usage_other()).isEqualTo(UPDATED_WATER_USAGE_OTHER);
        assertThat(testEnvironment_impact_detail.getWater_waste_process()).isEqualTo(UPDATED_WATER_WASTE_PROCESS);
        assertThat(testEnvironment_impact_detail.getWater_waste_cooling()).isEqualTo(UPDATED_WATER_WASTE_COOLING);
        assertThat(testEnvironment_impact_detail.getWater_waste_domesting()).isEqualTo(UPDATED_WATER_WASTE_DOMESTING);
        assertThat(testEnvironment_impact_detail.getWater_waste_other()).isEqualTo(UPDATED_WATER_WASTE_OTHER);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment()).isEqualTo(UPDATED_WASTE_WATER_TREATMENT);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment_document()).isEqualTo(UPDATED_WASTE_WATER_TREATMENT_DOCUMENT);
        assertThat(testEnvironment_impact_detail.getWaste_water_treatment_documentContentType()).isEqualTo(UPDATED_WASTE_WATER_TREATMENT_DOCUMENT_CONTENT_TYPE);
        assertThat(testEnvironment_impact_detail.getMode_of_disposal()).isEqualTo(UPDATED_MODE_OF_DISPOSAL);
        assertThat(testEnvironment_impact_detail.getEmissionid()).isEqualTo(UPDATED_EMISSIONID);
        assertThat(testEnvironment_impact_detail.getWater_waste_id()).isEqualTo(UPDATED_WATER_WASTE_ID);
    }

    @Test
    public void updateNonExistingEnvironment_impact_detail() throws Exception {
        int databaseSizeBeforeUpdate = environment_impact_detailRepository.findAll().size();

        // Create the Environment_impact_detail
        Environment_impact_detailDTO environment_impact_detailDTO = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(environment_impact_detail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEnvironment_impact_detailMockMvc.perform(put("/api/environment-impact-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impact_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Environment_impact_detail in the database
        List<Environment_impact_detail> environment_impact_detailList = environment_impact_detailRepository.findAll();
        assertThat(environment_impact_detailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEnvironment_impact_detail() throws Exception {
        // Initialize the database
        environment_impact_detailRepository.save(environment_impact_detail);
        int databaseSizeBeforeDelete = environment_impact_detailRepository.findAll().size();

        // Get the environment_impact_detail
        restEnvironment_impact_detailMockMvc.perform(delete("/api/environment-impact-details/{id}", environment_impact_detail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Environment_impact_detail> environment_impact_detailList = environment_impact_detailRepository.findAll();
        assertThat(environment_impact_detailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Environment_impact_detail.class);
    }
}
