package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeferedDiscipline implements Serializable {

    @SerializedName("title")
    String title;

    @SerializedName("disciplineTypeTitle")
    String disciplineTypeTitle;

    @SerializedName("code")
    String code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisciplineTypeTitle() {
        return disciplineTypeTitle;
    }

    public void setDisciplineTypeTitle(String disciplineTypeTitle) {
        this.disciplineTypeTitle = disciplineTypeTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
