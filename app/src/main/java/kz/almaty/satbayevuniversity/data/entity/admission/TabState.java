package kz.almaty.satbayevuniversity.data.entity.admission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TabState {
    @SerializedName("completedTab")
    @Expose
    private Integer completedTab;

    public Integer getCompletedTab() {
        return completedTab;
    }

    public void setCompletedTab(Integer completedTab) {
        this.completedTab = completedTab;
    }
}
