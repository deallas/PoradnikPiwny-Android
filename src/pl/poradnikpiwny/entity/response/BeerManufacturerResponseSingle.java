package pl.poradnikpiwny.entity.response;

import pl.poradnikpiwny.entity.BeerManufacturer;

public class BeerManufacturerResponseSingle {
	private BeerManufacturer manufacturer;
	
	public BeerManufacturer getManufacturer(){
		return this.manufacturer;
	}
	
	public void setManufacturer(BeerManufacturer manufacturer){
		this.manufacturer = manufacturer;
	}
}
