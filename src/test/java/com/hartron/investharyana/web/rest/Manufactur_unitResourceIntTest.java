package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Manufactur_unit;
import com.hartron.investharyana.repository.Manufactur_unitRepository;
import com.hartron.investharyana.service.Manufactur_unitService;
import com.hartron.investharyana.service.dto.Manufactur_unitDTO;
import com.hartron.investharyana.service.mapper.Manufactur_unitMapper;
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
 * Test class for the Manufactur_unitResource REST controller.
 *
 * @see Manufactur_unitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Manufactur_unitResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_UNIT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_TYPE = "BBBBBBBBBB";

    @Autowired
    private Manufactur_unitRepository manufactur_unitRepository;

    @Autowired
    private Manufactur_unitMapper manufactur_unitMapper;

    @Autowired
    private Manufactur_unitService manufactur_unitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restManufactur_unitMockMvc;

    private Manufactur_unit manufactur_unit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Manufactur_unitResource manufactur_unitResource = new Manufactur_unitResource(manufactur_unitService);
        this.restManufactur_unitMockMvc = MockMvcBuilders.standaloneSetup(manufactur_unitResource)
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
    public static Manufactur_unit createEntity() {
        Manufactur_unit manufactur_unit = new Manufactur_unit()
                .unit_type(DEFAULT_UNIT_TYPE);
        return manufactur_unit;
    }

    @Before
    public void initTest() {
        manufactur_unitRepository.deleteAll();
        manufactur_unit = createEntity();
    }

    @Test
    public void createManufactur_unit() throws Exception {
        int databaseSizeBeforeCreate = manufactur_unitRepository.findAll().size();

        // Create the Manufactur_unit
        Manufactur_unitDTO manufactur_unitDTO = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(manufactur_unit);

        restManufactur_unitMockMvc.perform(post("/api/manufactur-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufactur_unitDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufactur_unit in the database
        List<Manufactur_unit> manufactur_unitList = manufactur_unitRepository.findAll();
        assertThat(manufactur_unitList).hasSize(databaseSizeBeforeCreate + 1);
        Manufactur_unit testManufactur_unit = manufactur_unitList.get(manufactur_unitList.size() - 1);
        assertThat(testManufactur_unit.getUnit_type()).isEqualTo(DEFAULT_UNIT_TYPE);
    }

    @Test
    public void createManufactur_unitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufactur_unitRepository.findAll().size();

        // Create the Manufactur_unit with an existing ID
        Manufactur_unit existingManufactur_unit = new Manufactur_unit();
        existingManufactur_unit.setId(UUID.randomUUID());
        Manufactur_unitDTO existingManufactur_unitDTO = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(existingManufactur_unit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufactur_unitMockMvc.perform(post("/api/manufactur-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingManufactur_unitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Manufactur_unit> manufactur_unitList = manufactur_unitRepository.findAll();
        assertThat(manufactur_unitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllManufactur_units() throws Exception {
        // Initialize the database
        manufactur_unitRepository.save(manufactur_unit);

        // Get all the manufactur_unitList
        restManufactur_unitMockMvc.perform(get("/api/manufactur-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufactur_unit.getId().toString())))
            .andExpect(jsonPath("$.[*].unit_type").value(hasItem(DEFAULT_UNIT_TYPE.toString())));
    }

    @Test
    public void getManufactur_unit() throws Exception {
        // Initialize the database
        manufactur_unitRepository.save(manufactur_unit);

        // Get the manufactur_unit
        restManufactur_unitMockMvc.perform(get("/api/manufactur-units/{id}", manufactur_unit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manufactur_unit.getId().toString()))
            .andExpect(jsonPath("$.unit_type").value(DEFAULT_UNIT_TYPE.toString()));
    }

    @Test
    public void getNonExistingManufactur_unit() throws Exception {
        // Get the manufactur_unit
        restManufactur_unitMockMvc.perform(get("/api/manufactur-units/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateManufactur_unit() throws Exception {
        // Initialize the database
        manufactur_unitRepository.save(manufactur_unit);
        int databaseSizeBeforeUpdate = manufactur_unitRepository.findAll().size();

        // Update the manufactur_unit
        Manufactur_unit updatedManufactur_unit = manufactur_unitRepository.findOne(manufactur_unit.getId());
        updatedManufactur_unit
                .unit_type(UPDATED_UNIT_TYPE);
        Manufactur_unitDTO manufactur_unitDTO = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(updatedManufactur_unit);

        restManufactur_unitMockMvc.perform(put("/api/manufactur-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufactur_unitDTO)))
            .andExpect(status().isOk());

        // Validate the Manufactur_unit in the database
        List<Manufactur_unit> manufactur_unitList = manufactur_unitRepository.findAll();
        assertThat(manufactur_unitList).hasSize(databaseSizeBeforeUpdate);
        Manufactur_unit testManufactur_unit = manufactur_unitList.get(manufactur_unitList.size() - 1);
        assertThat(testManufactur_unit.getUnit_type()).isEqualTo(UPDATED_UNIT_TYPE);
    }

    @Test
    public void updateNonExistingManufactur_unit() throws Exception {
        int databaseSizeBeforeUpdate = manufactur_unitRepository.findAll().size();

        // Create the Manufactur_unit
        Manufactur_unitDTO manufactur_unitDTO = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(manufactur_unit);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManufactur_unitMockMvc.perform(put("/api/manufactur-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufactur_unitDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufactur_unit in the database
        List<Manufactur_unit> manufactur_unitList = manufactur_unitRepository.findAll();
        assertThat(manufactur_unitList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteManufactur_unit() throws Exception {
        // Initialize the database
        manufactur_unitRepository.save(manufactur_unit);
        int databaseSizeBeforeDelete = manufactur_unitRepository.findAll().size();

        // Get the manufactur_unit
        restManufactur_unitMockMvc.perform(delete("/api/manufactur-units/{id}", manufactur_unit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manufactur_unit> manufactur_unitList = manufactur_unitRepository.findAll();
        assertThat(manufactur_unitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufactur_unit.class);
    }
}
