package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.Semesters;

public class ChoosenDisciplineGroup implements Serializable {
    @SerializedName("title")
    String title;

    @SerializedName("disciplineSemesters")
    List<Semesters> chosenDisciplineList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Semesters> getChosenDisciplineList() {
        return chosenDisciplineList;
    }

    public void setChosenDisciplineList(List<Semesters> chosenDisciplineList) {
        this.chosenDisciplineList = chosenDisciplineList;
    }
}
