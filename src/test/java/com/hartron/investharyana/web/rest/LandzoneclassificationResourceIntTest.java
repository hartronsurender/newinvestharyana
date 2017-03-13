package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Landzoneclassification;
import com.hartron.investharyana.repository.LandzoneclassificationRepository;
import com.hartron.investharyana.service.LandzoneclassificationService;
import com.hartron.investharyana.service.dto.LandzoneclassificationDTO;
import com.hartron.investharyana.service.mapper.LandzoneclassificationMapper;
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
 * Test class for the LandzoneclassificationResource REST controller.
 *
 * @see LandzoneclassificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class LandzoneclassificationResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_LAND_ZONE_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_LAND_ZONE_CLASSIFICATION = "BBBBBBBBBB";

    @Autowired
    private LandzoneclassificationRepository landzoneclassificationRepository;

    @Autowired
    private LandzoneclassificationMapper landzoneclassificationMapper;

    @Autowired
    private LandzoneclassificationService landzoneclassificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restLandzoneclassificationMockMvc;

    private Landzoneclassification landzoneclassification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LandzoneclassificationResource landzoneclassificationResource = new LandzoneclassificationResource(landzoneclassificationService);
        this.restLandzoneclassificationMockMvc = MockMvcBuilders.standaloneSetup(landzoneclassificationResource)
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
    public static Landzoneclassification createEntity() {
        Landzoneclassification landzoneclassification = new Landzoneclassification()
                .land_zone_classification(DEFAULT_LAND_ZONE_CLASSIFICATION);
        return landzoneclassification;
    }

    @Before
    public void initTest() {
        landzoneclassificationRepository.deleteAll();
        landzoneclassification = createEntity();
    }

    @Test
    public void createLandzoneclassification() throws Exception {
        int databaseSizeBeforeCreate = landzoneclassificationRepository.findAll().size();

        // Create the Landzoneclassification
        LandzoneclassificationDTO landzoneclassificationDTO = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(landzoneclassification);

        restLandzoneclassificationMockMvc.perform(post("/api/landzoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landzoneclassificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Landzoneclassification in the database
        List<Landzoneclassification> landzoneclassificationList = landzoneclassificationRepository.findAll();
        assertThat(landzoneclassificationList).hasSize(databaseSizeBeforeCreate + 1);
        Landzoneclassification testLandzoneclassification = landzoneclassificationList.get(landzoneclassificationList.size() - 1);
        assertThat(testLandzoneclassification.getLand_zone_classification()).isEqualTo(DEFAULT_LAND_ZONE_CLASSIFICATION);
    }

    @Test
    public void createLandzoneclassificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = landzoneclassificationRepository.findAll().size();

        // Create the Landzoneclassification with an existing ID
        Landzoneclassification existingLandzoneclassification = new Landzoneclassification();
        existingLandzoneclassification.setId(UUID.randomUUID());
        LandzoneclassificationDTO existingLandzoneclassificationDTO = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(existingLandzoneclassification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLandzoneclassificationMockMvc.perform(post("/api/landzoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingLandzoneclassificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Landzoneclassification> landzoneclassificationList = landzoneclassificationRepository.findAll();
        assertThat(landzoneclassificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllLandzoneclassifications() throws Exception {
        // Initialize the database
        landzoneclassificationRepository.save(landzoneclassification);

        // Get all the landzoneclassificationList
        restLandzoneclassificationMockMvc.perform(get("/api/landzoneclassifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landzoneclassification.getId().toString())))
            .andExpect(jsonPath("$.[*].land_zone_classification").value(hasItem(DEFAULT_LAND_ZONE_CLASSIFICATION.toString())));
    }

    @Test
    public void getLandzoneclassification() throws Exception {
        // Initialize the database
        landzoneclassificationRepository.save(landzoneclassification);

        // Get the landzoneclassification
        restLandzoneclassificationMockMvc.perform(get("/api/landzoneclassifications/{id}", landzoneclassification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(landzoneclassification.getId().toString()))
            .andExpect(jsonPath("$.land_zone_classification").value(DEFAULT_LAND_ZONE_CLASSIFICATION.toString()));
    }

    @Test
    public void getNonExistingLandzoneclassification() throws Exception {
        // Get the landzoneclassification
        restLandzoneclassificationMockMvc.perform(get("/api/landzoneclassifications/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLandzoneclassification() throws Exception {
        // Initialize the database
        landzoneclassificationRepository.save(landzoneclassification);
        int databaseSizeBeforeUpdate = landzoneclassificationRepository.findAll().size();

        // Update the landzoneclassification
        Landzoneclassification updatedLandzoneclassification = landzoneclassificationRepository.findOne(landzoneclassification.getId());
        updatedLandzoneclassification
                .land_zone_classification(UPDATED_LAND_ZONE_CLASSIFICATION);
        LandzoneclassificationDTO landzoneclassificationDTO = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(updatedLandzoneclassification);

        restLandzoneclassificationMockMvc.perform(put("/api/landzoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landzoneclassificationDTO)))
            .andExpect(status().isOk());

        // Validate the Landzoneclassification in the database
        List<Landzoneclassification> landzoneclassificationList = landzoneclassificationRepository.findAll();
        assertThat(landzoneclassificationList).hasSize(databaseSizeBeforeUpdate);
        Landzoneclassification testLandzoneclassification = landzoneclassificationList.get(landzoneclassificationList.size() - 1);
        assertThat(testLandzoneclassification.getLand_zone_classification()).isEqualTo(UPDATED_LAND_ZONE_CLASSIFICATION);
    }

    @Test
    public void updateNonExistingLandzoneclassification() throws Exception {
        int databaseSizeBeforeUpdate = landzoneclassificationRepository.findAll().size();

        // Create the Landzoneclassification
        LandzoneclassificationDTO landzoneclassificationDTO = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(landzoneclassification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLandzoneclassificationMockMvc.perform(put("/api/landzoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landzoneclassificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Landzoneclassification in the database
        List<Landzoneclassification> landzoneclassificationList = landzoneclassificationRepository.findAll();
        assertThat(landzoneclassificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteLandzoneclassification() throws Exception {
        // Initialize the database
        landzoneclassificationRepository.save(landzoneclassification);
        int databaseSizeBeforeDelete = landzoneclassificationRepository.findAll().size();

        // Get the landzoneclassification
        restLandzoneclassificationMockMvc.perform(delete("/api/landzoneclassifications/{id}", landzoneclassification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Landzoneclassification> landzoneclassificationList = landzoneclassificationRepository.findAll();
        assertThat(landzoneclassificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Landzoneclassification.class);
    }
}
