package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Financeinvestment;
import com.hartron.investharyana.repository.FinanceinvestmentRepository;
import com.hartron.investharyana.service.FinanceinvestmentService;
import com.hartron.investharyana.service.dto.FinanceinvestmentDTO;
import com.hartron.investharyana.service.mapper.FinanceinvestmentMapper;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FinanceinvestmentResource REST controller.
 *
 * @see FinanceinvestmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class FinanceinvestmentResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final BigDecimal DEFAULT_LAND_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_LAND_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BUILDING_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUILDING_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PLAN_MACHINERY_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_PLAN_MACHINERY_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MISC_ASSETS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MISC_ASSETS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTALPROJECT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALPROJECT_COST = new BigDecimal(2);

    private static final Boolean DEFAULT_FDI_APPLICABLE = false;
    private static final Boolean UPDATED_FDI_APPLICABLE = true;

    private static final UUID DEFAULT_FDI_COUNTRY = UUID.randomUUID();
    private static final UUID UPDATED_FDI_COUNTRY = UUID.randomUUID();

    private static final BigDecimal DEFAULT_FDI_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FDI_VALUE = new BigDecimal(2);

    private static final UUID DEFAULT_FDI_RESOURCE = UUID.randomUUID();
    private static final UUID UPDATED_FDI_RESOURCE = UUID.randomUUID();

    private static final ZonedDateTime DEFAULT_PROJECT_CONSTRUCTION_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROJECT_CONSTRUCTION_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_PROJECT_COMMERCIAL_ACTIVITY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROJECT_COMMERCIAL_ACTIVITY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private FinanceinvestmentRepository financeinvestmentRepository;

    @Autowired
    private FinanceinvestmentMapper financeinvestmentMapper;

    @Autowired
    private FinanceinvestmentService financeinvestmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restFinanceinvestmentMockMvc;

    private Financeinvestment financeinvestment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FinanceinvestmentResource financeinvestmentResource = new FinanceinvestmentResource(financeinvestmentService);
        this.restFinanceinvestmentMockMvc = MockMvcBuilders.standaloneSetup(financeinvestmentResource)
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
    public static Financeinvestment createEntity() {
        Financeinvestment financeinvestment = new Financeinvestment()
                .projectid(DEFAULT_PROJECTID)
                .land_cost(DEFAULT_LAND_COST)
                .building_cost(DEFAULT_BUILDING_COST)
                .plan_machinery_cost(DEFAULT_PLAN_MACHINERY_COST)
                .misc_assets(DEFAULT_MISC_ASSETS)
                .totalproject_cost(DEFAULT_TOTALPROJECT_COST)
                .fdi_applicable(DEFAULT_FDI_APPLICABLE)
                .fdi_country(DEFAULT_FDI_COUNTRY)
                .fdi_value(DEFAULT_FDI_VALUE)
                .fdi_resource(DEFAULT_FDI_RESOURCE)
                .project_construction_start_date(DEFAULT_PROJECT_CONSTRUCTION_START_DATE)
                .project_commercial_activity_date(DEFAULT_PROJECT_COMMERCIAL_ACTIVITY_DATE);
        return financeinvestment;
    }

    @Before
    public void initTest() {
        financeinvestmentRepository.deleteAll();
        financeinvestment = createEntity();
    }

    @Test
    public void createFinanceinvestment() throws Exception {
        int databaseSizeBeforeCreate = financeinvestmentRepository.findAll().size();

        // Create the Financeinvestment
        FinanceinvestmentDTO financeinvestmentDTO = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(financeinvestment);

        restFinanceinvestmentMockMvc.perform(post("/api/financeinvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financeinvestmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Financeinvestment in the database
        List<Financeinvestment> financeinvestmentList = financeinvestmentRepository.findAll();
        assertThat(financeinvestmentList).hasSize(databaseSizeBeforeCreate + 1);
        Financeinvestment testFinanceinvestment = financeinvestmentList.get(financeinvestmentList.size() - 1);
        assertThat(testFinanceinvestment.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testFinanceinvestment.getLand_cost()).isEqualTo(DEFAULT_LAND_COST);
        assertThat(testFinanceinvestment.getBuilding_cost()).isEqualTo(DEFAULT_BUILDING_COST);
        assertThat(testFinanceinvestment.getPlan_machinery_cost()).isEqualTo(DEFAULT_PLAN_MACHINERY_COST);
        assertThat(testFinanceinvestment.getMisc_assets()).isEqualTo(DEFAULT_MISC_ASSETS);
        assertThat(testFinanceinvestment.getTotalproject_cost()).isEqualTo(DEFAULT_TOTALPROJECT_COST);
        assertThat(testFinanceinvestment.isFdi_applicable()).isEqualTo(DEFAULT_FDI_APPLICABLE);
        assertThat(testFinanceinvestment.getFdi_country()).isEqualTo(DEFAULT_FDI_COUNTRY);
        assertThat(testFinanceinvestment.getFdi_value()).isEqualTo(DEFAULT_FDI_VALUE);
        assertThat(testFinanceinvestment.getFdi_resource()).isEqualTo(DEFAULT_FDI_RESOURCE);
        assertThat(testFinanceinvestment.getProject_construction_start_date()).isEqualTo(DEFAULT_PROJECT_CONSTRUCTION_START_DATE);
        assertThat(testFinanceinvestment.getProject_commercial_activity_date()).isEqualTo(DEFAULT_PROJECT_COMMERCIAL_ACTIVITY_DATE);
    }

    @Test
    public void createFinanceinvestmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = financeinvestmentRepository.findAll().size();

        // Create the Financeinvestment with an existing ID
        Financeinvestment existingFinanceinvestment = new Financeinvestment();
        existingFinanceinvestment.setId(UUID.randomUUID());
        FinanceinvestmentDTO existingFinanceinvestmentDTO = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(existingFinanceinvestment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinanceinvestmentMockMvc.perform(post("/api/financeinvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFinanceinvestmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Financeinvestment> financeinvestmentList = financeinvestmentRepository.findAll();
        assertThat(financeinvestmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllFinanceinvestments() throws Exception {
        // Initialize the database
        financeinvestmentRepository.save(financeinvestment);

        // Get all the financeinvestmentList
        restFinanceinvestmentMockMvc.perform(get("/api/financeinvestments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financeinvestment.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].land_cost").value(hasItem(DEFAULT_LAND_COST.intValue())))
            .andExpect(jsonPath("$.[*].building_cost").value(hasItem(DEFAULT_BUILDING_COST.intValue())))
            .andExpect(jsonPath("$.[*].plan_machinery_cost").value(hasItem(DEFAULT_PLAN_MACHINERY_COST.intValue())))
            .andExpect(jsonPath("$.[*].misc_assets").value(hasItem(DEFAULT_MISC_ASSETS.intValue())))
            .andExpect(jsonPath("$.[*].totalproject_cost").value(hasItem(DEFAULT_TOTALPROJECT_COST.intValue())))
            .andExpect(jsonPath("$.[*].fdi_applicable").value(hasItem(DEFAULT_FDI_APPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].fdi_country").value(hasItem(DEFAULT_FDI_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].fdi_value").value(hasItem(DEFAULT_FDI_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].fdi_resource").value(hasItem(DEFAULT_FDI_RESOURCE.toString())))
            .andExpect(jsonPath("$.[*].project_construction_start_date").value(hasItem(sameInstant(DEFAULT_PROJECT_CONSTRUCTION_START_DATE))))
            .andExpect(jsonPath("$.[*].project_commercial_activity_date").value(hasItem(sameInstant(DEFAULT_PROJECT_COMMERCIAL_ACTIVITY_DATE))));
    }

    @Test
    public void getFinanceinvestment() throws Exception {
        // Initialize the database
        financeinvestmentRepository.save(financeinvestment);

        // Get the financeinvestment
        restFinanceinvestmentMockMvc.perform(get("/api/financeinvestments/{id}", financeinvestment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(financeinvestment.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.land_cost").value(DEFAULT_LAND_COST.intValue()))
            .andExpect(jsonPath("$.building_cost").value(DEFAULT_BUILDING_COST.intValue()))
            .andExpect(jsonPath("$.plan_machinery_cost").value(DEFAULT_PLAN_MACHINERY_COST.intValue()))
            .andExpect(jsonPath("$.misc_assets").value(DEFAULT_MISC_ASSETS.intValue()))
            .andExpect(jsonPath("$.totalproject_cost").value(DEFAULT_TOTALPROJECT_COST.intValue()))
            .andExpect(jsonPath("$.fdi_applicable").value(DEFAULT_FDI_APPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.fdi_country").value(DEFAULT_FDI_COUNTRY.toString()))
            .andExpect(jsonPath("$.fdi_value").value(DEFAULT_FDI_VALUE.intValue()))
            .andExpect(jsonPath("$.fdi_resource").value(DEFAULT_FDI_RESOURCE.toString()))
            .andExpect(jsonPath("$.project_construction_start_date").value(sameInstant(DEFAULT_PROJECT_CONSTRUCTION_START_DATE)))
            .andExpect(jsonPath("$.project_commercial_activity_date").value(sameInstant(DEFAULT_PROJECT_COMMERCIAL_ACTIVITY_DATE)));
    }

    @Test
    public void getNonExistingFinanceinvestment() throws Exception {
        // Get the financeinvestment
        restFinanceinvestmentMockMvc.perform(get("/api/financeinvestments/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFinanceinvestment() throws Exception {
        // Initialize the database
        financeinvestmentRepository.save(financeinvestment);
        int databaseSizeBeforeUpdate = financeinvestmentRepository.findAll().size();

        // Update the financeinvestment
        Financeinvestment updatedFinanceinvestment = financeinvestmentRepository.findOne(financeinvestment.getId());
        updatedFinanceinvestment
                .projectid(UPDATED_PROJECTID)
                .land_cost(UPDATED_LAND_COST)
                .building_cost(UPDATED_BUILDING_COST)
                .plan_machinery_cost(UPDATED_PLAN_MACHINERY_COST)
                .misc_assets(UPDATED_MISC_ASSETS)
                .totalproject_cost(UPDATED_TOTALPROJECT_COST)
                .fdi_applicable(UPDATED_FDI_APPLICABLE)
                .fdi_country(UPDATED_FDI_COUNTRY)
                .fdi_value(UPDATED_FDI_VALUE)
                .fdi_resource(UPDATED_FDI_RESOURCE)
                .project_construction_start_date(UPDATED_PROJECT_CONSTRUCTION_START_DATE)
                .project_commercial_activity_date(UPDATED_PROJECT_COMMERCIAL_ACTIVITY_DATE);
        FinanceinvestmentDTO financeinvestmentDTO = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(updatedFinanceinvestment);

        restFinanceinvestmentMockMvc.perform(put("/api/financeinvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financeinvestmentDTO)))
            .andExpect(status().isOk());

        // Validate the Financeinvestment in the database
        List<Financeinvestment> financeinvestmentList = financeinvestmentRepository.findAll();
        assertThat(financeinvestmentList).hasSize(databaseSizeBeforeUpdate);
        Financeinvestment testFinanceinvestment = financeinvestmentList.get(financeinvestmentList.size() - 1);
        assertThat(testFinanceinvestment.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testFinanceinvestment.getLand_cost()).isEqualTo(UPDATED_LAND_COST);
        assertThat(testFinanceinvestment.getBuilding_cost()).isEqualTo(UPDATED_BUILDING_COST);
        assertThat(testFinanceinvestment.getPlan_machinery_cost()).isEqualTo(UPDATED_PLAN_MACHINERY_COST);
        assertThat(testFinanceinvestment.getMisc_assets()).isEqualTo(UPDATED_MISC_ASSETS);
        assertThat(testFinanceinvestment.getTotalproject_cost()).isEqualTo(UPDATED_TOTALPROJECT_COST);
        assertThat(testFinanceinvestment.isFdi_applicable()).isEqualTo(UPDATED_FDI_APPLICABLE);
        assertThat(testFinanceinvestment.getFdi_country()).isEqualTo(UPDATED_FDI_COUNTRY);
        assertThat(testFinanceinvestment.getFdi_value()).isEqualTo(UPDATED_FDI_VALUE);
        assertThat(testFinanceinvestment.getFdi_resource()).isEqualTo(UPDATED_FDI_RESOURCE);
        assertThat(testFinanceinvestment.getProject_construction_start_date()).isEqualTo(UPDATED_PROJECT_CONSTRUCTION_START_DATE);
        assertThat(testFinanceinvestment.getProject_commercial_activity_date()).isEqualTo(UPDATED_PROJECT_COMMERCIAL_ACTIVITY_DATE);
    }

    @Test
    public void updateNonExistingFinanceinvestment() throws Exception {
        int databaseSizeBeforeUpdate = financeinvestmentRepository.findAll().size();

        // Create the Financeinvestment
        FinanceinvestmentDTO financeinvestmentDTO = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(financeinvestment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFinanceinvestmentMockMvc.perform(put("/api/financeinvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(financeinvestmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Financeinvestment in the database
        List<Financeinvestment> financeinvestmentList = financeinvestmentRepository.findAll();
        assertThat(financeinvestmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteFinanceinvestment() throws Exception {
        // Initialize the database
        financeinvestmentRepository.save(financeinvestment);
        int databaseSizeBeforeDelete = financeinvestmentRepository.findAll().size();

        // Get the financeinvestment
        restFinanceinvestmentMockMvc.perform(delete("/api/financeinvestments/{id}", financeinvestment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Financeinvestment> financeinvestmentList = financeinvestmentRepository.findAll();
        assertThat(financeinvestmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Financeinvestment.class);
    }
}
