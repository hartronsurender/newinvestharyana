package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Block entity.
 */
public class BlockDTO implements Serializable {

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

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }
    public UUID getDistrictid() {
        return districtid;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }
    public String getBlockname() {
        return blockname;
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

        BlockDTO blockDTO = (BlockDTO) o;

        if ( ! Objects.equals(id, blockDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BlockDTO{" +
            "id=" + id +
            ", stateid='" + stateid + "'" +
            ", districtid='" + districtid + "'" +
            ", blockname='" + blockname + "'" +
            '}';
    }
}
