package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emission_air_pollution;
import com.hartron.investharyana.repository.Emission_air_pollutionRepository;
import com.hartron.investharyana.service.Emission_air_pollutionService;
import com.hartron.investharyana.service.dto.Emission_air_pollutionDTO;
import com.hartron.investharyana.service.mapper.Emission_air_pollutionMapper;
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
 * Test class for the Emission_air_pollutionResource REST controller.
 *
 * @see Emission_air_pollutionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Emission_air_pollutionResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_POLLUTION_CONTROLL_DEVICE = "AAAAAAAAAA";
    private static final String UPDATED_POLLUTION_CONTROLL_DEVICE = "BBBBBBBBBB";

    @Autowired
    private Emission_air_pollutionRepository emission_air_pollutionRepository;

    @Autowired
    private Emission_air_pollutionMapper emission_air_pollutionMapper;

    @Autowired
    private Emission_air_pollutionService emission_air_pollutionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmission_air_pollutionMockMvc;

    private Emission_air_pollution emission_air_pollution;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Emission_air_pollutionResource emission_air_pollutionResource = new Emission_air_pollutionResource(emission_air_pollutionService);
        this.restEmission_air_pollutionMockMvc = MockMvcBuilders.standaloneSetup(emission_air_pollutionResource)
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
    public static Emission_air_pollution createEntity() {
        Emission_air_pollution emission_air_pollution = new Emission_air_pollution()
                .pollution_controll_device(DEFAULT_POLLUTION_CONTROLL_DEVICE);
        return emission_air_pollution;
    }

    @Before
    public void initTest() {
        emission_air_pollutionRepository.deleteAll();
        emission_air_pollution = createEntity();
    }

    @Test
    public void createEmission_air_pollution() throws Exception {
        int databaseSizeBeforeCreate = emission_air_pollutionRepository.findAll().size();

        // Create the Emission_air_pollution
        Emission_air_pollutionDTO emission_air_pollutionDTO = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(emission_air_pollution);

        restEmission_air_pollutionMockMvc.perform(post("/api/emission-air-pollutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_air_pollutionDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_air_pollution in the database
        List<Emission_air_pollution> emission_air_pollutionList = emission_air_pollutionRepository.findAll();
        assertThat(emission_air_pollutionList).hasSize(databaseSizeBeforeCreate + 1);
        Emission_air_pollution testEmission_air_pollution = emission_air_pollutionList.get(emission_air_pollutionList.size() - 1);
        assertThat(testEmission_air_pollution.getPollution_controll_device()).isEqualTo(DEFAULT_POLLUTION_CONTROLL_DEVICE);
    }

    @Test
    public void createEmission_air_pollutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emission_air_pollutionRepository.findAll().size();

        // Create the Emission_air_pollution with an existing ID
        Emission_air_pollution existingEmission_air_pollution = new Emission_air_pollution();
        existingEmission_air_pollution.setId(UUID.randomUUID());
        Emission_air_pollutionDTO existingEmission_air_pollutionDTO = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(existingEmission_air_pollution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmission_air_pollutionMockMvc.perform(post("/api/emission-air-pollutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmission_air_pollutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emission_air_pollution> emission_air_pollutionList = emission_air_pollutionRepository.findAll();
        assertThat(emission_air_pollutionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmission_air_pollutions() throws Exception {
        // Initialize the database
        emission_air_pollutionRepository.save(emission_air_pollution);

        // Get all the emission_air_pollutionList
        restEmission_air_pollutionMockMvc.perform(get("/api/emission-air-pollutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emission_air_pollution.getId().toString())))
            .andExpect(jsonPath("$.[*].pollution_controll_device").value(hasItem(DEFAULT_POLLUTION_CONTROLL_DEVICE.toString())));
    }

    @Test
    public void getEmission_air_pollution() throws Exception {
        // Initialize the database
        emission_air_pollutionRepository.save(emission_air_pollution);

        // Get the emission_air_pollution
        restEmission_air_pollutionMockMvc.perform(get("/api/emission-air-pollutions/{id}", emission_air_pollution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emission_air_pollution.getId().toString()))
            .andExpect(jsonPath("$.pollution_controll_device").value(DEFAULT_POLLUTION_CONTROLL_DEVICE.toString()));
    }

    @Test
    public void getNonExistingEmission_air_pollution() throws Exception {
        // Get the emission_air_pollution
        restEmission_air_pollutionMockMvc.perform(get("/api/emission-air-pollutions/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmission_air_pollution() throws Exception {
        // Initialize the database
        emission_air_pollutionRepository.save(emission_air_pollution);
        int databaseSizeBeforeUpdate = emission_air_pollutionRepository.findAll().size();

        // Update the emission_air_pollution
        Emission_air_pollution updatedEmission_air_pollution = emission_air_pollutionRepository.findOne(emission_air_pollution.getId());
        updatedEmission_air_pollution
                .pollution_controll_device(UPDATED_POLLUTION_CONTROLL_DEVICE);
        Emission_air_pollutionDTO emission_air_pollutionDTO = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(updatedEmission_air_pollution);

        restEmission_air_pollutionMockMvc.perform(put("/api/emission-air-pollutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_air_pollutionDTO)))
            .andExpect(status().isOk());

        // Validate the Emission_air_pollution in the database
        List<Emission_air_pollution> emission_air_pollutionList = emission_air_pollutionRepository.findAll();
        assertThat(emission_air_pollutionList).hasSize(databaseSizeBeforeUpdate);
        Emission_air_pollution testEmission_air_pollution = emission_air_pollutionList.get(emission_air_pollutionList.size() - 1);
        assertThat(testEmission_air_pollution.getPollution_controll_device()).isEqualTo(UPDATED_POLLUTION_CONTROLL_DEVICE);
    }

    @Test
    public void updateNonExistingEmission_air_pollution() throws Exception {
        int databaseSizeBeforeUpdate = emission_air_pollutionRepository.findAll().size();

        // Create the Emission_air_pollution
        Emission_air_pollutionDTO emission_air_pollutionDTO = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(emission_air_pollution);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmission_air_pollutionMockMvc.perform(put("/api/emission-air-pollutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_air_pollutionDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_air_pollution in the database
        List<Emission_air_pollution> emission_air_pollutionList = emission_air_pollutionRepository.findAll();
        assertThat(emission_air_pollutionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmission_air_pollution() throws Exception {
        // Initialize the database
        emission_air_pollutionRepository.save(emission_air_pollution);
        int databaseSizeBeforeDelete = emission_air_pollutionRepository.findAll().size();

        // Get the emission_air_pollution
        restEmission_air_pollutionMockMvc.perform(delete("/api/emission-air-pollutions/{id}", emission_air_pollution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emission_air_pollution> emission_air_pollutionList = emission_air_pollutionRepository.findAll();
        assertThat(emission_air_pollutionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emission_air_pollution.class);
    }
}
