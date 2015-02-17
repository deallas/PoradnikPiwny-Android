package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.util.PaginatorResponse;

public class BeerManufacturerResponseList extends PaginatorResponse{
	
	private List<BeerManufacturerResponseList> manufacturers;
	
	public List<BeerManufacturerResponseList> getManufacturers(){
		return this.manufacturers;
	}
	
	public void setManufacturers(List<BeerManufacturerResponseList> manufacturers){
		this.manufacturers = manufacturers;
	}
}
