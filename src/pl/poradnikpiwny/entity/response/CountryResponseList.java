package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.Country;
import pl.poradnikpiwny.util.PaginatorResponse;

public class CountryResponseList extends PaginatorResponse{
	private List<Country> countries;
	
	public List<Country> getCountries(){
		return this.countries;
	}
	
	public void setCountries(List<Country> countries){
		this.countries = countries;
	}
}
