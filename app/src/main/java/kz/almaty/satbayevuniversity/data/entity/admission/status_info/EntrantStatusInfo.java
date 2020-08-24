package kz.almaty.satbayevuniversity.data.entity.admission.status_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.status_info_master.CheckedBy;
public class EntrantStatusInfo {
    @SerializedName("entrantStatusTitle")
    @Expose
    private String entrantStatusTitle;
    @SerializedName("entrantStatusId")
    @Expose
    private Integer entrantStatusId;
    @SerializedName("byAdmission")
    @Expose
    private CheckedBy byAdmission;
    @SerializedName("byDoctor")
    @Expose
    private CheckedBy byDoctor;
    @SerializedName("langTests")
    @Expose
    private List<Object> langTests = null;
    @SerializedName("certificates")
    @Expose
    private List<Object> certificates = null;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;

    public String getEntrantStatusTitle() {
        return entrantStatusTitle;
    }

    public void setEntrantStatusTitle(String entrantStatusTitle) {
        this.entrantStatusTitle = entrantStatusTitle;
    }

    public Integer getEntrantStatusId() {
        return entrantStatusId;
    }

    public void setEntrantStatusId(Integer entrantStatusId) {
        this.entrantStatusId = entrantStatusId;
    }

    public CheckedBy getByAdmission() {
        return byAdmission;
    }

    public void setByAdmission(CheckedBy byAdmission) {
        this.byAdmission = byAdmission;
    }

    public CheckedBy getByDoctor() {
        return byDoctor;
    }

    public void setByDoctor(CheckedBy byDoctor) {
        this.byDoctor = byDoctor;
    }

    public List<Object> getLangTests() {
        return langTests;
    }

    public void setLangTests(List<Object> langTests) {
        this.langTests = langTests;
    }

    public List<Object> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Object> certificates) {
        this.certificates = certificates;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }
}
