package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDiscipline;

@Entity
public class Semesters1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int primaryKey;

    @SerializedName("id")
    private int id;

    @SerializedName("canDropRequiredCourses")
    private boolean canDropRequiredCourses;

    @SerializedName("title")
    String title;

    @SerializedName("semesterType")
    String semesterType;

    @SerializedName("disciplines")
    @TypeConverters({ChosenDisciplineConverter.class})
    List<ChosenDiscipline1> chosenDisciplineList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCanDropRequiredCourses() {
        return canDropRequiredCourses;
    }

    public void setCanDropRequiredCourses(boolean canDropRequiredCourses) {
        this.canDropRequiredCourses = canDropRequiredCourses;
    }

    public List<ChosenDiscipline1> getChosenDisciplineList() {
        return chosenDisciplineList;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setChosenDisciplineList(List<ChosenDiscipline1> chosenDisciplineList) {
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
