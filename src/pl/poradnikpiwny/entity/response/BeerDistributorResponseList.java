package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.BeerDistributor;
import pl.poradnikpiwny.util.PaginatorResponse;

public class BeerDistributorResponseList extends PaginatorResponse{
	
	private List<BeerDistributor> distributors;
	
	public List<BeerDistributor> getDistributors(){
		return this.distributors;
	}
	
	public void setDistributors(List<BeerDistributor> distributors){
		this.distributors = distributors;
	}
	
}
