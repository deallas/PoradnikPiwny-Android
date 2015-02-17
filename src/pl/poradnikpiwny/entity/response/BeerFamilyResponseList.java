package pl.poradnikpiwny.entity.response;

import java.util.List;

import pl.poradnikpiwny.entity.BeerFamily;
import pl.poradnikpiwny.util.PaginatorResponse;

public class BeerFamilyResponseList extends PaginatorResponse {
	
	private List<BeerFamily> families;
	
	public List<BeerFamily> getFamilies() {
		return this.families;
	}
	
	public void setFamilies(List<BeerFamily> families) {
		this.families = families;
	}
}
