package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDiscipline;


@Entity
public class Semesters1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;

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
