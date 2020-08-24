package kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.IeltsCert;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.ToeflCert;

public class AchievementInfo {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("hasScienceWorks")
    @Expose
    private Boolean hasScienceWorks;
    @SerializedName("scienceWorks")
    @Expose
    private List<ScienceWork> scienceWorks = null;
    @SerializedName("ieltsCert")
    @Expose
    private IeltsCert ieltsCert;
    @SerializedName("toeflCert")
    @Expose
    private ToeflCert toeflCert;
    @SerializedName("fileContainerID")
    @Expose
    private Object fileContainerID;
    @SerializedName("additionalInformation")
    @Expose
    private String additionalInformation;
    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("tabState")
    @Expose
    private TabState tabState;
    @SerializedName("lastUpdatedBy")
    @Expose
    private Object lastUpdatedBy;

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public Boolean getHasScienceWorks() {
        return hasScienceWorks;
    }

    public void setHasScienceWorks(Boolean hasScienceWorks) {
        this.hasScienceWorks = hasScienceWorks;
    }

    public List<ScienceWork> getScienceWorks() {
        return scienceWorks;
    }

    public void setScienceWorks(List<ScienceWork> scienceWorks) {
        this.scienceWorks = scienceWorks;
    }

    public IeltsCert getIeltsCert() {
        return ieltsCert;
    }

    public void setIeltsCert(IeltsCert ieltsCert) {
        this.ieltsCert = ieltsCert;
    }

    public ToeflCert getToeflCert() {
        return toeflCert;
    }

    public void setToeflCert(ToeflCert toeflCert) {
        this.toeflCert = toeflCert;
    }

    public Object getFileContainerID() {
        return fileContainerID;
    }

    public void setFileContainerID(Object fileContainerID) {
        this.fileContainerID = fileContainerID;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

    public TabState getTabState() {
        return tabState;
    }

    public void setTabState(TabState tabState) {
        this.tabState = tabState;
    }

    public Object getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Object lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}
