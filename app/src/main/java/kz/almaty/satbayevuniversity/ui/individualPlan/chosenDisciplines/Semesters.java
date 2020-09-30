package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Semesters implements Serializable {
    @SerializedName("title")
    String title;

    @SerializedName("semesterType")
    String semesterType;

    @SerializedName("disciplines")
    List<ChosenDiscipline> chosenDisciplineList;

    public List<ChosenDiscipline> getChosenDisciplineList() {
        return chosenDisciplineList;
    }

    public void setChosenDisciplineList(List<ChosenDiscipline> chosenDisciplineList) {
        this.chosenDisciplineList = chosenDisciplineList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(String semesterType) {
        this.semesterType = semesterType;
    }
}
