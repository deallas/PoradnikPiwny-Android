package pl.poradnikpiwny.entity;

import com.google.gson.annotations.SerializedName;

public class BeerImage {
	@SerializedName("id")
	private int id;
	
	@SerializedName("title")
	private String title;
	
	@SerializedName("path")
	private String path;
	
	@SerializedName("dateAdded")
	private String dateAdded;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getDateAdded(){
		return this.dateAdded;
	}
	
	public void setDateAdded(String date){
		this.dateAdded = date;
	}
}
