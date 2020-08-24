package kz.almaty.satbayevuniversity.data.entity.schedule;

import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("yearOfAdmission")
    private String yearOfAdmission;

    @SerializedName("levelTitle")
    private String levelTitle;

    @SerializedName("specialityCode")
    private String specialityCode;

    @SerializedName("specialityTitle")
    private String specialityTitle;

    @SerializedName("educationDurationTitle")
    private String educationDurationTitle;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("studentId")
    private Integer studentId;

    public String getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(String yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    public String getSpecialityTitle() {
        return specialityTitle;
    }

    public void setSpecialityTitle(String specialityTitle) {
        this.specialityTitle = specialityTitle;
    }

    public String getEducationDurationTitle() {
        return educationDurationTitle;
    }

    public void setEducationDurationTitle(String educationDurationTitle) {
        this.educationDurationTitle = educationDurationTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
