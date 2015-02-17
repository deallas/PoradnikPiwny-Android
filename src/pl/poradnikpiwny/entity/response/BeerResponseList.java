package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.Beer;
import pl.poradnikpiwny.util.PaginatorResponse;

public class BeerResponseList extends PaginatorResponse {
	
	private List<Beer> beers;
	
	public List<Beer> getBeers(){
		return this.beers;
	}
	
	public void setBeers(List<Beer> beers){
		this.beers = beers;
	}
}
