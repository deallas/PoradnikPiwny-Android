package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.City;
import pl.poradnikpiwny.util.PaginatorResponse;

public class CityResponseList extends PaginatorResponse{
	private List<City> cities;
	
	public List<City> getCities(){
		return this.cities;
	}
	
	public void setCities(List<City> cities){
		this.cities = cities;
	}
}
