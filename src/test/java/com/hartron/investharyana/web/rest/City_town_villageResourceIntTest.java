package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.City_town_village;
import com.hartron.investharyana.repository.City_town_villageRepository;
import com.hartron.investharyana.service.City_town_villageService;
import com.hartron.investharyana.service.dto.City_town_villageDTO;
import com.hartron.investharyana.service.mapper.City_town_villageMapper;
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
 * Test class for the City_town_villageResource REST controller.
 *
 * @see City_town_villageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class City_town_villageResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_STATEID = UUID.randomUUID();
    private static final UUID UPDATED_STATEID = UUID.randomUUID();

    private static final UUID DEFAULT_DISTRICTID = UUID.randomUUID();
    private static final UUID UPDATED_DISTRICTID = UUID.randomUUID();

    private static final UUID DEFAULT_BLOCKID = UUID.randomUUID();
    private static final UUID UPDATED_BLOCKID = UUID.randomUUID();

    private static final String DEFAULT_CITY_TOWN_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_TOWN_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private City_town_villageRepository city_town_villageRepository;

    @Autowired
    private City_town_villageMapper city_town_villageMapper;

    @Autowired
    private City_town_villageService city_town_villageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCity_town_villageMockMvc;

    private City_town_village city_town_village;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        City_town_villageResource city_town_villageResource = new City_town_villageResource(city_town_villageService);
        this.restCity_town_villageMockMvc = MockMvcBuilders.standaloneSetup(city_town_villageResource)
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
    public static City_town_village createEntity() {
        City_town_village city_town_village = new City_town_village()
                .stateid(DEFAULT_STATEID)
                .districtid(DEFAULT_DISTRICTID)
                .blockid(DEFAULT_BLOCKID)
                .city_town_village_name(DEFAULT_CITY_TOWN_VILLAGE_NAME)
                .description(DEFAULT_DESCRIPTION);
        return city_town_village;
    }

    @Before
    public void initTest() {
        city_town_villageRepository.deleteAll();
        city_town_village = createEntity();
    }

    @Test
    public void createCity_town_village() throws Exception {
        int databaseSizeBeforeCreate = city_town_villageRepository.findAll().size();

        // Create the City_town_village
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);

        restCity_town_villageMockMvc.perform(post("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isCreated());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeCreate + 1);
        City_town_village testCity_town_village = city_town_villageList.get(city_town_villageList.size() - 1);
        assertThat(testCity_town_village.getStateid()).isEqualTo(DEFAULT_STATEID);
        assertThat(testCity_town_village.getDistrictid()).isEqualTo(DEFAULT_DISTRICTID);
        assertThat(testCity_town_village.getBlockid()).isEqualTo(DEFAULT_BLOCKID);
        assertThat(testCity_town_village.getCity_town_village_name()).isEqualTo(DEFAULT_CITY_TOWN_VILLAGE_NAME);
        assertThat(testCity_town_village.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createCity_town_villageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = city_town_villageRepository.findAll().size();

        // Create the City_town_village with an existing ID
        City_town_village existingCity_town_village = new City_town_village();
        existingCity_town_village.setId(UUID.randomUUID());
        City_town_villageDTO existingCity_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(existingCity_town_village);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCity_town_villageMockMvc.perform(post("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCity_town_villageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCity_town_villages() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);

        // Get all the city_town_villageList
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(city_town_village.getId().toString())))
            .andExpect(jsonPath("$.[*].stateid").value(hasItem(DEFAULT_STATEID.toString())))
            .andExpect(jsonPath("$.[*].districtid").value(hasItem(DEFAULT_DISTRICTID.toString())))
            .andExpect(jsonPath("$.[*].blockid").value(hasItem(DEFAULT_BLOCKID.toString())))
            .andExpect(jsonPath("$.[*].city_town_village_name").value(hasItem(DEFAULT_CITY_TOWN_VILLAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);

        // Get the city_town_village
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages/{id}", city_town_village.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(city_town_village.getId().toString()))
            .andExpect(jsonPath("$.stateid").value(DEFAULT_STATEID.toString()))
            .andExpect(jsonPath("$.districtid").value(DEFAULT_DISTRICTID.toString()))
            .andExpect(jsonPath("$.blockid").value(DEFAULT_BLOCKID.toString()))
            .andExpect(jsonPath("$.city_town_village_name").value(DEFAULT_CITY_TOWN_VILLAGE_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingCity_town_village() throws Exception {
        // Get the city_town_village
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);
        int databaseSizeBeforeUpdate = city_town_villageRepository.findAll().size();

        // Update the city_town_village
        City_town_village updatedCity_town_village = city_town_villageRepository.findOne(city_town_village.getId());
        updatedCity_town_village
                .stateid(UPDATED_STATEID)
                .districtid(UPDATED_DISTRICTID)
                .blockid(UPDATED_BLOCKID)
                .city_town_village_name(UPDATED_CITY_TOWN_VILLAGE_NAME)
                .description(UPDATED_DESCRIPTION);
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(updatedCity_town_village);

        restCity_town_villageMockMvc.perform(put("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isOk());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeUpdate);
        City_town_village testCity_town_village = city_town_villageList.get(city_town_villageList.size() - 1);
        assertThat(testCity_town_village.getStateid()).isEqualTo(UPDATED_STATEID);
        assertThat(testCity_town_village.getDistrictid()).isEqualTo(UPDATED_DISTRICTID);
        assertThat(testCity_town_village.getBlockid()).isEqualTo(UPDATED_BLOCKID);
        assertThat(testCity_town_village.getCity_town_village_name()).isEqualTo(UPDATED_CITY_TOWN_VILLAGE_NAME);
        assertThat(testCity_town_village.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingCity_town_village() throws Exception {
        int databaseSizeBeforeUpdate = city_town_villageRepository.findAll().size();

        // Create the City_town_village
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCity_town_villageMockMvc.perform(put("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isCreated());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);
        int databaseSizeBeforeDelete = city_town_villageRepository.findAll().size();

        // Get the city_town_village
        restCity_town_villageMockMvc.perform(delete("/api/city-town-villages/{id}", city_town_village.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(City_town_village.class);
    }
}
