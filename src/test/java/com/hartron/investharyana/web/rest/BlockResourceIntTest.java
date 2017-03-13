package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Block;
import com.hartron.investharyana.repository.BlockRepository;
import com.hartron.investharyana.service.BlockService;
import com.hartron.investharyana.service.dto.BlockDTO;
import com.hartron.investharyana.service.mapper.BlockMapper;
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
 * Test class for the BlockResource REST controller.
 *
 * @see BlockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class BlockResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_STATEID = UUID.randomUUID();
    private static final UUID UPDATED_STATEID = UUID.randomUUID();

    private static final UUID DEFAULT_DISTRICTID = UUID.randomUUID();
    private static final UUID UPDATED_DISTRICTID = UUID.randomUUID();

    private static final String DEFAULT_BLOCKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BLOCKNAME = "BBBBBBBBBB";

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BlockService blockService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBlockMockMvc;

    private Block block;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BlockResource blockResource = new BlockResource(blockService);
        this.restBlockMockMvc = MockMvcBuilders.standaloneSetup(blockResource)
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
    public static Block createEntity() {
        Block block = new Block()
                .stateid(DEFAULT_STATEID)
                .districtid(DEFAULT_DISTRICTID)
                .blockname(DEFAULT_BLOCKNAME);
        return block;
    }

    @Before
    public void initTest() {
        blockRepository.deleteAll();
        block = createEntity();
    }

    @Test
    public void createBlock() throws Exception {
        int databaseSizeBeforeCreate = blockRepository.findAll().size();

        // Create the Block
        BlockDTO blockDTO = blockMapper.blockToBlockDTO(block);

        restBlockMockMvc.perform(post("/api/blocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
            .andExpect(status().isCreated());

        // Validate the Block in the database
        List<Block> blockList = blockRepository.findAll();
        assertThat(blockList).hasSize(databaseSizeBeforeCreate + 1);
        Block testBlock = blockList.get(blockList.size() - 1);
        assertThat(testBlock.getStateid()).isEqualTo(DEFAULT_STATEID);
        assertThat(testBlock.getDistrictid()).isEqualTo(DEFAULT_DISTRICTID);
        assertThat(testBlock.getBlockname()).isEqualTo(DEFAULT_BLOCKNAME);
    }

    @Test
    public void createBlockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blockRepository.findAll().size();

        // Create the Block with an existing ID
        Block existingBlock = new Block();
        existingBlock.setId(UUID.randomUUID());
        BlockDTO existingBlockDTO = blockMapper.blockToBlockDTO(existingBlock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlockMockMvc.perform(post("/api/blocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBlockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Block> blockList = blockRepository.findAll();
        assertThat(blockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllBlocks() throws Exception {
        // Initialize the database
        blockRepository.save(block);

        // Get all the blockList
        restBlockMockMvc.perform(get("/api/blocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(block.getId().toString())))
            .andExpect(jsonPath("$.[*].stateid").value(hasItem(DEFAULT_STATEID.toString())))
            .andExpect(jsonPath("$.[*].districtid").value(hasItem(DEFAULT_DISTRICTID.toString())))
            .andExpect(jsonPath("$.[*].blockname").value(hasItem(DEFAULT_BLOCKNAME.toString())));
    }

    @Test
    public void getBlock() throws Exception {
        // Initialize the database
        blockRepository.save(block);

        // Get the block
        restBlockMockMvc.perform(get("/api/blocks/{id}", block.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(block.getId().toString()))
            .andExpect(jsonPath("$.stateid").value(DEFAULT_STATEID.toString()))
            .andExpect(jsonPath("$.districtid").value(DEFAULT_DISTRICTID.toString()))
            .andExpect(jsonPath("$.blockname").value(DEFAULT_BLOCKNAME.toString()));
    }

    @Test
    public void getNonExistingBlock() throws Exception {
        // Get the block
        restBlockMockMvc.perform(get("/api/blocks/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlock() throws Exception {
        // Initialize the database
        blockRepository.save(block);
        int databaseSizeBeforeUpdate = blockRepository.findAll().size();

        // Update the block
        Block updatedBlock = blockRepository.findOne(block.getId());
        updatedBlock
                .stateid(UPDATED_STATEID)
                .districtid(UPDATED_DISTRICTID)
                .blockname(UPDATED_BLOCKNAME);
        BlockDTO blockDTO = blockMapper.blockToBlockDTO(updatedBlock);

        restBlockMockMvc.perform(put("/api/blocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
            .andExpect(status().isOk());

        // Validate the Block in the database
        List<Block> blockList = blockRepository.findAll();
        assertThat(blockList).hasSize(databaseSizeBeforeUpdate);
        Block testBlock = blockList.get(blockList.size() - 1);
        assertThat(testBlock.getStateid()).isEqualTo(UPDATED_STATEID);
        assertThat(testBlock.getDistrictid()).isEqualTo(UPDATED_DISTRICTID);
        assertThat(testBlock.getBlockname()).isEqualTo(UPDATED_BLOCKNAME);
    }

    @Test
    public void updateNonExistingBlock() throws Exception {
        int databaseSizeBeforeUpdate = blockRepository.findAll().size();

        // Create the Block
        BlockDTO blockDTO = blockMapper.blockToBlockDTO(block);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBlockMockMvc.perform(put("/api/blocks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
            .andExpect(status().isCreated());

        // Validate the Block in the database
        List<Block> blockList = blockRepository.findAll();
        assertThat(blockList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBlock() throws Exception {
        // Initialize the database
        blockRepository.save(block);
        int databaseSizeBeforeDelete = blockRepository.findAll().size();

        // Get the block
        restBlockMockMvc.perform(delete("/api/blocks/{id}", block.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Block> blockList = blockRepository.findAll();
        assertThat(blockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Block.class);
    }
}
