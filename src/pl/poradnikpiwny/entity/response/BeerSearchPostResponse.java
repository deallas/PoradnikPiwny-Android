package pl.poradnikpiwny.entity.response;

import com.google.gson.annotations.SerializedName;

public class BeerSearchPostResponse {
	@SerializedName("result_id")
	private String resultId;
	
	public String getResultId(){
		return this.resultId;
	}
	
	public void setResultId(String resultId){
		this.resultId = resultId;
	}
	
}
