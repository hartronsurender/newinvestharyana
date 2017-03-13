package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Foreignfundingresource;
import com.hartron.investharyana.repository.ForeignfundingresourceRepository;
import com.hartron.investharyana.service.ForeignfundingresourceService;
import com.hartron.investharyana.service.dto.ForeignfundingresourceDTO;
import com.hartron.investharyana.service.mapper.ForeignfundingresourceMapper;
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
 * Test class for the ForeignfundingresourceResource REST controller.
 *
 * @see ForeignfundingresourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ForeignfundingresourceResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_FOREIGN_FUNDING_RESOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FOREIGN_FUNDING_RESOURCE_TYPE = "BBBBBBBBBB";

    @Autowired
    private ForeignfundingresourceRepository foreignfundingresourceRepository;

    @Autowired
    private ForeignfundingresourceMapper foreignfundingresourceMapper;

    @Autowired
    private ForeignfundingresourceService foreignfundingresourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restForeignfundingresourceMockMvc;

    private Foreignfundingresource foreignfundingresource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ForeignfundingresourceResource foreignfundingresourceResource = new ForeignfundingresourceResource(foreignfundingresourceService);
        this.restForeignfundingresourceMockMvc = MockMvcBuilders.standaloneSetup(foreignfundingresourceResource)
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
    public static Foreignfundingresource createEntity() {
        Foreignfundingresource foreignfundingresource = new Foreignfundingresource()
                .foreign_funding_resource_type(DEFAULT_FOREIGN_FUNDING_RESOURCE_TYPE);
        return foreignfundingresource;
    }

    @Before
    public void initTest() {
        foreignfundingresourceRepository.deleteAll();
        foreignfundingresource = createEntity();
    }

    @Test
    public void createForeignfundingresource() throws Exception {
        int databaseSizeBeforeCreate = foreignfundingresourceRepository.findAll().size();

        // Create the Foreignfundingresource
        ForeignfundingresourceDTO foreignfundingresourceDTO = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(foreignfundingresource);

        restForeignfundingresourceMockMvc.perform(post("/api/foreignfundingresources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foreignfundingresourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Foreignfundingresource in the database
        List<Foreignfundingresource> foreignfundingresourceList = foreignfundingresourceRepository.findAll();
        assertThat(foreignfundingresourceList).hasSize(databaseSizeBeforeCreate + 1);
        Foreignfundingresource testForeignfundingresource = foreignfundingresourceList.get(foreignfundingresourceList.size() - 1);
        assertThat(testForeignfundingresource.getForeign_funding_resource_type()).isEqualTo(DEFAULT_FOREIGN_FUNDING_RESOURCE_TYPE);
    }

    @Test
    public void createForeignfundingresourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foreignfundingresourceRepository.findAll().size();

        // Create the Foreignfundingresource with an existing ID
        Foreignfundingresource existingForeignfundingresource = new Foreignfundingresource();
        existingForeignfundingresource.setId(UUID.randomUUID());
        ForeignfundingresourceDTO existingForeignfundingresourceDTO = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(existingForeignfundingresource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restForeignfundingresourceMockMvc.perform(post("/api/foreignfundingresources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingForeignfundingresourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Foreignfundingresource> foreignfundingresourceList = foreignfundingresourceRepository.findAll();
        assertThat(foreignfundingresourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllForeignfundingresources() throws Exception {
        // Initialize the database
        foreignfundingresourceRepository.save(foreignfundingresource);

        // Get all the foreignfundingresourceList
        restForeignfundingresourceMockMvc.perform(get("/api/foreignfundingresources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foreignfundingresource.getId().toString())))
            .andExpect(jsonPath("$.[*].foreign_funding_resource_type").value(hasItem(DEFAULT_FOREIGN_FUNDING_RESOURCE_TYPE.toString())));
    }

    @Test
    public void getForeignfundingresource() throws Exception {
        // Initialize the database
        foreignfundingresourceRepository.save(foreignfundingresource);

        // Get the foreignfundingresource
        restForeignfundingresourceMockMvc.perform(get("/api/foreignfundingresources/{id}", foreignfundingresource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(foreignfundingresource.getId().toString()))
            .andExpect(jsonPath("$.foreign_funding_resource_type").value(DEFAULT_FOREIGN_FUNDING_RESOURCE_TYPE.toString()));
    }

    @Test
    public void getNonExistingForeignfundingresource() throws Exception {
        // Get the foreignfundingresource
        restForeignfundingresourceMockMvc.perform(get("/api/foreignfundingresources/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateForeignfundingresource() throws Exception {
        // Initialize the database
        foreignfundingresourceRepository.save(foreignfundingresource);
        int databaseSizeBeforeUpdate = foreignfundingresourceRepository.findAll().size();

        // Update the foreignfundingresource
        Foreignfundingresource updatedForeignfundingresource = foreignfundingresourceRepository.findOne(foreignfundingresource.getId());
        updatedForeignfundingresource
                .foreign_funding_resource_type(UPDATED_FOREIGN_FUNDING_RESOURCE_TYPE);
        ForeignfundingresourceDTO foreignfundingresourceDTO = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(updatedForeignfundingresource);

        restForeignfundingresourceMockMvc.perform(put("/api/foreignfundingresources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foreignfundingresourceDTO)))
            .andExpect(status().isOk());

        // Validate the Foreignfundingresource in the database
        List<Foreignfundingresource> foreignfundingresourceList = foreignfundingresourceRepository.findAll();
        assertThat(foreignfundingresourceList).hasSize(databaseSizeBeforeUpdate);
        Foreignfundingresource testForeignfundingresource = foreignfundingresourceList.get(foreignfundingresourceList.size() - 1);
        assertThat(testForeignfundingresource.getForeign_funding_resource_type()).isEqualTo(UPDATED_FOREIGN_FUNDING_RESOURCE_TYPE);
    }

    @Test
    public void updateNonExistingForeignfundingresource() throws Exception {
        int databaseSizeBeforeUpdate = foreignfundingresourceRepository.findAll().size();

        // Create the Foreignfundingresource
        ForeignfundingresourceDTO foreignfundingresourceDTO = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(foreignfundingresource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restForeignfundingresourceMockMvc.perform(put("/api/foreignfundingresources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foreignfundingresourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Foreignfundingresource in the database
        List<Foreignfundingresource> foreignfundingresourceList = foreignfundingresourceRepository.findAll();
        assertThat(foreignfundingresourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteForeignfundingresource() throws Exception {
        // Initialize the database
        foreignfundingresourceRepository.save(foreignfundingresource);
        int databaseSizeBeforeDelete = foreignfundingresourceRepository.findAll().size();

        // Get the foreignfundingresource
        restForeignfundingresourceMockMvc.perform(delete("/api/foreignfundingresources/{id}", foreignfundingresource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Foreignfundingresource> foreignfundingresourceList = foreignfundingresourceRepository.findAll();
        assertThat(foreignfundingresourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Foreignfundingresource.class);
    }
}
