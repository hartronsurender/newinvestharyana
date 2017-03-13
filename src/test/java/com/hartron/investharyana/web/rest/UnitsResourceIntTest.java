package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Units;
import com.hartron.investharyana.repository.UnitsRepository;
import com.hartron.investharyana.service.UnitsService;
import com.hartron.investharyana.service.dto.UnitsDTO;
import com.hartron.investharyana.service.mapper.UnitsMapper;
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
 * Test class for the UnitsResource REST controller.
 *
 * @see UnitsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class UnitsResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_UNITTYPE = "AAAAAAAAAA";
    private static final String UPDATED_UNITTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private UnitsRepository unitsRepository;

    @Autowired
    private UnitsMapper unitsMapper;

    @Autowired
    private UnitsService unitsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restUnitsMockMvc;

    private Units units;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UnitsResource unitsResource = new UnitsResource(unitsService);
        this.restUnitsMockMvc = MockMvcBuilders.standaloneSetup(unitsResource)
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
    public static Units createEntity() {
        Units units = new Units()
                .unittype(DEFAULT_UNITTYPE)
                .description(DEFAULT_DESCRIPTION);
        return units;
    }

    @Before
    public void initTest() {
        unitsRepository.deleteAll();
        units = createEntity();
    }

    @Test
    public void createUnits() throws Exception {
        int databaseSizeBeforeCreate = unitsRepository.findAll().size();

        // Create the Units
        UnitsDTO unitsDTO = unitsMapper.unitsToUnitsDTO(units);

        restUnitsMockMvc.perform(post("/api/units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Units in the database
        List<Units> unitsList = unitsRepository.findAll();
        assertThat(unitsList).hasSize(databaseSizeBeforeCreate + 1);
        Units testUnits = unitsList.get(unitsList.size() - 1);
        assertThat(testUnits.getUnittype()).isEqualTo(DEFAULT_UNITTYPE);
        assertThat(testUnits.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createUnitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitsRepository.findAll().size();

        // Create the Units with an existing ID
        Units existingUnits = new Units();
        existingUnits.setId(UUID.randomUUID());
        UnitsDTO existingUnitsDTO = unitsMapper.unitsToUnitsDTO(existingUnits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitsMockMvc.perform(post("/api/units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUnitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Units> unitsList = unitsRepository.findAll();
        assertThat(unitsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllUnits() throws Exception {
        // Initialize the database
        unitsRepository.save(units);

        // Get all the unitsList
        restUnitsMockMvc.perform(get("/api/units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(units.getId().toString())))
            .andExpect(jsonPath("$.[*].unittype").value(hasItem(DEFAULT_UNITTYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getUnits() throws Exception {
        // Initialize the database
        unitsRepository.save(units);

        // Get the units
        restUnitsMockMvc.perform(get("/api/units/{id}", units.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(units.getId().toString()))
            .andExpect(jsonPath("$.unittype").value(DEFAULT_UNITTYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingUnits() throws Exception {
        // Get the units
        restUnitsMockMvc.perform(get("/api/units/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUnits() throws Exception {
        // Initialize the database
        unitsRepository.save(units);
        int databaseSizeBeforeUpdate = unitsRepository.findAll().size();

        // Update the units
        Units updatedUnits = unitsRepository.findOne(units.getId());
        updatedUnits
                .unittype(UPDATED_UNITTYPE)
                .description(UPDATED_DESCRIPTION);
        UnitsDTO unitsDTO = unitsMapper.unitsToUnitsDTO(updatedUnits);

        restUnitsMockMvc.perform(put("/api/units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitsDTO)))
            .andExpect(status().isOk());

        // Validate the Units in the database
        List<Units> unitsList = unitsRepository.findAll();
        assertThat(unitsList).hasSize(databaseSizeBeforeUpdate);
        Units testUnits = unitsList.get(unitsList.size() - 1);
        assertThat(testUnits.getUnittype()).isEqualTo(UPDATED_UNITTYPE);
        assertThat(testUnits.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingUnits() throws Exception {
        int databaseSizeBeforeUpdate = unitsRepository.findAll().size();

        // Create the Units
        UnitsDTO unitsDTO = unitsMapper.unitsToUnitsDTO(units);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUnitsMockMvc.perform(put("/api/units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Units in the database
        List<Units> unitsList = unitsRepository.findAll();
        assertThat(unitsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteUnits() throws Exception {
        // Initialize the database
        unitsRepository.save(units);
        int databaseSizeBeforeDelete = unitsRepository.findAll().size();

        // Get the units
        restUnitsMockMvc.perform(delete("/api/units/{id}", units.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Units> unitsList = unitsRepository.findAll();
        assertThat(unitsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Units.class);
    }
}
