package kz.almaty.satbayevuniversity.data.entity.grade.attestation;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity
public class Attestation implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private int primaryId;

	@SerializedName("semesterCourseID")
	private int semesterCourseID;

	@TypeConverters(DataConverterAttestation.class)
	@SerializedName("data")
	private AttestationDetail attestationDetail;

	@SerializedName("semesterCourseTitle")
	private String semesterCourseTitle;

	public void setSemesterCourseID(int semesterCourseID){
		this.semesterCourseID = semesterCourseID;
	}

	public int getSemesterCourseID(){
		return semesterCourseID;
	}

	public void setAttestationDetail(AttestationDetail attestationDetail){
		this.attestationDetail = attestationDetail;
	}

	public AttestationDetail getAttestationDetail(){
		return attestationDetail;
	}

	public void setSemesterCourseTitle(String semesterCourseTitle){
		this.semesterCourseTitle = semesterCourseTitle;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	public String getSemesterCourseTitle(){
		return semesterCourseTitle;
	}

	@Override
 	public String toString(){
		return 
			"Attestation{" +
			"semesterCourseID = '" + semesterCourseID + '\'' + 
			",attestationDetail = '" + attestationDetail + '\'' +
			",semesterCourseTitle = '" + semesterCourseTitle + '\'' + 
			"}";
		}

	public String getGrade1(){
		if(attestationDetail.getGrade1() != null){
			return String.valueOf(attestationDetail.getGrade1());
		}
		 else return "0.0";
	}

	public String getGrade2(){
		if(attestationDetail.getGrade2() != null){
			return String.valueOf(attestationDetail.getGrade2());
		}
		else return "0";
	}
	public String getExam(){
		if(attestationDetail.getExamGrade() != null){
			return String.valueOf(attestationDetail.getExamGrade());
		}
		else return "0";
	}
	public String getTotal(){
		if(attestationDetail.getTotalGrade() != null){
			return String.valueOf(attestationDetail.getTotalGrade());
		}
		else return "0.0";
	}

	@Override
	public boolean equals( Object obj) {
		if(obj instanceof Attestation) {
			Attestation attestation = (Attestation) obj;
			if(this.getSemesterCourseTitle()!=null && attestation.getSemesterCourseTitle()!=null){
				if(!this.getSemesterCourseTitle().equals(attestation.getSemesterCourseTitle())){
					return false;
				}
			}else if(this.getSemesterCourseTitle()!=null || attestation.getSemesterCourseTitle()!=null){
				return false;
			}
			if(this.getSemesterCourseID() != attestation.getSemesterCourseID()){
				return false;
			}
			if(this.getAttestationDetail()!=null && attestation.getAttestationDetail()!=null){
				if(!this.getAttestationDetail().equals(attestation.getAttestationDetail())){
					return false;
				}
			}else if(this.getAttestationDetail()!=null || attestation.getAttestationDetail()!=null){
				return false;
			}
			return true;
		}
		return false;
	}
}