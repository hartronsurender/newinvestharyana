package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Tehsil_subtehsil;
import com.hartron.investharyana.repository.Tehsil_subtehsilRepository;
import com.hartron.investharyana.service.Tehsil_subtehsilService;
import com.hartron.investharyana.service.dto.Tehsil_subtehsilDTO;
import com.hartron.investharyana.service.mapper.Tehsil_subtehsilMapper;
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
 * Test class for the Tehsil_subtehsilResource REST controller.
 *
 * @see Tehsil_subtehsilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Tehsil_subtehsilResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_DISTRICTID = UUID.randomUUID();
    private static final UUID UPDATED_DISTRICTID = UUID.randomUUID();

    private static final UUID DEFAULT_BLOCKID = UUID.randomUUID();
    private static final UUID UPDATED_BLOCKID = UUID.randomUUID();

    private static final UUID DEFAULT_CITY_TOWN_VILLAGEID = UUID.randomUUID();
    private static final UUID UPDATED_CITY_TOWN_VILLAGEID = UUID.randomUUID();

    private static final String DEFAULT_TEHSILNAME = "AAAAAAAAAA";
    private static final String UPDATED_TEHSILNAME = "BBBBBBBBBB";

    @Autowired
    private Tehsil_subtehsilRepository tehsil_subtehsilRepository;

    @Autowired
    private Tehsil_subtehsilMapper tehsil_subtehsilMapper;

    @Autowired
    private Tehsil_subtehsilService tehsil_subtehsilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTehsil_subtehsilMockMvc;

    private Tehsil_subtehsil tehsil_subtehsil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Tehsil_subtehsilResource tehsil_subtehsilResource = new Tehsil_subtehsilResource(tehsil_subtehsilService);
        this.restTehsil_subtehsilMockMvc = MockMvcBuilders.standaloneSetup(tehsil_subtehsilResource)
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
    public static Tehsil_subtehsil createEntity() {
        Tehsil_subtehsil tehsil_subtehsil = new Tehsil_subtehsil()
                .districtid(DEFAULT_DISTRICTID)
                .blockid(DEFAULT_BLOCKID)
                .city_town_villageid(DEFAULT_CITY_TOWN_VILLAGEID)
                .tehsilname(DEFAULT_TEHSILNAME);
        return tehsil_subtehsil;
    }

    @Before
    public void initTest() {
        tehsil_subtehsilRepository.deleteAll();
        tehsil_subtehsil = createEntity();
    }

    @Test
    public void createTehsil_subtehsil() throws Exception {
        int databaseSizeBeforeCreate = tehsil_subtehsilRepository.findAll().size();

        // Create the Tehsil_subtehsil
        Tehsil_subtehsilDTO tehsil_subtehsilDTO = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(tehsil_subtehsil);

        restTehsil_subtehsilMockMvc.perform(post("/api/tehsil-subtehsils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tehsil_subtehsilDTO)))
            .andExpect(status().isCreated());

        // Validate the Tehsil_subtehsil in the database
        List<Tehsil_subtehsil> tehsil_subtehsilList = tehsil_subtehsilRepository.findAll();
        assertThat(tehsil_subtehsilList).hasSize(databaseSizeBeforeCreate + 1);
        Tehsil_subtehsil testTehsil_subtehsil = tehsil_subtehsilList.get(tehsil_subtehsilList.size() - 1);
        assertThat(testTehsil_subtehsil.getDistrictid()).isEqualTo(DEFAULT_DISTRICTID);
        assertThat(testTehsil_subtehsil.getBlockid()).isEqualTo(DEFAULT_BLOCKID);
        assertThat(testTehsil_subtehsil.getCity_town_villageid()).isEqualTo(DEFAULT_CITY_TOWN_VILLAGEID);
        assertThat(testTehsil_subtehsil.getTehsilname()).isEqualTo(DEFAULT_TEHSILNAME);
    }

    @Test
    public void createTehsil_subtehsilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tehsil_subtehsilRepository.findAll().size();

        // Create the Tehsil_subtehsil with an existing ID
        Tehsil_subtehsil existingTehsil_subtehsil = new Tehsil_subtehsil();
        existingTehsil_subtehsil.setId(UUID.randomUUID());
        Tehsil_subtehsilDTO existingTehsil_subtehsilDTO = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(existingTehsil_subtehsil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTehsil_subtehsilMockMvc.perform(post("/api/tehsil-subtehsils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingTehsil_subtehsilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Tehsil_subtehsil> tehsil_subtehsilList = tehsil_subtehsilRepository.findAll();
        assertThat(tehsil_subtehsilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTehsil_subtehsils() throws Exception {
        // Initialize the database
        tehsil_subtehsilRepository.save(tehsil_subtehsil);

        // Get all the tehsil_subtehsilList
        restTehsil_subtehsilMockMvc.perform(get("/api/tehsil-subtehsils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tehsil_subtehsil.getId().toString())))
            .andExpect(jsonPath("$.[*].districtid").value(hasItem(DEFAULT_DISTRICTID.toString())))
            .andExpect(jsonPath("$.[*].blockid").value(hasItem(DEFAULT_BLOCKID.toString())))
            .andExpect(jsonPath("$.[*].city_town_villageid").value(hasItem(DEFAULT_CITY_TOWN_VILLAGEID.toString())))
            .andExpect(jsonPath("$.[*].tehsilname").value(hasItem(DEFAULT_TEHSILNAME.toString())));
    }

    @Test
    public void getTehsil_subtehsil() throws Exception {
        // Initialize the database
        tehsil_subtehsilRepository.save(tehsil_subtehsil);

        // Get the tehsil_subtehsil
        restTehsil_subtehsilMockMvc.perform(get("/api/tehsil-subtehsils/{id}", tehsil_subtehsil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tehsil_subtehsil.getId().toString()))
            .andExpect(jsonPath("$.districtid").value(DEFAULT_DISTRICTID.toString()))
            .andExpect(jsonPath("$.blockid").value(DEFAULT_BLOCKID.toString()))
            .andExpect(jsonPath("$.city_town_villageid").value(DEFAULT_CITY_TOWN_VILLAGEID.toString()))
            .andExpect(jsonPath("$.tehsilname").value(DEFAULT_TEHSILNAME.toString()));
    }

    @Test
    public void getNonExistingTehsil_subtehsil() throws Exception {
        // Get the tehsil_subtehsil
        restTehsil_subtehsilMockMvc.perform(get("/api/tehsil-subtehsils/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTehsil_subtehsil() throws Exception {
        // Initialize the database
        tehsil_subtehsilRepository.save(tehsil_subtehsil);
        int databaseSizeBeforeUpdate = tehsil_subtehsilRepository.findAll().size();

        // Update the tehsil_subtehsil
        Tehsil_subtehsil updatedTehsil_subtehsil = tehsil_subtehsilRepository.findOne(tehsil_subtehsil.getId());
        updatedTehsil_subtehsil
                .districtid(UPDATED_DISTRICTID)
                .blockid(UPDATED_BLOCKID)
                .city_town_villageid(UPDATED_CITY_TOWN_VILLAGEID)
                .tehsilname(UPDATED_TEHSILNAME);
        Tehsil_subtehsilDTO tehsil_subtehsilDTO = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(updatedTehsil_subtehsil);

        restTehsil_subtehsilMockMvc.perform(put("/api/tehsil-subtehsils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tehsil_subtehsilDTO)))
            .andExpect(status().isOk());

        // Validate the Tehsil_subtehsil in the database
        List<Tehsil_subtehsil> tehsil_subtehsilList = tehsil_subtehsilRepository.findAll();
        assertThat(tehsil_subtehsilList).hasSize(databaseSizeBeforeUpdate);
        Tehsil_subtehsil testTehsil_subtehsil = tehsil_subtehsilList.get(tehsil_subtehsilList.size() - 1);
        assertThat(testTehsil_subtehsil.getDistrictid()).isEqualTo(UPDATED_DISTRICTID);
        assertThat(testTehsil_subtehsil.getBlockid()).isEqualTo(UPDATED_BLOCKID);
        assertThat(testTehsil_subtehsil.getCity_town_villageid()).isEqualTo(UPDATED_CITY_TOWN_VILLAGEID);
        assertThat(testTehsil_subtehsil.getTehsilname()).isEqualTo(UPDATED_TEHSILNAME);
    }

    @Test
    public void updateNonExistingTehsil_subtehsil() throws Exception {
        int databaseSizeBeforeUpdate = tehsil_subtehsilRepository.findAll().size();

        // Create the Tehsil_subtehsil
        Tehsil_subtehsilDTO tehsil_subtehsilDTO = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(tehsil_subtehsil);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTehsil_subtehsilMockMvc.perform(put("/api/tehsil-subtehsils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tehsil_subtehsilDTO)))
            .andExpect(status().isCreated());

        // Validate the Tehsil_subtehsil in the database
        List<Tehsil_subtehsil> tehsil_subtehsilList = tehsil_subtehsilRepository.findAll();
        assertThat(tehsil_subtehsilList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTehsil_subtehsil() throws Exception {
        // Initialize the database
        tehsil_subtehsilRepository.save(tehsil_subtehsil);
        int databaseSizeBeforeDelete = tehsil_subtehsilRepository.findAll().size();

        // Get the tehsil_subtehsil
        restTehsil_subtehsilMockMvc.perform(delete("/api/tehsil-subtehsils/{id}", tehsil_subtehsil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tehsil_subtehsil> tehsil_subtehsilList = tehsil_subtehsilRepository.findAll();
        assertThat(tehsil_subtehsilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tehsil_subtehsil.class);
    }
}
