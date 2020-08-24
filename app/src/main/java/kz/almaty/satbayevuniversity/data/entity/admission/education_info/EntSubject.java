package kz.almaty.satbayevuniversity.data.entity.admission.education_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntSubject {
    @SerializedName("isProfileSubject")
    @Expose
    private Boolean isProfileSubject;
    @SerializedName("subjectId")
    @Expose
    private Integer subjectId;
    @SerializedName("score")
    @Expose
    private Object score;

    public Boolean getIsProfileSubject() {
        return isProfileSubject;
    }

    public void setIsProfileSubject(Boolean isProfileSubject) {
        this.isProfileSubject = isProfileSubject;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }
}
