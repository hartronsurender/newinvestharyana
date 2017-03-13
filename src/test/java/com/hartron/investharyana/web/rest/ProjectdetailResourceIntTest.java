package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectdetail;
import com.hartron.investharyana.repository.ProjectdetailRepository;
import com.hartron.investharyana.service.ProjectdetailService;
import com.hartron.investharyana.service.dto.ProjectdetailDTO;
import com.hartron.investharyana.service.mapper.ProjectdetailMapper;
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

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectdetailResource REST controller.
 *
 * @see ProjectdetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectdetailResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SITEADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SITEADDRESS = "BBBBBBBBBB";

    private static final UUID DEFAULT_DISTRICT = UUID.randomUUID();
    private static final UUID UPDATED_DISTRICT = UUID.randomUUID();

    private static final UUID DEFAULT_BLOCK = UUID.randomUUID();
    private static final UUID UPDATED_BLOCK = UUID.randomUUID();

    private static final UUID DEFAULT_CITY_TOWN_VILLAGE = UUID.randomUUID();
    private static final UUID UPDATED_CITY_TOWN_VILLAGE = UUID.randomUUID();

    private static final UUID DEFAULT_TEHSIL_SUBTEHSIL = UUID.randomUUID();
    private static final UUID UPDATED_TEHSIL_SUBTEHSIL = UUID.randomUUID();

    private static final Boolean DEFAULT_MULTYVILLAGEINVOLVED = false;
    private static final Boolean UPDATED_MULTYVILLAGEINVOLVED = true;

    private static final String DEFAULT_VILLAGEINVOLVED = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGEINVOLVED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FALLS_IN_ARAVALLI = false;
    private static final Boolean UPDATED_FALLS_IN_ARAVALLI = true;

    private static final Boolean DEFAULT_LANDPROCURED = false;
    private static final Boolean UPDATED_LANDPROCURED = true;

    private static final Boolean DEFAULT_ALLOTEDBYHSIIDC = false;
    private static final Boolean UPDATED_ALLOTEDBYHSIIDC = true;

    private static final String DEFAULT_ESTATE = "AAAAAAAAAA";
    private static final String UPDATED_ESTATE = "BBBBBBBBBB";

    private static final String DEFAULT_CLUSTER = "AAAAAAAAAA";
    private static final String UPDATED_CLUSTER = "BBBBBBBBBB";

    private static final String DEFAULT_PHASE = "AAAAAAAAAA";
    private static final String UPDATED_PHASE = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_PLOTNO = "AAAAAAAAAA";
    private static final String UPDATED_PLOTNO = "BBBBBBBBBB";

    private static final String DEFAULT_HADBASTNO = "AAAAAAAAAA";
    private static final String UPDATED_HADBASTNO = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_KHASRADOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_KHASRADOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_KHASRADOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_KHASRADOCUMENT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_LIES_UNDER_MC = false;
    private static final Boolean UPDATED_LIES_UNDER_MC = true;

    private static final Integer DEFAULT_DISTANCE_FROM_MC = 1;
    private static final Integer UPDATED_DISTANCE_FROM_MC = 2;

    private static final Boolean DEFAULT_LOCATED_IN_URBAN = false;
    private static final Boolean UPDATED_LOCATED_IN_URBAN = true;

    private static final ByteBuffer DEFAULT_REVENUE_SHAJRA_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_REVENUE_SHAJRA_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_JAMABANDI_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_JAMABANDI_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_JAMABANDI_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_JAMABANDI_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_NONENCUMBRANCECERTIFICATE = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_NONENCUMBRANCECERTIFICATE = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE = "image/png";

    private static final BigDecimal DEFAULT_TOTALPROPOSEDPROJECTAREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALPROPOSEDPROJECTAREA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROPOSED_BUILD_UP_AREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROPOSED_BUILD_UP_AREA = new BigDecimal(2);

    private static final UUID DEFAULT_SECOTR = UUID.randomUUID();
    private static final UUID UPDATED_SECOTR = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECTPURPOSE = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTPURPOSE = UUID.randomUUID();

    private static final UUID DEFAULT_SIZE_OF_INDUSTRY = UUID.randomUUID();
    private static final UUID UPDATED_SIZE_OF_INDUSTRY = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECT_TYPE = UUID.randomUUID();
    private static final UUID UPDATED_PROJECT_TYPE = UUID.randomUUID();

    private static final String DEFAULT_NIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NIC_CODE = "BBBBBBBBBB";

    private static final UUID DEFAULT_CATEGORY_OF_PROJECT = UUID.randomUUID();
    private static final UUID UPDATED_CATEGORY_OF_PROJECT = UUID.randomUUID();

    private static final UUID DEFAULT_COLLABORATION_WITH_FOREIGN = UUID.randomUUID();
    private static final UUID UPDATED_COLLABORATION_WITH_FOREIGN = UUID.randomUUID();

    private static final ByteBuffer DEFAULT_DETAIL_PROJECT_REPORT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_DETAIL_PROJECT_REPORT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_DETAIL_PROJECT_REPORT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DETAIL_PROJECT_REPORT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_EXISTING_REGULATORY_APPROVAL = false;
    private static final Boolean UPDATED_EXISTING_REGULATORY_APPROVAL = true;

    private static final UUID DEFAULT_APPROVAL_APPLICATION_FORM = UUID.randomUUID();
    private static final UUID UPDATED_APPROVAL_APPLICATION_FORM = UUID.randomUUID();

    private static final ByteBuffer DEFAULT_APPROVAL_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_APPROVAL_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_APPROVAL_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_APPROVAL_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE = false;
    private static final Boolean UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE = true;

    private static final ByteBuffer DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_EDC_SIF_CLU_FEE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_PROPOSE_EMPLOYEEMENT = 1;
    private static final Integer UPDATED_PROPOSE_EMPLOYEEMENT = 2;

    @Autowired
    private ProjectdetailRepository projectdetailRepository;

    @Autowired
    private ProjectdetailMapper projectdetailMapper;

    @Autowired
    private ProjectdetailService projectdetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectdetailMockMvc;

    private Projectdetail projectdetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectdetailResource projectdetailResource = new ProjectdetailResource(projectdetailService);
        this.restProjectdetailMockMvc = MockMvcBuilders.standaloneSetup(projectdetailResource)
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
    public static Projectdetail createEntity() {
        Projectdetail projectdetail = new Projectdetail()
                .siteaddress(DEFAULT_SITEADDRESS)
                .district(DEFAULT_DISTRICT)
                .block(DEFAULT_BLOCK)
                .city_town_village(DEFAULT_CITY_TOWN_VILLAGE)
                .tehsil_subtehsil(DEFAULT_TEHSIL_SUBTEHSIL)
                .multyvillageinvolved(DEFAULT_MULTYVILLAGEINVOLVED)
                .villageinvolved(DEFAULT_VILLAGEINVOLVED)
                .falls_in_aravalli(DEFAULT_FALLS_IN_ARAVALLI)
                .landprocured(DEFAULT_LANDPROCURED)
                .allotedbyhsiidc(DEFAULT_ALLOTEDBYHSIIDC)
                .estate(DEFAULT_ESTATE)
                .cluster(DEFAULT_CLUSTER)
                .phase(DEFAULT_PHASE)
                .sector(DEFAULT_SECTOR)
                .plotno(DEFAULT_PLOTNO)
                .hadbastno(DEFAULT_HADBASTNO)
                .khasradocument(DEFAULT_KHASRADOCUMENT)
                .khasradocumentContentType(DEFAULT_KHASRADOCUMENT_CONTENT_TYPE)
                .lies_under_mc(DEFAULT_LIES_UNDER_MC)
                .distance_from_mc(DEFAULT_DISTANCE_FROM_MC)
                .located_in_urban(DEFAULT_LOCATED_IN_URBAN)
                .revenue_shajra_document(DEFAULT_REVENUE_SHAJRA_DOCUMENT)
                .revenue_shajra_documentContentType(DEFAULT_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE)
                .jamabandi_document(DEFAULT_JAMABANDI_DOCUMENT)
                .jamabandi_documentContentType(DEFAULT_JAMABANDI_DOCUMENT_CONTENT_TYPE)
                .nonencumbrancecertificate(DEFAULT_NONENCUMBRANCECERTIFICATE)
                .nonencumbrancecertificateContentType(DEFAULT_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE)
                .totalproposedprojectarea(DEFAULT_TOTALPROPOSEDPROJECTAREA)
                .proposed_build_up_area(DEFAULT_PROPOSED_BUILD_UP_AREA)
                .secotr(DEFAULT_SECOTR)
                .projectpurpose(DEFAULT_PROJECTPURPOSE)
                .size_of_industry(DEFAULT_SIZE_OF_INDUSTRY)
                .project_type(DEFAULT_PROJECT_TYPE)
                .nic_code(DEFAULT_NIC_CODE)
                .category_of_project(DEFAULT_CATEGORY_OF_PROJECT)
                .collaboration_with_foreign(DEFAULT_COLLABORATION_WITH_FOREIGN)
                .detail_project_report(DEFAULT_DETAIL_PROJECT_REPORT)
                .detail_project_reportContentType(DEFAULT_DETAIL_PROJECT_REPORT_CONTENT_TYPE)
                .existing_regulatory_approval(DEFAULT_EXISTING_REGULATORY_APPROVAL)
                .approval_application_form(DEFAULT_APPROVAL_APPLICATION_FORM)
                .approval_document(DEFAULT_APPROVAL_DOCUMENT)
                .approval_documentContentType(DEFAULT_APPROVAL_DOCUMENT_CONTENT_TYPE)
                .edc_sif_clu_fee_paid_applicable(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE)
                .edc_sif_clu_fee_document(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT)
                .edc_sif_clu_fee_documentContentType(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE)
                .propose_employeement(DEFAULT_PROPOSE_EMPLOYEEMENT);
        return projectdetail;
    }

    @Before
    public void initTest() {
        projectdetailRepository.deleteAll();
        projectdetail = createEntity();
    }

    @Test
    public void createProjectdetail() throws Exception {
        int databaseSizeBeforeCreate = projectdetailRepository.findAll().size();

        // Create the Projectdetail
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);

        restProjectdetailMockMvc.perform(post("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Projectdetail testProjectdetail = projectdetailList.get(projectdetailList.size() - 1);
        assertThat(testProjectdetail.getSiteaddress()).isEqualTo(DEFAULT_SITEADDRESS);
        assertThat(testProjectdetail.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testProjectdetail.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testProjectdetail.getCity_town_village()).isEqualTo(DEFAULT_CITY_TOWN_VILLAGE);
        assertThat(testProjectdetail.getTehsil_subtehsil()).isEqualTo(DEFAULT_TEHSIL_SUBTEHSIL);
        assertThat(testProjectdetail.isMultyvillageinvolved()).isEqualTo(DEFAULT_MULTYVILLAGEINVOLVED);
        assertThat(testProjectdetail.getVillageinvolved()).isEqualTo(DEFAULT_VILLAGEINVOLVED);
        assertThat(testProjectdetail.isFalls_in_aravalli()).isEqualTo(DEFAULT_FALLS_IN_ARAVALLI);
        assertThat(testProjectdetail.isLandprocured()).isEqualTo(DEFAULT_LANDPROCURED);
        assertThat(testProjectdetail.isAllotedbyhsiidc()).isEqualTo(DEFAULT_ALLOTEDBYHSIIDC);
        assertThat(testProjectdetail.getEstate()).isEqualTo(DEFAULT_ESTATE);
        assertThat(testProjectdetail.getCluster()).isEqualTo(DEFAULT_CLUSTER);
        assertThat(testProjectdetail.getPhase()).isEqualTo(DEFAULT_PHASE);
        assertThat(testProjectdetail.getSector()).isEqualTo(DEFAULT_SECTOR);
        assertThat(testProjectdetail.getPlotno()).isEqualTo(DEFAULT_PLOTNO);
        assertThat(testProjectdetail.getHadbastno()).isEqualTo(DEFAULT_HADBASTNO);
        assertThat(testProjectdetail.getKhasradocument()).isEqualTo(DEFAULT_KHASRADOCUMENT);
        assertThat(testProjectdetail.getKhasradocumentContentType()).isEqualTo(DEFAULT_KHASRADOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.isLies_under_mc()).isEqualTo(DEFAULT_LIES_UNDER_MC);
        assertThat(testProjectdetail.getDistance_from_mc()).isEqualTo(DEFAULT_DISTANCE_FROM_MC);
        assertThat(testProjectdetail.isLocated_in_urban()).isEqualTo(DEFAULT_LOCATED_IN_URBAN);
        assertThat(testProjectdetail.getRevenue_shajra_document()).isEqualTo(DEFAULT_REVENUE_SHAJRA_DOCUMENT);
        assertThat(testProjectdetail.getRevenue_shajra_documentContentType()).isEqualTo(DEFAULT_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getJamabandi_document()).isEqualTo(DEFAULT_JAMABANDI_DOCUMENT);
        assertThat(testProjectdetail.getJamabandi_documentContentType()).isEqualTo(DEFAULT_JAMABANDI_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getNonencumbrancecertificate()).isEqualTo(DEFAULT_NONENCUMBRANCECERTIFICATE);
        assertThat(testProjectdetail.getNonencumbrancecertificateContentType()).isEqualTo(DEFAULT_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE);
        assertThat(testProjectdetail.getTotalproposedprojectarea()).isEqualTo(DEFAULT_TOTALPROPOSEDPROJECTAREA);
        assertThat(testProjectdetail.getProposed_build_up_area()).isEqualTo(DEFAULT_PROPOSED_BUILD_UP_AREA);
        assertThat(testProjectdetail.getSecotr()).isEqualTo(DEFAULT_SECOTR);
        assertThat(testProjectdetail.getProjectpurpose()).isEqualTo(DEFAULT_PROJECTPURPOSE);
        assertThat(testProjectdetail.getSize_of_industry()).isEqualTo(DEFAULT_SIZE_OF_INDUSTRY);
        assertThat(testProjectdetail.getProject_type()).isEqualTo(DEFAULT_PROJECT_TYPE);
        assertThat(testProjectdetail.getNic_code()).isEqualTo(DEFAULT_NIC_CODE);
        assertThat(testProjectdetail.getCategory_of_project()).isEqualTo(DEFAULT_CATEGORY_OF_PROJECT);
        assertThat(testProjectdetail.getCollaboration_with_foreign()).isEqualTo(DEFAULT_COLLABORATION_WITH_FOREIGN);
        assertThat(testProjectdetail.getDetail_project_report()).isEqualTo(DEFAULT_DETAIL_PROJECT_REPORT);
        assertThat(testProjectdetail.getDetail_project_reportContentType()).isEqualTo(DEFAULT_DETAIL_PROJECT_REPORT_CONTENT_TYPE);
        assertThat(testProjectdetail.isExisting_regulatory_approval()).isEqualTo(DEFAULT_EXISTING_REGULATORY_APPROVAL);
        assertThat(testProjectdetail.getApproval_application_form()).isEqualTo(DEFAULT_APPROVAL_APPLICATION_FORM);
        assertThat(testProjectdetail.getApproval_document()).isEqualTo(DEFAULT_APPROVAL_DOCUMENT);
        assertThat(testProjectdetail.getApproval_documentContentType()).isEqualTo(DEFAULT_APPROVAL_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.isEdc_sif_clu_fee_paid_applicable()).isEqualTo(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_document()).isEqualTo(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_documentContentType()).isEqualTo(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getPropose_employeement()).isEqualTo(DEFAULT_PROPOSE_EMPLOYEEMENT);
    }

    @Test
    public void createProjectdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectdetailRepository.findAll().size();

        // Create the Projectdetail with an existing ID
        Projectdetail existingProjectdetail = new Projectdetail();
        existingProjectdetail.setId(UUID.randomUUID());
        ProjectdetailDTO existingProjectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(existingProjectdetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectdetailMockMvc.perform(post("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectdetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectdetails() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);

        // Get all the projectdetailList
        restProjectdetailMockMvc.perform(get("/api/projectdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectdetail.getId().toString())))
            .andExpect(jsonPath("$.[*].siteaddress").value(hasItem(DEFAULT_SITEADDRESS.toString())))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK.toString())))
            .andExpect(jsonPath("$.[*].city_town_village").value(hasItem(DEFAULT_CITY_TOWN_VILLAGE.toString())))
            .andExpect(jsonPath("$.[*].tehsil_subtehsil").value(hasItem(DEFAULT_TEHSIL_SUBTEHSIL.toString())))
            .andExpect(jsonPath("$.[*].multyvillageinvolved").value(hasItem(DEFAULT_MULTYVILLAGEINVOLVED.booleanValue())))
            .andExpect(jsonPath("$.[*].villageinvolved").value(hasItem(DEFAULT_VILLAGEINVOLVED.toString())))
            .andExpect(jsonPath("$.[*].falls_in_aravalli").value(hasItem(DEFAULT_FALLS_IN_ARAVALLI.booleanValue())))
            .andExpect(jsonPath("$.[*].landprocured").value(hasItem(DEFAULT_LANDPROCURED.booleanValue())))
            .andExpect(jsonPath("$.[*].allotedbyhsiidc").value(hasItem(DEFAULT_ALLOTEDBYHSIIDC.booleanValue())))
            .andExpect(jsonPath("$.[*].estate").value(hasItem(DEFAULT_ESTATE.toString())))
            .andExpect(jsonPath("$.[*].cluster").value(hasItem(DEFAULT_CLUSTER.toString())))
            .andExpect(jsonPath("$.[*].phase").value(hasItem(DEFAULT_PHASE.toString())))
            .andExpect(jsonPath("$.[*].sector").value(hasItem(DEFAULT_SECTOR.toString())))
            .andExpect(jsonPath("$.[*].plotno").value(hasItem(DEFAULT_PLOTNO.toString())))
            .andExpect(jsonPath("$.[*].hadbastno").value(hasItem(DEFAULT_HADBASTNO.toString())))
            .andExpect(jsonPath("$.[*].khasradocumentContentType").value(hasItem(DEFAULT_KHASRADOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].khasradocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_KHASRADOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].lies_under_mc").value(hasItem(DEFAULT_LIES_UNDER_MC.booleanValue())))
            .andExpect(jsonPath("$.[*].distance_from_mc").value(hasItem(DEFAULT_DISTANCE_FROM_MC)))
            .andExpect(jsonPath("$.[*].located_in_urban").value(hasItem(DEFAULT_LOCATED_IN_URBAN.booleanValue())))
            .andExpect(jsonPath("$.[*].revenue_shajra_documentContentType").value(hasItem(DEFAULT_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].revenue_shajra_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_REVENUE_SHAJRA_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].jamabandi_documentContentType").value(hasItem(DEFAULT_JAMABANDI_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].jamabandi_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_JAMABANDI_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].nonencumbrancecertificateContentType").value(hasItem(DEFAULT_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nonencumbrancecertificate").value(hasItem(Base64Utils.encodeToString(DEFAULT_NONENCUMBRANCECERTIFICATE.array()))))
            .andExpect(jsonPath("$.[*].totalproposedprojectarea").value(hasItem(DEFAULT_TOTALPROPOSEDPROJECTAREA.intValue())))
            .andExpect(jsonPath("$.[*].proposed_build_up_area").value(hasItem(DEFAULT_PROPOSED_BUILD_UP_AREA.intValue())))
            .andExpect(jsonPath("$.[*].secotr").value(hasItem(DEFAULT_SECOTR.toString())))
            .andExpect(jsonPath("$.[*].projectpurpose").value(hasItem(DEFAULT_PROJECTPURPOSE.toString())))
            .andExpect(jsonPath("$.[*].size_of_industry").value(hasItem(DEFAULT_SIZE_OF_INDUSTRY.toString())))
            .andExpect(jsonPath("$.[*].project_type").value(hasItem(DEFAULT_PROJECT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].nic_code").value(hasItem(DEFAULT_NIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].category_of_project").value(hasItem(DEFAULT_CATEGORY_OF_PROJECT.toString())))
            .andExpect(jsonPath("$.[*].collaboration_with_foreign").value(hasItem(DEFAULT_COLLABORATION_WITH_FOREIGN.toString())))
            .andExpect(jsonPath("$.[*].detail_project_reportContentType").value(hasItem(DEFAULT_DETAIL_PROJECT_REPORT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].detail_project_report").value(hasItem(Base64Utils.encodeToString(DEFAULT_DETAIL_PROJECT_REPORT.array()))))
            .andExpect(jsonPath("$.[*].existing_regulatory_approval").value(hasItem(DEFAULT_EXISTING_REGULATORY_APPROVAL.booleanValue())))
            .andExpect(jsonPath("$.[*].approval_application_form").value(hasItem(DEFAULT_APPROVAL_APPLICATION_FORM.toString())))
            .andExpect(jsonPath("$.[*].approval_documentContentType").value(hasItem(DEFAULT_APPROVAL_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].approval_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_APPROVAL_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].edc_sif_clu_fee_paid_applicable").value(hasItem(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].edc_sif_clu_fee_documentContentType").value(hasItem(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].edc_sif_clu_fee_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].propose_employeement").value(hasItem(DEFAULT_PROPOSE_EMPLOYEEMENT)));
    }

    @Test
    public void getProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);

        // Get the projectdetail
        restProjectdetailMockMvc.perform(get("/api/projectdetails/{id}", projectdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectdetail.getId().toString()))
            .andExpect(jsonPath("$.siteaddress").value(DEFAULT_SITEADDRESS.toString()))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT.toString()))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK.toString()))
            .andExpect(jsonPath("$.city_town_village").value(DEFAULT_CITY_TOWN_VILLAGE.toString()))
            .andExpect(jsonPath("$.tehsil_subtehsil").value(DEFAULT_TEHSIL_SUBTEHSIL.toString()))
            .andExpect(jsonPath("$.multyvillageinvolved").value(DEFAULT_MULTYVILLAGEINVOLVED.booleanValue()))
            .andExpect(jsonPath("$.villageinvolved").value(DEFAULT_VILLAGEINVOLVED.toString()))
            .andExpect(jsonPath("$.falls_in_aravalli").value(DEFAULT_FALLS_IN_ARAVALLI.booleanValue()))
            .andExpect(jsonPath("$.landprocured").value(DEFAULT_LANDPROCURED.booleanValue()))
            .andExpect(jsonPath("$.allotedbyhsiidc").value(DEFAULT_ALLOTEDBYHSIIDC.booleanValue()))
            .andExpect(jsonPath("$.estate").value(DEFAULT_ESTATE.toString()))
            .andExpect(jsonPath("$.cluster").value(DEFAULT_CLUSTER.toString()))
            .andExpect(jsonPath("$.phase").value(DEFAULT_PHASE.toString()))
            .andExpect(jsonPath("$.sector").value(DEFAULT_SECTOR.toString()))
            .andExpect(jsonPath("$.plotno").value(DEFAULT_PLOTNO.toString()))
            .andExpect(jsonPath("$.hadbastno").value(DEFAULT_HADBASTNO.toString()))
            .andExpect(jsonPath("$.khasradocumentContentType").value(DEFAULT_KHASRADOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.khasradocument").value(Base64Utils.encodeToString(DEFAULT_KHASRADOCUMENT.array())))
            .andExpect(jsonPath("$.lies_under_mc").value(DEFAULT_LIES_UNDER_MC.booleanValue()))
            .andExpect(jsonPath("$.distance_from_mc").value(DEFAULT_DISTANCE_FROM_MC))
            .andExpect(jsonPath("$.located_in_urban").value(DEFAULT_LOCATED_IN_URBAN.booleanValue()))
            .andExpect(jsonPath("$.revenue_shajra_documentContentType").value(DEFAULT_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.revenue_shajra_document").value(Base64Utils.encodeToString(DEFAULT_REVENUE_SHAJRA_DOCUMENT.array())))
            .andExpect(jsonPath("$.jamabandi_documentContentType").value(DEFAULT_JAMABANDI_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.jamabandi_document").value(Base64Utils.encodeToString(DEFAULT_JAMABANDI_DOCUMENT.array())))
            .andExpect(jsonPath("$.nonencumbrancecertificateContentType").value(DEFAULT_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE))
            .andExpect(jsonPath("$.nonencumbrancecertificate").value(Base64Utils.encodeToString(DEFAULT_NONENCUMBRANCECERTIFICATE.array())))
            .andExpect(jsonPath("$.totalproposedprojectarea").value(DEFAULT_TOTALPROPOSEDPROJECTAREA.intValue()))
            .andExpect(jsonPath("$.proposed_build_up_area").value(DEFAULT_PROPOSED_BUILD_UP_AREA.intValue()))
            .andExpect(jsonPath("$.secotr").value(DEFAULT_SECOTR.toString()))
            .andExpect(jsonPath("$.projectpurpose").value(DEFAULT_PROJECTPURPOSE.toString()))
            .andExpect(jsonPath("$.size_of_industry").value(DEFAULT_SIZE_OF_INDUSTRY.toString()))
            .andExpect(jsonPath("$.project_type").value(DEFAULT_PROJECT_TYPE.toString()))
            .andExpect(jsonPath("$.nic_code").value(DEFAULT_NIC_CODE.toString()))
            .andExpect(jsonPath("$.category_of_project").value(DEFAULT_CATEGORY_OF_PROJECT.toString()))
            .andExpect(jsonPath("$.collaboration_with_foreign").value(DEFAULT_COLLABORATION_WITH_FOREIGN.toString()))
            .andExpect(jsonPath("$.detail_project_reportContentType").value(DEFAULT_DETAIL_PROJECT_REPORT_CONTENT_TYPE))
            .andExpect(jsonPath("$.detail_project_report").value(Base64Utils.encodeToString(DEFAULT_DETAIL_PROJECT_REPORT.array())))
            .andExpect(jsonPath("$.existing_regulatory_approval").value(DEFAULT_EXISTING_REGULATORY_APPROVAL.booleanValue()))
            .andExpect(jsonPath("$.approval_application_form").value(DEFAULT_APPROVAL_APPLICATION_FORM.toString()))
            .andExpect(jsonPath("$.approval_documentContentType").value(DEFAULT_APPROVAL_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.approval_document").value(Base64Utils.encodeToString(DEFAULT_APPROVAL_DOCUMENT.array())))
            .andExpect(jsonPath("$.edc_sif_clu_fee_paid_applicable").value(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.edc_sif_clu_fee_documentContentType").value(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.edc_sif_clu_fee_document").value(Base64Utils.encodeToString(DEFAULT_EDC_SIF_CLU_FEE_DOCUMENT.array())))
            .andExpect(jsonPath("$.propose_employeement").value(DEFAULT_PROPOSE_EMPLOYEEMENT));
    }

    @Test
    public void getNonExistingProjectdetail() throws Exception {
        // Get the projectdetail
        restProjectdetailMockMvc.perform(get("/api/projectdetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);
        int databaseSizeBeforeUpdate = projectdetailRepository.findAll().size();

        // Update the projectdetail
        Projectdetail updatedProjectdetail = projectdetailRepository.findOne(projectdetail.getId());
        updatedProjectdetail
                .siteaddress(UPDATED_SITEADDRESS)
                .district(UPDATED_DISTRICT)
                .block(UPDATED_BLOCK)
                .city_town_village(UPDATED_CITY_TOWN_VILLAGE)
                .tehsil_subtehsil(UPDATED_TEHSIL_SUBTEHSIL)
                .multyvillageinvolved(UPDATED_MULTYVILLAGEINVOLVED)
                .villageinvolved(UPDATED_VILLAGEINVOLVED)
                .falls_in_aravalli(UPDATED_FALLS_IN_ARAVALLI)
                .landprocured(UPDATED_LANDPROCURED)
                .allotedbyhsiidc(UPDATED_ALLOTEDBYHSIIDC)
                .estate(UPDATED_ESTATE)
                .cluster(UPDATED_CLUSTER)
                .phase(UPDATED_PHASE)
                .sector(UPDATED_SECTOR)
                .plotno(UPDATED_PLOTNO)
                .hadbastno(UPDATED_HADBASTNO)
                .khasradocument(UPDATED_KHASRADOCUMENT)
                .khasradocumentContentType(UPDATED_KHASRADOCUMENT_CONTENT_TYPE)
                .lies_under_mc(UPDATED_LIES_UNDER_MC)
                .distance_from_mc(UPDATED_DISTANCE_FROM_MC)
                .located_in_urban(UPDATED_LOCATED_IN_URBAN)
                .revenue_shajra_document(UPDATED_REVENUE_SHAJRA_DOCUMENT)
                .revenue_shajra_documentContentType(UPDATED_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE)
                .jamabandi_document(UPDATED_JAMABANDI_DOCUMENT)
                .jamabandi_documentContentType(UPDATED_JAMABANDI_DOCUMENT_CONTENT_TYPE)
                .nonencumbrancecertificate(UPDATED_NONENCUMBRANCECERTIFICATE)
                .nonencumbrancecertificateContentType(UPDATED_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE)
                .totalproposedprojectarea(UPDATED_TOTALPROPOSEDPROJECTAREA)
                .proposed_build_up_area(UPDATED_PROPOSED_BUILD_UP_AREA)
                .secotr(UPDATED_SECOTR)
                .projectpurpose(UPDATED_PROJECTPURPOSE)
                .size_of_industry(UPDATED_SIZE_OF_INDUSTRY)
                .project_type(UPDATED_PROJECT_TYPE)
                .nic_code(UPDATED_NIC_CODE)
                .category_of_project(UPDATED_CATEGORY_OF_PROJECT)
                .collaboration_with_foreign(UPDATED_COLLABORATION_WITH_FOREIGN)
                .detail_project_report(UPDATED_DETAIL_PROJECT_REPORT)
                .detail_project_reportContentType(UPDATED_DETAIL_PROJECT_REPORT_CONTENT_TYPE)
                .existing_regulatory_approval(UPDATED_EXISTING_REGULATORY_APPROVAL)
                .approval_application_form(UPDATED_APPROVAL_APPLICATION_FORM)
                .approval_document(UPDATED_APPROVAL_DOCUMENT)
                .approval_documentContentType(UPDATED_APPROVAL_DOCUMENT_CONTENT_TYPE)
                .edc_sif_clu_fee_paid_applicable(UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE)
                .edc_sif_clu_fee_document(UPDATED_EDC_SIF_CLU_FEE_DOCUMENT)
                .edc_sif_clu_fee_documentContentType(UPDATED_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE)
                .propose_employeement(UPDATED_PROPOSE_EMPLOYEEMENT);
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(updatedProjectdetail);

        restProjectdetailMockMvc.perform(put("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isOk());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeUpdate);
        Projectdetail testProjectdetail = projectdetailList.get(projectdetailList.size() - 1);
        assertThat(testProjectdetail.getSiteaddress()).isEqualTo(UPDATED_SITEADDRESS);
        assertThat(testProjectdetail.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testProjectdetail.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testProjectdetail.getCity_town_village()).isEqualTo(UPDATED_CITY_TOWN_VILLAGE);
        assertThat(testProjectdetail.getTehsil_subtehsil()).isEqualTo(UPDATED_TEHSIL_SUBTEHSIL);
        assertThat(testProjectdetail.isMultyvillageinvolved()).isEqualTo(UPDATED_MULTYVILLAGEINVOLVED);
        assertThat(testProjectdetail.getVillageinvolved()).isEqualTo(UPDATED_VILLAGEINVOLVED);
        assertThat(testProjectdetail.isFalls_in_aravalli()).isEqualTo(UPDATED_FALLS_IN_ARAVALLI);
        assertThat(testProjectdetail.isLandprocured()).isEqualTo(UPDATED_LANDPROCURED);
        assertThat(testProjectdetail.isAllotedbyhsiidc()).isEqualTo(UPDATED_ALLOTEDBYHSIIDC);
        assertThat(testProjectdetail.getEstate()).isEqualTo(UPDATED_ESTATE);
        assertThat(testProjectdetail.getCluster()).isEqualTo(UPDATED_CLUSTER);
        assertThat(testProjectdetail.getPhase()).isEqualTo(UPDATED_PHASE);
        assertThat(testProjectdetail.getSector()).isEqualTo(UPDATED_SECTOR);
        assertThat(testProjectdetail.getPlotno()).isEqualTo(UPDATED_PLOTNO);
        assertThat(testProjectdetail.getHadbastno()).isEqualTo(UPDATED_HADBASTNO);
        assertThat(testProjectdetail.getKhasradocument()).isEqualTo(UPDATED_KHASRADOCUMENT);
        assertThat(testProjectdetail.getKhasradocumentContentType()).isEqualTo(UPDATED_KHASRADOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.isLies_under_mc()).isEqualTo(UPDATED_LIES_UNDER_MC);
        assertThat(testProjectdetail.getDistance_from_mc()).isEqualTo(UPDATED_DISTANCE_FROM_MC);
        assertThat(testProjectdetail.isLocated_in_urban()).isEqualTo(UPDATED_LOCATED_IN_URBAN);
        assertThat(testProjectdetail.getRevenue_shajra_document()).isEqualTo(UPDATED_REVENUE_SHAJRA_DOCUMENT);
        assertThat(testProjectdetail.getRevenue_shajra_documentContentType()).isEqualTo(UPDATED_REVENUE_SHAJRA_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getJamabandi_document()).isEqualTo(UPDATED_JAMABANDI_DOCUMENT);
        assertThat(testProjectdetail.getJamabandi_documentContentType()).isEqualTo(UPDATED_JAMABANDI_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getNonencumbrancecertificate()).isEqualTo(UPDATED_NONENCUMBRANCECERTIFICATE);
        assertThat(testProjectdetail.getNonencumbrancecertificateContentType()).isEqualTo(UPDATED_NONENCUMBRANCECERTIFICATE_CONTENT_TYPE);
        assertThat(testProjectdetail.getTotalproposedprojectarea()).isEqualTo(UPDATED_TOTALPROPOSEDPROJECTAREA);
        assertThat(testProjectdetail.getProposed_build_up_area()).isEqualTo(UPDATED_PROPOSED_BUILD_UP_AREA);
        assertThat(testProjectdetail.getSecotr()).isEqualTo(UPDATED_SECOTR);
        assertThat(testProjectdetail.getProjectpurpose()).isEqualTo(UPDATED_PROJECTPURPOSE);
        assertThat(testProjectdetail.getSize_of_industry()).isEqualTo(UPDATED_SIZE_OF_INDUSTRY);
        assertThat(testProjectdetail.getProject_type()).isEqualTo(UPDATED_PROJECT_TYPE);
        assertThat(testProjectdetail.getNic_code()).isEqualTo(UPDATED_NIC_CODE);
        assertThat(testProjectdetail.getCategory_of_project()).isEqualTo(UPDATED_CATEGORY_OF_PROJECT);
        assertThat(testProjectdetail.getCollaboration_with_foreign()).isEqualTo(UPDATED_COLLABORATION_WITH_FOREIGN);
        assertThat(testProjectdetail.getDetail_project_report()).isEqualTo(UPDATED_DETAIL_PROJECT_REPORT);
        assertThat(testProjectdetail.getDetail_project_reportContentType()).isEqualTo(UPDATED_DETAIL_PROJECT_REPORT_CONTENT_TYPE);
        assertThat(testProjectdetail.isExisting_regulatory_approval()).isEqualTo(UPDATED_EXISTING_REGULATORY_APPROVAL);
        assertThat(testProjectdetail.getApproval_application_form()).isEqualTo(UPDATED_APPROVAL_APPLICATION_FORM);
        assertThat(testProjectdetail.getApproval_document()).isEqualTo(UPDATED_APPROVAL_DOCUMENT);
        assertThat(testProjectdetail.getApproval_documentContentType()).isEqualTo(UPDATED_APPROVAL_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.isEdc_sif_clu_fee_paid_applicable()).isEqualTo(UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_document()).isEqualTo(UPDATED_EDC_SIF_CLU_FEE_DOCUMENT);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_documentContentType()).isEqualTo(UPDATED_EDC_SIF_CLU_FEE_DOCUMENT_CONTENT_TYPE);
        assertThat(testProjectdetail.getPropose_employeement()).isEqualTo(UPDATED_PROPOSE_EMPLOYEEMENT);
    }

    @Test
    public void updateNonExistingProjectdetail() throws Exception {
        int databaseSizeBeforeUpdate = projectdetailRepository.findAll().size();

        // Create the Projectdetail
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectdetailMockMvc.perform(put("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);
        int databaseSizeBeforeDelete = projectdetailRepository.findAll().size();

        // Get the projectdetail
        restProjectdetailMockMvc.perform(delete("/api/projectdetails/{id}", projectdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectdetail.class);
    }
}
