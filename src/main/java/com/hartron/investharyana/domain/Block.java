package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Block.
 */

@Table(name = "block")
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID stateid;

    private UUID districtid;

    private String blockname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStateid() {
        return stateid;
    }

    public Block stateid(UUID stateid) {
        this.stateid = stateid;
        return this;
    }

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }

    public UUID getDistrictid() {
        return districtid;
    }

    public Block districtid(UUID districtid) {
        this.districtid = districtid;
        return this;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }

    public String getBlockname() {
        return blockname;
    }

    public Block blockname(String blockname) {
        this.blockname = blockname;
        return this;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        if (block.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, block.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Block{" +
            "id=" + id +
            ", stateid='" + stateid + "'" +
            ", districtid='" + districtid + "'" +
            ", blockname='" + blockname + "'" +
            '}';
    }
}
