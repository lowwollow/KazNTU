package kz.almaty.satbayevuniversity.data.entity.grade.transcript;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CoursesItem implements Serializable {

	@SerializedName("code")
	private String code;

	@SerializedName("title")
	private String title;

	@SerializedName("semester")
	private int semester;

	@SerializedName("credits")
	private int credits;

	@SerializedName("ects")
	private int ects;

	@SerializedName("letter")
	private String letter;

	@SerializedName("score")
	private Double score;

	@SerializedName("exam")
	private Double exam;

	@SerializedName("gpaGrade")
	private Double gpaGrade;

	@SerializedName("linkCode")
	private String linkCode;

	@SerializedName("isReplaced")
	private boolean isReplaced;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getExam() {
		return exam;
	}

	public void setExam(Double exam) {
		this.exam = exam;
	}

	public Double getGpaGrade() {
		return gpaGrade;
	}

	public void setGpaGrade(Double gpaGrade) {
		this.gpaGrade = gpaGrade;
	}

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	public boolean isReplaced() {
		return isReplaced;
	}

	public void setReplaced(boolean replaced) {
		isReplaced = replaced;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CoursesItem) {
			CoursesItem coursesItem = (CoursesItem) obj;

			if(this.getTitle()!=null && coursesItem.getTitle()!=null) {
				if (!this.getTitle().equals(coursesItem.getTitle()))
					return false;
			} else if(this.getTitle()!=null || coursesItem.getTitle()!=null) {
				return false;
			}

			if(this.getCode()!=null && coursesItem.getCode()!=null) {
				if (!this.getCode().equals(coursesItem.getCode()))
					return false;
			}else if(this.getCode()!=null || coursesItem.getCode()!=null) {
				return false;
			}

			if(this.getLetter()!=null && coursesItem.getLetter()!=null) {
				if (!this.getLetter().equals(coursesItem.getLetter()))
					return false;
			}else if(this.getLetter()!=null || coursesItem.getLetter()!=null) {
				return false;
			}

			if(this.getLinkCode()!=null && coursesItem.getLinkCode()!=null) {
				if (!this.getLinkCode().equals(coursesItem.getLinkCode()))
					return false;
			}else if(this.getLinkCode()!=null || coursesItem.getLinkCode()!=null) {
				return false;
			}

			if(this.getScore()!=null && coursesItem.getScore()!=null) {
				if (!this.getScore().equals(coursesItem.getScore()))
					return false;
			}else if(this.getScore()!=null || coursesItem.getScore()!=null) {
				return false;
			}

			if(this.getExam()!=null && coursesItem.getExam()!=null) {
				if (!this.getExam().equals(coursesItem.getExam()))
					return false;
			}else if(this.getExam()!=null || coursesItem.getExam()!=null) {
				return false;
			}

			if(this.getGpaGrade()!=null && coursesItem.getGpaGrade()!=null) {
				if (!this.getGpaGrade().equals(coursesItem.getGpaGrade()))
					return false;
			}else if(this.getGpaGrade()!=null || coursesItem.getGpaGrade()!=null) {
				return false;
			}

			if(this.getSemester() != coursesItem.getSemester())
				return false;
			if(this.getCredits() != coursesItem.getCredits())
				return false;
			if(this.getEcts() != coursesItem.getEcts())
				return false;
			if(this.isReplaced() != coursesItem.isReplaced())
				return false;
			return true;
		}
		return false;
	}
}