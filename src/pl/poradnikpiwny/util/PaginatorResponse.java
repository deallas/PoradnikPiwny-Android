package pl.poradnikpiwny.util;

import com.google.gson.annotations.SerializedName;

public abstract class PaginatorResponse 
{
	@SerializedName("currentPageNumber")
	private int currentPageNumber;
	
	@SerializedName("itemCountPerPage")
	private int itemCountPerPage;
	
	@SerializedName("totalItemCount")
	private int totalItemCount;
	
	public int getCurrentPageNumber(){
		return this.currentPageNumber;
	}
	
	public void setCurrentPageNumber(int number){
		this.currentPageNumber = number;
	}
	
	public int getItemCountPerPage(){
		return this.itemCountPerPage;
	}
	
	public void setItemCountPerPage(int number){
		this.itemCountPerPage = number;
	}
	
	public int getTotalItemCount(){
		return this.totalItemCount;
	}
	
	public void setTotalItemCount(int number){
		this.totalItemCount = number;
	}
}
