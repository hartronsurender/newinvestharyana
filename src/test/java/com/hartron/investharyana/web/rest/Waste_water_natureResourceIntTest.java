package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Waste_water_nature;
import com.hartron.investharyana.repository.Waste_water_natureRepository;
import com.hartron.investharyana.service.Waste_water_natureService;
import com.hartron.investharyana.service.dto.Waste_water_natureDTO;
import com.hartron.investharyana.service.mapper.Waste_water_natureMapper;
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
 * Test class for the Waste_water_natureResource REST controller.
 *
 * @see Waste_water_natureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Waste_water_natureResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_WASTE_WATER_NATURE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_WASTE_WATER_NATURE_TYPE = "BBBBBBBBBB";

    @Autowired
    private Waste_water_natureRepository waste_water_natureRepository;

    @Autowired
    private Waste_water_natureMapper waste_water_natureMapper;

    @Autowired
    private Waste_water_natureService waste_water_natureService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWaste_water_natureMockMvc;

    private Waste_water_nature waste_water_nature;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Waste_water_natureResource waste_water_natureResource = new Waste_water_natureResource(waste_water_natureService);
        this.restWaste_water_natureMockMvc = MockMvcBuilders.standaloneSetup(waste_water_natureResource)
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
    public static Waste_water_nature createEntity() {
        Waste_water_nature waste_water_nature = new Waste_water_nature()
                .waste_water_nature_type(DEFAULT_WASTE_WATER_NATURE_TYPE);
        return waste_water_nature;
    }

    @Before
    public void initTest() {
        waste_water_natureRepository.deleteAll();
        waste_water_nature = createEntity();
    }

    @Test
    public void createWaste_water_nature() throws Exception {
        int databaseSizeBeforeCreate = waste_water_natureRepository.findAll().size();

        // Create the Waste_water_nature
        Waste_water_natureDTO waste_water_natureDTO = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(waste_water_nature);

        restWaste_water_natureMockMvc.perform(post("/api/waste-water-natures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_natureDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_nature in the database
        List<Waste_water_nature> waste_water_natureList = waste_water_natureRepository.findAll();
        assertThat(waste_water_natureList).hasSize(databaseSizeBeforeCreate + 1);
        Waste_water_nature testWaste_water_nature = waste_water_natureList.get(waste_water_natureList.size() - 1);
        assertThat(testWaste_water_nature.getWaste_water_nature_type()).isEqualTo(DEFAULT_WASTE_WATER_NATURE_TYPE);
    }

    @Test
    public void createWaste_water_natureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waste_water_natureRepository.findAll().size();

        // Create the Waste_water_nature with an existing ID
        Waste_water_nature existingWaste_water_nature = new Waste_water_nature();
        existingWaste_water_nature.setId(UUID.randomUUID());
        Waste_water_natureDTO existingWaste_water_natureDTO = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(existingWaste_water_nature);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaste_water_natureMockMvc.perform(post("/api/waste-water-natures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWaste_water_natureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Waste_water_nature> waste_water_natureList = waste_water_natureRepository.findAll();
        assertThat(waste_water_natureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWaste_water_natures() throws Exception {
        // Initialize the database
        waste_water_natureRepository.save(waste_water_nature);

        // Get all the waste_water_natureList
        restWaste_water_natureMockMvc.perform(get("/api/waste-water-natures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waste_water_nature.getId().toString())))
            .andExpect(jsonPath("$.[*].waste_water_nature_type").value(hasItem(DEFAULT_WASTE_WATER_NATURE_TYPE.toString())));
    }

    @Test
    public void getWaste_water_nature() throws Exception {
        // Initialize the database
        waste_water_natureRepository.save(waste_water_nature);

        // Get the waste_water_nature
        restWaste_water_natureMockMvc.perform(get("/api/waste-water-natures/{id}", waste_water_nature.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(waste_water_nature.getId().toString()))
            .andExpect(jsonPath("$.waste_water_nature_type").value(DEFAULT_WASTE_WATER_NATURE_TYPE.toString()));
    }

    @Test
    public void getNonExistingWaste_water_nature() throws Exception {
        // Get the waste_water_nature
        restWaste_water_natureMockMvc.perform(get("/api/waste-water-natures/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWaste_water_nature() throws Exception {
        // Initialize the database
        waste_water_natureRepository.save(waste_water_nature);
        int databaseSizeBeforeUpdate = waste_water_natureRepository.findAll().size();

        // Update the waste_water_nature
        Waste_water_nature updatedWaste_water_nature = waste_water_natureRepository.findOne(waste_water_nature.getId());
        updatedWaste_water_nature
                .waste_water_nature_type(UPDATED_WASTE_WATER_NATURE_TYPE);
        Waste_water_natureDTO waste_water_natureDTO = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(updatedWaste_water_nature);

        restWaste_water_natureMockMvc.perform(put("/api/waste-water-natures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_natureDTO)))
            .andExpect(status().isOk());

        // Validate the Waste_water_nature in the database
        List<Waste_water_nature> waste_water_natureList = waste_water_natureRepository.findAll();
        assertThat(waste_water_natureList).hasSize(databaseSizeBeforeUpdate);
        Waste_water_nature testWaste_water_nature = waste_water_natureList.get(waste_water_natureList.size() - 1);
        assertThat(testWaste_water_nature.getWaste_water_nature_type()).isEqualTo(UPDATED_WASTE_WATER_NATURE_TYPE);
    }

    @Test
    public void updateNonExistingWaste_water_nature() throws Exception {
        int databaseSizeBeforeUpdate = waste_water_natureRepository.findAll().size();

        // Create the Waste_water_nature
        Waste_water_natureDTO waste_water_natureDTO = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(waste_water_nature);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWaste_water_natureMockMvc.perform(put("/api/waste-water-natures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_natureDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_nature in the database
        List<Waste_water_nature> waste_water_natureList = waste_water_natureRepository.findAll();
        assertThat(waste_water_natureList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWaste_water_nature() throws Exception {
        // Initialize the database
        waste_water_natureRepository.save(waste_water_nature);
        int databaseSizeBeforeDelete = waste_water_natureRepository.findAll().size();

        // Get the waste_water_nature
        restWaste_water_natureMockMvc.perform(delete("/api/waste-water-natures/{id}", waste_water_nature.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Waste_water_nature> waste_water_natureList = waste_water_natureRepository.findAll();
        assertThat(waste_water_natureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Waste_water_nature.class);
    }
}
