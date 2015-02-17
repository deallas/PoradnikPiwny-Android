package pl.poradnikpiwny.entity.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
	@SerializedName("msg")
	private String msg;
	
	public String getMsg(){
		return this.msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
}
