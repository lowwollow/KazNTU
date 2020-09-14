package kz.almaty.satbayevuniversity.data.entity.umkd;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Course implements Serializable {

	@SerializedName("fileName")
	private String fileName;

	@SerializedName("children")
	private List<Course> children;

	@SerializedName("categoryTitle")
	private String categoryTitle;

	@SerializedName("isFileData")
	private boolean isFileData;

	@SerializedName("id")
	private String id;

	@SerializedName("parentID")
	private int parentID;

	@SerializedName("categoryID")
	private int categoryID;

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

	public void setCategoryTitle(String categoryTitle){
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryTitle(){
		return categoryTitle;
	}

	public void setIsFileData(boolean isFileData){
		this.isFileData = isFileData;
	}

	public boolean isIsFileData(){
		return isFileData;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setParentID(int parentID){
		this.parentID = parentID;
	}

	public int getParentID(){
		return parentID;
	}

	public void setCategoryID(int categoryID){
		this.categoryID = categoryID;
	}

	public int getCategoryID(){
		return categoryID;
	}

	@Override
 	public String toString(){
		return 
			"Course{" +
			"fileName = '" + fileName + '\'' + 
			",children = '" + children + '\'' + 
			",categoryTitle = '" + categoryTitle + '\'' + 
			",isFileData = '" + isFileData + '\'' + 
			",id = '" + id + '\'' + 
			",parentID = '" + parentID + '\'' + 
			",categoryID = '" + categoryID + '\'' + 
			"}";
		}
}