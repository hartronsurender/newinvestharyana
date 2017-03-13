package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Electric_load_type;
import com.hartron.investharyana.repository.Electric_load_typeRepository;
import com.hartron.investharyana.service.Electric_load_typeService;
import com.hartron.investharyana.service.dto.Electric_load_typeDTO;
import com.hartron.investharyana.service.mapper.Electric_load_typeMapper;
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
 * Test class for the Electric_load_typeResource REST controller.
 *
 * @see Electric_load_typeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Electric_load_typeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private Electric_load_typeRepository electric_load_typeRepository;

    @Autowired
    private Electric_load_typeMapper electric_load_typeMapper;

    @Autowired
    private Electric_load_typeService electric_load_typeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restElectric_load_typeMockMvc;

    private Electric_load_type electric_load_type;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Electric_load_typeResource electric_load_typeResource = new Electric_load_typeResource(electric_load_typeService);
        this.restElectric_load_typeMockMvc = MockMvcBuilders.standaloneSetup(electric_load_typeResource)
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
    public static Electric_load_type createEntity() {
        Electric_load_type electric_load_type = new Electric_load_type()
                .regular_uhbvn_dhbvn_customer_type_name(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME);
        return electric_load_type;
    }

    @Before
    public void initTest() {
        electric_load_typeRepository.deleteAll();
        electric_load_type = createEntity();
    }

    @Test
    public void createElectric_load_type() throws Exception {
        int databaseSizeBeforeCreate = electric_load_typeRepository.findAll().size();

        // Create the Electric_load_type
        Electric_load_typeDTO electric_load_typeDTO = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(electric_load_type);

        restElectric_load_typeMockMvc.perform(post("/api/electric-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electric_load_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Electric_load_type in the database
        List<Electric_load_type> electric_load_typeList = electric_load_typeRepository.findAll();
        assertThat(electric_load_typeList).hasSize(databaseSizeBeforeCreate + 1);
        Electric_load_type testElectric_load_type = electric_load_typeList.get(electric_load_typeList.size() - 1);
        assertThat(testElectric_load_type.getRegular_uhbvn_dhbvn_customer_type_name()).isEqualTo(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME);
    }

    @Test
    public void createElectric_load_typeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = electric_load_typeRepository.findAll().size();

        // Create the Electric_load_type with an existing ID
        Electric_load_type existingElectric_load_type = new Electric_load_type();
        existingElectric_load_type.setId(UUID.randomUUID());
        Electric_load_typeDTO existingElectric_load_typeDTO = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(existingElectric_load_type);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElectric_load_typeMockMvc.perform(post("/api/electric-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingElectric_load_typeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Electric_load_type> electric_load_typeList = electric_load_typeRepository.findAll();
        assertThat(electric_load_typeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllElectric_load_types() throws Exception {
        // Initialize the database
        electric_load_typeRepository.save(electric_load_type);

        // Get all the electric_load_typeList
        restElectric_load_typeMockMvc.perform(get("/api/electric-load-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(electric_load_type.getId().toString())))
            .andExpect(jsonPath("$.[*].regular_uhbvn_dhbvn_customer_type_name").value(hasItem(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME.toString())));
    }

    @Test
    public void getElectric_load_type() throws Exception {
        // Initialize the database
        electric_load_typeRepository.save(electric_load_type);

        // Get the electric_load_type
        restElectric_load_typeMockMvc.perform(get("/api/electric-load-types/{id}", electric_load_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(electric_load_type.getId().toString()))
            .andExpect(jsonPath("$.regular_uhbvn_dhbvn_customer_type_name").value(DEFAULT_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME.toString()));
    }

    @Test
    public void getNonExistingElectric_load_type() throws Exception {
        // Get the electric_load_type
        restElectric_load_typeMockMvc.perform(get("/api/electric-load-types/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateElectric_load_type() throws Exception {
        // Initialize the database
        electric_load_typeRepository.save(electric_load_type);
        int databaseSizeBeforeUpdate = electric_load_typeRepository.findAll().size();

        // Update the electric_load_type
        Electric_load_type updatedElectric_load_type = electric_load_typeRepository.findOne(electric_load_type.getId());
        updatedElectric_load_type
                .regular_uhbvn_dhbvn_customer_type_name(UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME);
        Electric_load_typeDTO electric_load_typeDTO = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(updatedElectric_load_type);

        restElectric_load_typeMockMvc.perform(put("/api/electric-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electric_load_typeDTO)))
            .andExpect(status().isOk());

        // Validate the Electric_load_type in the database
        List<Electric_load_type> electric_load_typeList = electric_load_typeRepository.findAll();
        assertThat(electric_load_typeList).hasSize(databaseSizeBeforeUpdate);
        Electric_load_type testElectric_load_type = electric_load_typeList.get(electric_load_typeList.size() - 1);
        assertThat(testElectric_load_type.getRegular_uhbvn_dhbvn_customer_type_name()).isEqualTo(UPDATED_REGULAR_UHBVN_DHBVN_CUSTOMER_TYPE_NAME);
    }

    @Test
    public void updateNonExistingElectric_load_type() throws Exception {
        int databaseSizeBeforeUpdate = electric_load_typeRepository.findAll().size();

        // Create the Electric_load_type
        Electric_load_typeDTO electric_load_typeDTO = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(electric_load_type);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restElectric_load_typeMockMvc.perform(put("/api/electric-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electric_load_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Electric_load_type in the database
        List<Electric_load_type> electric_load_typeList = electric_load_typeRepository.findAll();
        assertThat(electric_load_typeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteElectric_load_type() throws Exception {
        // Initialize the database
        electric_load_typeRepository.save(electric_load_type);
        int databaseSizeBeforeDelete = electric_load_typeRepository.findAll().size();

        // Get the electric_load_type
        restElectric_load_typeMockMvc.perform(delete("/api/electric-load-types/{id}", electric_load_type.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Electric_load_type> electric_load_typeList = electric_load_typeRepository.findAll();
        assertThat(electric_load_typeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Electric_load_type.class);
    }
}
