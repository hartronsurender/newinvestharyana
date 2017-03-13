package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Connectingroad;
import com.hartron.investharyana.repository.ConnectingroadRepository;
import com.hartron.investharyana.service.ConnectingroadService;
import com.hartron.investharyana.service.dto.ConnectingroadDTO;
import com.hartron.investharyana.service.mapper.ConnectingroadMapper;
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
 * Test class for the ConnectingroadResource REST controller.
 *
 * @see ConnectingroadResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ConnectingroadResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_CONNECTINGROADTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONNECTINGROADTYPE = "BBBBBBBBBB";

    @Autowired
    private ConnectingroadRepository connectingroadRepository;

    @Autowired
    private ConnectingroadMapper connectingroadMapper;

    @Autowired
    private ConnectingroadService connectingroadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restConnectingroadMockMvc;

    private Connectingroad connectingroad;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConnectingroadResource connectingroadResource = new ConnectingroadResource(connectingroadService);
        this.restConnectingroadMockMvc = MockMvcBuilders.standaloneSetup(connectingroadResource)
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
    public static Connectingroad createEntity() {
        Connectingroad connectingroad = new Connectingroad()
                .connectingroadtype(DEFAULT_CONNECTINGROADTYPE);
        return connectingroad;
    }

    @Before
    public void initTest() {
        connectingroadRepository.deleteAll();
        connectingroad = createEntity();
    }

    @Test
    public void createConnectingroad() throws Exception {
        int databaseSizeBeforeCreate = connectingroadRepository.findAll().size();

        // Create the Connectingroad
        ConnectingroadDTO connectingroadDTO = connectingroadMapper.connectingroadToConnectingroadDTO(connectingroad);

        restConnectingroadMockMvc.perform(post("/api/connectingroads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(connectingroadDTO)))
            .andExpect(status().isCreated());

        // Validate the Connectingroad in the database
        List<Connectingroad> connectingroadList = connectingroadRepository.findAll();
        assertThat(connectingroadList).hasSize(databaseSizeBeforeCreate + 1);
        Connectingroad testConnectingroad = connectingroadList.get(connectingroadList.size() - 1);
        assertThat(testConnectingroad.getConnectingroadtype()).isEqualTo(DEFAULT_CONNECTINGROADTYPE);
    }

    @Test
    public void createConnectingroadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = connectingroadRepository.findAll().size();

        // Create the Connectingroad with an existing ID
        Connectingroad existingConnectingroad = new Connectingroad();
        existingConnectingroad.setId(UUID.randomUUID());
        ConnectingroadDTO existingConnectingroadDTO = connectingroadMapper.connectingroadToConnectingroadDTO(existingConnectingroad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConnectingroadMockMvc.perform(post("/api/connectingroads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingConnectingroadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Connectingroad> connectingroadList = connectingroadRepository.findAll();
        assertThat(connectingroadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllConnectingroads() throws Exception {
        // Initialize the database
        connectingroadRepository.save(connectingroad);

        // Get all the connectingroadList
        restConnectingroadMockMvc.perform(get("/api/connectingroads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(connectingroad.getId().toString())))
            .andExpect(jsonPath("$.[*].connectingroadtype").value(hasItem(DEFAULT_CONNECTINGROADTYPE.toString())));
    }

    @Test
    public void getConnectingroad() throws Exception {
        // Initialize the database
        connectingroadRepository.save(connectingroad);

        // Get the connectingroad
        restConnectingroadMockMvc.perform(get("/api/connectingroads/{id}", connectingroad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(connectingroad.getId().toString()))
            .andExpect(jsonPath("$.connectingroadtype").value(DEFAULT_CONNECTINGROADTYPE.toString()));
    }

    @Test
    public void getNonExistingConnectingroad() throws Exception {
        // Get the connectingroad
        restConnectingroadMockMvc.perform(get("/api/connectingroads/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateConnectingroad() throws Exception {
        // Initialize the database
        connectingroadRepository.save(connectingroad);
        int databaseSizeBeforeUpdate = connectingroadRepository.findAll().size();

        // Update the connectingroad
        Connectingroad updatedConnectingroad = connectingroadRepository.findOne(connectingroad.getId());
        updatedConnectingroad
                .connectingroadtype(UPDATED_CONNECTINGROADTYPE);
        ConnectingroadDTO connectingroadDTO = connectingroadMapper.connectingroadToConnectingroadDTO(updatedConnectingroad);

        restConnectingroadMockMvc.perform(put("/api/connectingroads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(connectingroadDTO)))
            .andExpect(status().isOk());

        // Validate the Connectingroad in the database
        List<Connectingroad> connectingroadList = connectingroadRepository.findAll();
        assertThat(connectingroadList).hasSize(databaseSizeBeforeUpdate);
        Connectingroad testConnectingroad = connectingroadList.get(connectingroadList.size() - 1);
        assertThat(testConnectingroad.getConnectingroadtype()).isEqualTo(UPDATED_CONNECTINGROADTYPE);
    }

    @Test
    public void updateNonExistingConnectingroad() throws Exception {
        int databaseSizeBeforeUpdate = connectingroadRepository.findAll().size();

        // Create the Connectingroad
        ConnectingroadDTO connectingroadDTO = connectingroadMapper.connectingroadToConnectingroadDTO(connectingroad);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConnectingroadMockMvc.perform(put("/api/connectingroads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(connectingroadDTO)))
            .andExpect(status().isCreated());

        // Validate the Connectingroad in the database
        List<Connectingroad> connectingroadList = connectingroadRepository.findAll();
        assertThat(connectingroadList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteConnectingroad() throws Exception {
        // Initialize the database
        connectingroadRepository.save(connectingroad);
        int databaseSizeBeforeDelete = connectingroadRepository.findAll().size();

        // Get the connectingroad
        restConnectingroadMockMvc.perform(delete("/api/connectingroads/{id}", connectingroad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Connectingroad> connectingroadList = connectingroadRepository.findAll();
        assertThat(connectingroadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Connectingroad.class);
    }
}
