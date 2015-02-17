package pl.poradnikpiwny.entity.response;

import pl.poradnikpiwny.entity.BeerManufacturerTranslation;

public class BeerManufacturerTranslationResponseSingle {
	private BeerManufacturerTranslation translation;
	
	public BeerManufacturerTranslation getTranslation(){
		return this.translation;
	}
	
	public void setTranslation(BeerManufacturerTranslation translation){
		this.translation = translation;
	}
}
