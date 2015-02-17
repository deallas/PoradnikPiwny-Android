package pl.poradnikpiwny.entity;

import com.google.gson.annotations.SerializedName;

public class BeerManufacturer {
	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("website")
	private String website;
	
	@SerializedName("address")
	public String address;
	
	private Country country;
	
	private Region region;
	
	private City city;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getWebsite(){
		return this.website;
	}
	
	public void setWebsite(String website){
		this.website = website;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public Country getCountry(){
		return this.country; 
	}
	
	public void setCountry(Country country){
		this.country = country;
	}
	
	public Region getRegion(){
		return this.region;
	}
	
	public void setRegion(Region region){
		this.region = region;
	}
	
	public City getCity(){
		return this.city;
	}
	
	public void setCity(City city){
		this.city = city;
	}
		
}
