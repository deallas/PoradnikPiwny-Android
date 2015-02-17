package pl.poradnikpiwny.entity;

import com.google.gson.annotations.SerializedName;

public class BeerTranslation {
	@SerializedName("description")
	private String description;
	
	@SerializedName("lang")
	private String lang;
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getLang(){
		return this.lang;
	}
	
	public void setLang(String lang){
		this.lang = lang;
	}
}
