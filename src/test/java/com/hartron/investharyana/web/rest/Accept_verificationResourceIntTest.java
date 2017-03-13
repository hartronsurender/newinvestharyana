package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Accept_verification;
import com.hartron.investharyana.repository.Accept_verificationRepository;
import com.hartron.investharyana.service.Accept_verificationService;
import com.hartron.investharyana.service.dto.Accept_verificationDTO;
import com.hartron.investharyana.service.mapper.Accept_verificationMapper;
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
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Accept_verificationResource REST controller.
 *
 * @see Accept_verificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Accept_verificationResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_INVESTORID = UUID.randomUUID();
    private static final UUID UPDATED_INVESTORID = UUID.randomUUID();

    private static final Boolean DEFAULT_ACCEPTCONDITION = false;
    private static final Boolean UPDATED_ACCEPTCONDITION = true;

    private static final ZonedDateTime DEFAULT_APPLICATIONDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_APPLICATIONDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ByteBuffer DEFAULT_SIGNATURE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_SIGNATURE_DOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_SIGNATURE_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SIGNATURE_DOCUMENT_CONTENT_TYPE = "image/png";

    @Autowired
    private Accept_verificationRepository accept_verificationRepository;

    @Autowired
    private Accept_verificationMapper accept_verificationMapper;

    @Autowired
    private Accept_verificationService accept_verificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAccept_verificationMockMvc;

    private Accept_verification accept_verification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Accept_verificationResource accept_verificationResource = new Accept_verificationResource(accept_verificationService);
        this.restAccept_verificationMockMvc = MockMvcBuilders.standaloneSetup(accept_verificationResource)
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
    public static Accept_verification createEntity() {
        Accept_verification accept_verification = new Accept_verification()
                .projectid(DEFAULT_PROJECTID)
                .investorid(DEFAULT_INVESTORID)
                .acceptcondition(DEFAULT_ACCEPTCONDITION)
                .applicationdate(DEFAULT_APPLICATIONDATE)
                .signature_document(DEFAULT_SIGNATURE_DOCUMENT)
                .signature_documentContentType(DEFAULT_SIGNATURE_DOCUMENT_CONTENT_TYPE);
        return accept_verification;
    }

    @Before
    public void initTest() {
        accept_verificationRepository.deleteAll();
        accept_verification = createEntity();
    }

    @Test
    public void createAccept_verification() throws Exception {
        int databaseSizeBeforeCreate = accept_verificationRepository.findAll().size();

        // Create the Accept_verification
        Accept_verificationDTO accept_verificationDTO = accept_verificationMapper.accept_verificationToAccept_verificationDTO(accept_verification);

        restAccept_verificationMockMvc.perform(post("/api/accept-verifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accept_verificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Accept_verification in the database
        List<Accept_verification> accept_verificationList = accept_verificationRepository.findAll();
        assertThat(accept_verificationList).hasSize(databaseSizeBeforeCreate + 1);
        Accept_verification testAccept_verification = accept_verificationList.get(accept_verificationList.size() - 1);
        assertThat(testAccept_verification.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testAccept_verification.getInvestorid()).isEqualTo(DEFAULT_INVESTORID);
        assertThat(testAccept_verification.isAcceptcondition()).isEqualTo(DEFAULT_ACCEPTCONDITION);
        assertThat(testAccept_verification.getApplicationdate()).isEqualTo(DEFAULT_APPLICATIONDATE);
        assertThat(testAccept_verification.getSignature_document()).isEqualTo(DEFAULT_SIGNATURE_DOCUMENT);
        assertThat(testAccept_verification.getSignature_documentContentType()).isEqualTo(DEFAULT_SIGNATURE_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void createAccept_verificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accept_verificationRepository.findAll().size();

        // Create the Accept_verification with an existing ID
        Accept_verification existingAccept_verification = new Accept_verification();
        existingAccept_verification.setId(UUID.randomUUID());
        Accept_verificationDTO existingAccept_verificationDTO = accept_verificationMapper.accept_verificationToAccept_verificationDTO(existingAccept_verification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccept_verificationMockMvc.perform(post("/api/accept-verifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAccept_verificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Accept_verification> accept_verificationList = accept_verificationRepository.findAll();
        assertThat(accept_verificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAccept_verifications() throws Exception {
        // Initialize the database
        accept_verificationRepository.save(accept_verification);

        // Get all the accept_verificationList
        restAccept_verificationMockMvc.perform(get("/api/accept-verifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accept_verification.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].investorid").value(hasItem(DEFAULT_INVESTORID.toString())))
            .andExpect(jsonPath("$.[*].acceptcondition").value(hasItem(DEFAULT_ACCEPTCONDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].applicationdate").value(hasItem(sameInstant(DEFAULT_APPLICATIONDATE))))
            .andExpect(jsonPath("$.[*].signature_documentContentType").value(hasItem(DEFAULT_SIGNATURE_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].signature_document").value(hasItem(Base64Utils.encodeToString(DEFAULT_SIGNATURE_DOCUMENT.array()))));
    }

    @Test
    public void getAccept_verification() throws Exception {
        // Initialize the database
        accept_verificationRepository.save(accept_verification);

        // Get the accept_verification
        restAccept_verificationMockMvc.perform(get("/api/accept-verifications/{id}", accept_verification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accept_verification.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.investorid").value(DEFAULT_INVESTORID.toString()))
            .andExpect(jsonPath("$.acceptcondition").value(DEFAULT_ACCEPTCONDITION.booleanValue()))
            .andExpect(jsonPath("$.applicationdate").value(sameInstant(DEFAULT_APPLICATIONDATE)))
            .andExpect(jsonPath("$.signature_documentContentType").value(DEFAULT_SIGNATURE_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.signature_document").value(Base64Utils.encodeToString(DEFAULT_SIGNATURE_DOCUMENT.array())));
    }

    @Test
    public void getNonExistingAccept_verification() throws Exception {
        // Get the accept_verification
        restAccept_verificationMockMvc.perform(get("/api/accept-verifications/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccept_verification() throws Exception {
        // Initialize the database
        accept_verificationRepository.save(accept_verification);
        int databaseSizeBeforeUpdate = accept_verificationRepository.findAll().size();

        // Update the accept_verification
        Accept_verification updatedAccept_verification = accept_verificationRepository.findOne(accept_verification.getId());
        updatedAccept_verification
                .projectid(UPDATED_PROJECTID)
                .investorid(UPDATED_INVESTORID)
                .acceptcondition(UPDATED_ACCEPTCONDITION)
                .applicationdate(UPDATED_APPLICATIONDATE)
                .signature_document(UPDATED_SIGNATURE_DOCUMENT)
                .signature_documentContentType(UPDATED_SIGNATURE_DOCUMENT_CONTENT_TYPE);
        Accept_verificationDTO accept_verificationDTO = accept_verificationMapper.accept_verificationToAccept_verificationDTO(updatedAccept_verification);

        restAccept_verificationMockMvc.perform(put("/api/accept-verifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accept_verificationDTO)))
            .andExpect(status().isOk());

        // Validate the Accept_verification in the database
        List<Accept_verification> accept_verificationList = accept_verificationRepository.findAll();
        assertThat(accept_verificationList).hasSize(databaseSizeBeforeUpdate);
        Accept_verification testAccept_verification = accept_verificationList.get(accept_verificationList.size() - 1);
        assertThat(testAccept_verification.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testAccept_verification.getInvestorid()).isEqualTo(UPDATED_INVESTORID);
        assertThat(testAccept_verification.isAcceptcondition()).isEqualTo(UPDATED_ACCEPTCONDITION);
        assertThat(testAccept_verification.getApplicationdate()).isEqualTo(UPDATED_APPLICATIONDATE);
        assertThat(testAccept_verification.getSignature_document()).isEqualTo(UPDATED_SIGNATURE_DOCUMENT);
        assertThat(testAccept_verification.getSignature_documentContentType()).isEqualTo(UPDATED_SIGNATURE_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingAccept_verification() throws Exception {
        int databaseSizeBeforeUpdate = accept_verificationRepository.findAll().size();

        // Create the Accept_verification
        Accept_verificationDTO accept_verificationDTO = accept_verificationMapper.accept_verificationToAccept_verificationDTO(accept_verification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAccept_verificationMockMvc.perform(put("/api/accept-verifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accept_verificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Accept_verification in the database
        List<Accept_verification> accept_verificationList = accept_verificationRepository.findAll();
        assertThat(accept_verificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAccept_verification() throws Exception {
        // Initialize the database
        accept_verificationRepository.save(accept_verification);
        int databaseSizeBeforeDelete = accept_verificationRepository.findAll().size();

        // Get the accept_verification
        restAccept_verificationMockMvc.perform(delete("/api/accept-verifications/{id}", accept_verification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Accept_verification> accept_verificationList = accept_verificationRepository.findAll();
        assertThat(accept_verificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Accept_verification.class);
    }
}
