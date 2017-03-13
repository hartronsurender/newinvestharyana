package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emission_fuel_type;
import com.hartron.investharyana.repository.Emission_fuel_typeRepository;
import com.hartron.investharyana.service.Emission_fuel_typeService;
import com.hartron.investharyana.service.dto.Emission_fuel_typeDTO;
import com.hartron.investharyana.service.mapper.Emission_fuel_typeMapper;
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
 * Test class for the Emission_fuel_typeResource REST controller.
 *
 * @see Emission_fuel_typeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Emission_fuel_typeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_FUEL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FUEL_TYPE = "BBBBBBBBBB";

    @Autowired
    private Emission_fuel_typeRepository emission_fuel_typeRepository;

    @Autowired
    private Emission_fuel_typeMapper emission_fuel_typeMapper;

    @Autowired
    private Emission_fuel_typeService emission_fuel_typeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmission_fuel_typeMockMvc;

    private Emission_fuel_type emission_fuel_type;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Emission_fuel_typeResource emission_fuel_typeResource = new Emission_fuel_typeResource(emission_fuel_typeService);
        this.restEmission_fuel_typeMockMvc = MockMvcBuilders.standaloneSetup(emission_fuel_typeResource)
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
    public static Emission_fuel_type createEntity() {
        Emission_fuel_type emission_fuel_type = new Emission_fuel_type()
                .fuel_type(DEFAULT_FUEL_TYPE);
        return emission_fuel_type;
    }

    @Before
    public void initTest() {
        emission_fuel_typeRepository.deleteAll();
        emission_fuel_type = createEntity();
    }

    @Test
    public void createEmission_fuel_type() throws Exception {
        int databaseSizeBeforeCreate = emission_fuel_typeRepository.findAll().size();

        // Create the Emission_fuel_type
        Emission_fuel_typeDTO emission_fuel_typeDTO = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(emission_fuel_type);

        restEmission_fuel_typeMockMvc.perform(post("/api/emission-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_fuel_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_fuel_type in the database
        List<Emission_fuel_type> emission_fuel_typeList = emission_fuel_typeRepository.findAll();
        assertThat(emission_fuel_typeList).hasSize(databaseSizeBeforeCreate + 1);
        Emission_fuel_type testEmission_fuel_type = emission_fuel_typeList.get(emission_fuel_typeList.size() - 1);
        assertThat(testEmission_fuel_type.getFuel_type()).isEqualTo(DEFAULT_FUEL_TYPE);
    }

    @Test
    public void createEmission_fuel_typeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emission_fuel_typeRepository.findAll().size();

        // Create the Emission_fuel_type with an existing ID
        Emission_fuel_type existingEmission_fuel_type = new Emission_fuel_type();
        existingEmission_fuel_type.setId(UUID.randomUUID());
        Emission_fuel_typeDTO existingEmission_fuel_typeDTO = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(existingEmission_fuel_type);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmission_fuel_typeMockMvc.perform(post("/api/emission-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmission_fuel_typeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emission_fuel_type> emission_fuel_typeList = emission_fuel_typeRepository.findAll();
        assertThat(emission_fuel_typeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmission_fuel_types() throws Exception {
        // Initialize the database
        emission_fuel_typeRepository.save(emission_fuel_type);

        // Get all the emission_fuel_typeList
        restEmission_fuel_typeMockMvc.perform(get("/api/emission-fuel-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emission_fuel_type.getId().toString())))
            .andExpect(jsonPath("$.[*].fuel_type").value(hasItem(DEFAULT_FUEL_TYPE.toString())));
    }

    @Test
    public void getEmission_fuel_type() throws Exception {
        // Initialize the database
        emission_fuel_typeRepository.save(emission_fuel_type);

        // Get the emission_fuel_type
        restEmission_fuel_typeMockMvc.perform(get("/api/emission-fuel-types/{id}", emission_fuel_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emission_fuel_type.getId().toString()))
            .andExpect(jsonPath("$.fuel_type").value(DEFAULT_FUEL_TYPE.toString()));
    }

    @Test
    public void getNonExistingEmission_fuel_type() throws Exception {
        // Get the emission_fuel_type
        restEmission_fuel_typeMockMvc.perform(get("/api/emission-fuel-types/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmission_fuel_type() throws Exception {
        // Initialize the database
        emission_fuel_typeRepository.save(emission_fuel_type);
        int databaseSizeBeforeUpdate = emission_fuel_typeRepository.findAll().size();

        // Update the emission_fuel_type
        Emission_fuel_type updatedEmission_fuel_type = emission_fuel_typeRepository.findOne(emission_fuel_type.getId());
        updatedEmission_fuel_type
                .fuel_type(UPDATED_FUEL_TYPE);
        Emission_fuel_typeDTO emission_fuel_typeDTO = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(updatedEmission_fuel_type);

        restEmission_fuel_typeMockMvc.perform(put("/api/emission-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_fuel_typeDTO)))
            .andExpect(status().isOk());

        // Validate the Emission_fuel_type in the database
        List<Emission_fuel_type> emission_fuel_typeList = emission_fuel_typeRepository.findAll();
        assertThat(emission_fuel_typeList).hasSize(databaseSizeBeforeUpdate);
        Emission_fuel_type testEmission_fuel_type = emission_fuel_typeList.get(emission_fuel_typeList.size() - 1);
        assertThat(testEmission_fuel_type.getFuel_type()).isEqualTo(UPDATED_FUEL_TYPE);
    }

    @Test
    public void updateNonExistingEmission_fuel_type() throws Exception {
        int databaseSizeBeforeUpdate = emission_fuel_typeRepository.findAll().size();

        // Create the Emission_fuel_type
        Emission_fuel_typeDTO emission_fuel_typeDTO = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(emission_fuel_type);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmission_fuel_typeMockMvc.perform(put("/api/emission-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_fuel_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_fuel_type in the database
        List<Emission_fuel_type> emission_fuel_typeList = emission_fuel_typeRepository.findAll();
        assertThat(emission_fuel_typeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmission_fuel_type() throws Exception {
        // Initialize the database
        emission_fuel_typeRepository.save(emission_fuel_type);
        int databaseSizeBeforeDelete = emission_fuel_typeRepository.findAll().size();

        // Get the emission_fuel_type
        restEmission_fuel_typeMockMvc.perform(delete("/api/emission-fuel-types/{id}", emission_fuel_type.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emission_fuel_type> emission_fuel_typeList = emission_fuel_typeRepository.findAll();
        assertThat(emission_fuel_typeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emission_fuel_type.class);
    }
}
