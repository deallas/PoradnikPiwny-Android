package pl.poradnikpiwny.entity.response;

import pl.poradnikpiwny.entity.Beer;

public class BeerResponseSingle {
	
	private Beer beer;
	
	public Beer getBeer(){
		return this.beer;
	}
	
	public void setBeer(Beer beer){
		this.beer = beer;
	}
}
