package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Project_electricity_detail;
import com.hartron.investharyana.repository.Project_electricity_detailRepository;
import com.hartron.investharyana.service.Project_electricity_detailService;
import com.hartron.investharyana.service.dto.Project_electricity_detailDTO;
import com.hartron.investharyana.service.mapper.Project_electricity_detailMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Project_electricity_detailResource REST controller.
 *
 * @see Project_electricity_detailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Project_electricity_detailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final ByteBuffer DEFAULT_TEMPORARYCONNECTION = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_TEMPORARYCONNECTION = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_TEMPORARYCONNECTION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TEMPORARYCONNECTION_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_LOAD_REQUIRED = false;
    private static final Boolean UPDATED_LOAD_REQUIRED = true;

    private static final Boolean DEFAULT_EXISTING_CONNECTON = false;
    private static final Boolean UPDATED_EXISTING_CONNECTON = true;

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LOAD_DEMAND = new BigDecimal(1);
    private static final BigDecimal UPDATED_LOAD_DEMAND = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NEW_LOAD_DEMAND_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEW_LOAD_DEMAND_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NEW_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_NEW_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_LOAD_DEMAND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LOAD_DEMAND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ByteBuffer DEFAULT_REGULAR_CONNECTION = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_REGULAR_CONNECTION = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_REGULAR_CONNECTION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REGULAR_CONNECTION_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_REGULAR_LOAD_REQUIRED = false;
    private static final Boolean UPDATED_REGULAR_LOAD_REQUIRED = true;

    private static final Boolean DEFAULT_REGULAR_EXISTING_CONNECTION = false;
    private static final Boolean UPDATED_REGULAR_EXISTING_CONNECTION = true;

    private static final UUID DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE = UUID.randomUUID();
    private static final UUID UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE = UUID.randomUUID();

    private static final String DEFAULT_REGULAR_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGULAR_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_REGULAR_LOAD_IF_ANY_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_LOAD_IF_ANY_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_EXISTING_LOAD_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_EXISTING_LOAD_KVA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_NEW_LOAD_DEMAND_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_REGULAR_DEMAND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGULAR_DEMAND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private Project_electricity_detailRepository project_electricity_detailRepository;

    @Autowired
    private Project_electricity_detailMapper project_electricity_detailMapper;

    @Autowired
    private Project_electricity_detailService project_electricity_detailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProject_electricity_detailMockMvc;

    private Project_electricity_detail project_electricity_detail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Project_electricity_detailResource project_electricity_detailResource = new Project_electricity_detailResource(project_electricity_detailService);
        this.restProject_electricity_detailMockMvc = MockMvcBuilders.standaloneSetup(project_electricity_detailResource)
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
    public static Project_electricity_detail createEntity() {
        Project_electricity_detail project_electricity_detail = new Project_electricity_detail()
                .projectid(DEFAULT_PROJECTID)
                .temporaryconnection(DEFAULT_TEMPORARYCONNECTION)
                .temporaryconnectionContentType(DEFAULT_TEMPORARYCONNECTION_CONTENT_TYPE)
                .load_required(DEFAULT_LOAD_REQUIRED)
                .existing_connecton(DEFAULT_EXISTING_CONNECTON)
                .account_number(DEFAULT_ACCOUNT_NUMBER)
                .load_demand(DEFAULT_LOAD_DEMAND)
                .load_demand_kva(DEFAULT_LOAD_DEMAND_KVA)
                .new_load_demand_kw(DEFAULT_NEW_LOAD_DEMAND_KW)
                .new_load_demand_kva(DEFAULT_NEW_LOAD_DEMAND_KVA)
                .load_demand_date(DEFAULT_LOAD_DEMAND_DATE)
                .regular_connection(DEFAULT_REGULAR_CONNECTION)
                .regular_connectionContentType(DEFAULT_REGULAR_CONNECTION_CONTENT_TYPE)
                .regular_load_required(DEFAULT_REGULAR_LOAD_REQUIRED)
                .regular_existing_connection(DEFAULT_REGULAR_EXISTING_CONNECTION)
                .regular_uhbvn_dhbvn_customer_type(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE)
                .regular_account_number(DEFAULT_REGULAR_ACCOUNT_NUMBER)
                .regular_load_if_any_kw(DEFAULT_REGULAR_LOAD_IF_ANY_KW)
                .regular_existing_load_kva(DEFAULT_REGULAR_EXISTING_LOAD_KVA)
                .regular_new_load_demand_kw(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW)
                .regular_new_load_demand_kva(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA)
                .regular_demand_date(DEFAULT_REGULAR_DEMAND_DATE);
        return project_electricity_detail;
    }

    @Before
    public void initTest() {
        project_electricity_detailRepository.deleteAll();
        project_electricity_detail = createEntity();
    }

    @Test
    public void createProject_electricity_detail() throws Exception {
        int databaseSizeBeforeCreate = project_electricity_detailRepository.findAll().size();

        // Create the Project_electricity_detail
        Project_electricity_detailDTO project_electricity_detailDTO = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(project_electricity_detail);

        restProject_electricity_detailMockMvc.perform(post("/api/project-electricity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_electricity_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_electricity_detail in the database
        List<Project_electricity_detail> project_electricity_detailList = project_electricity_detailRepository.findAll();
        assertThat(project_electricity_detailList).hasSize(databaseSizeBeforeCreate + 1);
        Project_electricity_detail testProject_electricity_detail = project_electricity_detailList.get(project_electricity_detailList.size() - 1);
        assertThat(testProject_electricity_detail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProject_electricity_detail.getTemporaryconnection()).isEqualTo(DEFAULT_TEMPORARYCONNECTION);
        assertThat(testProject_electricity_detail.getTemporaryconnectionContentType()).isEqualTo(DEFAULT_TEMPORARYCONNECTION_CONTENT_TYPE);
        assertThat(testProject_electricity_detail.isLoad_required()).isEqualTo(DEFAULT_LOAD_REQUIRED);
        assertThat(testProject_electricity_detail.isExisting_connecton()).isEqualTo(DEFAULT_EXISTING_CONNECTON);
        assertThat(testProject_electricity_detail.getAccount_number()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testProject_electricity_detail.getLoad_demand()).isEqualTo(DEFAULT_LOAD_DEMAND);
        assertThat(testProject_electricity_detail.getLoad_demand_kva()).isEqualTo(DEFAULT_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getNew_load_demand_kw()).isEqualTo(DEFAULT_NEW_LOAD_DEMAND_KW);
        assertThat(testProject_electricity_detail.getNew_load_demand_kva()).isEqualTo(DEFAULT_NEW_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getLoad_demand_date()).isEqualTo(DEFAULT_LOAD_DEMAND_DATE);
        assertThat(testProject_electricity_detail.getRegular_connection()).isEqualTo(DEFAULT_REGULAR_CONNECTION);
        assertThat(testProject_electricity_detail.getRegular_connectionContentType()).isEqualTo(DEFAULT_REGULAR_CONNECTION_CONTENT_TYPE);
        assertThat(testProject_electricity_detail.isRegular_load_required()).isEqualTo(DEFAULT_REGULAR_LOAD_REQUIRED);
        assertThat(testProject_electricity_detail.isRegular_existing_connection()).isEqualTo(DEFAULT_REGULAR_EXISTING_CONNECTION);
        assertThat(testProject_electricity_detail.getRegular_uhbvn_dhbvn_customer_type()).isEqualTo(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE);
        assertThat(testProject_electricity_detail.getRegular_account_number()).isEqualTo(DEFAULT_REGULAR_ACCOUNT_NUMBER);
        assertThat(testProject_electricity_detail.getRegular_load_if_any_kw()).isEqualTo(DEFAULT_REGULAR_LOAD_IF_ANY_KW);
        assertThat(testProject_electricity_detail.getRegular_existing_load_kva()).isEqualTo(DEFAULT_REGULAR_EXISTING_LOAD_KVA);
        assertThat(testProject_electricity_detail.getRegular_new_load_demand_kw()).isEqualTo(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW);
        assertThat(testProject_electricity_detail.getRegular_new_load_demand_kva()).isEqualTo(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getRegular_demand_date()).isEqualTo(DEFAULT_REGULAR_DEMAND_DATE);
    }

    @Test
    public void createProject_electricity_detailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = project_electricity_detailRepository.findAll().size();

        // Create the Project_electricity_detail with an existing ID
        Project_electricity_detail existingProject_electricity_detail = new Project_electricity_detail();
        existingProject_electricity_detail.setId(UUID.randomUUID());
        Project_electricity_detailDTO existingProject_electricity_detailDTO = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(existingProject_electricity_detail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProject_electricity_detailMockMvc.perform(post("/api/project-electricity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProject_electricity_detailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Project_electricity_detail> project_electricity_detailList = project_electricity_detailRepository.findAll();
        assertThat(project_electricity_detailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProject_electricity_details() throws Exception {
        // Initialize the database
        project_electricity_detailRepository.save(project_electricity_detail);

        // Get all the project_electricity_detailList
        restProject_electricity_detailMockMvc.perform(get("/api/project-electricity-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project_electricity_detail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].temporaryconnectionContentType").value(hasItem(DEFAULT_TEMPORARYCONNECTION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].temporaryconnection").value(hasItem(Base64Utils.encodeToString(DEFAULT_TEMPORARYCONNECTION.array()))))
            .andExpect(jsonPath("$.[*].load_required").value(hasItem(DEFAULT_LOAD_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].existing_connecton").value(hasItem(DEFAULT_EXISTING_CONNECTON.booleanValue())))
            .andExpect(jsonPath("$.[*].account_number").value(hasItem(DEFAULT_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].load_demand").value(hasItem(DEFAULT_LOAD_DEMAND.intValue())))
            .andExpect(jsonPath("$.[*].load_demand_kva").value(hasItem(DEFAULT_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].new_load_demand_kw").value(hasItem(DEFAULT_NEW_LOAD_DEMAND_KW.intValue())))
            .andExpect(jsonPath("$.[*].new_load_demand_kva").value(hasItem(DEFAULT_NEW_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].load_demand_date").value(hasItem(sameInstant(DEFAULT_LOAD_DEMAND_DATE))))
            .andExpect(jsonPath("$.[*].regular_connectionContentType").value(hasItem(DEFAULT_REGULAR_CONNECTION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].regular_connection").value(hasItem(Base64Utils.encodeToString(DEFAULT_REGULAR_CONNECTION.array()))))
            .andExpect(jsonPath("$.[*].regular_load_required").value(hasItem(DEFAULT_REGULAR_LOAD_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].regular_existing_connection").value(hasItem(DEFAULT_REGULAR_EXISTING_CONNECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].regular_uhbvn_dhbvn_customer_type").value(hasItem(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].regular_account_number").value(hasItem(DEFAULT_REGULAR_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].regular_load_if_any_kw").value(hasItem(DEFAULT_REGULAR_LOAD_IF_ANY_KW.intValue())))
            .andExpect(jsonPath("$.[*].regular_existing_load_kva").value(hasItem(DEFAULT_REGULAR_EXISTING_LOAD_KVA.intValue())))
            .andExpect(jsonPath("$.[*].regular_new_load_demand_kw").value(hasItem(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW.intValue())))
            .andExpect(jsonPath("$.[*].regular_new_load_demand_kva").value(hasItem(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].regular_demand_date").value(hasItem(sameInstant(DEFAULT_REGULAR_DEMAND_DATE))));
    }

    @Test
    public void getProject_electricity_detail() throws Exception {
        // Initialize the database
        project_electricity_detailRepository.save(project_electricity_detail);

        // Get the project_electricity_detail
        restProject_electricity_detailMockMvc.perform(get("/api/project-electricity-details/{id}", project_electricity_detail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project_electricity_detail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.temporaryconnectionContentType").value(DEFAULT_TEMPORARYCONNECTION_CONTENT_TYPE))
            .andExpect(jsonPath("$.temporaryconnection").value(Base64Utils.encodeToString(DEFAULT_TEMPORARYCONNECTION.array())))
            .andExpect(jsonPath("$.load_required").value(DEFAULT_LOAD_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.existing_connecton").value(DEFAULT_EXISTING_CONNECTON.booleanValue()))
            .andExpect(jsonPath("$.account_number").value(DEFAULT_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.load_demand").value(DEFAULT_LOAD_DEMAND.intValue()))
            .andExpect(jsonPath("$.load_demand_kva").value(DEFAULT_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.new_load_demand_kw").value(DEFAULT_NEW_LOAD_DEMAND_KW.intValue()))
            .andExpect(jsonPath("$.new_load_demand_kva").value(DEFAULT_NEW_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.load_demand_date").value(sameInstant(DEFAULT_LOAD_DEMAND_DATE)))
            .andExpect(jsonPath("$.regular_connectionContentType").value(DEFAULT_REGULAR_CONNECTION_CONTENT_TYPE))
            .andExpect(jsonPath("$.regular_connection").value(Base64Utils.encodeToString(DEFAULT_REGULAR_CONNECTION.array())))
            .andExpect(jsonPath("$.regular_load_required").value(DEFAULT_REGULAR_LOAD_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.regular_existing_connection").value(DEFAULT_REGULAR_EXISTING_CONNECTION.booleanValue()))
            .andExpect(jsonPath("$.regular_uhbvn_dhbvn_customer_type").value(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE.toString()))
            .andExpect(jsonPath("$.regular_account_number").value(DEFAULT_REGULAR_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.regular_load_if_any_kw").value(DEFAULT_REGULAR_LOAD_IF_ANY_KW.intValue()))
            .andExpect(jsonPath("$.regular_existing_load_kva").value(DEFAULT_REGULAR_EXISTING_LOAD_KVA.intValue()))
            .andExpect(jsonPath("$.regular_new_load_demand_kw").value(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW.intValue()))
            .andExpect(jsonPath("$.regular_new_load_demand_kva").value(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.regular_demand_date").value(sameInstant(DEFAULT_REGULAR_DEMAND_DATE)));
    }

    @Test
    public void getNonExistingProject_electricity_detail() throws Exception {
        // Get the project_electricity_detail
        restProject_electricity_detailMockMvc.perform(get("/api/project-electricity-details/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProject_electricity_detail() throws Exception {
        // Initialize the database
        project_electricity_detailRepository.save(project_electricity_detail);
        int databaseSizeBeforeUpdate = project_electricity_detailRepository.findAll().size();

        // Update the project_electricity_detail
        Project_electricity_detail updatedProject_electricity_detail = project_electricity_detailRepository.findOne(project_electricity_detail.getId());
        updatedProject_electricity_detail
                .projectid(UPDATED_PROJECTID)
                .temporaryconnection(UPDATED_TEMPORARYCONNECTION)
                .temporaryconnectionContentType(UPDATED_TEMPORARYCONNECTION_CONTENT_TYPE)
                .load_required(UPDATED_LOAD_REQUIRED)
                .existing_connecton(UPDATED_EXISTING_CONNECTON)
                .account_number(UPDATED_ACCOUNT_NUMBER)
                .load_demand(UPDATED_LOAD_DEMAND)
                .load_demand_kva(UPDATED_LOAD_DEMAND_KVA)
                .new_load_demand_kw(UPDATED_NEW_LOAD_DEMAND_KW)
                .new_load_demand_kva(UPDATED_NEW_LOAD_DEMAND_KVA)
                .load_demand_date(UPDATED_LOAD_DEMAND_DATE)
                .regular_connection(UPDATED_REGULAR_CONNECTION)
                .regular_connectionContentType(UPDATED_REGULAR_CONNECTION_CONTENT_TYPE)
                .regular_load_required(UPDATED_REGULAR_LOAD_REQUIRED)
                .regular_existing_connection(UPDATED_REGULAR_EXISTING_CONNECTION)
                .regular_uhbvn_dhbvn_customer_type(UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE)
                .regular_account_number(UPDATED_REGULAR_ACCOUNT_NUMBER)
                .regular_load_if_any_kw(UPDATED_REGULAR_LOAD_IF_ANY_KW)
                .regular_existing_load_kva(UPDATED_REGULAR_EXISTING_LOAD_KVA)
                .regular_new_load_demand_kw(UPDATED_REGULAR_NEW_LOAD_DEMAND_KW)
                .regular_new_load_demand_kva(UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA)
                .regular_demand_date(UPDATED_REGULAR_DEMAND_DATE);
        Project_electricity_detailDTO project_electricity_detailDTO = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(updatedProject_electricity_detail);

        restProject_electricity_detailMockMvc.perform(put("/api/project-electricity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_electricity_detailDTO)))
            .andExpect(status().isOk());

        // Validate the Project_electricity_detail in the database
        List<Project_electricity_detail> project_electricity_detailList = project_electricity_detailRepository.findAll();
        assertThat(project_electricity_detailList).hasSize(databaseSizeBeforeUpdate);
        Project_electricity_detail testProject_electricity_detail = project_electricity_detailList.get(project_electricity_detailList.size() - 1);
        assertThat(testProject_electricity_detail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProject_electricity_detail.getTemporaryconnection()).isEqualTo(UPDATED_TEMPORARYCONNECTION);
        assertThat(testProject_electricity_detail.getTemporaryconnectionContentType()).isEqualTo(UPDATED_TEMPORARYCONNECTION_CONTENT_TYPE);
        assertThat(testProject_electricity_detail.isLoad_required()).isEqualTo(UPDATED_LOAD_REQUIRED);
        assertThat(testProject_electricity_detail.isExisting_connecton()).isEqualTo(UPDATED_EXISTING_CONNECTON);
        assertThat(testProject_electricity_detail.getAccount_number()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testProject_electricity_detail.getLoad_demand()).isEqualTo(UPDATED_LOAD_DEMAND);
        assertThat(testProject_electricity_detail.getLoad_demand_kva()).isEqualTo(UPDATED_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getNew_load_demand_kw()).isEqualTo(UPDATED_NEW_LOAD_DEMAND_KW);
        assertThat(testProject_electricity_detail.getNew_load_demand_kva()).isEqualTo(UPDATED_NEW_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getLoad_demand_date()).isEqualTo(UPDATED_LOAD_DEMAND_DATE);
        assertThat(testProject_electricity_detail.getRegular_connection()).isEqualTo(UPDATED_REGULAR_CONNECTION);
        assertThat(testProject_electricity_detail.getRegular_connectionContentType()).isEqualTo(UPDATED_REGULAR_CONNECTION_CONTENT_TYPE);
        assertThat(testProject_electricity_detail.isRegular_load_required()).isEqualTo(UPDATED_REGULAR_LOAD_REQUIRED);
        assertThat(testProject_electricity_detail.isRegular_existing_connection()).isEqualTo(UPDATED_REGULAR_EXISTING_CONNECTION);
        assertThat(testProject_electricity_detail.getRegular_uhbvn_dhbvn_customer_type()).isEqualTo(UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE);
        assertThat(testProject_electricity_detail.getRegular_account_number()).isEqualTo(UPDATED_REGULAR_ACCOUNT_NUMBER);
        assertThat(testProject_electricity_detail.getRegular_load_if_any_kw()).isEqualTo(UPDATED_REGULAR_LOAD_IF_ANY_KW);
        assertThat(testProject_electricity_detail.getRegular_existing_load_kva()).isEqualTo(UPDATED_REGULAR_EXISTING_LOAD_KVA);
        assertThat(testProject_electricity_detail.getRegular_new_load_demand_kw()).isEqualTo(UPDATED_REGULAR_NEW_LOAD_DEMAND_KW);
        assertThat(testProject_electricity_detail.getRegular_new_load_demand_kva()).isEqualTo(UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA);
        assertThat(testProject_electricity_detail.getRegular_demand_date()).isEqualTo(UPDATED_REGULAR_DEMAND_DATE);
    }

    @Test
    public void updateNonExistingProject_electricity_detail() throws Exception {
        int databaseSizeBeforeUpdate = project_electricity_detailRepository.findAll().size();

        // Create the Project_electricity_detail
        Project_electricity_detailDTO project_electricity_detailDTO = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(project_electricity_detail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProject_electricity_detailMockMvc.perform(put("/api/project-electricity-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_electricity_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_electricity_detail in the database
        List<Project_electricity_detail> project_electricity_detailList = project_electricity_detailRepository.findAll();
        assertThat(project_electricity_detailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProject_electricity_detail() throws Exception {
        // Initialize the database
        project_electricity_detailRepository.save(project_electricity_detail);
        int databaseSizeBeforeDelete = project_electricity_detailRepository.findAll().size();

        // Get the project_electricity_detail
        restProject_electricity_detailMockMvc.perform(delete("/api/project-electricity-details/{id}", project_electricity_detail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Project_electricity_detail> project_electricity_detailList = project_electricity_detailRepository.findAll();
        assertThat(project_electricity_detailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project_electricity_detail.class);
    }
}
