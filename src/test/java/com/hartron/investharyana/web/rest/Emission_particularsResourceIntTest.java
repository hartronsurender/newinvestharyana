package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emission_particulars;
import com.hartron.investharyana.repository.Emission_particularsRepository;
import com.hartron.investharyana.service.Emission_particularsService;
import com.hartron.investharyana.service.dto.Emission_particularsDTO;
import com.hartron.investharyana.service.mapper.Emission_particularsMapper;
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
 * Test class for the Emission_particularsResource REST controller.
 *
 * @see Emission_particularsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Emission_particularsResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PARTICULAR_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PARTICULAR_TYPE = "BBBBBBBBBB";

    @Autowired
    private Emission_particularsRepository emission_particularsRepository;

    @Autowired
    private Emission_particularsMapper emission_particularsMapper;

    @Autowired
    private Emission_particularsService emission_particularsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmission_particularsMockMvc;

    private Emission_particulars emission_particulars;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Emission_particularsResource emission_particularsResource = new Emission_particularsResource(emission_particularsService);
        this.restEmission_particularsMockMvc = MockMvcBuilders.standaloneSetup(emission_particularsResource)
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
    public static Emission_particulars createEntity() {
        Emission_particulars emission_particulars = new Emission_particulars()
                .particular_type(DEFAULT_PARTICULAR_TYPE);
        return emission_particulars;
    }

    @Before
    public void initTest() {
        emission_particularsRepository.deleteAll();
        emission_particulars = createEntity();
    }

    @Test
    public void createEmission_particulars() throws Exception {
        int databaseSizeBeforeCreate = emission_particularsRepository.findAll().size();

        // Create the Emission_particulars
        Emission_particularsDTO emission_particularsDTO = emission_particularsMapper.emission_particularsToEmission_particularsDTO(emission_particulars);

        restEmission_particularsMockMvc.perform(post("/api/emission-particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_particularsDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_particulars in the database
        List<Emission_particulars> emission_particularsList = emission_particularsRepository.findAll();
        assertThat(emission_particularsList).hasSize(databaseSizeBeforeCreate + 1);
        Emission_particulars testEmission_particulars = emission_particularsList.get(emission_particularsList.size() - 1);
        assertThat(testEmission_particulars.getParticular_type()).isEqualTo(DEFAULT_PARTICULAR_TYPE);
    }

    @Test
    public void createEmission_particularsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emission_particularsRepository.findAll().size();

        // Create the Emission_particulars with an existing ID
        Emission_particulars existingEmission_particulars = new Emission_particulars();
        existingEmission_particulars.setId(UUID.randomUUID());
        Emission_particularsDTO existingEmission_particularsDTO = emission_particularsMapper.emission_particularsToEmission_particularsDTO(existingEmission_particulars);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmission_particularsMockMvc.perform(post("/api/emission-particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmission_particularsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emission_particulars> emission_particularsList = emission_particularsRepository.findAll();
        assertThat(emission_particularsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmission_particulars() throws Exception {
        // Initialize the database
        emission_particularsRepository.save(emission_particulars);

        // Get all the emission_particularsList
        restEmission_particularsMockMvc.perform(get("/api/emission-particulars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emission_particulars.getId().toString())))
            .andExpect(jsonPath("$.[*].particular_type").value(hasItem(DEFAULT_PARTICULAR_TYPE.toString())));
    }

    @Test
    public void getEmission_particulars() throws Exception {
        // Initialize the database
        emission_particularsRepository.save(emission_particulars);

        // Get the emission_particulars
        restEmission_particularsMockMvc.perform(get("/api/emission-particulars/{id}", emission_particulars.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emission_particulars.getId().toString()))
            .andExpect(jsonPath("$.particular_type").value(DEFAULT_PARTICULAR_TYPE.toString()));
    }

    @Test
    public void getNonExistingEmission_particulars() throws Exception {
        // Get the emission_particulars
        restEmission_particularsMockMvc.perform(get("/api/emission-particulars/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmission_particulars() throws Exception {
        // Initialize the database
        emission_particularsRepository.save(emission_particulars);
        int databaseSizeBeforeUpdate = emission_particularsRepository.findAll().size();

        // Update the emission_particulars
        Emission_particulars updatedEmission_particulars = emission_particularsRepository.findOne(emission_particulars.getId());
        updatedEmission_particulars
                .particular_type(UPDATED_PARTICULAR_TYPE);
        Emission_particularsDTO emission_particularsDTO = emission_particularsMapper.emission_particularsToEmission_particularsDTO(updatedEmission_particulars);

        restEmission_particularsMockMvc.perform(put("/api/emission-particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_particularsDTO)))
            .andExpect(status().isOk());

        // Validate the Emission_particulars in the database
        List<Emission_particulars> emission_particularsList = emission_particularsRepository.findAll();
        assertThat(emission_particularsList).hasSize(databaseSizeBeforeUpdate);
        Emission_particulars testEmission_particulars = emission_particularsList.get(emission_particularsList.size() - 1);
        assertThat(testEmission_particulars.getParticular_type()).isEqualTo(UPDATED_PARTICULAR_TYPE);
    }

    @Test
    public void updateNonExistingEmission_particulars() throws Exception {
        int databaseSizeBeforeUpdate = emission_particularsRepository.findAll().size();

        // Create the Emission_particulars
        Emission_particularsDTO emission_particularsDTO = emission_particularsMapper.emission_particularsToEmission_particularsDTO(emission_particulars);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmission_particularsMockMvc.perform(put("/api/emission-particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emission_particularsDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission_particulars in the database
        List<Emission_particulars> emission_particularsList = emission_particularsRepository.findAll();
        assertThat(emission_particularsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmission_particulars() throws Exception {
        // Initialize the database
        emission_particularsRepository.save(emission_particulars);
        int databaseSizeBeforeDelete = emission_particularsRepository.findAll().size();

        // Get the emission_particulars
        restEmission_particularsMockMvc.perform(delete("/api/emission-particulars/{id}", emission_particulars.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emission_particulars> emission_particularsList = emission_particularsRepository.findAll();
        assertThat(emission_particularsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emission_particulars.class);
    }
}
