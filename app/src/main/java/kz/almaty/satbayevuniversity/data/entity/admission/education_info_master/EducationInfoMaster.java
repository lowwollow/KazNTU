package kz.almaty.satbayevuniversity.data.entity.admission.education_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;

public class EducationInfoMaster {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("datas")
    @Expose
    private List<Data> datas = null;
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

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
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
