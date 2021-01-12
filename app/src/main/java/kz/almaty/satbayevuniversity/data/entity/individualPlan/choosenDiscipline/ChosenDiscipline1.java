package kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class ChosenDiscipline1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int primaryId;

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("readingChairTitle")
    private String readingChairTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("cycleTitle")
    private String cycleTitle;

    @SerializedName("disciplineTypeTitle")
    private String disciplineTypeTitle;

    @SerializedName("totalCredits")
    private int totalCredits;

    @SerializedName("totalRowCount")
    private int totalRowCount;

    //лк/ лаб/ пр
    @SerializedName("lectureCredits")
    private int lectureCredits;

    @SerializedName("practiceCredits")
    private int practiceCredits;

    @SerializedName("labCredits")
    private int labCredits;


    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
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
