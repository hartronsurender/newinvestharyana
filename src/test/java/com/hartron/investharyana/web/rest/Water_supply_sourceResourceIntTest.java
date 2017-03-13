package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Water_supply_source;
import com.hartron.investharyana.repository.Water_supply_sourceRepository;
import com.hartron.investharyana.service.Water_supply_sourceService;
import com.hartron.investharyana.service.dto.Water_supply_sourceDTO;
import com.hartron.investharyana.service.mapper.Water_supply_sourceMapper;
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
 * Test class for the Water_supply_sourceResource REST controller.
 *
 * @see Water_supply_sourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Water_supply_sourceResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    @Autowired
    private Water_supply_sourceRepository water_supply_sourceRepository;

    @Autowired
    private Water_supply_sourceMapper water_supply_sourceMapper;

    @Autowired
    private Water_supply_sourceService water_supply_sourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWater_supply_sourceMockMvc;

    private Water_supply_source water_supply_source;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Water_supply_sourceResource water_supply_sourceResource = new Water_supply_sourceResource(water_supply_sourceService);
        this.restWater_supply_sourceMockMvc = MockMvcBuilders.standaloneSetup(water_supply_sourceResource)
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
    public static Water_supply_source createEntity() {
        Water_supply_source water_supply_source = new Water_supply_source()
                .source_type(DEFAULT_SOURCE_TYPE);
        return water_supply_source;
    }

    @Before
    public void initTest() {
        water_supply_sourceRepository.deleteAll();
        water_supply_source = createEntity();
    }

    @Test
    public void createWater_supply_source() throws Exception {
        int databaseSizeBeforeCreate = water_supply_sourceRepository.findAll().size();

        // Create the Water_supply_source
        Water_supply_sourceDTO water_supply_sourceDTO = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(water_supply_source);

        restWater_supply_sourceMockMvc.perform(post("/api/water-supply-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(water_supply_sourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Water_supply_source in the database
        List<Water_supply_source> water_supply_sourceList = water_supply_sourceRepository.findAll();
        assertThat(water_supply_sourceList).hasSize(databaseSizeBeforeCreate + 1);
        Water_supply_source testWater_supply_source = water_supply_sourceList.get(water_supply_sourceList.size() - 1);
        assertThat(testWater_supply_source.getSource_type()).isEqualTo(DEFAULT_SOURCE_TYPE);
    }

    @Test
    public void createWater_supply_sourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = water_supply_sourceRepository.findAll().size();

        // Create the Water_supply_source with an existing ID
        Water_supply_source existingWater_supply_source = new Water_supply_source();
        existingWater_supply_source.setId(UUID.randomUUID());
        Water_supply_sourceDTO existingWater_supply_sourceDTO = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(existingWater_supply_source);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWater_supply_sourceMockMvc.perform(post("/api/water-supply-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWater_supply_sourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Water_supply_source> water_supply_sourceList = water_supply_sourceRepository.findAll();
        assertThat(water_supply_sourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWater_supply_sources() throws Exception {
        // Initialize the database
        water_supply_sourceRepository.save(water_supply_source);

        // Get all the water_supply_sourceList
        restWater_supply_sourceMockMvc.perform(get("/api/water-supply-sources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(water_supply_source.getId().toString())))
            .andExpect(jsonPath("$.[*].source_type").value(hasItem(DEFAULT_SOURCE_TYPE.toString())));
    }

    @Test
    public void getWater_supply_source() throws Exception {
        // Initialize the database
        water_supply_sourceRepository.save(water_supply_source);

        // Get the water_supply_source
        restWater_supply_sourceMockMvc.perform(get("/api/water-supply-sources/{id}", water_supply_source.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(water_supply_source.getId().toString()))
            .andExpect(jsonPath("$.source_type").value(DEFAULT_SOURCE_TYPE.toString()));
    }

    @Test
    public void getNonExistingWater_supply_source() throws Exception {
        // Get the water_supply_source
        restWater_supply_sourceMockMvc.perform(get("/api/water-supply-sources/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWater_supply_source() throws Exception {
        // Initialize the database
        water_supply_sourceRepository.save(water_supply_source);
        int databaseSizeBeforeUpdate = water_supply_sourceRepository.findAll().size();

        // Update the water_supply_source
        Water_supply_source updatedWater_supply_source = water_supply_sourceRepository.findOne(water_supply_source.getId());
        updatedWater_supply_source
                .source_type(UPDATED_SOURCE_TYPE);
        Water_supply_sourceDTO water_supply_sourceDTO = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(updatedWater_supply_source);

        restWater_supply_sourceMockMvc.perform(put("/api/water-supply-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(water_supply_sourceDTO)))
            .andExpect(status().isOk());

        // Validate the Water_supply_source in the database
        List<Water_supply_source> water_supply_sourceList = water_supply_sourceRepository.findAll();
        assertThat(water_supply_sourceList).hasSize(databaseSizeBeforeUpdate);
        Water_supply_source testWater_supply_source = water_supply_sourceList.get(water_supply_sourceList.size() - 1);
        assertThat(testWater_supply_source.getSource_type()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    public void updateNonExistingWater_supply_source() throws Exception {
        int databaseSizeBeforeUpdate = water_supply_sourceRepository.findAll().size();

        // Create the Water_supply_source
        Water_supply_sourceDTO water_supply_sourceDTO = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(water_supply_source);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWater_supply_sourceMockMvc.perform(put("/api/water-supply-sources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(water_supply_sourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Water_supply_source in the database
        List<Water_supply_source> water_supply_sourceList = water_supply_sourceRepository.findAll();
        assertThat(water_supply_sourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWater_supply_source() throws Exception {
        // Initialize the database
        water_supply_sourceRepository.save(water_supply_source);
        int databaseSizeBeforeDelete = water_supply_sourceRepository.findAll().size();

        // Get the water_supply_source
        restWater_supply_sourceMockMvc.perform(delete("/api/water-supply-sources/{id}", water_supply_source.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Water_supply_source> water_supply_sourceList = water_supply_sourceRepository.findAll();
        assertThat(water_supply_sourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Water_supply_source.class);
    }
}
