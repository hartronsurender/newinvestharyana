package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Waste_water_disposal_mode;
import com.hartron.investharyana.repository.Waste_water_disposal_modeRepository;
import com.hartron.investharyana.service.Waste_water_disposal_modeService;
import com.hartron.investharyana.service.dto.Waste_water_disposal_modeDTO;
import com.hartron.investharyana.service.mapper.Waste_water_disposal_modeMapper;
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
 * Test class for the Waste_water_disposal_modeResource REST controller.
 *
 * @see Waste_water_disposal_modeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Waste_water_disposal_modeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DISPOSAL_MODE = "AAAAAAAAAA";
    private static final String UPDATED_DISPOSAL_MODE = "BBBBBBBBBB";

    @Autowired
    private Waste_water_disposal_modeRepository waste_water_disposal_modeRepository;

    @Autowired
    private Waste_water_disposal_modeMapper waste_water_disposal_modeMapper;

    @Autowired
    private Waste_water_disposal_modeService waste_water_disposal_modeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWaste_water_disposal_modeMockMvc;

    private Waste_water_disposal_mode waste_water_disposal_mode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Waste_water_disposal_modeResource waste_water_disposal_modeResource = new Waste_water_disposal_modeResource(waste_water_disposal_modeService);
        this.restWaste_water_disposal_modeMockMvc = MockMvcBuilders.standaloneSetup(waste_water_disposal_modeResource)
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
    public static Waste_water_disposal_mode createEntity() {
        Waste_water_disposal_mode waste_water_disposal_mode = new Waste_water_disposal_mode()
                .disposal_mode(DEFAULT_DISPOSAL_MODE);
        return waste_water_disposal_mode;
    }

    @Before
    public void initTest() {
        waste_water_disposal_modeRepository.deleteAll();
        waste_water_disposal_mode = createEntity();
    }

    @Test
    public void createWaste_water_disposal_mode() throws Exception {
        int databaseSizeBeforeCreate = waste_water_disposal_modeRepository.findAll().size();

        // Create the Waste_water_disposal_mode
        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(waste_water_disposal_mode);

        restWaste_water_disposal_modeMockMvc.perform(post("/api/waste-water-disposal-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_disposal_modeDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_disposal_mode in the database
        List<Waste_water_disposal_mode> waste_water_disposal_modeList = waste_water_disposal_modeRepository.findAll();
        assertThat(waste_water_disposal_modeList).hasSize(databaseSizeBeforeCreate + 1);
        Waste_water_disposal_mode testWaste_water_disposal_mode = waste_water_disposal_modeList.get(waste_water_disposal_modeList.size() - 1);
        assertThat(testWaste_water_disposal_mode.getDisposal_mode()).isEqualTo(DEFAULT_DISPOSAL_MODE);
    }

    @Test
    public void createWaste_water_disposal_modeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waste_water_disposal_modeRepository.findAll().size();

        // Create the Waste_water_disposal_mode with an existing ID
        Waste_water_disposal_mode existingWaste_water_disposal_mode = new Waste_water_disposal_mode();
        existingWaste_water_disposal_mode.setId(UUID.randomUUID());
        Waste_water_disposal_modeDTO existingWaste_water_disposal_modeDTO = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(existingWaste_water_disposal_mode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaste_water_disposal_modeMockMvc.perform(post("/api/waste-water-disposal-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWaste_water_disposal_modeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Waste_water_disposal_mode> waste_water_disposal_modeList = waste_water_disposal_modeRepository.findAll();
        assertThat(waste_water_disposal_modeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWaste_water_disposal_modes() throws Exception {
        // Initialize the database
        waste_water_disposal_modeRepository.save(waste_water_disposal_mode);

        // Get all the waste_water_disposal_modeList
        restWaste_water_disposal_modeMockMvc.perform(get("/api/waste-water-disposal-modes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waste_water_disposal_mode.getId().toString())))
            .andExpect(jsonPath("$.[*].disposal_mode").value(hasItem(DEFAULT_DISPOSAL_MODE.toString())));
    }

    @Test
    public void getWaste_water_disposal_mode() throws Exception {
        // Initialize the database
        waste_water_disposal_modeRepository.save(waste_water_disposal_mode);

        // Get the waste_water_disposal_mode
        restWaste_water_disposal_modeMockMvc.perform(get("/api/waste-water-disposal-modes/{id}", waste_water_disposal_mode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(waste_water_disposal_mode.getId().toString()))
            .andExpect(jsonPath("$.disposal_mode").value(DEFAULT_DISPOSAL_MODE.toString()));
    }

    @Test
    public void getNonExistingWaste_water_disposal_mode() throws Exception {
        // Get the waste_water_disposal_mode
        restWaste_water_disposal_modeMockMvc.perform(get("/api/waste-water-disposal-modes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWaste_water_disposal_mode() throws Exception {
        // Initialize the database
        waste_water_disposal_modeRepository.save(waste_water_disposal_mode);
        int databaseSizeBeforeUpdate = waste_water_disposal_modeRepository.findAll().size();

        // Update the waste_water_disposal_mode
        Waste_water_disposal_mode updatedWaste_water_disposal_mode = waste_water_disposal_modeRepository.findOne(waste_water_disposal_mode.getId());
        updatedWaste_water_disposal_mode
                .disposal_mode(UPDATED_DISPOSAL_MODE);
        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(updatedWaste_water_disposal_mode);

        restWaste_water_disposal_modeMockMvc.perform(put("/api/waste-water-disposal-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_disposal_modeDTO)))
            .andExpect(status().isOk());

        // Validate the Waste_water_disposal_mode in the database
        List<Waste_water_disposal_mode> waste_water_disposal_modeList = waste_water_disposal_modeRepository.findAll();
        assertThat(waste_water_disposal_modeList).hasSize(databaseSizeBeforeUpdate);
        Waste_water_disposal_mode testWaste_water_disposal_mode = waste_water_disposal_modeList.get(waste_water_disposal_modeList.size() - 1);
        assertThat(testWaste_water_disposal_mode.getDisposal_mode()).isEqualTo(UPDATED_DISPOSAL_MODE);
    }

    @Test
    public void updateNonExistingWaste_water_disposal_mode() throws Exception {
        int databaseSizeBeforeUpdate = waste_water_disposal_modeRepository.findAll().size();

        // Create the Waste_water_disposal_mode
        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(waste_water_disposal_mode);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWaste_water_disposal_modeMockMvc.perform(put("/api/waste-water-disposal-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_disposal_modeDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_disposal_mode in the database
        List<Waste_water_disposal_mode> waste_water_disposal_modeList = waste_water_disposal_modeRepository.findAll();
        assertThat(waste_water_disposal_modeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWaste_water_disposal_mode() throws Exception {
        // Initialize the database
        waste_water_disposal_modeRepository.save(waste_water_disposal_mode);
        int databaseSizeBeforeDelete = waste_water_disposal_modeRepository.findAll().size();

        // Get the waste_water_disposal_mode
        restWaste_water_disposal_modeMockMvc.perform(delete("/api/waste-water-disposal-modes/{id}", waste_water_disposal_mode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Waste_water_disposal_mode> waste_water_disposal_modeList = waste_water_disposal_modeRepository.findAll();
        assertThat(waste_water_disposal_modeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Waste_water_disposal_mode.class);
    }
}
