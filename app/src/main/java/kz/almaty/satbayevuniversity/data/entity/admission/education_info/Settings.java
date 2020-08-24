package kz.almaty.satbayevuniversity.data.entity.admission.education_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("minScore")
    @Expose
    private Double minScore;
    @SerializedName("maxScore")
    @Expose
    private Double maxScore;
    @SerializedName("minDate")
    @Expose
    private String minDate;

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }
}
