package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectsitedetail;
import com.hartron.investharyana.repository.ProjectsitedetailRepository;
import com.hartron.investharyana.service.ProjectsitedetailService;
import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;
import com.hartron.investharyana.service.mapper.ProjectsitedetailMapper;
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
 * Test class for the ProjectsitedetailResource REST controller.
 *
 * @see ProjectsitedetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectsitedetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final Boolean DEFAULT_CERTIFIEDOWNERSHIP = false;
    private static final Boolean UPDATED_CERTIFIEDOWNERSHIP = true;

    private static final ByteBuffer DEFAULT_OWNERSHIP_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_OWNERSHIP_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_OWNERSHIP_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OWNERSHIP_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_LEASEAPPLICABLE = false;
    private static final Boolean UPDATED_LEASEAPPLICABLE = true;

    private static final ByteBuffer DEFAULT_LEASE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_LEASE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_LEASE_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LEASE_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_LANDAGREEMENTAPPLICABLE = false;
    private static final Boolean UPDATED_LANDAGREEMENTAPPLICABLE = true;

    private static final ByteBuffer DEFAULT_LANDAGREEMENT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_LANDAGREEMENT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_SITEPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_SITEPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_SITEPLAN_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SITEPLAN_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_LOCATIONPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_LOCATIONPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_LINEARSTRIPPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_LINEARSTRIPPLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final UUID DEFAULT_CONNECTINGROADID = UUID.randomUUID();
    private static final UUID UPDATED_CONNECTINGROADID = UUID.randomUUID();

    private static final Boolean DEFAULT_INTERSECTION_DISTANCE = false;
    private static final Boolean UPDATED_INTERSECTION_DISTANCE = true;

    private static final Boolean DEFAULT_RAILWAY_DISTANCE = false;
    private static final Boolean UPDATED_RAILWAY_DISTANCE = true;

    private static final Boolean DEFAULT_CONFIRMITY_LANDUSE = false;
    private static final Boolean UPDATED_CONFIRMITY_LANDUSE = true;

    private static final UUID DEFAULT_LANDZONE_USETYPE = UUID.randomUUID();
    private static final UUID UPDATED_LANDZONE_USETYPE = UUID.randomUUID();

    private static final ByteBuffer DEFAULT_SITESITUATED_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_SITESITUATED_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_SITESITUATED_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SITESITUATED_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final UUID DEFAULT_BUILDING_EXISTED = UUID.randomUUID();
    private static final UUID UPDATED_BUILDING_EXISTED = UUID.randomUUID();

    private static final Boolean DEFAULT_EXISTING_BUILDING_APPLICABLE = false;
    private static final Boolean UPDATED_EXISTING_BUILDING_APPLICABLE = true;

    private static final ByteBuffer DEFAULT_BUILDING_PLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_BUILDING_PLAN_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_SITESITUATEDINCONTROLLEDAREA = false;
    private static final Boolean UPDATED_SITESITUATEDINCONTROLLEDAREA = true;

    private static final ByteBuffer DEFAULT_CONTROLLED_AREA_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_CONTROLLED_AREA_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE = "image/png";

    @Autowired
    private ProjectsitedetailRepository projectsitedetailRepository;

    @Autowired
    private ProjectsitedetailMapper projectsitedetailMapper;

    @Autowired
    private ProjectsitedetailService projectsitedetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectsitedetailMockMvc;

    private Projectsitedetail projectsitedetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectsitedetailResource projectsitedetailResource = new ProjectsitedetailResource(projectsitedetailService);
        this.restProjectsitedetailMockMvc = MockMvcBuilders.standaloneSetup(projectsitedetailResource)
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
    public static Projectsitedetail createEntity() {
        Projectsitedetail projectsitedetail = new Projectsitedetail()
                .projectid(DEFAULT_PROJECTID)
                .certifiedownership(DEFAULT_CERTIFIEDOWNERSHIP)
                .ownership_document(DEFAULT_OWNERSHIP_DOCUMENT)
                .ownership_documentContentType(DEFAULT_OWNERSHIP_DOCUMENT_CONTENT_TYPE)
                .leaseapplicable(DEFAULT_LEASEAPPLICABLE)
                .lease_document(DEFAULT_LEASE_DOCUMENT)
                .lease_documentContentType(DEFAULT_LEASE_DOCUMENT_CONTENT_TYPE)
                .landagreementapplicable(DEFAULT_LANDAGREEMENTAPPLICABLE)
                .landagreement_document(DEFAULT_LANDAGREEMENT_DOCUMENT)
                .landagreement_documentContentType(DEFAULT_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE)
                .siteplan_document(DEFAULT_SITEPLAN_DOCUMENT)
                .siteplan_documentContentType(DEFAULT_SITEPLAN_DOCUMENT_CONTENT_TYPE)
                .locationplan_document(DEFAULT_LOCATIONPLAN_DOCUMENT)
                .locationplan_documentContentType(DEFAULT_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE)
                .linearstripplan_document(DEFAULT_LINEARSTRIPPLAN_DOCUMENT)
                .linearstripplan_documentContentType(DEFAULT_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE)
                .connectingroadid(DEFAULT_CONNECTINGROADID)
                .intersection_distance(DEFAULT_INTERSECTION_DISTANCE)
                .railway_distance(DEFAULT_RAILWAY_DISTANCE)
                .confirmity_landuse(DEFAULT_CONFIRMITY_LANDUSE)
                .landzone_usetype(DEFAULT_LANDZONE_USETYPE)
                .sitesituated_document(DEFAULT_SITESITUATED_DOCUMENT)
                .sitesituated_documentContentType(DEFAULT_SITESITUATED_DOCUMENT_CONTENT_TYPE)
                .building_existed(DEFAULT_BUILDING_EXISTED)
                .existing_building_applicable(DEFAULT_EXISTING_BUILDING_APPLICABLE)
                .building_plan_document(DEFAULT_BUILDING_PLAN_DOCUMENT)
                .building_plan_documentContentType(DEFAULT_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE)
                .sitesituatedincontrolledarea(DEFAULT_SITESITUATEDINCONTROLLEDAREA)
                .controlled_area_document(DEFAULT_CONTROLLED_AREA_DOCUMENT)
                .controlled_area_documentContentType(DEFAULT_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE);
        return projectsitedetail;
    }

    @Before
    public void initTest() {
        projectsitedetailRepository.deleteAll();
        projectsitedetail = createEntity();
    }

    @Test
    public void createProjectsitedetail() throws Exception {
        int databaseSizeBeforeCreate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);

        restProjectsitedetailMockMvc.perform(post("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeCreate + 1);
        Projectsitedetail testProjectsitedetail = projectsitedetailList.get(projectsitedetailList.size() - 1);
        assertThat(testProjectsitedetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectsitedetail.isCertifiedownership()).isEqualTo(DEFAULT_CERTIFIEDOWNERSHIP);
        assertThat(testProjectsitedetail.getOwnership_document()).isEqualTo(DEFAULT_OWNERSHIP_DOCUMENT);
        assertThat(testProjectsitedetail.getOwnership_documentContentType()).isEqualTo(DEFAULT_OWNERSHIP_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isLeaseapplicable()).isEqualTo(DEFAULT_LEASEAPPLICABLE);
        assertThat(testProjectsitedetail.getLease_document()).isEqualTo(DEFAULT_LEASE_DOCUMENT);
        assertThat(testProjectsitedetail.getLease_documentContentType()).isEqualTo(DEFAULT_LEASE_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isLandagreementapplicable()).isEqualTo(DEFAULT_LANDAGREEMENTAPPLICABLE);
        assertThat(testProjectsitedetail.getLandagreement_document()).isEqualTo(DEFAULT_LANDAGREEMENT_DOCUMENT);
        assertThat(testProjectsitedetail.getLandagreement_documentContentType()).isEqualTo(DEFAULT_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getSiteplan_document()).isEqualTo(DEFAULT_SITEPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getSiteplan_documentContentType()).isEqualTo(DEFAULT_SITEPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getLocationplan_document()).isEqualTo(DEFAULT_LOCATIONPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getLocationplan_documentContentType()).isEqualTo(DEFAULT_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getLinearstripplan_document()).isEqualTo(DEFAULT_LINEARSTRIPPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getLinearstripplan_documentContentType()).isEqualTo(DEFAULT_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getConnectingroadid()).isEqualTo(DEFAULT_CONNECTINGROADID);
        assertThat(testProjectsitedetail.isIntersection_distance()).isEqualTo(DEFAULT_INTERSECTION_DISTANCE);
        assertThat(testProjectsitedetail.isRailway_distance()).isEqualTo(DEFAULT_RAILWAY_DISTANCE);
        assertThat(testProjectsitedetail.isConfirmity_landuse()).isEqualTo(DEFAULT_CONFIRMITY_LANDUSE);
        assertThat(testProjectsitedetail.getLandzone_usetype()).isEqualTo(DEFAULT_LANDZONE_USETYPE);
        assertThat(testProjectsitedetail.getSitesituated_document()).isEqualTo(DEFAULT_SITESITUATED_DOCUMENT);
        assertThat(testProjectsitedetail.getSitesituated_documentContentType()).isEqualTo(DEFAULT_SITESITUATED_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getBuilding_existed()).isEqualTo(DEFAULT_BUILDING_EXISTED);
        assertThat(testProjectsitedetail.isExisting_building_applicable()).isEqualTo(DEFAULT_EXISTING_BUILDING_APPLICABLE);
        assertThat(testProjectsitedetail.getBuilding_plan_document()).isEqualTo(DEFAULT_BUILDING_PLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getBuilding_plan_documentContentType()).isEqualTo(DEFAULT_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isSitesituatedincontrolledarea()).isEqualTo(DEFAULT_SITESITUATEDINCONTROLLEDAREA);
        assertThat(testProjectsitedetail.getControlled_area_document()).isEqualTo(DEFAULT_CONTROLLED_AREA_DOCUMENT);
        assertThat(testProjectsitedetail.getControlled_area_documentContentType()).isEqualTo(DEFAULT_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void createProjectsitedetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail with an existing ID
        Projectsitedetail existingProjectsitedetail = new Projectsitedetail();
        existingProjectsitedetail.setId(UUID.randomUUID());
        ProjectsitedetailDTO existingProjectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(existingProjectsitedetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectsitedetailMockMvc.perform(post("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectsitedetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectsitedetails() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);

        // Get all the projectsitedetailList
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectsitedetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].certifiedownership").value(hasItem(DEFAULT_CERTIFIEDOWNERSHIP.booleanValue())))
            .andExpect(jsonPath("$.[*].ownership_documentContentType").value(hasItem(DEFAULT_OWNERSHIP_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].ownership_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_OWNERSHIP_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].leaseapplicable").value(hasItem(DEFAULT_LEASEAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].lease_documentContentType").value(hasItem(DEFAULT_LEASE_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].lease_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_LEASE_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].landagreementapplicable").value(hasItem(DEFAULT_LANDAGREEMENTAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].landagreement_documentContentType").value(hasItem(DEFAULT_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].landagreement_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_LANDAGREEMENT_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].siteplan_documentContentType").value(hasItem(DEFAULT_SITEPLAN_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].siteplan_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_SITEPLAN_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].locationplan_documentContentType").value(hasItem(DEFAULT_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].locationplan_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOCATIONPLAN_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].linearstripplan_documentContentType").value(hasItem(DEFAULT_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].linearstripplan_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_LINEARSTRIPPLAN_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].connectingroadid").value(hasItem(DEFAULT_CONNECTINGROADID.toString())))
            .andExpect(jsonPath("$.[*].intersection_distance").value(hasItem(DEFAULT_INTERSECTION_DISTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].railway_distance").value(hasItem(DEFAULT_RAILWAY_DISTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].confirmity_landuse").value(hasItem(DEFAULT_CONFIRMITY_LANDUSE.booleanValue())))
            .andExpect(jsonPath("$.[*].landzone_usetype").value(hasItem(DEFAULT_LANDZONE_USETYPE.toString())))
            .andExpect(jsonPath("$.[*].sitesituated_documentContentType").value(hasItem(DEFAULT_SITESITUATED_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].sitesituated_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_SITESITUATED_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].building_existed").value(hasItem(DEFAULT_BUILDING_EXISTED.toString())))
            .andExpect(jsonPath("$.[*].existing_building_applicable").value(hasItem(DEFAULT_EXISTING_BUILDING_APPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].building_plan_documentContentType").value(hasItem(DEFAULT_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].building_plan_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_BUILDING_PLAN_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].sitesituatedincontrolledarea").value(hasItem(DEFAULT_SITESITUATEDINCONTROLLEDAREA.booleanValue())))
            .andExpect(jsonPath("$.[*].controlled_area_documentContentType").value(hasItem(DEFAULT_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].controlled_area_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTROLLED_AREA_DOCUMENT.array()))));
    }

    @Test
    public void getProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);

        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails/{id}", projectsitedetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectsitedetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.certifiedownership").value(DEFAULT_CERTIFIEDOWNERSHIP.booleanValue()))
            .andExpect(jsonPath("$.ownership_documentContentType").value(DEFAULT_OWNERSHIP_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.ownership_document").value(Base64Utils.encodeToString(DEFAULT_OWNERSHIP_DOCUMENT.array())))
            .andExpect(jsonPath("$.leaseapplicable").value(DEFAULT_LEASEAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.lease_documentContentType").value(DEFAULT_LEASE_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.lease_document").value(Base64Utils.encodeToString(DEFAULT_LEASE_DOCUMENT.array())))
            .andExpect(jsonPath("$.landagreementapplicable").value(DEFAULT_LANDAGREEMENTAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.landagreement_documentContentType").value(DEFAULT_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.landagreement_document").value(Base64Utils.encodeToString(DEFAULT_LANDAGREEMENT_DOCUMENT.array())))
            .andExpect(jsonPath("$.siteplan_documentContentType").value(DEFAULT_SITEPLAN_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.siteplan_document").value(Base64Utils.encodeToString(DEFAULT_SITEPLAN_DOCUMENT.array())))
            .andExpect(jsonPath("$.locationplan_documentContentType").value(DEFAULT_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.locationplan_document").value(Base64Utils.encodeToString(DEFAULT_LOCATIONPLAN_DOCUMENT.array())))
            .andExpect(jsonPath("$.linearstripplan_documentContentType").value(DEFAULT_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.linearstripplan_document").value(Base64Utils.encodeToString(DEFAULT_LINEARSTRIPPLAN_DOCUMENT.array())))
            .andExpect(jsonPath("$.connectingroadid").value(DEFAULT_CONNECTINGROADID.toString()))
            .andExpect(jsonPath("$.intersection_distance").value(DEFAULT_INTERSECTION_DISTANCE.booleanValue()))
            .andExpect(jsonPath("$.railway_distance").value(DEFAULT_RAILWAY_DISTANCE.booleanValue()))
            .andExpect(jsonPath("$.confirmity_landuse").value(DEFAULT_CONFIRMITY_LANDUSE.booleanValue()))
            .andExpect(jsonPath("$.landzone_usetype").value(DEFAULT_LANDZONE_USETYPE.toString()))
            .andExpect(jsonPath("$.sitesituated_documentContentType").value(DEFAULT_SITESITUATED_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.sitesituated_document").value(Base64Utils.encodeToString(DEFAULT_SITESITUATED_DOCUMENT.array())))
            .andExpect(jsonPath("$.building_existed").value(DEFAULT_BUILDING_EXISTED.toString()))
            .andExpect(jsonPath("$.existing_building_applicable").value(DEFAULT_EXISTING_BUILDING_APPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.building_plan_documentContentType").value(DEFAULT_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.building_plan_document").value(Base64Utils.encodeToString(DEFAULT_BUILDING_PLAN_DOCUMENT.array())))
            .andExpect(jsonPath("$.sitesituatedincontrolledarea").value(DEFAULT_SITESITUATEDINCONTROLLEDAREA.booleanValue()))
            .andExpect(jsonPath("$.controlled_area_documentContentType").value(DEFAULT_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.controlled_area_document").value(Base64Utils.encodeToString(DEFAULT_CONTROLLED_AREA_DOCUMENT.array())));
    }

    @Test
    public void getNonExistingProjectsitedetail() throws Exception {
        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);
        int databaseSizeBeforeUpdate = projectsitedetailRepository.findAll().size();

        // Update the projectsitedetail
        Projectsitedetail updatedProjectsitedetail = projectsitedetailRepository.findOne(projectsitedetail.getId());
        updatedProjectsitedetail
                .projectid(UPDATED_PROJECTID)
                .certifiedownership(UPDATED_CERTIFIEDOWNERSHIP)
                .ownership_document(UPDATED_OWNERSHIP_DOCUMENT)
                .ownership_documentContentType(UPDATED_OWNERSHIP_DOCUMENT_CONTENT_TYPE)
                .leaseapplicable(UPDATED_LEASEAPPLICABLE)
                .lease_document(UPDATED_LEASE_DOCUMENT)
                .lease_documentContentType(UPDATED_LEASE_DOCUMENT_CONTENT_TYPE)
                .landagreementapplicable(UPDATED_LANDAGREEMENTAPPLICABLE)
                .landagreement_document(UPDATED_LANDAGREEMENT_DOCUMENT)
                .landagreement_documentContentType(UPDATED_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE)
                .siteplan_document(UPDATED_SITEPLAN_DOCUMENT)
                .siteplan_documentContentType(UPDATED_SITEPLAN_DOCUMENT_CONTENT_TYPE)
                .locationplan_document(UPDATED_LOCATIONPLAN_DOCUMENT)
                .locationplan_documentContentType(UPDATED_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE)
                .linearstripplan_document(UPDATED_LINEARSTRIPPLAN_DOCUMENT)
                .linearstripplan_documentContentType(UPDATED_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE)
                .connectingroadid(UPDATED_CONNECTINGROADID)
                .intersection_distance(UPDATED_INTERSECTION_DISTANCE)
                .railway_distance(UPDATED_RAILWAY_DISTANCE)
                .confirmity_landuse(UPDATED_CONFIRMITY_LANDUSE)
                .landzone_usetype(UPDATED_LANDZONE_USETYPE)
                .sitesituated_document(UPDATED_SITESITUATED_DOCUMENT)
                .sitesituated_documentContentType(UPDATED_SITESITUATED_DOCUMENT_CONTENT_TYPE)
                .building_existed(UPDATED_BUILDING_EXISTED)
                .existing_building_applicable(UPDATED_EXISTING_BUILDING_APPLICABLE)
                .building_plan_document(UPDATED_BUILDING_PLAN_DOCUMENT)
                .building_plan_documentContentType(UPDATED_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE)
                .sitesituatedincontrolledarea(UPDATED_SITESITUATEDINCONTROLLEDAREA)
                .controlled_area_document(UPDATED_CONTROLLED_AREA_DOCUMENT)
                .controlled_area_documentContentType(UPDATED_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE);
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(updatedProjectsitedetail);

        restProjectsitedetailMockMvc.perform(put("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isOk());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeUpdate);
        Projectsitedetail testProjectsitedetail = projectsitedetailList.get(projectsitedetailList.size() - 1);
        assertThat(testProjectsitedetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectsitedetail.isCertifiedownership()).isEqualTo(UPDATED_CERTIFIEDOWNERSHIP);
        assertThat(testProjectsitedetail.getOwnership_document()).isEqualTo(UPDATED_OWNERSHIP_DOCUMENT);
        assertThat(testProjectsitedetail.getOwnership_documentContentType()).isEqualTo(UPDATED_OWNERSHIP_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isLeaseapplicable()).isEqualTo(UPDATED_LEASEAPPLICABLE);
        assertThat(testProjectsitedetail.getLease_document()).isEqualTo(UPDATED_LEASE_DOCUMENT);
        assertThat(testProjectsitedetail.getLease_documentContentType()).isEqualTo(UPDATED_LEASE_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isLandagreementapplicable()).isEqualTo(UPDATED_LANDAGREEMENTAPPLICABLE);
        assertThat(testProjectsitedetail.getLandagreement_document()).isEqualTo(UPDATED_LANDAGREEMENT_DOCUMENT);
        assertThat(testProjectsitedetail.getLandagreement_documentContentType()).isEqualTo(UPDATED_LANDAGREEMENT_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getSiteplan_document()).isEqualTo(UPDATED_SITEPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getSiteplan_documentContentType()).isEqualTo(UPDATED_SITEPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getLocationplan_document()).isEqualTo(UPDATED_LOCATIONPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getLocationplan_documentContentType()).isEqualTo(UPDATED_LOCATIONPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getLinearstripplan_document()).isEqualTo(UPDATED_LINEARSTRIPPLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getLinearstripplan_documentContentType()).isEqualTo(UPDATED_LINEARSTRIPPLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getConnectingroadid()).isEqualTo(UPDATED_CONNECTINGROADID);
        assertThat(testProjectsitedetail.isIntersection_distance()).isEqualTo(UPDATED_INTERSECTION_DISTANCE);
        assertThat(testProjectsitedetail.isRailway_distance()).isEqualTo(UPDATED_RAILWAY_DISTANCE);
        assertThat(testProjectsitedetail.isConfirmity_landuse()).isEqualTo(UPDATED_CONFIRMITY_LANDUSE);
        assertThat(testProjectsitedetail.getLandzone_usetype()).isEqualTo(UPDATED_LANDZONE_USETYPE);
        assertThat(testProjectsitedetail.getSitesituated_document()).isEqualTo(UPDATED_SITESITUATED_DOCUMENT);
        assertThat(testProjectsitedetail.getSitesituated_documentContentType()).isEqualTo(UPDATED_SITESITUATED_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.getBuilding_existed()).isEqualTo(UPDATED_BUILDING_EXISTED);
        assertThat(testProjectsitedetail.isExisting_building_applicable()).isEqualTo(UPDATED_EXISTING_BUILDING_APPLICABLE);
        assertThat(testProjectsitedetail.getBuilding_plan_document()).isEqualTo(UPDATED_BUILDING_PLAN_DOCUMENT);
        assertThat(testProjectsitedetail.getBuilding_plan_documentContentType()).isEqualTo(UPDATED_BUILDING_PLAN_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectsitedetail.isSitesituatedincontrolledarea()).isEqualTo(UPDATED_SITESITUATEDINCONTROLLEDAREA);
        assertThat(testProjectsitedetail.getControlled_area_document()).isEqualTo(UPDATED_CONTROLLED_AREA_DOCUMENT);
        assertThat(testProjectsitedetail.getControlled_area_documentContentType()).isEqualTo(UPDATED_CONTROLLED_AREA_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingProjectsitedetail() throws Exception {
        int databaseSizeBeforeUpdate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectsitedetailMockMvc.perform(put("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);
        int databaseSizeBeforeDelete = projectsitedetailRepository.findAll().size();

        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(delete("/api/projectsitedetails/{id}", projectsitedetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectsitedetail.class);
    }
}
