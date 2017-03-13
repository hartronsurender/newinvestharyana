package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Disposal_for_discharge;
import com.hartron.investharyana.repository.Disposal_for_dischargeRepository;
import com.hartron.investharyana.service.Disposal_for_dischargeService;
import com.hartron.investharyana.service.dto.Disposal_for_dischargeDTO;
import com.hartron.investharyana.service.mapper.Disposal_for_dischargeMapper;
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
 * Test class for the Disposal_for_dischargeResource REST controller.
 *
 * @see Disposal_for_dischargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Disposal_for_dischargeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DISPOSAL_FOR_DISCHARGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DISPOSAL_FOR_DISCHARGE_TYPE = "BBBBBBBBBB";

    @Autowired
    private Disposal_for_dischargeRepository disposal_for_dischargeRepository;

    @Autowired
    private Disposal_for_dischargeMapper disposal_for_dischargeMapper;

    @Autowired
    private Disposal_for_dischargeService disposal_for_dischargeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDisposal_for_dischargeMockMvc;

    private Disposal_for_discharge disposal_for_discharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Disposal_for_dischargeResource disposal_for_dischargeResource = new Disposal_for_dischargeResource(disposal_for_dischargeService);
        this.restDisposal_for_dischargeMockMvc = MockMvcBuilders.standaloneSetup(disposal_for_dischargeResource)
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
    public static Disposal_for_discharge createEntity() {
        Disposal_for_discharge disposal_for_discharge = new Disposal_for_discharge()
                .disposal_for_discharge_type(DEFAULT_DISPOSAL_FOR_DISCHARGE_TYPE);
        return disposal_for_discharge;
    }

    @Before
    public void initTest() {
        disposal_for_dischargeRepository.deleteAll();
        disposal_for_discharge = createEntity();
    }

    @Test
    public void createDisposal_for_discharge() throws Exception {
        int databaseSizeBeforeCreate = disposal_for_dischargeRepository.findAll().size();

        // Create the Disposal_for_discharge
        Disposal_for_dischargeDTO disposal_for_dischargeDTO = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(disposal_for_discharge);

        restDisposal_for_dischargeMockMvc.perform(post("/api/disposal-for-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disposal_for_dischargeDTO)))
            .andExpect(status().isCreated());

        // Validate the Disposal_for_discharge in the database
        List<Disposal_for_discharge> disposal_for_dischargeList = disposal_for_dischargeRepository.findAll();
        assertThat(disposal_for_dischargeList).hasSize(databaseSizeBeforeCreate + 1);
        Disposal_for_discharge testDisposal_for_discharge = disposal_for_dischargeList.get(disposal_for_dischargeList.size() - 1);
        assertThat(testDisposal_for_discharge.getDisposal_for_discharge_type()).isEqualTo(DEFAULT_DISPOSAL_FOR_DISCHARGE_TYPE);
    }

    @Test
    public void createDisposal_for_dischargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disposal_for_dischargeRepository.findAll().size();

        // Create the Disposal_for_discharge with an existing ID
        Disposal_for_discharge existingDisposal_for_discharge = new Disposal_for_discharge();
        existingDisposal_for_discharge.setId(UUID.randomUUID());
        Disposal_for_dischargeDTO existingDisposal_for_dischargeDTO = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(existingDisposal_for_discharge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisposal_for_dischargeMockMvc.perform(post("/api/disposal-for-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDisposal_for_dischargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Disposal_for_discharge> disposal_for_dischargeList = disposal_for_dischargeRepository.findAll();
        assertThat(disposal_for_dischargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDisposal_for_discharges() throws Exception {
        // Initialize the database
        disposal_for_dischargeRepository.save(disposal_for_discharge);

        // Get all the disposal_for_dischargeList
        restDisposal_for_dischargeMockMvc.perform(get("/api/disposal-for-discharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disposal_for_discharge.getId().toString())))
            .andExpect(jsonPath("$.[*].disposal_for_discharge_type").value(hasItem(DEFAULT_DISPOSAL_FOR_DISCHARGE_TYPE.toString())));
    }

    @Test
    public void getDisposal_for_discharge() throws Exception {
        // Initialize the database
        disposal_for_dischargeRepository.save(disposal_for_discharge);

        // Get the disposal_for_discharge
        restDisposal_for_dischargeMockMvc.perform(get("/api/disposal-for-discharges/{id}", disposal_for_discharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disposal_for_discharge.getId().toString()))
            .andExpect(jsonPath("$.disposal_for_discharge_type").value(DEFAULT_DISPOSAL_FOR_DISCHARGE_TYPE.toString()));
    }

    @Test
    public void getNonExistingDisposal_for_discharge() throws Exception {
        // Get the disposal_for_discharge
        restDisposal_for_dischargeMockMvc.perform(get("/api/disposal-for-discharges/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDisposal_for_discharge() throws Exception {
        // Initialize the database
        disposal_for_dischargeRepository.save(disposal_for_discharge);
        int databaseSizeBeforeUpdate = disposal_for_dischargeRepository.findAll().size();

        // Update the disposal_for_discharge
        Disposal_for_discharge updatedDisposal_for_discharge = disposal_for_dischargeRepository.findOne(disposal_for_discharge.getId());
        updatedDisposal_for_discharge
                .disposal_for_discharge_type(UPDATED_DISPOSAL_FOR_DISCHARGE_TYPE);
        Disposal_for_dischargeDTO disposal_for_dischargeDTO = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(updatedDisposal_for_discharge);

        restDisposal_for_dischargeMockMvc.perform(put("/api/disposal-for-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disposal_for_dischargeDTO)))
            .andExpect(status().isOk());

        // Validate the Disposal_for_discharge in the database
        List<Disposal_for_discharge> disposal_for_dischargeList = disposal_for_dischargeRepository.findAll();
        assertThat(disposal_for_dischargeList).hasSize(databaseSizeBeforeUpdate);
        Disposal_for_discharge testDisposal_for_discharge = disposal_for_dischargeList.get(disposal_for_dischargeList.size() - 1);
        assertThat(testDisposal_for_discharge.getDisposal_for_discharge_type()).isEqualTo(UPDATED_DISPOSAL_FOR_DISCHARGE_TYPE);
    }

    @Test
    public void updateNonExistingDisposal_for_discharge() throws Exception {
        int databaseSizeBeforeUpdate = disposal_for_dischargeRepository.findAll().size();

        // Create the Disposal_for_discharge
        Disposal_for_dischargeDTO disposal_for_dischargeDTO = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(disposal_for_discharge);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisposal_for_dischargeMockMvc.perform(put("/api/disposal-for-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disposal_for_dischargeDTO)))
            .andExpect(status().isCreated());

        // Validate the Disposal_for_discharge in the database
        List<Disposal_for_discharge> disposal_for_dischargeList = disposal_for_dischargeRepository.findAll();
        assertThat(disposal_for_dischargeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDisposal_for_discharge() throws Exception {
        // Initialize the database
        disposal_for_dischargeRepository.save(disposal_for_discharge);
        int databaseSizeBeforeDelete = disposal_for_dischargeRepository.findAll().size();

        // Get the disposal_for_discharge
        restDisposal_for_dischargeMockMvc.perform(delete("/api/disposal-for-discharges/{id}", disposal_for_discharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Disposal_for_discharge> disposal_for_dischargeList = disposal_for_dischargeRepository.findAll();
        assertThat(disposal_for_dischargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disposal_for_discharge.class);
    }
}
