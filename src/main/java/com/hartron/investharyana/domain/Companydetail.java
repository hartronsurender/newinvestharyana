package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Companydetail.
 */

@Table(name = "companydetail")
public class Companydetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID investorid;

    private String promoter_md_director;

    private String designation;

    private String businessentity;

    private UUID businesstype;

    private Integer number_of_director_mds_ceo;

    private ByteBuffer list_director_md_ceo;

    @Column(name = "list_director_md_ceo_content_type")
    private String list_director_md_ceoContentType;

    private String pannumber;

    private ByteBuffer pancard;

    @Column(name = "pancard_content_type")
    private String pancardContentType;

    private Double aadharnumber;

    private ByteBuffer aadharcard;

    @Column(name = "aadharcard_content_type")
    private String aadharcardContentType;

    private Boolean nri;

    private String tin_vat_no;

    private ByteBuffer tin_vat_document;

    @Column(name = "tin_vat_document_content_type")
    private String tin_vat_documentContentType;

    private String cstno;

    private ByteBuffer cstdocument;

    @Column(name = "cstdocument_content_type")
    private String cstdocumentContentType;

    private ByteBuffer moa_partnershipdeed_document;

    @Column(name = "moa_partnershipdeed_document_content_type")
    private String moa_partnershipdeed_documentContentType;

    private ByteBuffer registration_document;

    @Column(name = "registration_document_content_type")
    private String registration_documentContentType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInvestorid() {
        return investorid;
    }

    public Companydetail investorid(UUID investorid) {
        this.investorid = investorid;
        return this;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }

    public String getPromoter_md_director() {
        return promoter_md_director;
    }

    public Companydetail promoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
        return this;
    }

    public void setPromoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
    }

    public String getDesignation() {
        return designation;
    }

    public Companydetail designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBusinessentity() {
        return businessentity;
    }

    public Companydetail businessentity(String businessentity) {
        this.businessentity = businessentity;
        return this;
    }

    public void setBusinessentity(String businessentity) {
        this.businessentity = businessentity;
    }

    public UUID getBusinesstype() {
        return businesstype;
    }

    public Companydetail businesstype(UUID businesstype) {
        this.businesstype = businesstype;
        return this;
    }

    public void setBusinesstype(UUID businesstype) {
        this.businesstype = businesstype;
    }

    public Integer getNumber_of_director_mds_ceo() {
        return number_of_director_mds_ceo;
    }

    public Companydetail number_of_director_mds_ceo(Integer number_of_director_mds_ceo) {
        this.number_of_director_mds_ceo = number_of_director_mds_ceo;
        return this;
    }

    public void setNumber_of_director_mds_ceo(Integer number_of_director_mds_ceo) {
        this.number_of_director_mds_ceo = number_of_director_mds_ceo;
    }

    public ByteBuffer getList_director_md_ceo() {
        return list_director_md_ceo;
    }

    public Companydetail list_director_md_ceo(ByteBuffer list_director_md_ceo) {
        this.list_director_md_ceo = list_director_md_ceo;
        return this;
    }

    public void setList_director_md_ceo(ByteBuffer list_director_md_ceo) {
        this.list_director_md_ceo = list_director_md_ceo;
    }

    public String getList_director_md_ceoContentType() {
        return list_director_md_ceoContentType;
    }

    public Companydetail list_director_md_ceoContentType(String list_director_md_ceoContentType) {
        this.list_director_md_ceoContentType = list_director_md_ceoContentType;
        return this;
    }

    public void setList_director_md_ceoContentType(String list_director_md_ceoContentType) {
        this.list_director_md_ceoContentType = list_director_md_ceoContentType;
    }

    public String getPannumber() {
        return pannumber;
    }

    public Companydetail pannumber(String pannumber) {
        this.pannumber = pannumber;
        return this;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
    }

    public ByteBuffer getPancard() {
        return pancard;
    }

    public Companydetail pancard(ByteBuffer pancard) {
        this.pancard = pancard;
        return this;
    }

    public void setPancard(ByteBuffer pancard) {
        this.pancard = pancard;
    }

    public String getPancardContentType() {
        return pancardContentType;
    }

    public Companydetail pancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
        return this;
    }

    public void setPancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
    }

    public Double getAadharnumber() {
        return aadharnumber;
    }

    public Companydetail aadharnumber(Double aadharnumber) {
        this.aadharnumber = aadharnumber;
        return this;
    }

    public void setAadharnumber(Double aadharnumber) {
        this.aadharnumber = aadharnumber;
    }

    public ByteBuffer getAadharcard() {
        return aadharcard;
    }

    public Companydetail aadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
        return this;
    }

    public void setAadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
    }

    public String getAadharcardContentType() {
        return aadharcardContentType;
    }

    public Companydetail aadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
        return this;
    }

    public void setAadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
    }

    public Boolean isNri() {
        return nri;
    }

    public Companydetail nri(Boolean nri) {
        this.nri = nri;
        return this;
    }

    public void setNri(Boolean nri) {
        this.nri = nri;
    }

    public String getTin_vat_no() {
        return tin_vat_no;
    }

    public Companydetail tin_vat_no(String tin_vat_no) {
        this.tin_vat_no = tin_vat_no;
        return this;
    }

    public void setTin_vat_no(String tin_vat_no) {
        this.tin_vat_no = tin_vat_no;
    }

    public ByteBuffer getTin_vat_document() {
        return tin_vat_document;
    }

    public Companydetail tin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
        return this;
    }

    public void setTin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }

    public String getTin_vat_documentContentType() {
        return tin_vat_documentContentType;
    }

    public Companydetail tin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
        return this;
    }

    public void setTin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
    }

    public String getCstno() {
        return cstno;
    }

    public Companydetail cstno(String cstno) {
        this.cstno = cstno;
        return this;
    }

    public void setCstno(String cstno) {
        this.cstno = cstno;
    }

    public ByteBuffer getCstdocument() {
        return cstdocument;
    }

    public Companydetail cstdocument(ByteBuffer cstdocument) {
        this.cstdocument = cstdocument;
        return this;
    }

    public void setCstdocument(ByteBuffer cstdocument) {
        this.cstdocument = cstdocument;
    }

    public String getCstdocumentContentType() {
        return cstdocumentContentType;
    }

    public Companydetail cstdocumentContentType(String cstdocumentContentType) {
        this.cstdocumentContentType = cstdocumentContentType;
        return this;
    }

    public void setCstdocumentContentType(String cstdocumentContentType) {
        this.cstdocumentContentType = cstdocumentContentType;
    }

    public ByteBuffer getMoa_partnershipdeed_document() {
        return moa_partnershipdeed_document;
    }

    public Companydetail moa_partnershipdeed_document(ByteBuffer moa_partnershipdeed_document) {
        this.moa_partnershipdeed_document = moa_partnershipdeed_document;
        return this;
    }

    public void setMoa_partnershipdeed_document(ByteBuffer moa_partnershipdeed_document) {
        this.moa_partnershipdeed_document = moa_partnershipdeed_document;
    }

    public String getMoa_partnershipdeed_documentContentType() {
        return moa_partnershipdeed_documentContentType;
    }

    public Companydetail moa_partnershipdeed_documentContentType(String moa_partnershipdeed_documentContentType) {
        this.moa_partnershipdeed_documentContentType = moa_partnershipdeed_documentContentType;
        return this;
    }

    public void setMoa_partnershipdeed_documentContentType(String moa_partnershipdeed_documentContentType) {
        this.moa_partnershipdeed_documentContentType = moa_partnershipdeed_documentContentType;
    }

    public ByteBuffer getRegistration_document() {
        return registration_document;
    }

    public Companydetail registration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
        return this;
    }

    public void setRegistration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
    }

    public String getRegistration_documentContentType() {
        return registration_documentContentType;
    }

    public Companydetail registration_documentContentType(String registration_documentContentType) {
        this.registration_documentContentType = registration_documentContentType;
        return this;
    }

    public void setRegistration_documentContentType(String registration_documentContentType) {
        this.registration_documentContentType = registration_documentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Companydetail companydetail = (Companydetail) o;
        if (companydetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, companydetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Companydetail{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", promoter_md_director='" + promoter_md_director + "'" +
            ", designation='" + designation + "'" +
            ", businessentity='" + businessentity + "'" +
            ", businesstype='" + businesstype + "'" +
            ", number_of_director_mds_ceo='" + number_of_director_mds_ceo + "'" +
            ", list_director_md_ceo='" + list_director_md_ceo + "'" +
            ", list_director_md_ceoContentType='" + list_director_md_ceoContentType + "'" +
            ", pannumber='" + pannumber + "'" +
            ", pancard='" + pancard + "'" +
            ", pancardContentType='" + pancardContentType + "'" +
            ", aadharnumber='" + aadharnumber + "'" +
            ", aadharcard='" + aadharcard + "'" +
            ", aadharcardContentType='" + aadharcardContentType + "'" +
            ", nri='" + nri + "'" +
            ", tin_vat_no='" + tin_vat_no + "'" +
            ", tin_vat_document='" + tin_vat_document + "'" +
            ", tin_vat_documentContentType='" + tin_vat_documentContentType + "'" +
            ", cstno='" + cstno + "'" +
            ", cstdocument='" + cstdocument + "'" +
            ", cstdocumentContentType='" + cstdocumentContentType + "'" +
            ", moa_partnershipdeed_document='" + moa_partnershipdeed_document + "'" +
            ", moa_partnershipdeed_documentContentType='" + moa_partnershipdeed_documentContentType + "'" +
            ", registration_document='" + registration_document + "'" +
            ", registration_documentContentType='" + registration_documentContentType + "'" +
            '}';
    }
}
