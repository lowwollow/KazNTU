package kz.almaty.satbayevuniversity.data.entity.grade.transcript;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResponseTranscript implements Serializable {

	@SerializedName("semesters")
	private List<SemestersItem> semesters;

	public void setSemesters(List<SemestersItem> semesters){
		this.semesters = semesters;
	}

	public List<SemestersItem> getSemesters(){
		return semesters;
	}

}