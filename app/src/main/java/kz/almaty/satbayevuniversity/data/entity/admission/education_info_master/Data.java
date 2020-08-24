package kz.almaty.satbayevuniversity.data.entity.admission.education_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Document;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.University;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.DocumentScan;

public class Data {
    @SerializedName("university")
    @Expose
    private University university;
    @SerializedName("speciality")
    @Expose
    private Speciality speciality;
    @SerializedName("studyLanguageId")
    @Expose
    private Integer studyLanguageId;
    @SerializedName("finishedYear")
    @Expose
    private Integer finishedYear;
    @SerializedName("averageScoreOfDiploma")
    @Expose
    private Double averageScoreOfDiploma;
    @SerializedName("diplomaScan")
    @Expose
    private List<DocumentScan> diplomaScan = null;
    @SerializedName("fileContainerId")
    @Expose
    private String fileContainerId;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Integer getStudyLanguageId() {
        return studyLanguageId;
    }

    public void setStudyLanguageId(Integer studyLanguageId) {
        this.studyLanguageId = studyLanguageId;
    }

    public Integer getFinishedYear() {
        return finishedYear;
    }

    public void setFinishedYear(Integer finishedYear) {
        this.finishedYear = finishedYear;
    }

    public Double getAverageScoreOfDiploma() {
        return averageScoreOfDiploma;
    }

    public void setAverageScoreOfDiploma(Double averageScoreOfDiploma) {
        this.averageScoreOfDiploma = averageScoreOfDiploma;
    }

    public List<DocumentScan> getDiplomaScan() {
        return diplomaScan;
    }

    public void setDiplomaScan(List<DocumentScan> diplomaScan) {
        this.diplomaScan = diplomaScan;
    }

    public String getFileContainerId() {
        return fileContainerId;
    }

    public void setFileContainerId(String fileContainerId) {
        this.fileContainerId = fileContainerId;
    }

}
