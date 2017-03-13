package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Waste_water_detail;
import com.hartron.investharyana.repository.Waste_water_detailRepository;
import com.hartron.investharyana.service.Waste_water_detailService;
import com.hartron.investharyana.service.dto.Waste_water_detailDTO;
import com.hartron.investharyana.service.mapper.Waste_water_detailMapper;
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
 * Test class for the Waste_water_detailResource REST controller.
 *
 * @see Waste_water_detailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Waste_water_detailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_SOURCE_OF_GENERATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_OF_GENERATION = "BBBBBBBBBB";

    private static final UUID DEFAULT_NATURE_TYPE = UUID.randomUUID();
    private static final UUID UPDATED_NATURE_TYPE = UUID.randomUUID();

    private static final String DEFAULT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY = "BBBBBBBBBB";

    private static final UUID DEFAULT_MODE_OF_DISPOSAL = UUID.randomUUID();
    private static final UUID UPDATED_MODE_OF_DISPOSAL = UUID.randomUUID();

    @Autowired
    private Waste_water_detailRepository waste_water_detailRepository;

    @Autowired
    private Waste_water_detailMapper waste_water_detailMapper;

    @Autowired
    private Waste_water_detailService waste_water_detailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWaste_water_detailMockMvc;

    private Waste_water_detail waste_water_detail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Waste_water_detailResource waste_water_detailResource = new Waste_water_detailResource(waste_water_detailService);
        this.restWaste_water_detailMockMvc = MockMvcBuilders.standaloneSetup(waste_water_detailResource)
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
    public static Waste_water_detail createEntity() {
        Waste_water_detail waste_water_detail = new Waste_water_detail()
                .projectid(DEFAULT_PROJECTID)
                .source_of_generation(DEFAULT_SOURCE_OF_GENERATION)
                .nature_type(DEFAULT_NATURE_TYPE)
                .quantity(DEFAULT_QUANTITY)
                .mode_of_disposal(DEFAULT_MODE_OF_DISPOSAL);
        return waste_water_detail;
    }

    @Before
    public void initTest() {
        waste_water_detailRepository.deleteAll();
        waste_water_detail = createEntity();
    }

    @Test
    public void createWaste_water_detail() throws Exception {
        int databaseSizeBeforeCreate = waste_water_detailRepository.findAll().size();

        // Create the Waste_water_detail
        Waste_water_detailDTO waste_water_detailDTO = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(waste_water_detail);

        restWaste_water_detailMockMvc.perform(post("/api/waste-water-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_detail in the database
        List<Waste_water_detail> waste_water_detailList = waste_water_detailRepository.findAll();
        assertThat(waste_water_detailList).hasSize(databaseSizeBeforeCreate + 1);
        Waste_water_detail testWaste_water_detail = waste_water_detailList.get(waste_water_detailList.size() - 1);
        assertThat(testWaste_water_detail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testWaste_water_detail.getSource_of_generation()).isEqualTo(DEFAULT_SOURCE_OF_GENERATION);
        assertThat(testWaste_water_detail.getNature_type()).isEqualTo(DEFAULT_NATURE_TYPE);
        assertThat(testWaste_water_detail.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testWaste_water_detail.getMode_of_disposal()).isEqualTo(DEFAULT_MODE_OF_DISPOSAL);
    }

    @Test
    public void createWaste_water_detailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waste_water_detailRepository.findAll().size();

        // Create the Waste_water_detail with an existing ID
        Waste_water_detail existingWaste_water_detail = new Waste_water_detail();
        existingWaste_water_detail.setId(UUID.randomUUID());
        Waste_water_detailDTO existingWaste_water_detailDTO = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(existingWaste_water_detail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaste_water_detailMockMvc.perform(post("/api/waste-water-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWaste_water_detailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Waste_water_detail> waste_water_detailList = waste_water_detailRepository.findAll();
        assertThat(waste_water_detailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWaste_water_details() throws Exception {
        // Initialize the database
        waste_water_detailRepository.save(waste_water_detail);

        // Get all the waste_water_detailList
        restWaste_water_detailMockMvc.perform(get("/api/waste-water-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waste_water_detail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].source_of_generation").value(hasItem(DEFAULT_SOURCE_OF_GENERATION.toString())))
            .andExpect(jsonPath("$.[*].nature_type").value(hasItem(DEFAULT_NATURE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.toString())))
            .andExpect(jsonPath("$.[*].mode_of_disposal").value(hasItem(DEFAULT_MODE_OF_DISPOSAL.toString())));
    }

    @Test
    public void getWaste_water_detail() throws Exception {
        // Initialize the database
        waste_water_detailRepository.save(waste_water_detail);

        // Get the waste_water_detail
        restWaste_water_detailMockMvc.perform(get("/api/waste-water-details/{id}", waste_water_detail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(waste_water_detail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.source_of_generation").value(DEFAULT_SOURCE_OF_GENERATION.toString()))
            .andExpect(jsonPath("$.nature_type").value(DEFAULT_NATURE_TYPE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.toString()))
            .andExpect(jsonPath("$.mode_of_disposal").value(DEFAULT_MODE_OF_DISPOSAL.toString()));
    }

    @Test
    public void getNonExistingWaste_water_detail() throws Exception {
        // Get the waste_water_detail
        restWaste_water_detailMockMvc.perform(get("/api/waste-water-details/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWaste_water_detail() throws Exception {
        // Initialize the database
        waste_water_detailRepository.save(waste_water_detail);
        int databaseSizeBeforeUpdate = waste_water_detailRepository.findAll().size();

        // Update the waste_water_detail
        Waste_water_detail updatedWaste_water_detail = waste_water_detailRepository.findOne(waste_water_detail.getId());
        updatedWaste_water_detail
                .projectid(UPDATED_PROJECTID)
                .source_of_generation(UPDATED_SOURCE_OF_GENERATION)
                .nature_type(UPDATED_NATURE_TYPE)
                .quantity(UPDATED_QUANTITY)
                .mode_of_disposal(UPDATED_MODE_OF_DISPOSAL);
        Waste_water_detailDTO waste_water_detailDTO = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(updatedWaste_water_detail);

        restWaste_water_detailMockMvc.perform(put("/api/waste-water-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_detailDTO)))
            .andExpect(status().isOk());

        // Validate the Waste_water_detail in the database
        List<Waste_water_detail> waste_water_detailList = waste_water_detailRepository.findAll();
        assertThat(waste_water_detailList).hasSize(databaseSizeBeforeUpdate);
        Waste_water_detail testWaste_water_detail = waste_water_detailList.get(waste_water_detailList.size() - 1);
        assertThat(testWaste_water_detail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testWaste_water_detail.getSource_of_generation()).isEqualTo(UPDATED_SOURCE_OF_GENERATION);
        assertThat(testWaste_water_detail.getNature_type()).isEqualTo(UPDATED_NATURE_TYPE);
        assertThat(testWaste_water_detail.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testWaste_water_detail.getMode_of_disposal()).isEqualTo(UPDATED_MODE_OF_DISPOSAL);
    }

    @Test
    public void updateNonExistingWaste_water_detail() throws Exception {
        int databaseSizeBeforeUpdate = waste_water_detailRepository.findAll().size();

        // Create the Waste_water_detail
        Waste_water_detailDTO waste_water_detailDTO = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(waste_water_detail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWaste_water_detailMockMvc.perform(put("/api/waste-water-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_detailDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_detail in the database
        List<Waste_water_detail> waste_water_detailList = waste_water_detailRepository.findAll();
        assertThat(waste_water_detailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWaste_water_detail() throws Exception {
        // Initialize the database
        waste_water_detailRepository.save(waste_water_detail);
        int databaseSizeBeforeDelete = waste_water_detailRepository.findAll().size();

        // Get the waste_water_detail
        restWaste_water_detailMockMvc.perform(delete("/api/waste-water-details/{id}", waste_water_detail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Waste_water_detail> waste_water_detailList = waste_water_detailRepository.findAll();
        assertThat(waste_water_detailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Waste_water_detail.class);
    }
}
