package kz.almaty.satbayevuniversity.data.entity.grade.transcript;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


@Entity
public class SemestersItem implements Serializable {
	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("semesterCredits")
	private int semesterCredits;

	@SerializedName("semesterEctsCredits")
	private int semesterEctsCredits;

	@SerializedName("calcCredits")
	private int calcCredits;

	@SerializedName("calcEctsCredits")
	private int calcEctsCredits;

	@SerializedName("totalCredits")
	private int totalCredits;

	@SerializedName("totalEctsCredits")
	private int totalEctsCredits;

	@SerializedName("semesterGpa")
	private Double semesterGpa;

	@SerializedName("yearGpa")
	private Double yearGpa;

	@SerializedName("cumulativeGpa")
	private Double cumulativeGpa;

	@SerializedName("endsOn")
	private String endsOn;

	@TypeConverters(DataConverterTranscript.class)
	@SerializedName("courses")
	private List<CoursesItem> courses;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String  getTitle(){
		return title;
	}

	public void setSemesterCredits(int semesterCredits){
		this.semesterCredits = semesterCredits;
	}

	public int getSemesterCredits(){
		return semesterCredits;
	}

	public void setSemesterEctsCredits(int semesterEctsCredits){
		this.semesterEctsCredits = semesterEctsCredits;
	}

	public int getSemesterEctsCredits(){
		return semesterEctsCredits;
	}

	public void setCalcCredits(int calcCredits){
		this.calcCredits = calcCredits;
	}

	public int getCalcCredits(){
		return calcCredits;
	}

	public void setCalcEctsCredits(int calcEctsCredits){
		this.calcEctsCredits = calcEctsCredits;
	}

	public int getCalcEctsCredits(){
		return calcEctsCredits;
	}

	public void setTotalCredits(int totalCredits){
		this.totalCredits = totalCredits;
	}

	public int getTotalCredits(){
		return totalCredits;
	}

	public void setTotalEctsCredits(int totalEctsCredits){
		this.totalEctsCredits = totalEctsCredits;
	}

	public int getTotalEctsCredits(){
		return totalEctsCredits;
	}

	public Double getSemesterGpa() {
		return semesterGpa;
	}

	public void setSemesterGpa(Double semesterGpa) {
		this.semesterGpa = semesterGpa;
	}

	public Double getYearGpa() {
		return yearGpa;
	}

	public void setYearGpa(Double yearGpa) {
		this.yearGpa = yearGpa;
	}

	public Double getCumulativeGpa() {
		return cumulativeGpa;
	}

	public void setCumulativeGpa(Double cumulativeGpa) {
		this.cumulativeGpa = cumulativeGpa;
	}

	public void setEndsOn(String endsOn){
		this.endsOn = endsOn;
	}

	public String getEndsOn(){
		return endsOn;
	}

	public void setCourses(List<CoursesItem> courses){
		this.courses = courses;
	}

	public List<CoursesItem> getCourses(){
		return courses;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SemestersItem) {
			SemestersItem semestersItem = (SemestersItem) obj;

				if(this.getTitle()!=null && semestersItem.getTitle()!=null) {
					if (!this.getTitle().equals(semestersItem.getTitle())){
						return false;
					}
				}else if(this.getTitle() !=null || semestersItem.getTitle()!=null){
					return false;
				}

				if(this.getEndsOn()!=null && semestersItem.getEndsOn()!=null) {
					if (!this.getEndsOn().equals(semestersItem.getEndsOn())){
						return false;
					}
				}else if(this.getEndsOn()!=null || semestersItem.getEndsOn()!=null) {
						return false;
				}

				if(this.getSemesterGpa()!=null && semestersItem.getSemesterGpa()!=null) {
					if (!this.getSemesterGpa().equals(semestersItem.getSemesterGpa())){
						return false;
					}
				} else if(this.getSemesterGpa()!=null || semestersItem.getSemesterGpa()!=null) {
						return false;
				}

				if(this.getYearGpa()!=null && semestersItem.getYearGpa()!=null) {
					if (!this.getYearGpa().equals(semestersItem.getYearGpa())){
						return false;
					}
				}else if(this.getYearGpa()!=null || semestersItem.getYearGpa()!=null) {
					return false;
				}

				if(this.getCumulativeGpa()!=null && semestersItem.getCumulativeGpa()!=null) {
					if (!this.getCumulativeGpa().equals(semestersItem.getCumulativeGpa())){
						return false;
					}
				}else if(this.getCumulativeGpa()!=null || semestersItem.getCumulativeGpa()!=null) {
					return false;
				}

				if(this.getCalcCredits() != semestersItem.getCalcCredits()){
					return false;
				}
				if(this.getSemesterCredits() != semestersItem.getSemesterCredits()){
					return false;
				}
				if(this.getTotalCredits() != semestersItem.getTotalCredits()){
					return false;
				}
				if(this.getCalcEctsCredits() != semestersItem.getCalcEctsCredits()){
					return false;
				}
				if(this.getSemesterEctsCredits() != semestersItem.getSemesterEctsCredits()){
					return false;
				}
				if(this.getTotalEctsCredits() != semestersItem.getTotalEctsCredits()){
					return false;
				}
				if(this.getCourses() !=null && semestersItem.getCourses()!=null){
					if(!this.getCourses().equals(semestersItem.getCourses())){
						return false;
					}
				}else if(this.getCourses() !=null || semestersItem.getCourses()!=null){
					return false;
				}
				return true;
		}
		return false;
	}
}