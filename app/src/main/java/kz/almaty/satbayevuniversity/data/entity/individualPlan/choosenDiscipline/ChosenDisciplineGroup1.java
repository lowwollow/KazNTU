package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.Semesters;


public class ChosenDisciplineGroup1 implements Serializable {

    @SerializedName("title")
    String title;

    @SerializedName("disciplineSemesters")
    List<Semesters1> chosenDisciplineList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Semesters1> getChosenDisciplineList() {
        return chosenDisciplineList;
    }

    public void setChosenDisciplineList(List<Semesters1> chosenDisciplineList) {
        this.chosenDisciplineList = chosenDisciplineList;
    }
}
