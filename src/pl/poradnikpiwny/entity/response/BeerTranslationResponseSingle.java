package pl.poradnikpiwny.entity.response;

import pl.poradnikpiwny.entity.BeerTranslation;

public class BeerTranslationResponseSingle {
	private BeerTranslation translation;
	
	public BeerTranslation getTranslation(){
		return this.translation;
	}
	
	public void setTranslation(BeerTranslation translation){
		this.translation = translation;
	}
}
