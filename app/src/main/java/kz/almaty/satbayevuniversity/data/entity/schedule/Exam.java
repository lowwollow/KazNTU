package kz.almaty.satbayevuniversity.data.entity.schedule;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kz.almaty.satbayevuniversity.data.entity.notification.Notification;

@Entity
public class Exam implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private int primaryId;

	@SerializedName("endTimeId")
	private int endTimeId;

	@SerializedName("examDate")
	private String examDate;

	@SerializedName("courseCode")
	private String courseCode;

	@SerializedName("start")
	private String start;

	@SerializedName("examNumber")
	private int examNumber;

	@SerializedName("roomId")
	private int roomId;

	@SerializedName("courseTitle")
	private String courseTitle;

	@SerializedName("studentsCount")
	private int studentsCount;

	@SerializedName("cellCount")
	private int cellCount;

	@SerializedName("startTimeId")
	private int startTimeId;

	@SerializedName("examId")
	private int examId;

	@SerializedName("examinatorName")
	private String examinatorName;

	@SerializedName("end")
	private String end;

	@SerializedName("proctorNames")
	private String proctorNames;

	@SerializedName("roomTitle")
	private String roomTitle;

	public void setEndTimeId(int endTimeId){
		this.endTimeId = endTimeId;
	}

	public int getEndTimeId(){
		return endTimeId;
	}

	public void setExamDate(String examDate){
		this.examDate = examDate;
	}

	public String getExamDate(){
		return examDate;
	}

	public void setCourseCode(String courseCode){
		this.courseCode = courseCode;
	}

	public String getCourseCode(){
		return courseCode;
	}

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setExamNumber(int examNumber){
		this.examNumber = examNumber;
	}

	public int getExamNumber(){
		return examNumber;
	}

	public void setRoomId(int roomId){
		this.roomId = roomId;
	}

	public int getRoomId(){
		return roomId;
	}

	public void setCourseTitle(String courseTitle){
		this.courseTitle = courseTitle;
	}

	public String getCourseTitle(){
		return courseTitle;
	}

	public void setStudentsCount(int studentsCount){
		this.studentsCount = studentsCount;
	}

	public int getStudentsCount(){
		return studentsCount;
	}

	public void setCellCount(int cellCount){
		this.cellCount = cellCount;
	}

	public int getCellCount(){
		return cellCount;
	}

	public void setStartTimeId(int startTimeId){
		this.startTimeId = startTimeId;
	}

	public int getStartTimeId(){
		return startTimeId;
	}

	public void setExamId(int examId){
		this.examId = examId;
	}

	public int getExamId(){
		return examId;
	}

	public void setExaminatorName(String examinatorName){
		this.examinatorName = examinatorName;
	}

	public String getExaminatorName(){
		return examinatorName;
	}

	public void setEnd(String end){
		this.end = end;
	}

	public String getEnd(){
		return end;
	}

	public void setProctorNames(String proctorNames){
		this.proctorNames = proctorNames;
	}

	public String getProctorNames(){
		return proctorNames;
	}

	public void setRoomTitle(String roomTitle){
		this.roomTitle = roomTitle;
	}

	public String getRoomTitle(){
		return roomTitle;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	@Override
 	public String toString(){
		return 
			"Exam{" + 
			"endTimeId = '" + endTimeId + '\'' + 
			",examDate = '" + examDate + '\'' + 
			",courseCode = '" + courseCode + '\'' + 
			",start = '" + start + '\'' + 
			",examNumber = '" + examNumber + '\'' + 
			",roomId = '" + roomId + '\'' + 
			",courseTitle = '" + courseTitle + '\'' + 
			",studentsCount = '" + studentsCount + '\'' + 
			",cellCount = '" + cellCount + '\'' + 
			",startTimeId = '" + startTimeId + '\'' + 
			",examId = '" + examId + '\'' + 
			",examinatorName = '" + examinatorName + '\'' + 
			",end = '" + end + '\'' + 
			",proctorNames = '" + proctorNames + '\'' + 
			",roomTitle = '" + roomTitle + '\'' + 
			"}";
		}

	public String getDateFormatted(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.forLanguageTag("ru"));
		Date myDate = null;
		try {
			myDate = formatter.parse(examDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("dd MMM", Locale.forLanguageTag("ru"));
		timeFormat.format(myDate);
		return timeFormat.format(myDate);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Exam) {
			Exam exam = (Exam) obj;
				if(this.getCourseCode()!=null && exam.getCourseCode()!=null) {
					if (!this.getCourseCode().equals(exam.getCourseCode()))
						return false;
				}else if(this.getCourseCode()!=null || exam.getCourseCode()!=null) {
					return false;
				}

				if(this.getCourseTitle()!=null && exam.getCourseTitle()!=null) {
					if (!this.getCourseTitle().equals(exam.getCourseTitle()))
						return false;
				}else if(this.getCourseTitle()!=null || exam.getCourseTitle()!=null) {
					return false;
				}

				if(this.getStart()!=null && exam.getStart()!=null) {
					if (!this.getStart().equals(exam.getStart()))
						return false;
				}else if(this.getStart()!=null || exam.getStart()!=null) {
					return false;
				}

				if(this.getEnd()!=null && exam.getEnd()!=null) {
					if (!this.getEnd().equals(exam.getEnd()))
						return false;
				}else if(this.getEnd()!=null || exam.getEnd()!=null) {
					return false;
				}

				if(this.getExaminatorName()!=null && exam.getExaminatorName()!=null) {
					if (!this.getExaminatorName().equals(exam.getExaminatorName()))
						return false;
				}else if(this.getExaminatorName()!=null || exam.getExaminatorName()!=null) {
					return false;
				}

				if(this.getExamDate()!=null && exam.getExamDate()!=null) {
					if (!this.getExamDate().equals(exam.getExamDate()))
						return false;
				}else if(this.getExamDate()!=null || exam.getExamDate()!=null) {
					return false;
				}

				if(this.getProctorNames()!=null && exam.getProctorNames()!=null) {
					if (!this.getProctorNames().equals(exam.getProctorNames()))
						return false;
				}else if(this.getProctorNames()!=null || exam.getProctorNames()!=null) {
					return false;
				}

				if(this.getRoomTitle()!=null && exam.getRoomTitle()!=null) {
					if (!this.getRoomTitle().equals(exam.getRoomTitle()))
						return false;
				}else if(this.getRoomTitle()!=null || exam.getRoomTitle()!=null) {
					return false;
				}

				if(this.getExamId()  !=exam.getExamId())
					return false;
				if(this.getEndTimeId() != exam.getEndTimeId())
					return false;
				if(this.getCellCount() != exam.getCellCount())
					return false;
				if(this.getExamNumber() != exam.getExamNumber())
					return false;
				if(this.getRoomId() != exam.getRoomId())
					return false;
				if(this.getStartTimeId() != exam.getStartTimeId())
					return false;
				if(this.getStudentsCount() != exam.getStudentsCount())
					return false;
				return true;
		}
		return false;
	}
}