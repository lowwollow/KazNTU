package kz.almaty.satbayevuniversity.data.entity.umkd;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class File implements Serializable {

	@SerializedName("fileName")
	private String fileName;

	@SerializedName("children")
	private List<Course> children;

	@SerializedName("isFileData")
	private boolean isFileData;

	@SerializedName("id")
	private int id;

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return fileName;
	}

	public void setChildren(List<Course> children){
		this.children = children;
	}

	public List<Course> getChildren(){
		return children;
	}

	public void setIsFileData(boolean isFileData){
		this.isFileData = isFileData;
	}

	public boolean isIsFileData(){
		return isFileData;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"File{" +
			"fileName = '" + fileName + '\'' + 
			",children = '" + children + '\'' + 
			",isFileData = '" + isFileData + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}