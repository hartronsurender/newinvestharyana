package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emissiondetail;
import com.hartron.investharyana.repository.EmissiondetailRepository;
import com.hartron.investharyana.service.EmissiondetailService;
import com.hartron.investharyana.service.dto.EmissiondetailDTO;
import com.hartron.investharyana.service.mapper.EmissiondetailMapper;
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
 * Test class for the EmissiondetailResource REST controller.
 *
 * @see EmissiondetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class EmissiondetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_PARTICULARS = UUID.randomUUID();
    private static final UUID UPDATED_PARTICULARS = UUID.randomUUID();

    private static final String DEFAULT_CAPACITY = "AAAAAAAAAA";
    private static final String UPDATED_CAPACITY = "BBBBBBBBBB";

    private static final UUID DEFAULT_TYPE_OF_FUEL = UUID.randomUUID();
    private static final UUID UPDATED_TYPE_OF_FUEL = UUID.randomUUID();

    private static final UUID DEFAULT_AIR_POLLUTION_CONTROL_DEVICE = UUID.randomUUID();
    private static final UUID UPDATED_AIR_POLLUTION_CONTROL_DEVICE = UUID.randomUUID();

    @Autowired
    private EmissiondetailRepository emissiondetailRepository;

    @Autowired
    private EmissiondetailMapper emissiondetailMapper;

    @Autowired
    private EmissiondetailService emissiondetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmissiondetailMockMvc;

    private Emissiondetail emissiondetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmissiondetailResource emissiondetailResource = new EmissiondetailResource(emissiondetailService);
        this.restEmissiondetailMockMvc = MockMvcBuilders.standaloneSetup(emissiondetailResource)
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
    public static Emissiondetail createEntity() {
        Emissiondetail emissiondetail = new Emissiondetail()
                .projectid(DEFAULT_PROJECTID)
                .particulars(DEFAULT_PARTICULARS)
                .capacity(DEFAULT_CAPACITY)
                .type_of_fuel(DEFAULT_TYPE_OF_FUEL)
                .air_pollution_control_device(DEFAULT_AIR_POLLUTION_CONTROL_DEVICE);
        return emissiondetail;
    }

    @Before
    public void initTest() {
        emissiondetailRepository.deleteAll();
        emissiondetail = createEntity();
    }

    @Test
    public void createEmissiondetail() throws Exception {
        int databaseSizeBeforeCreate = emissiondetailRepository.findAll().size();

        // Create the Emissiondetail
        EmissiondetailDTO emissiondetailDTO = emissiondetailMapper.emissiondetailToEmissiondetailDTO(emissiondetail);

        restEmissiondetailMockMvc.perform(post("/api/emissiondetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emissiondetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Emissiondetail in the database
        List<Emissiondetail> emissiondetailList = emissiondetailRepository.findAll();
        assertThat(emissiondetailList).hasSize(databaseSizeBeforeCreate + 1);
        Emissiondetail testEmissiondetail = emissiondetailList.get(emissiondetailList.size() - 1);
        assertThat(testEmissiondetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testEmissiondetail.getParticulars()).isEqualTo(DEFAULT_PARTICULARS);
        assertThat(testEmissiondetail.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testEmissiondetail.getType_of_fuel()).isEqualTo(DEFAULT_TYPE_OF_FUEL);
        assertThat(testEmissiondetail.getAir_pollution_control_device()).isEqualTo(DEFAULT_AIR_POLLUTION_CONTROL_DEVICE);
    }

    @Test
    public void createEmissiondetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emissiondetailRepository.findAll().size();

        // Create the Emissiondetail with an existing ID
        Emissiondetail existingEmissiondetail = new Emissiondetail();
        existingEmissiondetail.setId(UUID.randomUUID());
        EmissiondetailDTO existingEmissiondetailDTO = emissiondetailMapper.emissiondetailToEmissiondetailDTO(existingEmissiondetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmissiondetailMockMvc.perform(post("/api/emissiondetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmissiondetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emissiondetail> emissiondetailList = emissiondetailRepository.findAll();
        assertThat(emissiondetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmissiondetails() throws Exception {
        // Initialize the database
        emissiondetailRepository.save(emissiondetail);

        // Get all the emissiondetailList
        restEmissiondetailMockMvc.perform(get("/api/emissiondetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emissiondetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].particulars").value(hasItem(DEFAULT_PARTICULARS.toString())))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.toString())))
            .andExpect(jsonPath("$.[*].type_of_fuel").value(hasItem(DEFAULT_TYPE_OF_FUEL.toString())))
            .andExpect(jsonPath("$.[*].air_pollution_control_device").value(hasItem(DEFAULT_AIR_POLLUTION_CONTROL_DEVICE.toString())));
    }

    @Test
    public void getEmissiondetail() throws Exception {
        // Initialize the database
        emissiondetailRepository.save(emissiondetail);

        // Get the emissiondetail
        restEmissiondetailMockMvc.perform(get("/api/emissiondetails/{id}", emissiondetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emissiondetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.particulars").value(DEFAULT_PARTICULARS.toString()))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY.toString()))
            .andExpect(jsonPath("$.type_of_fuel").value(DEFAULT_TYPE_OF_FUEL.toString()))
            .andExpect(jsonPath("$.air_pollution_control_device").value(DEFAULT_AIR_POLLUTION_CONTROL_DEVICE.toString()));
    }

    @Test
    public void getNonExistingEmissiondetail() throws Exception {
        // Get the emissiondetail
        restEmissiondetailMockMvc.perform(get("/api/emissiondetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmissiondetail() throws Exception {
        // Initialize the database
        emissiondetailRepository.save(emissiondetail);
        int databaseSizeBeforeUpdate = emissiondetailRepository.findAll().size();

        // Update the emissiondetail
        Emissiondetail updatedEmissiondetail = emissiondetailRepository.findOne(emissiondetail.getId());
        updatedEmissiondetail
                .projectid(UPDATED_PROJECTID)
                .particulars(UPDATED_PARTICULARS)
                .capacity(UPDATED_CAPACITY)
                .type_of_fuel(UPDATED_TYPE_OF_FUEL)
                .air_pollution_control_device(UPDATED_AIR_POLLUTION_CONTROL_DEVICE);
        EmissiondetailDTO emissiondetailDTO = emissiondetailMapper.emissiondetailToEmissiondetailDTO(updatedEmissiondetail);

        restEmissiondetailMockMvc.perform(put("/api/emissiondetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emissiondetailDTO)))
            .andExpect(status().isOk());

        // Validate the Emissiondetail in the database
        List<Emissiondetail> emissiondetailList = emissiondetailRepository.findAll();
        assertThat(emissiondetailList).hasSize(databaseSizeBeforeUpdate);
        Emissiondetail testEmissiondetail = emissiondetailList.get(emissiondetailList.size() - 1);
        assertThat(testEmissiondetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testEmissiondetail.getParticulars()).isEqualTo(UPDATED_PARTICULARS);
        assertThat(testEmissiondetail.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testEmissiondetail.getType_of_fuel()).isEqualTo(UPDATED_TYPE_OF_FUEL);
        assertThat(testEmissiondetail.getAir_pollution_control_device()).isEqualTo(UPDATED_AIR_POLLUTION_CONTROL_DEVICE);
    }

    @Test
    public void updateNonExistingEmissiondetail() throws Exception {
        int databaseSizeBeforeUpdate = emissiondetailRepository.findAll().size();

        // Create the Emissiondetail
        EmissiondetailDTO emissiondetailDTO = emissiondetailMapper.emissiondetailToEmissiondetailDTO(emissiondetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmissiondetailMockMvc.perform(put("/api/emissiondetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emissiondetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Emissiondetail in the database
        List<Emissiondetail> emissiondetailList = emissiondetailRepository.findAll();
        assertThat(emissiondetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmissiondetail() throws Exception {
        // Initialize the database
        emissiondetailRepository.save(emissiondetail);
        int databaseSizeBeforeDelete = emissiondetailRepository.findAll().size();

        // Get the emissiondetail
        restEmissiondetailMockMvc.perform(delete("/api/emissiondetails/{id}", emissiondetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emissiondetail> emissiondetailList = emissiondetailRepository.findAll();
        assertThat(emissiondetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emissiondetail.class);
    }
}
