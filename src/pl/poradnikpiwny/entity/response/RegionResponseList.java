package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.Region;
import pl.poradnikpiwny.util.PaginatorResponse;

public class RegionResponseList extends PaginatorResponse {
	private List<Region> regions;
	
	public List<Region> getRegions(){
		return this.regions;
	}
	
	public void setRegions(List<Region> regions){
		this.regions = regions;
	}
	
}
