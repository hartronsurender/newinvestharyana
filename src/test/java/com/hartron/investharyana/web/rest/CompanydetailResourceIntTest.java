package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Companydetail;
import com.hartron.investharyana.repository.CompanydetailRepository;
import com.hartron.investharyana.service.CompanydetailService;
import com.hartron.investharyana.service.dto.CompanydetailDTO;
import com.hartron.investharyana.service.mapper.CompanydetailMapper;
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
 * Test class for the CompanydetailResource REST controller.
 *
 * @see CompanydetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class CompanydetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_INVESTORID = UUID.randomUUID();
    private static final UUID UPDATED_INVESTORID = UUID.randomUUID();

    private static final String DEFAULT_PROMOTER_MD_DIRECTOR = "AAAAAAAAAA";
    private static final String UPDATED_PROMOTER_MD_DIRECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSENTITY = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITY = "BBBBBBBBBB";

    private static final UUID DEFAULT_BUSINESSTYPE = UUID.randomUUID();
    private static final UUID UPDATED_BUSINESSTYPE = UUID.randomUUID();

    private static final Integer DEFAULT_NUMBER_OF_DIRECTOR_MDS_CEO = 1;
    private static final Integer UPDATED_NUMBER_OF_DIRECTOR_MDS_CEO = 2;

    private static final ByteBuffer DEFAULT_LIST_DIRECTOR_MD_CEO = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_LIST_DIRECTOR_MD_CEO = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PANNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PANNUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_PANCARD = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_PANCARD = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_PANCARD_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PANCARD_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_AADHARNUMBER = 1D;
    private static final Double UPDATED_AADHARNUMBER = 2D;

    private static final ByteBuffer DEFAULT_AADHARCARD = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_AADHARCARD = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_AADHARCARD_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AADHARCARD_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_NRI = false;
    private static final Boolean UPDATED_NRI = true;

    private static final String DEFAULT_TIN_VAT_NO = "AAAAAAAAAA";
    private static final String UPDATED_TIN_VAT_NO = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_TIN_VAT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_TIN_VAT_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CSTNO = "AAAAAAAAAA";
    private static final String UPDATED_CSTNO = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_CSTDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_CSTDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_CSTDOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CSTDOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE = "image/png";

    private static final ByteBuffer DEFAULT_REGISTRATION_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_REGISTRATION_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE = "image/png";

    @Autowired
    private CompanydetailRepository companydetailRepository;

    @Autowired
    private CompanydetailMapper companydetailMapper;

    @Autowired
    private CompanydetailService companydetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCompanydetailMockMvc;

    private Companydetail companydetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompanydetailResource companydetailResource = new CompanydetailResource(companydetailService);
        this.restCompanydetailMockMvc = MockMvcBuilders.standaloneSetup(companydetailResource)
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
    public static Companydetail createEntity() {
        Companydetail companydetail = new Companydetail()
                .investorid(DEFAULT_INVESTORID)
                .promoter_md_director(DEFAULT_PROMOTER_MD_DIRECTOR)
                .designation(DEFAULT_DESIGNATION)
                .businessentity(DEFAULT_BUSINESSENTITY)
                .businesstype(DEFAULT_BUSINESSTYPE)
                .number_of_director_mds_ceo(DEFAULT_NUMBER_OF_DIRECTOR_MDS_CEO)
                .list_director_md_ceo(DEFAULT_LIST_DIRECTOR_MD_CEO)
                .list_director_md_ceoContentType(DEFAULT_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE)
                .pannumber(DEFAULT_PANNUMBER)
                .pancard(DEFAULT_PANCARD)
                .pancardContentType(DEFAULT_PANCARD_CONTENT_TYPE)
                .aadharnumber(DEFAULT_AADHARNUMBER)
                .aadharcard(DEFAULT_AADHARCARD)
                .aadharcardContentType(DEFAULT_AADHARCARD_CONTENT_TYPE)
                .nri(DEFAULT_NRI)
                .tin_vat_no(DEFAULT_TIN_VAT_NO)
                .tin_vat_document(DEFAULT_TIN_VAT_DOCUMENT)
                .tin_vat_documentContentType(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE)
                .cstno(DEFAULT_CSTNO)
                .cstdocument(DEFAULT_CSTDOCUMENT)
                .cstdocumentContentType(DEFAULT_CSTDOCUMENT_CONTENT_TYPE)
                .moa_partnershipdeed_document(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT)
                .moa_partnershipdeed_documentContentType(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE)
                .registration_document(DEFAULT_REGISTRATION_DOCUMENT)
                .registration_documentContentType(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE);
        return companydetail;
    }

    @Before
    public void initTest() {
        companydetailRepository.deleteAll();
        companydetail = createEntity();
    }

    @Test
    public void createCompanydetail() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate + 1);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getInvestorid()).isEqualTo(DEFAULT_INVESTORID);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(DEFAULT_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(DEFAULT_BUSINESSENTITY);
        assertThat(testCompanydetail.getBusinesstype()).isEqualTo(DEFAULT_BUSINESSTYPE);
        assertThat(testCompanydetail.getNumber_of_director_mds_ceo()).isEqualTo(DEFAULT_NUMBER_OF_DIRECTOR_MDS_CEO);
        assertThat(testCompanydetail.getList_director_md_ceo()).isEqualTo(DEFAULT_LIST_DIRECTOR_MD_CEO);
        assertThat(testCompanydetail.getList_director_md_ceoContentType()).isEqualTo(DEFAULT_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE);
        assertThat(testCompanydetail.getPannumber()).isEqualTo(DEFAULT_PANNUMBER);
        assertThat(testCompanydetail.getPancard()).isEqualTo(DEFAULT_PANCARD);
        assertThat(testCompanydetail.getPancardContentType()).isEqualTo(DEFAULT_PANCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.getAadharnumber()).isEqualTo(DEFAULT_AADHARNUMBER);
        assertThat(testCompanydetail.getAadharcard()).isEqualTo(DEFAULT_AADHARCARD);
        assertThat(testCompanydetail.getAadharcardContentType()).isEqualTo(DEFAULT_AADHARCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.isNri()).isEqualTo(DEFAULT_NRI);
        assertThat(testCompanydetail.getTin_vat_no()).isEqualTo(DEFAULT_TIN_VAT_NO);
        assertThat(testCompanydetail.getTin_vat_document()).isEqualTo(DEFAULT_TIN_VAT_DOCUMENT);
        assertThat(testCompanydetail.getTin_vat_documentContentType()).isEqualTo(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getCstno()).isEqualTo(DEFAULT_CSTNO);
        assertThat(testCompanydetail.getCstdocument()).isEqualTo(DEFAULT_CSTDOCUMENT);
        assertThat(testCompanydetail.getCstdocumentContentType()).isEqualTo(DEFAULT_CSTDOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getMoa_partnershipdeed_document()).isEqualTo(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT);
        assertThat(testCompanydetail.getMoa_partnershipdeed_documentContentType()).isEqualTo(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getRegistration_document()).isEqualTo(DEFAULT_REGISTRATION_DOCUMENT);
        assertThat(testCompanydetail.getRegistration_documentContentType()).isEqualTo(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void createCompanydetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail with an existing ID
        Companydetail existingCompanydetail = new Companydetail();
        existingCompanydetail.setId(UUID.randomUUID());
        CompanydetailDTO existingCompanydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(existingCompanydetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompanydetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCompanydetails() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get all the companydetailList
        restCompanydetailMockMvc.perform(get("/api/companydetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companydetail.getId().toString())))
            .andExpect(jsonPath("$.[*].investorid").value(hasItem(DEFAULT_INVESTORID.toString())))
            .andExpect(jsonPath("$.[*].promoter_md_director").value(hasItem(DEFAULT_PROMOTER_MD_DIRECTOR.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].businessentity").value(hasItem(DEFAULT_BUSINESSENTITY.toString())))
            .andExpect(jsonPath("$.[*].businesstype").value(hasItem(DEFAULT_BUSINESSTYPE.toString())))
            .andExpect(jsonPath("$.[*].number_of_director_mds_ceo").value(hasItem(DEFAULT_NUMBER_OF_DIRECTOR_MDS_CEO)))
            .andExpect(jsonPath("$.[*].list_director_md_ceoContentType").value(hasItem(DEFAULT_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].list_director_md_ceo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LIST_DIRECTOR_MD_CEO.array()))))
            .andExpect(jsonPath("$.[*].pannumber").value(hasItem(DEFAULT_PANNUMBER.toString())))
            .andExpect(jsonPath("$.[*].pancardContentType").value(hasItem(DEFAULT_PANCARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pancard").value(hasItem(Base64Utils.encodeToString(DEFAULT_PANCARD.array()))))
            .andExpect(jsonPath("$.[*].aadharnumber").value(hasItem(DEFAULT_AADHARNUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].aadharcardContentType").value(hasItem(DEFAULT_AADHARCARD_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].aadharcard").value(hasItem(Base64Utils.encodeToString(DEFAULT_AADHARCARD.array()))))
            .andExpect(jsonPath("$.[*].nri").value(hasItem(DEFAULT_NRI.booleanValue())))
            .andExpect(jsonPath("$.[*].tin_vat_no").value(hasItem(DEFAULT_TIN_VAT_NO.toString())))
            .andExpect(jsonPath("$.[*].tin_vat_documentContentType").value(hasItem(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].tin_vat_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_TIN_VAT_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].cstno").value(hasItem(DEFAULT_CSTNO.toString())))
            .andExpect(jsonPath("$.[*].cstdocumentContentType").value(hasItem(DEFAULT_CSTDOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].cstdocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_CSTDOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].moa_partnershipdeed_documentContentType").value(hasItem(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].moa_partnershipdeed_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].registration_documentContentType").value(hasItem(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].registration_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_REGISTRATION_DOCUMENT.array()))));
    }

    @Test
    public void getCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", companydetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companydetail.getId().toString()))
            .andExpect(jsonPath("$.investorid").value(DEFAULT_INVESTORID.toString()))
            .andExpect(jsonPath("$.promoter_md_director").value(DEFAULT_PROMOTER_MD_DIRECTOR.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.businessentity").value(DEFAULT_BUSINESSENTITY.toString()))
            .andExpect(jsonPath("$.businesstype").value(DEFAULT_BUSINESSTYPE.toString()))
            .andExpect(jsonPath("$.number_of_director_mds_ceo").value(DEFAULT_NUMBER_OF_DIRECTOR_MDS_CEO))
            .andExpect(jsonPath("$.list_director_md_ceoContentType").value(DEFAULT_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.list_director_md_ceo").value(Base64Utils.encodeToString(DEFAULT_LIST_DIRECTOR_MD_CEO.array())))
            .andExpect(jsonPath("$.pannumber").value(DEFAULT_PANNUMBER.toString()))
            .andExpect(jsonPath("$.pancardContentType").value(DEFAULT_PANCARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.pancard").value(Base64Utils.encodeToString(DEFAULT_PANCARD.array())))
            .andExpect(jsonPath("$.aadharnumber").value(DEFAULT_AADHARNUMBER.doubleValue()))
            .andExpect(jsonPath("$.aadharcardContentType").value(DEFAULT_AADHARCARD_CONTENT_TYPE))
            .andExpect(jsonPath("$.aadharcard").value(Base64Utils.encodeToString(DEFAULT_AADHARCARD.array())))
            .andExpect(jsonPath("$.nri").value(DEFAULT_NRI.booleanValue()))
            .andExpect(jsonPath("$.tin_vat_no").value(DEFAULT_TIN_VAT_NO.toString()))
            .andExpect(jsonPath("$.tin_vat_documentContentType").value(DEFAULT_TIN_VAT_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.tin_vat_document").value(Base64Utils.encodeToString(DEFAULT_TIN_VAT_DOCUMENT.array())))
            .andExpect(jsonPath("$.cstno").value(DEFAULT_CSTNO.toString()))
            .andExpect(jsonPath("$.cstdocumentContentType").value(DEFAULT_CSTDOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.cstdocument").value(Base64Utils.encodeToString(DEFAULT_CSTDOCUMENT.array())))
            .andExpect(jsonPath("$.moa_partnershipdeed_documentContentType").value(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.moa_partnershipdeed_document").value(Base64Utils.encodeToString(DEFAULT_MOA_PARTNERSHIPDEED_DOCUMENT.array())))
            .andExpect(jsonPath("$.registration_documentContentType").value(DEFAULT_REGISTRATION_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.registration_document").value(Base64Utils.encodeToString(DEFAULT_REGISTRATION_DOCUMENT.array())));
    }

    @Test
    public void getNonExistingCompanydetail() throws Exception {
        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Update the companydetail
        Companydetail updatedCompanydetail = companydetailRepository.findOne(companydetail.getId());
        updatedCompanydetail
                .investorid(UPDATED_INVESTORID)
                .promoter_md_director(UPDATED_PROMOTER_MD_DIRECTOR)
                .designation(UPDATED_DESIGNATION)
                .businessentity(UPDATED_BUSINESSENTITY)
                .businesstype(UPDATED_BUSINESSTYPE)
                .number_of_director_mds_ceo(UPDATED_NUMBER_OF_DIRECTOR_MDS_CEO)
                .list_director_md_ceo(UPDATED_LIST_DIRECTOR_MD_CEO)
                .list_director_md_ceoContentType(UPDATED_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE)
                .pannumber(UPDATED_PANNUMBER)
                .pancard(UPDATED_PANCARD)
                .pancardContentType(UPDATED_PANCARD_CONTENT_TYPE)
                .aadharnumber(UPDATED_AADHARNUMBER)
                .aadharcard(UPDATED_AADHARCARD)
                .aadharcardContentType(UPDATED_AADHARCARD_CONTENT_TYPE)
                .nri(UPDATED_NRI)
                .tin_vat_no(UPDATED_TIN_VAT_NO)
                .tin_vat_document(UPDATED_TIN_VAT_DOCUMENT)
                .tin_vat_documentContentType(UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE)
                .cstno(UPDATED_CSTNO)
                .cstdocument(UPDATED_CSTDOCUMENT)
                .cstdocumentContentType(UPDATED_CSTDOCUMENT_CONTENT_TYPE)
                .moa_partnershipdeed_document(UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT)
                .moa_partnershipdeed_documentContentType(UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE)
                .registration_document(UPDATED_REGISTRATION_DOCUMENT)
                .registration_documentContentType(UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE);
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(updatedCompanydetail);

        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isOk());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getInvestorid()).isEqualTo(UPDATED_INVESTORID);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(UPDATED_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(UPDATED_BUSINESSENTITY);
        assertThat(testCompanydetail.getBusinesstype()).isEqualTo(UPDATED_BUSINESSTYPE);
        assertThat(testCompanydetail.getNumber_of_director_mds_ceo()).isEqualTo(UPDATED_NUMBER_OF_DIRECTOR_MDS_CEO);
        assertThat(testCompanydetail.getList_director_md_ceo()).isEqualTo(UPDATED_LIST_DIRECTOR_MD_CEO);
        assertThat(testCompanydetail.getList_director_md_ceoContentType()).isEqualTo(UPDATED_LIST_DIRECTOR_MD_CEO_CONTENT_TYPE);
        assertThat(testCompanydetail.getPannumber()).isEqualTo(UPDATED_PANNUMBER);
        assertThat(testCompanydetail.getPancard()).isEqualTo(UPDATED_PANCARD);
        assertThat(testCompanydetail.getPancardContentType()).isEqualTo(UPDATED_PANCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.getAadharnumber()).isEqualTo(UPDATED_AADHARNUMBER);
        assertThat(testCompanydetail.getAadharcard()).isEqualTo(UPDATED_AADHARCARD);
        assertThat(testCompanydetail.getAadharcardContentType()).isEqualTo(UPDATED_AADHARCARD_CONTENT_TYPE);
        assertThat(testCompanydetail.isNri()).isEqualTo(UPDATED_NRI);
        assertThat(testCompanydetail.getTin_vat_no()).isEqualTo(UPDATED_TIN_VAT_NO);
        assertThat(testCompanydetail.getTin_vat_document()).isEqualTo(UPDATED_TIN_VAT_DOCUMENT);
        assertThat(testCompanydetail.getTin_vat_documentContentType()).isEqualTo(UPDATED_TIN_VAT_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getCstno()).isEqualTo(UPDATED_CSTNO);
        assertThat(testCompanydetail.getCstdocument()).isEqualTo(UPDATED_CSTDOCUMENT);
        assertThat(testCompanydetail.getCstdocumentContentType()).isEqualTo(UPDATED_CSTDOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getMoa_partnershipdeed_document()).isEqualTo(UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT);
        assertThat(testCompanydetail.getMoa_partnershipdeed_documentContentType()).isEqualTo(UPDATED_MOA_PARTNERSHIPDEED_DOCUMENT_CONTENT_TYPE);
        assertThat(testCompanydetail.getRegistration_document()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT);
        assertThat(testCompanydetail.getRegistration_documentContentType()).isEqualTo(UPDATED_REGISTRATION_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingCompanydetail() throws Exception {
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeDelete = companydetailRepository.findAll().size();

        // Get the companydetail
        restCompanydetailMockMvc.perform(delete("/api/companydetails/{id}", companydetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Companydetail.class);
    }
}
