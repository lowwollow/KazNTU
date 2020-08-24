package kz.almaty.satbayevuniversity.data.entity.admission.status_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasterStatusInfo {
    @SerializedName("entrantStatusId")
    @Expose
    private Integer entrantStatusId;
    @SerializedName("testEngResult")
    @Expose
    private Double testEngResult;
    @SerializedName("profileResult")
    @Expose
    private Double profileResult;
    @SerializedName("appealationResult")
    @Expose
    private Double appealationResult;
    @SerializedName("isAppealation")
    @Expose
    private Boolean isAppealation;
    @SerializedName("hasScheduled")
    @Expose
    private Boolean hasScheduled;
    @SerializedName("profileExamEndTime")
    @Expose
    private Object profileExamEndTime;
    @SerializedName("totalResult")
    @Expose
    private Double totalResult;
    @SerializedName("hasAccessToListApplicants")
    @Expose
    private Boolean hasAccessToListApplicants;
    @SerializedName("isCorporate")
    @Expose
    private Boolean isCorporate;
    @SerializedName("byDPOEmployee")
    @Expose
    private CheckedBy byDPOEmployee;
    @SerializedName("entrantStatusTitle")
    @Expose
    private String entrantStatusTitle;
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

    public Integer getEntrantStatusId() {
        return entrantStatusId;
    }

    public void setEntrantStatusId(Integer entrantStatusId) {
        this.entrantStatusId = entrantStatusId;
    }

    public Double getTestEngResult() {
        return testEngResult;
    }

    public void setTestEngResult(Double testEngResult) {
        this.testEngResult = testEngResult;
    }

    public Double getProfileResult() {
        return profileResult;
    }

    public void setProfileResult(Double profileResult) {
        this.profileResult = profileResult;
    }

    public Double getAppealationResult() {
        return appealationResult;
    }

    public void setAppealationResult(Double appealationResult) {
        this.appealationResult = appealationResult;
    }

    public Boolean getIsAppealation() {
        return isAppealation;
    }

    public void setIsAppealation(Boolean isAppealation) {
        this.isAppealation = isAppealation;
    }

    public Boolean getHasScheduled() {
        return hasScheduled;
    }

    public void setHasScheduled(Boolean hasScheduled) {
        this.hasScheduled = hasScheduled;
    }

    public Object getProfileExamEndTime() {
        return profileExamEndTime;
    }

    public void setProfileExamEndTime(Object profileExamEndTime) {
        this.profileExamEndTime = profileExamEndTime;
    }

    public Double getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Double totalResult) {
        this.totalResult = totalResult;
    }

    public Boolean getHasAccessToListApplicants() {
        return hasAccessToListApplicants;
    }

    public void setHasAccessToListApplicants(Boolean hasAccessToListApplicants) {
        this.hasAccessToListApplicants = hasAccessToListApplicants;
    }

    public Boolean getIsCorporate() {
        return isCorporate;
    }

    public void setIsCorporate(Boolean isCorporate) {
        this.isCorporate = isCorporate;
    }

    public CheckedBy getByDPOEmployee() {
        return byDPOEmployee;
    }

    public void setByDPOEmployee(CheckedBy byDPOEmployee) {
        this.byDPOEmployee = byDPOEmployee;
    }

    public String getEntrantStatusTitle() {
        return entrantStatusTitle;
    }

    public void setEntrantStatusTitle(String entrantStatusTitle) {
        this.entrantStatusTitle = entrantStatusTitle;
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
