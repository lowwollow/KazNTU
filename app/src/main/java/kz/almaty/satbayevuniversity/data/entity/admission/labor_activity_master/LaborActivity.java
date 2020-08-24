package kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;

public class LaborActivity {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("fileContainerId")
    @Expose
    private String fileContainerId;
    @SerializedName("employmentHistoryScan")
    @Expose
    private List<DocumentScan> employmentHistoryScan = null;
    @SerializedName("placesOfWork")
    @Expose
    private List<PlaceOfWork> placesOfWork = null;
    @SerializedName("tabState")
    @Expose
    private TabState tabState;
    @SerializedName("lastUpdatedBy")
    @Expose
    private Object lastUpdatedBy;
    @SerializedName("language")
    @Expose
    private Object language;

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public String getFileContainerId() {
        return fileContainerId;
    }

    public void setFileContainerId(String fileContainerId) {
        this.fileContainerId = fileContainerId;
    }

    public List<DocumentScan> getEmploymentHistoryScan() {
        return employmentHistoryScan;
    }

    public void setEmploymentHistoryScan(List<DocumentScan> employmentHistoryScan) {
        this.employmentHistoryScan = employmentHistoryScan;
    }

    public List<PlaceOfWork> getPlacesOfWork() {
        return placesOfWork;
    }

    public void setPlacesOfWork(List<PlaceOfWork> placesOfWork) {
        this.placesOfWork = placesOfWork;
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

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

}
