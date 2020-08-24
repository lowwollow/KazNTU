package kz.almaty.satbayevuniversity.data.entity.academic;


import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatesItem implements Serializable {
	@SerializedName("sectionID")
	private int sectionID;

	@SerializedName("classTypeID")
	private int classTypeID;

	@SerializedName("attendanceDate")
	private String attendanceDate;

	@SerializedName("timeTitle")
	private String timeTitle;

	@SerializedName("grade")
	private Double grade;

	@SerializedName("attended")
	private boolean attended;

	@SerializedName("comment")
	private String comment;

	@SerializedName("showComment")
	private boolean showComment;

	@SerializedName("isEditable")
	private boolean isEditable;

	@SerializedName("isAttendanceEditable")
	private boolean isAttendanceEditable;

	@SerializedName("isScoresEditable")
	private boolean isScoresEditable;

	@SerializedName("isStatmentClosed")
	private boolean isStatmentClosed;

	@SerializedName("isEnabled")
	private boolean isEnabled;

	@SerializedName("weekDay")
	private String weekDay;

	@SerializedName("isOpened")
	private boolean isOpened;

	@SerializedName("isOpenedByDate")
	private boolean isOpenedByDate;

	@SerializedName("isOldDay")
	private boolean isOldDay;

	protected DatesItem(Parcel in) {
		sectionID = in.readInt();
		classTypeID = in.readInt();
		attendanceDate = in.readString();
		timeTitle = in.readString();
		grade = in.readDouble();
		attended = in.readByte() != 0;
		comment = in.readString();
		showComment = in.readByte() != 0;
		isEditable = in.readByte() != 0;
		isAttendanceEditable = in.readByte() != 0;
		isScoresEditable = in.readByte() != 0;
		isStatmentClosed = in.readByte() != 0;
		isEnabled = in.readByte() != 0;
		weekDay = in.readString();
		isOpened = in.readByte() != 0;
		isOpenedByDate = in.readByte() != 0;
		isOldDay = in.readByte() != 0;
	}


	public void setSectionID(int sectionID){
		this.sectionID = sectionID;
	}

	public int getSectionID(){
		return sectionID;
	}

	public void setClassTypeID(int classTypeID){
		this.classTypeID = classTypeID;
	}

	public int getClassTypeID(){
		return classTypeID;
	}

	public void setAttendanceDate(String attendanceDate){
		this.attendanceDate = attendanceDate;
	}

	public String getAttendanceDate(){
		return attendanceDate;
	}

	public void setTimeTitle(String timeTitle){
		this.timeTitle = timeTitle;
	}

	public String getTimeTitle(){
		return timeTitle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAttended(boolean attended){
		this.attended = attended;
	}

	public boolean isAttended(){
		return attended;
	}

	public void setShowComment(boolean showComment){
		this.showComment = showComment;
	}

	public boolean isShowComment(){
		return showComment;
	}

	public void setIsEditable(boolean isEditable){
		this.isEditable = isEditable;
	}

	public boolean isIsEditable(){
		return isEditable;
	}

	public void setIsAttendanceEditable(boolean isAttendanceEditable){
		this.isAttendanceEditable = isAttendanceEditable;
	}

	public boolean isIsAttendanceEditable(){
		return isAttendanceEditable;
	}

	public void setIsScoresEditable(boolean isScoresEditable){
		this.isScoresEditable = isScoresEditable;
	}

	public boolean isIsScoresEditable(){
		return isScoresEditable;
	}

	public void setIsStatmentClosed(boolean isStatmentClosed){
		this.isStatmentClosed = isStatmentClosed;
	}

	public boolean isIsStatmentClosed(){
		return isStatmentClosed;
	}

	public void setIsEnabled(boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public boolean isIsEnabled(){
		return isEnabled;
	}

	public void setWeekDay(String weekDay){
		this.weekDay = weekDay;
	}

	public String getWeekDay(){
		return weekDay;
	}

	public void setIsOpened(boolean isOpened){
		this.isOpened = isOpened;
	}

	public boolean isIsOpened(){
		return isOpened;
	}

	public void setIsOpenedByDate(boolean isOpenedByDate){
		this.isOpenedByDate = isOpenedByDate;
	}

	public boolean isIsOpenedByDate(){
		return isOpenedByDate;
	}

	public void setIsOldDay(boolean isOldDay){
		this.isOldDay = isOldDay;
	}

	public boolean isIsOldDay(){
		return isOldDay;
	}

	@Override
 	public String toString(){
		return 
			"DatesItem{" + 
			"sectionID = '" + sectionID + '\'' + 
			",classTypeID = '" + classTypeID + '\'' + 
			",attendanceDate = '" + attendanceDate + '\'' + 
			",timeTitle = '" + timeTitle + '\'' + 
			",grade = '" + grade + '\'' + 
			",attended = '" + attended + '\'' + 
			",comment = '" + comment + '\'' + 
			",showComment = '" + showComment + '\'' + 
			",isEditable = '" + isEditable + '\'' + 
			",isAttendanceEditable = '" + isAttendanceEditable + '\'' + 
			",isScoresEditable = '" + isScoresEditable + '\'' + 
			",isStatmentClosed = '" + isStatmentClosed + '\'' + 
			",isEnabled = '" + isEnabled + '\'' + 
			",weekDay = '" + weekDay + '\'' + 
			",isOpened = '" + isOpened + '\'' + 
			",isOpenedByDate = '" + isOpenedByDate + '\'' + 
			",isOldDay = '" + isOldDay + '\'' + 
			"}";
		}
	//конвертация attendanceTime
	public String getDateFormatted(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date myDate = null;
		try {
			myDate = formatter.parse(attendanceDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yy");
		timeFormat.format(myDate);
		return timeFormat.format(myDate);
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Double getGrade() {
		return grade;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DatesItem) {
			DatesItem datesItem = (DatesItem) obj;

			if(this.getAttendanceDate()!=null && datesItem.getAttendanceDate() !=null) {
				if (!this.getAttendanceDate().equals(datesItem.getAttendanceDate())){
					return false;
				}

			}else if(this.getAttendanceDate() !=null || datesItem.getAttendanceDate()!=null){
				return false;
			}

			if(this.getComment()!=null && datesItem.getComment() !=null ) {
				if (!this.getComment().equals(datesItem.getComment())){
					return false;
				}

			}else if(this.getComment() !=null || datesItem.getComment()!=null){
				return false;
			}

			if(this.getTimeTitle()!=null && datesItem.getTimeTitle() !=null ) {
				if (!this.getTimeTitle().equals(datesItem.getTimeTitle())){
					return false;
				}

			}else if(this.getTimeTitle() !=null || datesItem.getTimeTitle()!=null){
				return false;
			}

			if(this.getWeekDay()!=null && datesItem.getWeekDay() !=null) {
				if (!this.getWeekDay().equals(datesItem.getWeekDay())){
					return false;
				}
			}else if(this.getWeekDay() !=null || datesItem.getWeekDay()!=null){
				return false;
			}

			if(this.getGrade()!=null && datesItem.getGrade() !=null) {
				if (!this.getGrade().equals(datesItem.getGrade())){
					return false;
				}
			}else if(this.getGrade() !=null || datesItem.getGrade()!=null){
				return false;
			}

			if(this.getClassTypeID()  !=datesItem.getClassTypeID())
				return false;
			if(this.getSectionID() != datesItem.getSectionID())
				return false;
			if(this.isAttended()  !=datesItem.isAttended())
				return false;
			if(this.isIsAttendanceEditable() != datesItem.isIsAttendanceEditable())
				return false;
			if(this.isIsEditable()  !=datesItem.isIsEditable())
				return false;
			if(this.isIsEnabled() != datesItem.isIsEnabled())
				return false;
			if(this.isIsOldDay()  !=datesItem.isIsOldDay())
				return false;
			if(this.isIsOpened() != datesItem.isIsOpened())
				return false;
			if(this.isShowComment()  !=datesItem.isShowComment())
				return false;
			if(this.isIsScoresEditable() != datesItem.isIsScoresEditable())
				return false;
			if(this.isIsStatmentClosed()  !=datesItem.isIsStatmentClosed())
				return false;
			if(this.isIsOpenedByDate() != datesItem.isIsOpenedByDate())
				return false;

			return true;
		}
		return false;
	}
}