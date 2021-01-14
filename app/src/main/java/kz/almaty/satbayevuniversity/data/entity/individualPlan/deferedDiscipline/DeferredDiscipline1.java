package kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class DeferredDiscipline1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int primaryKey;

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    String code;

    @SerializedName("readingChairTitle")
    String readingChairTitle;

    @SerializedName("title")
    String title;

    @SerializedName("cycleTitle")
    String cycleTitle;

    @SerializedName("disciplineTypeTitle")
    String disciplineTypeTitle;

    @SerializedName("totalCredits")
    int totalCredits;

    @SerializedName("totalRowCount")
    int totalRowCount;

    //лк/ лаб/ пр
    @SerializedName("lectureCredits")
    int lectureCredits;

    @SerializedName("practiceCredits")
    int practiceCredits;

    @SerializedName("labCredits")
    int labCredits;

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public int getLabCredits() {
        return labCredits;
    }

    public void setLabCredits(int labCredits) {
        this.labCredits = labCredits;
    }

    public int getLectureCredits() {
        return lectureCredits;
    }

    public void setLectureCredits(int lectureCredits) {
        this.lectureCredits = lectureCredits;
    }

    public int getPracticeCredits() {
        return practiceCredits;
    }

    public void setPracticeCredits(int practiceCredits) {
        this.practiceCredits = practiceCredits;
    }

    @SerializedName("prerequisites")
    String prerequisites;

    public String getReadingChairTitle() {
        return readingChairTitle;
    }

    public void setReadingChairTitle(String readingChairTitle) {
        this.readingChairTitle = readingChairTitle;
    }

    public String getCycleTitle() {
        return cycleTitle;
    }

    public void setCycleTitle(String cycleTitle) {
        this.cycleTitle = cycleTitle;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisciplineTypeTitle() {
        return disciplineTypeTitle;
    }

    public void setDisciplineTypeTitle(String disciplineTypeTitle) {
        this.disciplineTypeTitle = disciplineTypeTitle;
    }
}
